package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.AssertTrue;

import org.junit.runner.RunWith;

import asw.model.impl.Proposal;
import asw.model.impl.User;
import asw.persistence.services.UserService;

@RunWith(Cucumber.class)
public class VoteSteps {
	
	UserService us = new UserService();
	List<User> users = us.getAllUsers();
	User user;

    @Given("^Im a logged user$")
    public void im_a_logged_user(User u) throws Throwable {
		this.user = u;
    }

    @When("^i click \"([^\"]*)\"$")
    public void i_click_something(String strArg1) throws Throwable {
        throw new PendingException();
    }

    @Then("^it will be voted$")
    public void it_will_be_voted(Proposal p) throws Throwable {
        p.vote(user);
    }

    @And("^other logged users will see it$")
    public void other_logged_users_will_see_it() throws Throwable {
        
    }

}
