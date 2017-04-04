package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class CommentSteps {

    @Given("^Im a logged user \"([^\"]*)\" with password \"([^\"]*)\"$")
    public void im_a_logged_user_something_with_password_something(String name, String pass) throws Throwable {
        System.out.println("User with name: " + name + " and pass: " + pass);
    }

    @When("^i click \"([^\"]*)\"$")
    public void i_click_something(String button) throws Throwable {
        System.out.println(button + " button cliked");
    }

    @Then("^it will be publish$")
    public void it_will_be_publish() throws Throwable {
        System.out.println("Proposal has been published");
    }

    @And("^i choose proposal \"([^\"]*)\"$")
    public void i_choose_proposal_something(String proposal) throws Throwable {
        System.out.println("proposal: " + " chosen");
    }

    @And("^i click \"([^\"]*)\"$")
    public void i_click_comment(String button) throws Throwable {
        System.out.println(button + " button cliked");
    }

    @And("^i write \"([^\"]*)\"$")
    public void i_write_something(String comment) throws Throwable {
        System.out.println("comment: ");
    }
}