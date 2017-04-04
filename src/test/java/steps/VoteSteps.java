package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class VoteSteps {

    @Given("^Im a logged user with name \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void im_a_logged_user_with_name_something_and_password_something(String name, String pass) throws Throwable {
        System.out.println("user with name: " + name + " and password: " + pass);
    }

    @When("^i click \"([^\"]*)\"$")
    public void i_click_something(String button) throws Throwable {
        System.out.println(button + " button cliked");
    }

    @Then("^it will be voted$")
    public void it_will_be_voted() throws Throwable {
        System.err.println("Proposal voted");
    }

    @And("^other logged users will see it$")
    public void other_logged_users_will_see_it() throws Throwable {
        System.out.println("Other users can see the votes");
    }

}
	
	/*
	@Autowired
	UserService us;
	@Autowired
	List<User> users;
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
        p.addVote();
    }

    @And("^other logged users will see it$")
    public void other_logged_users_will_see_it() throws Throwable {
        
    }
    */