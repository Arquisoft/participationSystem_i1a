package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import asw.model.impl.Comment;
import asw.model.impl.Proposal;
import asw.model.impl.User;
import asw.model.types.Topic;
import asw.persistence.services.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelTest {

	@Autowired
	private ServiceFactory servicesFactory;
	
	@Test
	public void testUser() {
		
		UserService service = servicesFactory.getUserService();
		User diego = new User("Diego","Freijo", "diego@uniovi.es", 
				createDate("13/06/1996"), "Calle de Avilés", "Española", "12345678A");
		User dani = new User("Daniel", "Fernandez", "daniel@uniovi.es", 
				createDate("02/08/1996"), "Calle de Tineo", "Española", "87654321B");
		
		service.createUser(diego);
		service.createUser(dani);

		assertEquals(2, service.getAllUsers().size());
		
		service.deleteUser(dani);
		assertEquals(1, service.getAllUsers().size());
		assertEquals("Diego", service.getAllUsers().get(0).getFirstName());

	}
	
	@Test
	public void proposalTest(){
		UserService userService= servicesFactory.getUserService();
		
		User diego = new User("Diego","Freijo", "diego@uniovi.es", 
				createDate("13/06/1996"), "Calle de Avilés", "Española", "12345678A");
		
		userService.createUser(diego);
		
		ProposalService proposalService = servicesFactory.getProposalService();
		

		Proposal prop = new Proposal(diego, "One proposal", "description of the proposal", Topic.Sports);
		
		proposalService.createProposal(prop);
		assertEquals(prop, proposalService.getProposalByTitle("One proposal"));
		proposalService.delete(prop);
		assertNull(proposalService.getProposalByTitle("One proposal"));
	}
	
	@Test
	public void commentTest() {
		CommentService commentService = servicesFactory.getCommentService();
		UserService userService= servicesFactory.getUserService();
		
		User diego = new User("Diego","Freijo", "diego@uniovi.es", 
				createDate("13/06/1996"), "Calle de Avilés", "Española", "12345678A");
		
		Comment comment = new Comment(diego, "content of the comment", null);
		
		userService.createUser(diego);
		commentService.createComment(comment);
		assertEquals(1, commentService.getAllComments().size());
		
		Comment comment2 = new Comment(diego, "content of the comment 2", null);
		commentService.createComment(comment2);
		assertEquals(2, commentService.getAllComments().size());
		commentService.deleteComment(comment2);
		assertEquals(1, commentService.getAllComments().size());
	}

	private Date createDate(String dateStr)
	{
		Date date = new Date();
		DateFormat format= new SimpleDateFormat("dd/MM/yyyy");
		try {
			date= format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}