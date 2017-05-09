package asw.model.impl;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import asw.model.types.VoteType;

@Entity
@Configurable
@Table(name = "TVotes")
//@IdClass(KeyVote.class)
public class Vote {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
	private Votable votable;

    @Enumerated(EnumType.STRING)
	private VoteType voteType;
    
	Vote() {}

	public Vote(User user, Votable votable, VoteType voteType) {
		this.voteType=voteType;
		Association.MakeVote.link(user, this, votable);
	}

    public void setUser(User user) {
		this.user = user;
	}

	public User getUser(){
	    return user;
	}

	public Votable getVotable() {
		return votable;
	}

	public void setVotable(Votable votable) {
		this.votable = votable;
	}

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        if (user != null ? !user.equals(vote.user) : vote.user != null) return false;
        return votable != null ? votable.equals(vote.votable) : vote.votable == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (votable != null ? votable.hashCode() : 0);
        return result;
    }

    @Override
	public String toString() {
		return "Vote [user=" + user + ", votable=" + votable + ", voteType=" + voteType + "]";
	}

}
