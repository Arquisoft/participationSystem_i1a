package asw.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class SimpleConsumer {

	private KafkaConsumer<String, String> consumer;
	private boolean kill = false;
	private List<ConsumerRecord<String, String>> messages = new ArrayList<>();

	public SimpleConsumer() {
		// Kafka consumer configuration settings
		String topicName = "newVote";

		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "testGroup");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,
				"1000");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class);

		// Properties props = new Properties();
		// props.put("bootstrap.servers", "localhost:9092");
		// props.put("group.id", "test");
		// props.put("enable.auto.commit", "true");
		// props.put("auto.commit.interval.ms", "1000");
		// props.put("session.timeout.ms", "30000");
		// props.put("key.deserializer",
		// "org.apache.kafka.common.serializa-tion.StringDeserializer");
		// props.put("value.deserializer",
		// "org.apache.kafka.common.serializa-tion.StringDeserializer");
		consumer = new KafkaConsumer<String, String>(props);

		// Kafka Consumer subscribes list of topics here.
		consumer.subscribe(Arrays.asList(topicName));

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					loopRead();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}).start();
	}

	private void loopRead() throws InterruptedException {
		while (!kill) {
			for (ConsumerRecord<String, String> record : consumer
					.poll(100)) {
				messages.add(record);
			}
			Thread.sleep(50);
		}
	}

	public List<ConsumerRecord<String,String>> read() throws Exception {
		List<ConsumerRecord<String, String>> result = messages;
		this.messages = new ArrayList<>();
		return result;
	}

	public void close() {
		kill = true;
	}
}