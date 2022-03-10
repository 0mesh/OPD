package beans.web.model;

import java.sql.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "Patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
	@GenericGenerator(name = "book_seq", strategy = "beans.web.model.PatientIdGenerator")
	@Column(name = "PatientId")
	private String patientId;
	@Column(name = "FirstName", length = 30, nullable = false)
	private String firstName;
	@Column(name = "LastName", length = 30, nullable = false)
	private String lastName;
	@Column(name = "Address", nullable = false)
	private String address;
	@Column(name = "Email", nullable = false)
	private String email;
	@Column(name = "PhoneNumber", nullable = false)
	private String phoneNumber;

	@Column(name = "Gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column(name = "City", length = 30, nullable = false)
	private String city;
	@Column(name = "State", length = 30, nullable = false)
	private String state;
	@Column(name = "DateOfBirth", nullable = false)
	private Date dateOfBirth;
	@Column(name = "SSN", nullable = false)
	private String ssn;
	
	

	public Patient() {
		super();
	}
	
	
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws NullPointerException, IllegalArgumentException {
		if (validateFirstName(firstName).equals("Valid"))
			this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws NullPointerException, IllegalArgumentException {
		if (validateLastName(lastName).equals("Valid"))
			this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws NullPointerException {
		try {
			this.address = address;
		} catch (NullPointerException e) {
			throw new NullPointerException("Address cannot be empty");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws NullPointerException, IllegalArgumentException {
		if (validateEmail(email).equals("Valid"))
			this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {

		if (validatePhoneNumber(phoneNumber).equals("Valid")) {
			this.phoneNumber = phoneNumber;
		}
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {

		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) throws NullPointerException, IllegalArgumentException {
		if (validateCity(city).equals("Valid"))
			this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) throws NullPointerException, IllegalArgumentException {
		if (validateState(state).equals("Valid"))
			this.state = state;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String sSN) throws NullPointerException, IllegalArgumentException {
		if (validateSSN(sSN).equals("Valid"))
			this.ssn = sSN;
	}

	private String validateFirstName(String str) throws NullPointerException {

		try {
			if (validate(str, 5)) {
				return "Valid";
			} else {
				throw new IllegalArgumentException("First Name should be atleast 5 characters long");
			}
		} catch (NullPointerException e) {
			throw new NullPointerException("First Name cannot be empty");
		}
	}

	private String validateLastName(String str) throws NullPointerException, IllegalArgumentException {

		try {
			if (validate(str, 5)) {
				return "Valid";
			} else {
				throw new IllegalArgumentException("Last Name should be atleast 5 characters long");
			}
		} catch (NullPointerException e) {
			throw new NullPointerException("Last Name cannot be empty");
		}
	}

	private String validateCity(String str) throws NullPointerException {

		try {
			if (validate(str, 3)) {
				return "Valid";
			} else {
				throw new IllegalArgumentException("City name should be atleast 3 characters long");
			}
		} catch (NullPointerException e) {
			throw new NullPointerException("City cannot be empty");
		}
	}

	private String validateState(String str) throws NullPointerException {

		try {
			if (validate(str, 3)) {
				return "Valid";
			} else {
				throw new IllegalArgumentException("State name should be atleast 3 characters long");
			}
		} catch (NullPointerException e) {
			throw new NullPointerException("State cannot be empty");
		}
	}

	private boolean validate(String str, int minlength) throws NullPointerException {
		return str.length() >= minlength;
	}

	private String validateEmail(String email) throws NullPointerException {
		try {
			String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				return "Valid";
			} else {
				throw new IllegalArgumentException("Email address is invalid");
			}
		} catch (NullPointerException e) {
			throw new NullPointerException("Email cannot be empty");
		}
	}

	private String validatePhoneNumber(String number) throws NullPointerException, IllegalArgumentException {
		StringBuilder sb = new StringBuilder();
		try {
			if (!(number.length() == 10 || number.length() == 12)) {
				throw new IllegalArgumentException(
						"Phone number is short. Example – 1234567890 / 123-456-7890 are valid");
			}
			if (number.length() == 12) {
				int numHyphen = 0;
				for (int i = 0; i < 12; i++) {
					char c = number.charAt(i);
					if (!(isCharNumber(c) || c == '-')) {
						throw new IllegalArgumentException(
								"Phone number should contain numbers. Example – 1234567890 / 123-456-7890 are valid");
					}
					if (c == '-') {
						numHyphen++;
					}
				}
				if (numHyphen == 2) {
					return "Valid";
				} else {
					throw new IllegalArgumentException("Invalid format. Example – 1234567890 / 123-456-7890 are valid");
				}
			} else {
				for (int i = 0; i < 10; i++) {
					char c = number.charAt(i);
					if (!isCharNumber(c)) {
						throw new IllegalArgumentException(
								"Phone number should contain numbers. Example – 1234567890 / 123-456-7890 are valid");
					}
				}
				return "Valid";
			}
		} catch (NullPointerException e) {
			throw new NullPointerException("Phone cannot be empty");
		}
	}

	private boolean isCharNumber(char c) {

		if (c >= '0' && c <= '9') {
			return true;
		}
		return false;
	}

	private String validateSSN(String SSN) throws NullPointerException, IllegalArgumentException {
		String regex = "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$";
		Pattern pattern = Pattern.compile(regex);
		try {
			Matcher matcher = pattern.matcher(SSN);
			if (matcher.matches()) {
				return "Valid";
			} else {
				throw new IllegalArgumentException("SSN is invalid");
			}
		} catch (NullPointerException e) {
			throw new NullPointerException("SSN cannot be null");
		}
	}


	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
				+ address + ", email=" + email + ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", city="
				+ city + ", state=" + state + ", dateOfBirth=" + dateOfBirth + ", ssn=" + ssn + "]";
	}


	

}
