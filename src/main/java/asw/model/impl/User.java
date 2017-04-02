package asw.model.impl;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

	private String firstName;
	private String lastName;
	private String Email;
	private Date dateOfBirth;

	private String Address;
	private String Nationality;
	private String identification;
	private String password;
	
	@Id private Long id;
	
	User(){}

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
	
	public User(String firstName, String pass){
		this.firstName = firstName;
		this.password = pass;
	}

	public User(String firstName, String lastName, String email,
			String Address, String Nationality,
			String identification) {

		this(identification);
		this.firstName = firstName;
		this.lastName = lastName;
		this.Email = email;
		this.Address = Address;
		this.Nationality = Nationality;

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
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getNationality() {
		return Nationality;
	}

	public void setNationality(String nationality) {
		Nationality = nationality;
	}

	public String getIdentification() {
		return identification;
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
				+ lastName + ", Email=" + Email + ", dateOfBirth="
				+ dateOfBirth + ", Address=" + Address
				+ ", Nationality=" + Nationality + ", Identification="
				+ identification + "]";
	}
	
}
