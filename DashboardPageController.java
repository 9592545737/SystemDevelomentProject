package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardPageController {
	
	@FXML Label userLabel;
	CConnection conn = new CConnection();

	public void initialize() {
		userLabel.setText(CurrentUser.getUser());		// This gets the first name last name string from CurrentUser class
	}
	
	public void OrderFormBtnClicked(ActionEvent ev) {
		try {
			Parent parent =FXMLLoader.load(getClass().getResource("Order_Form.fxml"));
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
			Parent parent =FXMLLoader.load(getClass().getResource("orderList.fxml"));
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
			Parent parent =FXMLLoader.load(getClass().getResource("Inventory.fxml"));
			Scene inventoryList = new Scene(parent);
			Stage dashBoardStage =(Stage)((Node)ev.getSource()).getScene().getWindow();
			dashBoardStage.setScene(inventoryList);
			dashBoardStage.setTitle("Inventory");
			}
		catch(Exception e){
			e.printStackTrace();				
			}
	}
	public void TasksBtnClicked(ActionEvent ev) {
		try {
			Parent parent =FXMLLoader.load(getClass().getResource("Tasks.fxml"));
			Scene tasksList = new Scene(parent);
			Stage dashBoardStage =(Stage)((Node)ev.getSource()).getScene().getWindow();
			dashBoardStage.setScene(tasksList);
			dashBoardStage.setTitle("Tasks");
		}
		catch(Exception e){
			e.printStackTrace();				
		}
	}
		
	public void backBtnClicked(ActionEvent ev) {
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
}