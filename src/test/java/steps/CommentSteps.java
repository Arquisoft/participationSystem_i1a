package steps;

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
    

    /*
     *  @Given("^Im a logged user$")
     public void im_a_logged_user() throws Throwable {
         throw new PendingException();
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
     public void i_click_something1(String strArg1) throws Throwable {
         throw new PendingException();
     public void i_click_seeProposals(String strArg1) throws Throwable {
         for(Proposal p : proposals){
         	System.out.println(p);
         }
      }
  
      @Then("^it will be publish$")
      public void it_will_be_publish() throws Throwable {
          throw new PendingException();
      }
  
     @And("^i choose one$")
     public void i_choose_one() throws Throwable {
         throw new PendingException();
     @And("^i choose one \"([^\"]*)\"$")
     public void i_choose_one(String tit) throws Throwable {
     	Proposal p = ps.getProposalByTitle(tit);
         if(p != null){
         	this.proposal = p;
         }
      }
  
      @And("^i click \"([^\"]*)\"$")
     public void i_click_something(String strArg1) throws Throwable {
         throw new PendingException();
     }
 
     @And("^i write it$")
     public void i_write_it() throws Throwable {
         throw new PendingException();
     public void i_click_comment(String strArg1) throws Throwable {
     	System.out.println("Comment clicked");
      }
  
     @And("^other logged users will see it$")
     public void other_logged_users_will_see_it() throws Throwable {
         throw new PendingException();
     @And("^i write it \"([^\"]*)\"$")
     public void i_write_it(String content) throws Throwable {
         this.comment = new Comment(user, content, proposal.getId());
      }
  
      @And("^it should be able to be commented$")
      public void it_should_be_able_to_be_commented() throws Throwable {
         throw new PendingException();
         
      }
      */
}