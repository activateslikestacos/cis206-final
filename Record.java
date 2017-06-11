import java.io.Serializable;

/**
 * This class holds all record data for a person
 * @author chris
 *
 */
public class Record implements Serializable {

	// Serializing ID!
	private static final long serialVersionUID = 1384785L;

	// All internal class data
	private String name,
				   address,
				   website,
				   eMail,
				   birthDay;

	// The constructor for building the class
	public Record(String name, String address, String website, String eMail, String birthDay) {
		this.name = name;
		this.address = address;
		this.website = website;
		this.eMail = eMail;
		this.birthDay = birthDay;
	}
	
	// Empty constructor
	public Record() {
		this.name = new String();
		this.address = new String();
		this.website = new String();
		this.eMail = new String();
		this.birthDay = new String();
	}
	
	// All of my setters for the class data (mutators)
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public void setEmail(String eMail) {
		this.eMail = eMail;
	}
	
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	
	// All of my getters for the class data (accessors)
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public String getEmail() {
		return eMail;
	}
	
	public String getBirthday() {
		return birthDay;
	}
	
}