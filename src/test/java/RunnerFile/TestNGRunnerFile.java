package RunnerFile;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= {"src/test/java/FeatureFile/RestfulBooker.feature"},
		glue= {"StepDefination"},
		plugin= {"pretty","html:target/sou.html"},
		tags="@regression"
		
		)
public class TestNGRunnerFile extends AbstractTestNGCucumberTests{

}
