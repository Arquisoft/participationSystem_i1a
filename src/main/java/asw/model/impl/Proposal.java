package asw.model.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import asw.model.Interactive;
import asw.model.types.NotAllowedWords;
import asw.model.types.Topic;

@Document(collection = "proposals")
public class Proposal implements Interactive{

	@Id private ObjectId id;
	
	private User user;
	private String title;
	private String description;
	private Topic topic;
	private Date created;
	private int votes;
	private int minSupport;
	
	private Set<String> notAllowedWords = NotAllowedWords.getInstance().getSet();
	
	private Set<Comment> comments = new HashSet<>();	
	private Set<User> userVotes = new HashSet<>();
	
	Proposal(){}
	
	public Proposal(User user, String tit, String desc, Topic topic){
		this.user = user;
		this.title = tit;
		this.description = desc;
		this.topic = topic;
		this.votes = 0;
		this.created = new Date();
		this.comments = new HashSet<Comment>();
		this.userVotes = new HashSet<User>();
	}
	
	public Proposal(User user, String tit, String desc, 
			Topic topic, int minSupport, Set<String> l){
		this(user, tit, desc, topic);
		this.minSupport = minSupport;
		this.notAllowedWords = l;
	}
	
	public Proposal(User user, String tit){
		this.user = user;
		this.title = tit;
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public void setTopic(Topic t)
	{
		this.topic = t;
	}
	public int getMinSupport() {
		return minSupport;
	}

	public void setMinSupport(int minSupport) {
		this.minSupport = minSupport;
	}

	public Set<String> getNotAllowedWords() {
		return notAllowedWords;
	}

	public void setNotAllowedWords(Set<String> notAllowedWords) {
		this.notAllowedWords = notAllowedWords;
	}

	public ObjectId getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Topic getTopic() {
		return topic;
	}

	public int getVotes() {
		return votes;
	}

	public Date getCreated() {
		return created;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public Set<User> getUserVotes() {
		return userVotes;
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
		Proposal other = (Proposal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Proposal [user=" + user + ", title=" + title + ", description=" + description + ", topic=" + topic
				+ ", created=" + created + ", minSupport=" + minSupport + "]";
	}

	public void vote(User votant) {
		userVotes.add(votant);
		this.votes++;
	}
	
	public boolean checkNotAllowedWords(){
		for(String s: notAllowedWords){
			if(description.contains(s)){
				System.out.println("Not allowed Word: " + s);
				return false;
			}
		}
		return true;
		
	}
	
}
