package com.learn2earn.todo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="task")
public class ToDo {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@JsonView(View.dwarf.class)
	private int id;
	
	@NotEmpty(message = "Username cannot be null (or) empty.")
	@Size(min=3, max= 10, message = "Username should be minimum of 3 letters.")
	@JsonView(View.dwarf.class)
	private String username;
	
	@NotEmpty(message = "Description cannot be null (or) empty.")
	@Size(max= 255, message = "Description should be maximum of 255 letters.")
	@JsonView(View.dwarf.class)
	private String description;
	
	@AssertFalse(message = "Sholuld be false. No reason for creating todo.")
	private boolean completed;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") // Formats for displaying or serializing.
	@Future(message = "Target date must be Future Date.")
	private Date targetDate;
	
	@Transient // Will not be persisted to Database.
	@JsonIgnore //Ignores this field while serializing and de-serializing.
	// This field in this POJO will be used by some other program which uses it.
	private String email = "companyname@gmail.com";
	
	public ToDo() {}
	public ToDo(int id, String username, String description, boolean completed, Date targetDate, String email) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.completed = completed;
		this.targetDate = targetDate;
		this.email = email;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "ToDo [id=" + id + ", username=" + username + ", description=" + description + ", completed=" + completed
				+ ", targetDate=" + targetDate + ", email=" + email + "]";
	}

}