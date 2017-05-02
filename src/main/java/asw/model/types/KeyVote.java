package asw.model.types;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class KeyVote implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(name = "userId")
    Long user;

    @Column(name = "votableId")
    Long votable;

	public KeyVote(){}

	public KeyVote(Long user, Long votable){
	    this.user = user;
	    this.votable = votable;
    }

    public Long getUser() {
        return user;
    }

    public Long getVotable() {
        return votable;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((votable == null) ? 0 : votable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyVote other = (KeyVote) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (votable == null) {
			if (other.votable != null)
				return false;
		} else if (!votable.equals(other.votable))
			return false;
		return true;
	}
}
