package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class ProposeSteps {

    @Given("^Im a logged user$")
    public void im_a_logged_user() throws Throwable {
        throw new PendingException();
    }

    @When("^i click \"([^\"]*)\"$")
    public void i_click_something1(String strArg1) throws Throwable {
        throw new PendingException();
    }

    @Then("^it will be publish$")
    public void it_will_be_publish() throws Throwable {
        throw new PendingException();
    }
    
    @And("^i choose the topic$")
    public void i_choose_the_topic() throws Throwable {
        throw new PendingException();
    }

    @And("^i write it$")
    public void i_write_it() throws Throwable {
        throw new PendingException();
    }

    @And("^i click \"([^\"]*)\"$")
    public void i_click_something(String strArg1) throws Throwable {
        throw new PendingException();
    }

    @And("^other logged users will see it$")
    public void other_logged_users_will_see_it() throws Throwable {
        throw new PendingException();
    }

    @And("^it should be able to be voted$")
    public void it_should_be_able_to_be_voted() throws Throwable {
        throw new PendingException();
    }

    @And("^it should be able to be commented$")
    public void it_should_be_able_to_be_commented() throws Throwable {
        throw new PendingException();
    }

}