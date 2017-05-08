package asw.kafka;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.test.context.junit4.SpringRunner;

import asw.model.impl.Proposal;
import asw.model.impl.User;
import asw.model.impl.Vote;
import asw.model.types.VoteType;
import asw.persistence.FillDatabase;
import asw.persistence.services.CommentService;
import asw.persistence.services.ProposalService;
import asw.persistence.services.UserService;
import asw.persistence.services.VoteService;
import asw.producers.KafkaProducer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest {

	@Autowired
	private UserService uS;
	@Autowired
	private ProposalService pS;
	@Autowired
	private KafkaProducer producer;
	@Autowired
	private FillDatabase fillDatabase;

	private Set<String> expectedMessages;
	private Set<String> unexpectedMessages;

	@Before
	public void setUp() throws Exception {
		fillDatabase.fill();
		expectedMessages = Collections
				.synchronizedSet(new HashSet<>());
		unexpectedMessages = Collections
				.synchronizedSet(new HashSet<>());
	}

	@Test
	public void testMessages() throws Exception {
		expectedMessages.add("TestVote1");
		expectedMessages.add("#$%&/()=");
		for (String message : expectedMessages) {
			producer.send("newVote", message);
		}
		assertReceived();
	}

	private void assertReceived() {
		long startMillis = System.currentTimeMillis();
		long timeOut = 5000;
		while (expectedMessages.size() != 0
				&& System.currentTimeMillis()
						- startMillis < timeOut) {
		}
		Assert.assertEquals(
				"Expected messages not received: " + expectedMessages,
				0, expectedMessages.size());
		Assert.assertEquals(
				"Unexpected messages received: " + unexpectedMessages,
				0, unexpectedMessages.size());
	}

	@Test
	public void testVotes() throws Exception {
		Set<String> expectedMessages = new HashSet<>();
		Proposal prop = pS.findAll().get(0);
		User user0 = uS.findAll().get(0);
		User user1 = uS.findAll().get(1);

		expectedMessages.add(String.format("%d;+", prop.getId()));
		expectedMessages.add(String.format("%d;-", prop.getId()));

		new Vote(user0, prop, VoteType.POSITIVE);
		// vS.save(vote);
		new Vote(user1, prop, VoteType.NEGATIVE);
		// vS.save(vote);
		assertReceived();
	}

	@KafkaListener(topics = "newVote")
	public void listen(String message) {
		if (!expectedMessages.remove(message)) {
			unexpectedMessages.add(message);
		}

	}
}
