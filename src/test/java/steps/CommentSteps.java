package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;

import java.util.List;

import org.junit.runner.RunWith;

import asw.model.impl.Comment;
import asw.model.impl.Proposal;
import asw.model.impl.User;
import asw.persistence.services.ProposalService;
import asw.persistence.services.UserService;

@RunWith(Cucumber.class)
public class CommentSteps {

	UserService us = new UserService();
	ProposalService ps = new ProposalService();
	List<Proposal> proposals = ps.getAllProposals();
	Proposal proposal;
	List<User> users = us.getAllUsers();
	User user;
	Comment comment;
	
	
    @Given("^Im a logged user$ \"([^\"]*)\" and \"([^\"]*)\"$")
    public void im_a_logged_user(String name, String pass) throws Throwable {
        this.user = new User(name, pass);
    }

    @When("^i click \"([^\"]*)\"$")
    public void i_click_seeProposals(String strArg1) throws Throwable {
        for(Proposal p : proposals){
        	System.out.println(p);
        }
    }

    @Then("^it will be publish$")
    public void it_will_be_publish() throws Throwable {
        throw new PendingException();
    }

    @And("^i choose one \"([^\"]*)\"$")
    public void i_choose_one(String tit) throws Throwable {
    	Proposal p = ps.getProposalByTitle(tit);
        if(p != null){
        	this.proposal = p;
        }
    }

    @And("^i click \"([^\"]*)\"$")
    public void i_click_comment(String strArg1) throws Throwable {
    	System.out.println("Comment clicked");
    }

    @And("^i write it \"([^\"]*)\"$")
    public void i_write_it(String content) throws Throwable {
        this.comment = new Comment(user, content, proposal.getId());
    }

    @And("^it should be able to be commented$")
    public void it_should_be_able_to_be_commented() throws Throwable {
        
    }

}