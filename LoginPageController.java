package application;

import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginPageController {
	
	@FXML TextField user_ID;
	@FXML PasswordField pass;
	@FXML Label loginLabel;
	
	CurrentUser cUser = new CurrentUser();
	
	CConnection con=new CConnection();

	public void LoginButtonClicked(ActionEvent ev) {
		try {
			Statement stmt=con.createConnection();
			String sqlQuery="Select * from employee WHERE userName='"+user_ID.getText()+"' AND Pass ='"+pass.getText().toString()+"'";
			
			ResultSet rs=stmt.executeQuery(sqlQuery);
			
			if(rs.next()) {
				
				CurrentUser.setUser(rs.getString("fName") + " " + rs.getString("lName") + " |");		// Setting firstname lastname to CurrentUser class
				CurrentUser.setUserID(rs.getString("userName"));
				
				Parent parent=FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
				Scene Dashboard=new Scene(parent);
				Stage dashBoardStage=(Stage)((Node)ev.getSource()).getScene().getWindow();
				dashBoardStage.setScene(Dashboard);
				dashBoardStage.setTitle("Dashboard");
				
			}
			else {
				loginLabel.setTextFill(Color.RED);
				loginLabel.setText("LOGIN FAILED!");
				System.out.println("Login failed"); 
			}
		}
		catch(Exception e) { 
			System.out.println("Error in SQL");
			e.printStackTrace();
		}
	}
	
	
	public void AdminLoginButtonClicked(ActionEvent ev) {
		try {
			Statement stmt=con.createConnection();
			String sqlQuery="Select * from admin WHERE user_ID='"+user_ID.getText()+"' AND pass ='"+pass.getText().toString()+"'";
			ResultSet rs=stmt.executeQuery(sqlQuery);
			if(rs.next()) {
				
				CurrentUser.setUser("+ " + rs.getString("fName") + " " + rs.getString("lName") + " |");		// Setting firstname lastname to CurrentUser class
				CurrentUser.setUserID(rs.getString("user_ID"));
				
				Parent parent=FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
				Scene AdminDashboard=new Scene(parent);
				Stage dashBoardStage=(Stage)((Node)ev.getSource()).getScene().getWindow();
				dashBoardStage.setScene(AdminDashboard);
				dashBoardStage.setTitle("Administrator Dashboard");
				
			}
			else {
				loginLabel.setTextFill(Color.RED);
				loginLabel.setText("LOGIN FAILED!");
				System.out.println("Login failed"); 
			}
		} 
		catch(Exception e) {
			System.out.println("Error in SQL");
			e.printStackTrace();
		}
	}
	
	public void ClearButtonClicked() {
		user_ID.clear();
		pass.clear();
		loginLabel.setTextFill(Color.rgb(25, 45, 75));
		loginLabel.setText("SKYNET LOGIN");
	}
}