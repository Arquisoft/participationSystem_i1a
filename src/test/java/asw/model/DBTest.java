package asw.model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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
public class DBTest {

	private User diego = new User("Diego","Freijo", "diego@uniovi.es", 
			createDate("13/06/1996"), "Calle de Avilés", "Española", "12345678A");
	private User dani = new User("Daniel", "Fernandez", "daniel@uniovi.es", 
			createDate("02/08/1996"), "Calle de Tineo", "Española", "87654321B");
	private Proposal prop = new Proposal(diego, "One proposal", "description of the proposal");
	private Comment comment1 = new Comment(diego, "content of the comment", prop);
	private Comment comment2 = new Comment(dani, "content of the comment 2", prop);
	
    @Autowired
    private TestEntityManager entityManager;
	
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
	
	private void addData() {
		uS.save(diego);
		uS.save(dani);
		pS.save(prop);
		cS.save(comment1);
		cS.save(comment2);
	}
	
	@Test
	public void userTest() {
		assertEquals(2, uS.findAll().size());
		assertTrue(uS.checkExists(diego.getId()));
		assertTrue(uS.checkExists(dani.getId()));
	}
	
	@Test
	public void proposalTest(){
		assertEquals(1, pS.findAll().size());
		assertTrue(pS.checkExists(prop.getId()));
	}
	
	@Test
	public void commentTest() {
		assertEquals(2, cS.findAll().size());
		assertTrue(cS.checkExists(comment1.getId()));
		assertTrue(cS.checkExists(comment2.getId()));
	}
	
	@Test
	public void makeProposalTest() {
		diego.propose(prop);
		assertEquals(1, diego.getProposals().size());
		diego.deleteProposal(prop);
		assertEquals(0, diego.getProposals().size());
	}
	
	@Test
	public void makeCommentTest() {
		diego.comment(prop, comment1);
		dani.comment(prop, comment2);
		assertEquals(1, diego.getComments().size());
		assertEquals(1, dani.getComments().size());
		diego.deleteComment(prop, comment1);
		assertEquals(0, diego.getComments().size());
		dani.deleteComment(prop, comment2);
		assertEquals(0, dani.getComments().size());
	}
	
	@Test
	public void makeVoteTest() {
		diego.vote(new Vote(diego, prop, VoteType.POSITIVE), prop);
		assertEquals(1, diego.getVotes().size());
		dani.vote(new Vote(dani, prop, VoteType.POSITIVE), prop);
		assertEquals(1, dani.getVotes().size());
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