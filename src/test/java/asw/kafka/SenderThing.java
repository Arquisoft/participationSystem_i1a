package asw.kafka;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import asw.model.impl.Comment;
import asw.model.impl.Proposal;
import asw.model.impl.User;
import asw.model.impl.Vote;
import asw.model.types.VoteType;
import asw.persistence.services.CommentService;
import asw.persistence.services.ProposalService;
import asw.persistence.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderThing {
	
	private User diego = new User("Diego","Freijo", "diego@uniovi.es", 
			createDate("13/06/1996"), "Calle de Avilés", "Española", "12345678A");
	private User dani = new User("Daniel", "Fernandez", "daniel@uniovi.es", 
			createDate("02/08/1996"), "Calle de Tineo", "Española", "87654321B");
	private Proposal prop = new Proposal(diego, "One proposal", "description of the proposal");
	private Comment comment1 = new Comment(diego, "content of the comment", prop);
	private Comment comment2 = new Comment(dani, "content of the comment 2", prop);
	
	
	@Autowired
	private UserService uS;
	@Autowired
	private CommentService cS;
	@Autowired
	private ProposalService pS;
	
	@Before
	public void setUp() {
		try {
			addData();
		} catch(DataIntegrityViolationException e){
			e.printStackTrace();
		}
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

	private void addData() {
		uS.save(diego);
		uS.save(dani);
		pS.save(prop);
		cS.save(comment1);
		cS.save(comment2);
	}

	@Test
	public void test() {
		// This is a basic method to allow for a visual check of the Kafka output
		// and should be substituted in the future by a Kafka consumer that 
		// actually checks the output
		Vote vote = new Vote(null, null, VoteType.POSITIVE);
		pS.vote(prop, vote, dani);
		
		vote = new Vote(null, null, VoteType.NEGATIVE);
		pS.vote(prop, vote, diego);
	}
}
