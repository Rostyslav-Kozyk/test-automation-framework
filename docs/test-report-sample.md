# Test Execution Report

This portfolio sample is generated from Maven Surefire result XML files.
It contains only test names, statuses, counts, and durations—no logs, headers, payloads, paths, or secrets.

## Summary

| Total | Passed | Failed | Skipped | Duration |
|------:|-------:|-------:|--------:|---------:|
|    21 |     21 |      0 |       0 | 43.802 s |

## API Tests

| Test class | Test                                  | Status | Duration |
|------------|---------------------------------------|--------|---------:|
| UsersTest  | createUserTest                        | Passed |  4.547 s |
| UsersTest  | deleteUserTest                        | Passed |  0.341 s |
| UsersTest  | getSingleUserByIdTest                 | Passed |  0.316 s |
| UsersTest  | getSingleUserByInvalidIdTest (case 1) | Passed |  0.235 s |
| UsersTest  | getSingleUserByInvalidIdTest (case 2) | Passed |  0.245 s |
| UsersTest  | getUsersListByPageTest (case 1)       | Passed |  0.404 s |
| UsersTest  | getUsersListByPageTest (case 2)       | Passed |  0.189 s |
| UsersTest  | getUsersListTest                      | Passed |  0.230 s |
| UsersTest  | replaceUserTest                       | Passed |  0.220 s |
| UsersTest  | updateUserTest                        | Passed |  0.196 s |

## UI Tests

| Test class              | Test                                     | Status | Duration |
|-------------------------|------------------------------------------|--------|---------:|
| CartTest                | cartProductRemovalTest                   | Passed |  0.516 s |
| CartTest                | cartProductTest                          | Passed |  0.306 s |
| CheckoutInformationTest | checkoutInformationTest                  | Passed |  0.253 s |
| CheckoutInformationTest | requiredCheckoutInformationTest (case 1) | Passed |  1.807 s |
| CheckoutInformationTest | requiredCheckoutInformationTest (case 2) | Passed |  0.184 s |
| CheckoutInformationTest | requiredCheckoutInformationTest (case 3) | Passed |  0.720 s |
| CheckoutTest            | successfulCheckoutTest                   | Passed |  0.784 s |
| LoginTest               | loginWithInvalidCredentialsTest (case 1) | Passed |  0.199 s |
| LoginTest               | loginWithInvalidCredentialsTest (case 2) | Passed |  0.814 s |
| LoginTest               | loginWithValidCredentialsTest            | Passed |  1.456 s |
| ProductsTest            | addProductToCartTest                     | Passed |  0.409 s |
