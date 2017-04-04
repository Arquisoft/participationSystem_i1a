package asw.cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class CucumberTest{
	
	@BeforeClass
    public static void setUp() {
        //CucumberSteps.setUp();
    }

    @AfterClass
    public static void tearDown() {
        //CucumberSteps.tearDown();
    }
}