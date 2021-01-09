package cucumberOptions;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features",  // if you want to perform only 1 feature add the specific feature /Login.feature
		glue = "stepDefinitions") 

public class TestRunner {

}
