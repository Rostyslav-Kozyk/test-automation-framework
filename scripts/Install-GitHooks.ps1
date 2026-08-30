param(
    [string]$JavaHome
)

$ErrorActionPreference = "Stop"
$repositoryRoot = Split-Path -Parent $PSScriptRoot

function Get-CompatibleJavaHome {
    param(
        [string[]]$Candidates
    )

    foreach ($candidate in $Candidates | Where-Object { -not [string]::IsNullOrWhiteSpace($_) }) {
        $javaExecutable = Join-Path $candidate "bin/java.exe"
        if (-not (Test-Path -LiteralPath $javaExecutable)) {
            continue
        }

        $versionOutput = (& $javaExecutable -version 2>&1) -join " "
        if ($versionOutput -match 'version "(?<major>\d+)' -and [int]$Matches.major -ge 17) {
            return (Resolve-Path -LiteralPath $candidate).Path
        }
    }

    throw "Java 17 or newer was not found. Pass its directory with -JavaHome."
}

if ($JavaHome) {
    $javaCandidates = @($JavaHome)
} else {
    $javaCandidates = [System.Collections.Generic.List[string]]::new()
    $javaCandidates.Add($env:JAVA_HOME)

    $javaCommand = Get-Command java.exe -ErrorAction SilentlyContinue
    if ($javaCommand) {
        $javaCandidates.Add((Split-Path -Parent (Split-Path -Parent $javaCommand.Source)))
    }

    $userJdksDirectory = Join-Path $env:USERPROFILE ".jdks"
    if (Test-Path -LiteralPath $userJdksDirectory) {
        Get-ChildItem -LiteralPath $userJdksDirectory -Directory |
            Sort-Object Name -Descending |
            ForEach-Object { $javaCandidates.Add($_.FullName) }
    }
}

$compatibleJavaHome = Get-CompatibleJavaHome -Candidates $javaCandidates
$portableJavaHome = $compatibleJavaHome.Replace("\", "/")

Push-Location $repositoryRoot
try {
    git config --local core.hooksPath .githooks
    git config --local hooks.javaHome $portableJavaHome
} finally {
    Pop-Location
}

Write-Host "Git hooks enabled. Pre-push checks will use Java from: $compatibleJavaHome"
