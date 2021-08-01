package gherkin_tests.step_def;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SortDataByParamTest {
    @Given("data from input.csv collected in java data structure")
    public void dataFromInputCsvCollectedInJavaDataStructure() {
    }

    @When("user specified sorting parameter {string}")
    public void userSpecifiedSortingParameter(String sortingParam) {
    }

    @Then("sort data in data structure by sorting parameter")
    public void sortDataInDataStructureBySortingParameter() {
    }
}
