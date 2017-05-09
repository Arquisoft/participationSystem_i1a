package asw.model.impl;

import java.util.Date;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TUsers")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	private Date dateOfBirth;

	private String address;
	private String nationality;
	
	private boolean isAdmin;
	
	@Column(unique = true)
	
	private String identification;
	
	private String password;
	private String login;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Proposal> proposals = new HashSet<Proposal>();
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	private Set<Vote> votes = new HashSet<Vote>();

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Comment> comments = new HashSet<Comment>();
	
	public User(){}

	public User(String identification) {
		super();
		this.identification = identification;
	}

	public User(String firstName, String lastName, String email,
			Date dateOfBirth, String address, String nationality,
			String identification) {
		this(firstName, lastName, email, address, nationality,
				identification);
		setDateOfBirth(dateOfBirth);
	}

	public User(String firstName, String lastName, String email,
			String Address, String Nationality,
			String identification) {

		this(identification);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = Address;
		this.nationality = Nationality;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getIdentification() {
		return identification;
	}
	
	public boolean isAdmin(){
		return isAdmin;
	}
	
	public void setAdmin(boolean admin){
		this.isAdmin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identification == null) ? 0
				: identification.hashCode());
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
		User other = (User) obj;
		if (identification == null) {
			if (other.identification != null)
				return false;
		} else if (!identification.equals(other.identification))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName="
				+ lastName + ", Email=" + email + ", dateOfBirth="
				+ dateOfBirth + ", Address=" + address
				+ ", Nationality=" + nationality + ", Identification="
				+ identification 
				+ ", isAdmin= "+ isAdmin + "]";
	}
	
	public Set<Proposal> getProposals() {
		return new HashSet<Proposal>(proposals);
	}

	Set<Proposal> _getProposals() {
		return proposals;
	}

	public Set<Comment> getComments() {
		return new HashSet<Comment>(comments);
	}

	Set<Comment> _getComments() {
		return comments;
	}

	Set<Vote> _getVotes() {
		return votes;
	}
	
	public Set<Vote> getVotes() {
		return new HashSet<Vote>(votes);
	}
	
	public void propose(Proposal p) {
		Association.Propose.link(this, p);
	}
	
	public void comment(Proposal proposal, Comment comment) {
		Association.MakeComment.link(this, comment, proposal);
	}
	
	public void deleteProposal(Proposal proposal) {
		Association.Propose.unlink(this, proposal);
	}
	
	public void deleteComment(Proposal proposal, Comment comment) {
		Association.MakeComment.unlink(this, comment, proposal);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
