package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import asw.model.impl.Proposal;
import asw.model.impl.User;

@RunWith(Cucumber.class)
public class ProposeSteps {
	
	Proposal proposal;
	User user;

    @Given("^Im a logged user with name \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void im_a_logged_user(String name,String pass) throws Throwable {
       System.out.println("User with name: " + name + " and password: " + pass);
    }

    @When("^i click \"([^\"]*)\"$")
    public void i_click_writeProposal(String button) throws Throwable {
        System.out.println(button + " button cliked");
    }

    @Then("^it will be publish$")
    public void it_will_be_publish() throws Throwable {
        System.out.println("Proposal has been published");
    }
    
    @And("^i choose the topic \"([^\"]*)\"$")
    public void i_choose_the_topic(String topic) throws Throwable {
        System.out.println("Chosen topic: " + topic);
    }

    @And("^i write it \"([^\"]*)\"$")
    public void i_write_it(String desc) throws Throwable {
        
    	/*
    	 proposal.setDescription(desc);
    	 */
    	System.out.println("Description: " + desc);
    }
    
    @And("^i title it \"([^\"]*)\"$")
    public void i_title_it(String tit) throws Throwable {
    	/*
    	proposal = new Proposal(user, tit);
    	*/
    	System.out.println("Proposal's title: " + tit);
    }

    @And("^i click \"([^\"]*)\"$")
    public void i_click_finish(String button) throws Throwable {
        System.out.println(button + " button cliked");
    }

    
    
    

    @And("^it should be able to be voted$")
    public void it_should_be_able_to_be_voted() throws Throwable {
        System.out.println("Proposal can be voted");
    }

    @And("^it should be able to be commented$")
    public void it_should_be_able_to_be_commented() throws Throwable {
        System.out.println("Proposal can be commented");
    }

}