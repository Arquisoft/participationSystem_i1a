package asw.model.impl;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import asw.model.types.*;
import asw.producers.VoteNotifier;

public class Association {

	public static class MakeVote {

		@Configurable
		public static class NotifierWrapper {
			@Autowired
			private VoteNotifier notifier;
			
			public void notifyNewVote(Votable votable, boolean positive) {
				if (notifier != null) {
					notifier.notifyNewVote(votable, positive);
				}
			}
		}
		
		public static void link(User User, Vote vote, Votable votable) {
			NotifierWrapper notifier = new NotifierWrapper();
			
			vote.setVotable(votable);
			vote.setUser(User);
			
			if(vote.getVoteType().equals(VoteType.POSITIVE)) {
				votable.incrementUpvotes();
				notifier.notifyNewVote(votable, true);					
			} else if(vote.getVoteType().equals(VoteType.NEGATIVE)){
				votable.incrementDownvotes();
				notifier.notifyNewVote(votable, false);
			}
			
			votable._getVotes().add(vote);
			User._getVotes().add(vote);
		}

		public static void unlink(User User, Vote vote, Votable votable) {
			NotifierWrapper notifier = new NotifierWrapper();
			
			votable._getVotes().remove(vote);
			User._getVotes().remove(vote);

			vote.setVotable(null);
			vote.setUser(null);

			if(vote.getVoteType().equals(VoteType.POSITIVE)) {
				votable.decrementUpvotes();
				notifier.notifyNewVote(votable, false);
			} else if(vote.getVoteType().equals(VoteType.NEGATIVE)) {
				votable.decrementDownvotes();
				notifier.notifyNewVote(votable, true);
			}
			
		}
	}

	public static class Propose {

		public static void link(User user, Proposal proposal) {
			proposal.setUser(user);
			user._getProposals().add(proposal);
		}

		public static void unlink(User user, Proposal proposal) {
			user._getProposals().remove(proposal);
			proposal.setUser(null);
		}
	}

	public static class MakeComment {

		public static void link(User user, Comment comment, Proposal proposal) {
			comment._setProposal(proposal);
			comment._setUser(user);
			proposal._getComments().add(comment);
			user._getComments().add(comment);
		}

		public static void unlink(User user, Comment comment, Proposal proposal) {
			proposal._getComments().remove(comment);
			user._getComments().remove(comment);

			comment._setProposal(null);
			comment._setUser(null);
		}
	}
}
