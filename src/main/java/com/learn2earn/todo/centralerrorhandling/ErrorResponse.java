package com.learn2earn.todo.centralerrorhandling;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {

	public ErrorResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
	}

	//General error message about nature of error
	private String message;

	//Specific errors in API request processing
	private List<String> details;

	private String companyName = "Learn2EarnHub.inc";

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date errorDate = new Date();

	//Getters and setters

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getErrorDate() {
		return errorDate;
	}

	public void setErrorDate(Date errorDate) {
		this.errorDate = errorDate;
	}

	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + ", details=" + details + ", companyName=" + companyName
				+ ", errorDate=" + errorDate + "]";
	}

}
