package asw.cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import asw.cucumber.steps.DriverFactory;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class CucumberTest {
	@AfterClass
	public static void tearDown() {
		DriverFactory.getDriver().quit();
	}
}