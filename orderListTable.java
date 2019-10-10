package application;

public class orderListTable {
	
	String orderID, clientID, firstName, lastName, dateIn, deadline, email, phone, paid;
	
	public orderListTable(String orderID, String clientID, String firstName, String lastName, String dateIn, String deadline, String email, String phone, String paid) {
		this.orderID = orderID;
		this.clientID = clientID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateIn = dateIn;
		this.deadline = deadline;
		this.email = email;
		this.phone = phone;
		this.paid = paid;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateIn() {
		return dateIn;
	}

	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}