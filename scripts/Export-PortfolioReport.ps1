param(
    [string]$OutputPath = "docs/test-report-sample.md"
)

$ErrorActionPreference = "Stop"
$repositoryRoot = Split-Path -Parent $PSScriptRoot
$invariantCulture = [System.Globalization.CultureInfo]::InvariantCulture

$modules = @(
    @{
        Name = "API Tests"
        Report = "api-tests/target/surefire-reports/TEST-TestSuite.xml"
    },
    @{
        Name = "UI Tests"
        Report = "ui-tests/target/surefire-reports/TEST-TestSuite.xml"
    }
)

$results = foreach ($module in $modules) {
    $reportPath = Join-Path $repositoryRoot $module.Report
    if (-not (Test-Path -LiteralPath $reportPath)) {
        throw "Test report not found: $reportPath. Run 'mvn test' first."
    }

    [xml]$report = Get-Content -LiteralPath $reportPath -Raw
    $suite = $report.testsuite
    $cases = foreach ($testCase in $suite.testcase) {
        $testName = $testCase.name
        if ($testName -match "^(.*?)\[.*\]\((\d+)\)$") {
            $testName = "$($Matches[1]) (case $($Matches[2]))"
        }

        $status = if ($null -ne $testCase.failure) {
            "Failed"
        } elseif ($null -ne $testCase.error) {
            "Error"
        } elseif ($null -ne $testCase.skipped) {
            "Skipped"
        } else {
            "Passed"
        }

        [pscustomobject]@{
            Class = ($testCase.classname -split '\.')[-1]
            Name = $testName.Replace("|", "\|")
            Status = $status
            Duration = ([double]$testCase.time).ToString("0.000", $invariantCulture)
        }
    }

    [pscustomobject]@{
        Name = $module.Name
        Tests = [int]$suite.tests
        Failures = [int]$suite.failures + [int]$suite.errors
        Skipped = [int]$suite.skipped
        Duration = [double]$suite.time
        Cases = $cases
    }
}

$totalTests = ($results | Measure-Object -Property Tests -Sum).Sum
$totalFailures = ($results | Measure-Object -Property Failures -Sum).Sum
$totalSkipped = ($results | Measure-Object -Property Skipped -Sum).Sum
$totalDuration = ($results | Measure-Object -Property Duration -Sum).Sum
$passedTests = $totalTests - $totalFailures - $totalSkipped

$lines = [System.Collections.Generic.List[string]]::new()
$lines.Add("# Test Execution Report")
$lines.Add("")
$lines.Add("This portfolio sample is generated from Maven Surefire result XML files.")
$lines.Add("It contains only test names, statuses, counts, and durations—no logs, headers, payloads, paths, or secrets.")
$lines.Add("")
$lines.Add("## Summary")
$lines.Add("")
$lines.Add("| Total | Passed | Failed | Skipped | Duration |")
$lines.Add("| ---: | ---: | ---: | ---: | ---: |")
$formattedTotalDuration = ([double]$totalDuration).ToString("0.000", $invariantCulture)
$lines.Add("| $totalTests | $passedTests | $totalFailures | $totalSkipped | $formattedTotalDuration s |")

foreach ($result in $results) {
    $lines.Add("")
    $lines.Add("## $($result.Name)")
    $lines.Add("")
    $lines.Add("| Test class | Test | Status | Duration |")
    $lines.Add("| --- | --- | --- | ---: |")

    foreach ($testCase in $result.Cases) {
        $lines.Add(
            "| $($testCase.Class) | $($testCase.Name) | $($testCase.Status) | $($testCase.Duration) s |"
        )
    }
}

$destination = Join-Path $repositoryRoot $OutputPath
$destinationDirectory = Split-Path -Parent $destination
New-Item -ItemType Directory -Path $destinationDirectory -Force | Out-Null
[System.IO.File]::WriteAllLines($destination, $lines, [System.Text.UTF8Encoding]::new($false))

Write-Host "Portfolio report created: $destination"
