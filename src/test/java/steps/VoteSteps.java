package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class VoteSteps {

    @Given("^Im a logged user$")
    public void im_a_logged_user() throws Throwable {
        throw new PendingException();
    }

    @When("^i click \"([^\"]*)\"$")
    public void i_click_something(String strArg1) throws Throwable {
        throw new PendingException();
    }

    @Then("^it will be voted$")
    public void it_will_be_voted() throws Throwable {
        throw new PendingException();
    }

    @And("^other logged users will see it$")
    public void other_logged_users_will_see_it() throws Throwable {
        throw new PendingException();
    }

}
