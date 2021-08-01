Feature: Creating output.csv file

  Scenario: Create output.csv
    Given sorted data in java data structure
    When user entered absolute folder path to export output.csv
    Then put all sorted data in output.csv and export it in specific folder

  Scenario: verify output.csv exists
    Given absolute path to output.csv as string
    When path transformed in file java object
    Then check existence of output.csv