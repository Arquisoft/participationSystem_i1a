package asw.persistence;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import asw.model.impl.Comment;
import asw.model.impl.Proposal;
import asw.model.impl.User;
import asw.model.types.Topic;
import asw.persistence.services.CommentService;
import asw.persistence.services.ProposalService;
import asw.persistence.services.UserService;
import asw.persistence.services.VoteService;

@Service
public class FillDatabase {

	private User u1, u2, u3, u4, uadmin;
	private Proposal p1, p2, p3, p4, p5;
	private Comment c1, c2, c3, c4, c5, c6;
	
	@Autowired
	private UserService uS;
	@Autowired
	private CommentService cS;
	@Autowired
	private ProposalService pS;
	@Autowired
	private VoteService vS;
	
	public void fill() {
		vS.clearTable();
		cS.clearTable();
		pS.clearTable();
		uS.clearTable();
		initializeData();
		
		try {
			addUsers();
			addProposals();
			addComments();
		} catch (DataIntegrityViolationException e) {
			System.out.println("Some errors occur during the initialization of the database (;-;) ");
		}
	}
	
	private void initializeData() {
		initializeUsers();
		initializeProposals();
		initializeComments();
	}
	
	private void initializeUsers() {
		u1 = new User("Daniel", 	 "Fernández Feito", "email1", new Date(), "Address1", "Spain", 	   "1111");
		u2 = new User("Diego Roger", "Freijó Álvarez",  "email2", new Date(), "Address2", "Spain", 	   "2222");
		u3 = new User("Sergio", 	 "García Álvarez",  "email3", new Date(), "Address3", "Australia", "3333");		
		u4 = new User("Pablo", 	 	 "García Ledo", 	"email4", new Date(), "Address4", "Australia", "4444");
		uadmin = new User("Admin",	 "Istrador",		"emailadmin", new Date(), "AddressAdmin", "Spain", "5555");
		uadmin.setAdmin(true);
		u4.setLogin("dani39");
		u4.setPassword("password");
	}

	private void initializeProposals() {
		p1 = new Proposal(u1, "Title1", "Description1", Topic.HEALTHCARE);
		p2 = new Proposal(u2, "Title2", "Description2", Topic.ENVIROMENT);
		p3 = new Proposal(u3, "Title3", "Description3", Topic.POLITICS);
		p4 = new Proposal(u4, "Title4", "Description4", Topic.SECURITY);
		p5 = new Proposal(u4, "Title5", "Description5", Topic.SPORTS);
	}

	private void initializeComments() {
		c1 = new Comment(u1, "Comment body 1", p1);
		c2 = new Comment(u2, "Comment body 2", p2);
		c3 = new Comment(u3, "Comment body 3", p3);
		c4 = new Comment(u4, "Comment body 4", p4);
		c5 = new Comment(u4, "Comment body 5", p5);
		c6 = new Comment(u3, "Comment body 6", p5);
	}

	private void addUsers() {
		uS.save(u1);
		uS.save(u2);
		uS.save(u3);
		uS.save(u4);
		uS.save(uadmin);
	}

	private void addProposals() {
		pS.save(p1);
		pS.save(p2);
		pS.save(p3);
		pS.save(p4);
		pS.save(p5);
	}

	private void addComments() {
		cS.save(c1);
		cS.save(c2);
		cS.save(c3);
		cS.save(c4);
		cS.save(c5);
		cS.save(c6);
	}

}
