package asw.producers;

import asw.model.impl.Votable;

public interface VoteNotifier {
	void notifyNewVote(Votable votable, boolean positive);
}
