package asw.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import asw.model.impl.Vote;
import asw.model.types.VoteType;

@Configurable
public class VoteNotifier {
	
	@Autowired
	private KafkaProducer producer;
	
	public void notifyNewVote(Vote vote) {
		long votableId = vote.getVotable().getId();
		char sign = vote.getVoteType() == VoteType.POSITIVE? '+' : '-';
		String message = String.format("%d;%s", votableId, sign);
		producer.send("newVote", message);
	}
}
