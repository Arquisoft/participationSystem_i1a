package asw.model.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Votable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
		return upvotes - downvotes;
	}
	
	public Set<Vote> getVotes(){
		return new HashSet<Vote>(votes);
	}

	public int getUpvotes() {
		return upvotes;
	}

	public int getDownvotes() {
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
}
