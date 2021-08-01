package gherkin_tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/gherkin_tests/features"},
        glue = {"gherkin_tests.step_def"},
        plugin = {"pretty"}
)
public class GherkinTestsRunner {
}
