package application;

public class CurrentUser {
	
	static String user = "";
	static String userID = "";

	public CurrentUser() {
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		CurrentUser.user = user;
	}
	
	public static String getUserID() {
		return userID;
	}
	
	public static void setUserID(String userID) {
		CurrentUser.userID = userID;
	}

}
