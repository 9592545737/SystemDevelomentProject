package application;

public class UserManagementTable {
	String userName, Pass, fName, lName, email, ph_1, ph_2, stAddress, ave, city;

	public UserManagementTable(String userName, String Pass, String fName, String lName, String email, String ph_1,
			String ph_2, String stAddress, String ave,  String city) {
		super();
		this.userName = userName;
		this.Pass = Pass;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.ph_1 = ph_1;
		this.ph_2 = ph_2;
		this.stAddress = stAddress;
		this.ave = ave;
		this.city = city;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String Pass) {
		this.Pass = Pass;
	}

	public String getFName() {
		return fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPh_1() {
		return ph_1;
	}

	public void setPh_1(String ph_1) {
		this.ph_1 = ph_1;
	}

	public String getPh_2() {
		return ph_2;
	}

	public void setPh_2(String ph_2) {
		this.ph_2 = ph_2;
	}

	public String getStAddress() {
		return stAddress;
	}

	public void setStAddress(String stAddress) {
		this.stAddress = stAddress;
	}

	public String getAve() {
		return ave;
	}

	public void setAve(String ave) {
		this.ave = ave;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}