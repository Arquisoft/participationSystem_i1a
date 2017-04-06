package asw.producers;

import asw.model.impl.Vote;

public interface VoteNotifier {
	public void notifyNewVote(Vote vote);
}
