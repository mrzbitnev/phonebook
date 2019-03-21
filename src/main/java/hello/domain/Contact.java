package hello.domain;

import javax.persistence.*;

@Entity
@Table
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String contactField1;
	private String contactField2;
	private String contactField3;
	private String contactField4;

	protected Contact() {

	}

	public Contact(String firstName, String lastName, String phoneNumber,
				   String contactField1, String contactField2, String contactField3,
				   String contactField4) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.contactField1 = contactField1;
		this.contactField2 = contactField2;
		this.contactField3 = contactField3;
		this.contactField4 = contactField4;
	}

	public Long getId() {
		return id;
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

	public String getContactField1() {
		return contactField1;
	}

	public void setContactField1(String contactField1) {
		this.contactField1 = contactField1;
	}

	public String getContactField2() {
		return contactField2;
	}

	public void setContactField2(String contactField2) {
		this.contactField2 = contactField2;
	}

	public String getContactField3() {
		return contactField3;
	}

	public void setContactField3(String contactField3) {
		this.contactField3 = contactField3;
	}

	public String getContactField4() {
		return contactField4;
	}

	public void setContactField4(String contactField4) {
		this.contactField4 = contactField4;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Contact{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", contactField1='" + contactField1 + '\'' +
				", contactField2='" + contactField2 + '\'' +
				", contactField3='" + contactField3 + '\'' +
				", contactField4='" + contactField4 + '\'' +
				'}';
	}
}