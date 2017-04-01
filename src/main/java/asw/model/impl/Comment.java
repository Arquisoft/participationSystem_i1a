package asw.model.impl;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import asw.model.Commentable;

@Document(collection = "comments")
public class Comment implements Commentable{
	
	@Id private ObjectId id;
	private String content;
	private User user;
	private Date created;
	private ObjectId proposalId;
	
	public Comment(){}
	
	public Comment(User user, String content, ObjectId proposalId){
		this.content = content;
		this.user = user;
		this.proposalId = proposalId;
		created = new Date();
	}

	public ObjectId getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public User getUser() {
		return user;
	}

	public Date getCreated() {
		return created;
	}

	public ObjectId getProposalId() {
		return proposalId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", user=" + user + ", created=" + created
				+ ", proposalId=" + proposalId + "]";
	}

}
