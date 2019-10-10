package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminDashboardController {
	
	CConnection conn = new CConnection();
	
	@FXML Label userLabel;
	public void initialize() {
		userLabel.setText(CurrentUser.getUser());
	}
	
	
	public void OrderFormBtnClicked(ActionEvent ev) {
		try {
			Parent parent =FXMLLoader.load(getClass().getResource("AdminOrder_Form.fxml"));
			Scene orderForm = new Scene(parent);
			Stage dashBoardStage =(Stage)((Node)ev.getSource()).getScene().getWindow();
			dashBoardStage.setScene(orderForm);
			dashBoardStage.setTitle("Create Order");
		}
		catch(Exception e){
			e.printStackTrace();;
		}
	}
	
	public void orderListBtnClicked(ActionEvent ev) {
		try {
			Parent parent =FXMLLoader.load(getClass().getResource("AdminOrderList.fxml"));
			Scene orderList = new Scene(parent);
			Stage dashBoardStage =(Stage)((Node)ev.getSource()).getScene().getWindow();
			dashBoardStage.setScene(orderList);
			dashBoardStage.setTitle("Order List");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void inventoryBtnClicked(ActionEvent ev) {
		try {
			Parent parent =FXMLLoader.load(getClass().getResource("AdminInventory.fxml"));
			Scene inventoryList = new Scene(parent);
			Stage dashBoardStage =(Stage)((Node)ev.getSource()).getScene().getWindow();
			dashBoardStage.setScene(inventoryList);
			dashBoardStage.setTitle("Inventory Management");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void TasksBtnClicked(ActionEvent ev) {
		try {
			Parent parent =FXMLLoader.load(getClass().getResource("AdminTasks.fxml"));
			Scene tasksList = new Scene(parent);
			Stage dashBoardStage =(Stage)((Node)ev.getSource()).getScene().getWindow();
			dashBoardStage.setScene(tasksList);
			dashBoardStage.setTitle("Tasks");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
	public void logoutBtnClicked(ActionEvent ev) {
		try {
		Parent parent=FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene LoginScene=new Scene(parent);
		Stage dashBoardStage=(Stage)((Node)ev.getSource()).getScene().getWindow();
		dashBoardStage.setScene(LoginScene);
		dashBoardStage.setTitle("Login");
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void userBtnClicked(ActionEvent ev) {
		try {
			Parent parent =FXMLLoader.load(getClass().getResource("UserManagement.fxml"));
			Scene UserManagement = new Scene(parent);
			Stage dashBoardStage =(Stage)((Node)ev.getSource()).getScene().getWindow();
			dashBoardStage.setScene(UserManagement);
			dashBoardStage.setTitle("User Management");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}