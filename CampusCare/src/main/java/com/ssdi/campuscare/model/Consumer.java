package com.ssdi.campuscare.model;

public class Consumer implements IConsumer {

	private int consumerId;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	public Consumer() {

	}

	public Consumer(String userName, String firstName, String lastName, String email, String password) {
		this.consumerId = -1;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public Consumer(int consumerId, String userName, String firstName, String lastName, String email, String password) {
		this.consumerId = consumerId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public int getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(int consumerId) {
		this.consumerId = consumerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object rhs) {
		Consumer rhs_actual = (Consumer) rhs;
		System.out.println("In Consumer::equals");
		if(rhs_actual.userName.equals(this.userName)  && 
		   rhs_actual.email.equals( this.email))  
		{
			System.out.println("In Consumer::equals : inside if statement when true");	
			return true;
		}
		else
		{
			System.out.println("In Consumer::equals : inside if statement when false");
			return false;
		}
			
	}
	
}
