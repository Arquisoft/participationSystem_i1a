package asw.kafka;

import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.After;
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
import asw.persistence.services.VoteService;
import asw.producers.KafkaProducer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest {

	private User diego = new User("Diego", "Freijo",
			"diego@uniovi.es", createDate("13/06/1996"),
			"Calle de Avilés", "Española", "12345678A");
	private User dani = new User("Daniel", "Fernandez",
			"daniel@uniovi.es", createDate("02/08/1996"),
			"Calle de Tineo", "Española", "87654321B");
	private Proposal prop = new Proposal(diego, "One proposal",
			"description of the proposal");
	private Comment comment1 = new Comment(diego,
			"content of the comment", prop);
	private Comment comment2 = new Comment(dani,
			"content of the comment 2", prop);

	@Autowired
	private UserService uS;
	@Autowired
	private CommentService cS;
	@Autowired
	private ProposalService pS;
	@Autowired
	private VoteService vS;
	@Autowired
	private KafkaProducer producer;
	
	private SimpleConsumer consumer;

	@Before
	public void setUp() {
		consumer = new SimpleConsumer();
		try {
			addData();
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void cleanup() {
		consumer.close();
	}

	private Date createDate(String dateStr) {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = format.parse(dateStr);
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
	public void testMessages() throws Exception {
		Set<String> messages = new HashSet<>();
		messages.add("TestVote1");
		messages.add("#$%&/()=");
		for(String message : messages) {
			producer.send("newVote", message);			
		}
		checkMessages(messages);
	}
	
	@Test
	public void testVotes() throws Exception {
		Set<String> expectedMessages = new HashSet<>();
		Vote vote = new Vote(dani, prop, VoteType.POSITIVE);
		vS.save(vote);
		expectedMessages.add(String.format("%d;+", prop.getId()));

		vote = new Vote(diego, prop, VoteType.NEGATIVE);
		vS.save(vote);
		expectedMessages.add(String.format("%d;-", prop.getId()));

		checkMessages(expectedMessages);
	}

	private void checkMessages(Set<String> expectedMessages)
			throws Exception {
		List<ConsumerRecord<String,String>> messages = consumer.read();

		for (ConsumerRecord<String, String> message : messages) {
			if (expectedMessages.contains(message.value())) {
				expectedMessages.remove(message.value());
			} else {
				String failure = String.format(
						"Read unexpected message %s", message);
				fail(failure);
			}
		}
		if (expectedMessages.size() != 0) {
			String failure = String.format(
					"%d expected messages were not read: %s",
					expectedMessages.size(),
					expectedMessages.toString());
			fail(failure);
		}
	}
}
