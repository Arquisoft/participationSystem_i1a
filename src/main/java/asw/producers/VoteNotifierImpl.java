package asw.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.model.impl.Votable;


@Service
public class VoteNotifierImpl implements VoteNotifier {
	
	@Autowired
	private KafkaProducer producer;
	
	@Override
	public void notifyNewVote(Votable votable, boolean positive) {
		long votableId = votable.getId();
		char sign = positive? '+' : '-';
		String message = String.format("%d;%s", votableId, sign);
		producer.send("newVote", message);
	}
}
