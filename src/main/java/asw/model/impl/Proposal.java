package asw.model.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import asw.model.types.MinSupport;
import asw.model.types.NotAllowedWords;
import asw.model.types.Topic;

@Entity
@Table(name="TProposals")
public class Proposal extends Votable {
	
	@ManyToOne
	private User user;
	
	private String title;
	private String description;
	@Enumerated(EnumType.STRING)
	private Topic topic;
	private String topicAux;
	private Date created;
	private int minSupport;
	
	@ElementCollection
	private Set<String> notAllowedWords = NotAllowedWords.getInstance().getSet();
	
	@OneToMany(mappedBy = "proposal", fetch = FetchType.EAGER)
	private Set<Comment> comments = new HashSet<>();	
	
	public Proposal(){}
	
	public Proposal(User user, String tit) {
		this.title = tit;
        this.comments = new HashSet<Comment>();
        this.notAllowedWords = NotAllowedWords.getInstance().getSet();
        this.minSupport = MinSupport.getInstance().getSupport();
        Association.Propose.link(user, this);
    }
	
	public Proposal(User user, String tit, String description){
		this.title = tit;
		this.description = description;
        this.comments = new HashSet<Comment>();
        this.notAllowedWords = NotAllowedWords.getInstance().getSet();
        this.minSupport = MinSupport.getInstance().getSupport();
        Association.Propose.link(user, this);
	}
	
	public Proposal(User user, String tit, String desc, String topic){
        this.title = tit;
        this.description = desc;
		setTopicAux(topic);
		this.minSupport = MinSupport.getInstance().getSupport();
		this.created = new Date();
		this.comments = new HashSet<Comment>();
        this.notAllowedWords = NotAllowedWords.getInstance().getSet();
        Association.Propose.link(user, this);
	}
	
	public Proposal(User user, String tit, String desc, String topic, int minSupport, Set<String> l){
        this.title = tit;
        this.description = desc;
        setTopicAux(topic);
        this.created = new Date();
        this.comments = new HashSet<Comment>();
		this.minSupport = minSupport;
        this.notAllowedWords = l;
        Association.Propose.link(user, this);
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

	public void setTitle(String title) {
		this.title = title;
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

	public String getTopicAux() {
		return topicAux;
	}

	public void setTopicAux(String topicAux) {
		this.topicAux = topicAux;
		if( topicAux == "POLITICS" )
			this.topic = Topic.POLITICS;
		if( topicAux == "HEALTHCARE" ) 
			this.topic = Topic.HEALTHCARE;
		if( topicAux == "SECURITY" )
			this.topic = Topic.SECURITY;
		if( topicAux == "SPORTS" )
			this.topic = Topic.SPORTS;
	}
	
}
