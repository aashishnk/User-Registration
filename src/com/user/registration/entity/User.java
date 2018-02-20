package com.user.registration.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.user.registration.validations.UniqueMailId;

@Entity
@Table(name="user_data")
public class User {
	
	public interface userLoginPage{		
	}
	
	public interface userRegistrationPage{		
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="userId_seq")
	@SequenceGenerator(name="userId_seq", sequenceName="user_data_seq", allocationSize=1)
	@Column(name="user_id")
	private int userId;
	
	@NotNull(message="is requiered", groups = {userRegistrationPage.class})
	@Pattern(regexp="[a-zA-z]+",message="Please enter a valid name", groups = {userRegistrationPage.class})
	@Column(name="first_name")
	private String firstName;
	
	@NotNull(message="is requiered", groups = {userRegistrationPage.class})
	@Pattern(regexp="[a-zA-z]+",message="Please enter a valid name", groups = {userRegistrationPage.class})
	@Column(name="last_name")
	private String lastName;
	
	@NotNull(message="is requiered", groups = {userLoginPage.class, userRegistrationPage.class})
	@Pattern(regexp="[a-zA-Z0-9_]+@{1}[a-zA-Z]+.{1}[a-zA-Z]+.?[a-zA-Z]*",groups = {userLoginPage.class, userRegistrationPage.class}, message="Enter Mail-ID in Correct format")
	@UniqueMailId(groups = {userRegistrationPage.class})
	@Column(name="email_id")
	private String emailId;
	
	@NotNull(message="is requiered", groups = {userLoginPage.class, userRegistrationPage.class})
	@Size(min=3,max=8, message="Password length must be 3-8 characters", groups = {userLoginPage.class, userRegistrationPage.class})
	@Column(name="password")
	private String password;
	
	@Pattern(regexp="[0-9-]+",message="Please enter valid phone number", groups = {userRegistrationPage.class})
	@Size(min=6,max=10, message="Contact number must be 6-10 digits", groups = {userRegistrationPage.class})
	@Column(name="contact_number")
	private String contactNumber;
	
	@Column(name="Active_Flag")
	private int activeFlag;
	
	@Column(name="Status")
	private int status;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date", updatable=false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;
	
	public User() {
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", password=" + password + ", contactNumber=" + contactNumber + ", activeFlag=" + activeFlag
				+ ", status=" + status + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}


	
	

	
	

}
