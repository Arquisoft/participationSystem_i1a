package asw.model.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import asw.model.types.VoteType;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Votable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@OneToMany(mappedBy = "votable", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	private Set<Vote> votes = new HashSet<Vote>();
	
	private int upvotes;
	private int downvotes;
	
	Votable() {}
	
	public Long getId() {
		return id;
	}
	
	Set<Vote> _getVotes() {
		return votes;
	}
	
	public int getScore() {
        process();
        int number = upvotes - downvotes;
        return number;
	}
	
	public Set<Vote> getVotes(){
		return new HashSet<Vote>(votes);
	}

	public int getUpvotes() {
		process();
	    return upvotes;
	}

	public int getDownvotes() {
		process();
	    return downvotes;
	}

	public void incrementUpvotes() {
		this.upvotes++;
	}

	public void incrementDownvotes() {
		this.downvotes++;
	}

	public void decrementUpvotes() {
		this.upvotes--;
	}

	public void decrementDownvotes() {
		this.downvotes--;
	}

	private void process(){
	    upvotes = 0; downvotes = 0;
	    for(Vote v: votes){
	        if( v.getVoteType() == VoteType.POSITIVE)
	            upvotes++;
	        if ( v.getVoteType() == VoteType.NEGATIVE)
	            downvotes++;
        }
    }
}
