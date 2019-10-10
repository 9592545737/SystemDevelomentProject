package application;

public class InventoryTable {

	String product_ID, type, name, company, model, serial, quantity, comments;

	public InventoryTable(String product_ID, String type, String name, String company, String model, String serial,
			String quantity, String comments) {
		super();
		this.product_ID = product_ID;
		this.type = type;
		this.name = name;
		this.company = company;
		this.model = model;
		this.serial = serial;
		this.quantity = quantity;
		this.comments = comments;
	}

	public String getProduct_ID() {
		return product_ID;
	}

	public void setProduct_ID(String product_ID) {
		this.product_ID = product_ID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}