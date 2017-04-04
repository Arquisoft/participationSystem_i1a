package asw.model.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import asw.model.types.NotAllowedWords;
import asw.model.types.Topic;

@Entity
@Table(name="TProposals")
public class Proposal extends Votable {
	
	@ManyToOne
	private User user;
	
	private String title;
	private String description;
	private Topic topic;
	private Date created;
	private int minSupport;
	
	private Set<String> notAllowedWords = NotAllowedWords.getInstance().getSet();
	
	@OneToMany(mappedBy = "proposal")
	private Set<Comment> comments = new HashSet<>();	
	
	private Set<User> userVotes = new HashSet<>();
	
	Proposal(){}
	
	public Proposal(User user, String tit, String desc, Topic topic){
		this(user, tit, desc);
		this.topic = topic;
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
	
	public Proposal(User user, String tit, String description){
		this(user, tit);
		this.description = description;
	}
	
	public Proposal(User user, String tit) {
		Association.Propose.link(user, this);
		this.title = tit;
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public void setTopic(Topic t) {
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

	public Date getCreated() {
		return created;
	}

	public Set<Comment> getComments() {
		return new HashSet<Comment>(comments);
	}
	
	Set<Comment> _getComments(){
		return this.comments;
	}

	public Set<User> getUserVotes() {
		return userVotes;
	}

	@Override
	public String toString() {
		return "Proposal [user=" + user + ", title=" + title + ", description=" + description + ", topic=" + topic
				+ ", created=" + created + ", minSupport=" + minSupport + "]";
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

	public void setUser(User user) {
		this.user = user;		
	}
	
}
