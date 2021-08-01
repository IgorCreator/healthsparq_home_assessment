Feature: reading CSV file and storing content in datastructure

  Scenario: Input.csv file exist
    Given input.csv with data stored locally
    When user entered absolute file path of the input.csv
    Then the result file exists

  Scenario: Read data from input.csv file
    Given file input.csv loaded as object in JVM
    When read file line by line
    Then each line transformed in separate java object and added in data structure