package application;

public class eTable {
	String  Client_ID, FName, LName, Email, Phone_H, Phone_C,  St_No, Ave, PCode, City, Accessories, Comments;

	public eTable(String client_ID, String fName, String lName, String email, String phone_H, String phone_C, String st_No,
			String ave,String pCode, String city, String accessories, String comments ) {
		Client_ID = client_ID;
		FName = fName;
		LName = lName;
		Email = email;
		Phone_H = phone_H;
		Phone_C = phone_C;
		St_No = st_No;
		Ave = ave;
		PCode = pCode;
		City = city;
		Accessories = accessories;
		Comments = comments;
	}

	public String getFName() {
		return FName;
	}

	public void setFName(String fName) {
		FName = fName;
	}

	public String getLName() {
		return LName;
	}

	public void setLName(String lName) {
		LName = lName;
	}

	public String getPhone_C() {
		return Phone_C;
	}

	public void setPhone_C(String phone_C) {
		Phone_C = phone_C;
	}

	public String getPhone_H() {
		return Phone_H;
	}

	public void setPhone_H(String phone_H) {
		Phone_H = phone_H;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPCode() {
		return PCode;
	}

	public void setPCode(String pCode) {
		PCode = pCode;
	}

	public String getSt_No() {
		return St_No;
	}

	public void setSt_No(String st_No) {
		St_No = st_No;
	}

	public String getAve() {
		return Ave;
	}

	public void setAve(String ave) {
		Ave = ave;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getAccessories() {
		return Accessories;
	}

	public void setAccessories(String accessories) {
		Accessories = accessories;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	public String getClient_ID() {
		return Client_ID;
	}

	public void setClient_ID(String client_ID) {
		Client_ID = client_ID;
	}  

}
