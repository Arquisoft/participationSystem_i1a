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
	
	private int numberOfVotes;
	
	public Long getId() {
		return id;
	}
	
	Set<Vote> _getVotes() {
		return votes;
	}
	
	public int getNumberOfVotes() {
		return numberOfVotes;
	}
	
	public Set<Vote> getVotes(){
		return new HashSet<Vote>(votes);
	}

	public void setNumberOfVotes(int numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}
	
	public void addVote(){
		numberOfVotes++;
	}
}
