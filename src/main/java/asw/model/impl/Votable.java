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
	
	@OneToMany(mappedBy = "votable")
	private Set<Vote> votes = new HashSet<Vote>();
	
	private int score;
	private int upvotes;
	private int downvotes;
	
	public Long getId() {
		return id;
	}
	
	Set<Vote> _getVotes() {
		return votes;
	}
	
	public int getScore() {
		return score;
	}
	
	public Set<Vote> getVotes(){
		return new HashSet<Vote>(votes);
	}

	public void setScore(int numberOfVotes) {
		this.score = numberOfVotes;
	}

	public int getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	public int getDownvotes() {
		return downvotes;
	}

	public void setDownvotes(int downvotes) {
		this.downvotes = downvotes;
	}
}
