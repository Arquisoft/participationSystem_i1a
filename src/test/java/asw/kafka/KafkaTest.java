package asw.kafka;

import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
	private CommentService cS;
	@Autowired
	private ProposalService pS;
	@Autowired
	private VoteService vS;
	@Autowired
	private KafkaProducer producer;
	@Autowired
	private FillDatabase fillDatabase;

	private SimpleConsumer consumer;

	@Before
	public void setUp() throws Exception {
		fillDatabase.fill();
		consumer = new SimpleConsumer();
		TimeUnit.SECONDS.sleep(1);
		consumer.read();
	}

	@After
	public void cleanup() {
		consumer.close();
	}

	@Test
	public void testMessages() throws Exception {
		Set<String> messages = new HashSet<>();
		messages.add("TestVote1");
		messages.add("#$%&/()=");
		for (String message : messages) {
			producer.send("newVote", message);
		}
		checkMessages(messages);
	}

	@Test
	public void testVotes() throws Exception {
		Set<String> expectedMessages = new HashSet<>();
		Proposal prop = pS.findAll().get(0);
		User user0 = uS.findAll().get(0);
		User user1 = uS.findAll().get(1);

		Vote vote = new Vote(user0, prop, VoteType.POSITIVE);
//		vS.save(vote);
		expectedMessages.add(String.format("%d;+", prop.getId()));

		vote = new Vote(user1, prop, VoteType.NEGATIVE);
//		vS.save(vote);
		expectedMessages.add(String.format("%d;-", prop.getId()));

		checkMessages(expectedMessages);
	}

	private void checkMessages(Set<String> expectedMessages)
			throws Exception {
		long start = System.currentTimeMillis();
		while (expectedMessages.size() != 0) {
			if (System.currentTimeMillis() - start > 20 * 1000) {
				String failure = String.format(
						"%d expected messages were not read: %s",
						expectedMessages.size(),
						expectedMessages.toString());
				fail(failure);
			}
			
			List<ConsumerRecord<String, String>> messages = consumer
					.read();

			for (ConsumerRecord<String, String> message : messages) {
				if (!expectedMessages.remove(message.value())) {
					String failure = String.format(
							"Read unexpected message %s", message);
					fail(failure);
				}
			}

		}
	}
}
