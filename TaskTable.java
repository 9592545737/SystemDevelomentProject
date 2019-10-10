package application;

public class TaskTable {
	
	private String order_ID, client_ID, lName, payment, comments, deadline, order_Status;

	 public TaskTable(String order_ID, String client_ID, String lName, String deadline, String payment, String comments, String order_Status) {
		this.order_ID = order_ID;
		this.client_ID = client_ID;
		this.lName = lName;
		this.deadline = deadline;
		this.payment = payment;
		this.comments =comments;
		this.order_Status =order_Status;
	}

	public String getOrder_ID() {
		return order_ID;
	}

	public void setOrder_ID(String order_ID) {
		this.order_ID = order_ID;
	}

	public String getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}

	public String getLName() {
		return lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	public String getComments() {
		return comments;
	}

	public String getOrder_Status() {
		return order_Status;
	}


	public void setOrder_Status(String order_Status) {
		this.order_Status = order_Status;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}
}
