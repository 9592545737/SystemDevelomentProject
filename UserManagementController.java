package application;

import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class UserManagementController {

	@FXML private TableView<UserManagementTable> table;
	@FXML private TableColumn<UserManagementTable, String> u_user_ID;
	@FXML private TableColumn<UserManagementTable, String> u_pass;
	@FXML private TableColumn<UserManagementTable, String> u_firstName;
	@FXML private TableColumn<UserManagementTable, String> u_lastName;
	@FXML private TableColumn<UserManagementTable, String> u_email;
	@FXML private TableColumn<UserManagementTable, String> u_ph1;
	@FXML private TableColumn<UserManagementTable, String> u_ph2;
	@FXML private TableColumn<UserManagementTable, String> u_st_No;
	@FXML private TableColumn<UserManagementTable, String> u_ave;
	@FXML private TableColumn<UserManagementTable, String> u_city;
	@FXML private TextField tf_user_ID;
	@FXML private TextField tf_pass;
	@FXML private TextField tf_firstName;
	@FXML private TextField tf_lastName;
	@FXML private TextField tf_email;
	@FXML private TextField tf_ph1;
	@FXML private TextField tf_ph2;
	@FXML private TextField tf_st_No;
	@FXML private TextField tf_ave;
	@FXML private TextField tf_city;
	
	ObservableList<UserManagementTable> uTable = FXCollections.observableArrayList();
	
	CConnection con = new CConnection();
	
	@FXML Label userLabel;
	
	static String userNameValue = "";
	
	public void initialize() {
		
		userLabel.setText(CurrentUser.getUser());		// This gets the first name last name string from CurrentUser class
		
		table.setEditable(true);
		
		// Adds items to observable list //
		// ObservableList object enables the tracking of any changes to its elements //
		// The TableView content automatically updates whenever the data changes //
		try {
			Statement stmt=con.createConnection();
			String sqlQuery = "SELECT `userName`, `Pass`, `fName`, `lName`, `email`, `ph_1`, `ph_2`, `stAddress`, `ave`, `city` FROM employee";
			ResultSet rs=stmt.executeQuery(sqlQuery);
			
			while (rs.next()) {
				uTable.add(new UserManagementTable(rs.getString("userName"), rs.getString("Pass"), rs.getString("fName"), rs.getString("lName"), rs.getString("email"), 
						rs.getString("ph_1"), rs.getString("ph_2"), rs.getString("stAddress"), rs.getString("ave"), rs.getString("city")));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		
		// Initialize Columns, associate data with table columns
		u_user_ID.setCellValueFactory(new PropertyValueFactory<>("userName"));
		
		u_pass.setCellValueFactory(new PropertyValueFactory<>("Pass"));
		u_pass.setCellFactory(TextFieldTableCell.forTableColumn());
		u_pass.setOnEditCommit(event -> {
		    UserManagementTable pass = event.getRowValue();
		    pass.setPass(event.getNewValue());
		    updateData("Pass", event.getNewValue());
		});
		
		u_firstName.setCellValueFactory(new PropertyValueFactory<>("fName"));
		u_firstName.setCellFactory(TextFieldTableCell.forTableColumn());
		u_firstName.setOnEditCommit(event -> {
		    UserManagementTable fName = event.getRowValue();
		    fName.setFName(event.getNewValue());
		    updateData("fName", event.getNewValue());
		});
		
		u_lastName.setCellValueFactory(new PropertyValueFactory<>("lName"));
		u_lastName.setCellFactory(TextFieldTableCell.forTableColumn());
		u_lastName.setOnEditCommit(event -> {
		    UserManagementTable lName = event.getRowValue();
		    lName.setLName(event.getNewValue());
		    updateData("lName", event.getNewValue());
		});
		
		
		u_email.setCellValueFactory(new PropertyValueFactory<>("email"));
		u_email.setCellFactory(TextFieldTableCell.forTableColumn());
		u_email.setOnEditCommit(event -> {
		    UserManagementTable email = event.getRowValue();
		    email.setEmail(event.getNewValue());
		    updateData("email", event.getNewValue());
		});
		
		u_ph1.setCellValueFactory(new PropertyValueFactory<>("ph_1"));
		u_ph1.setCellFactory(TextFieldTableCell.forTableColumn());
		u_ph1.setOnEditCommit(event -> {
		    UserManagementTable ph1 = event.getRowValue();
		    ph1.setPh_1(event.getNewValue());
		    updateData("ph_1", event.getNewValue());
		});
		
		u_ph2.setCellValueFactory(new PropertyValueFactory<>("ph_2"));
		u_ph2.setCellFactory(TextFieldTableCell.forTableColumn());
		u_ph2.setOnEditCommit(event -> {
		    UserManagementTable ph2 = event.getRowValue();
		    ph2.setPh_2(event.getNewValue());
		    updateData("ph_2", event.getNewValue());
		});
		
		u_st_No.setCellValueFactory(new PropertyValueFactory<>("stAddress"));
		u_st_No.setCellFactory(TextFieldTableCell.forTableColumn());
		u_st_No.setOnEditCommit(event -> {
		    UserManagementTable stNo = event.getRowValue();
		    stNo.setStAddress(event.getNewValue());
		    updateData("stAddress", event.getNewValue());
		});
		
		u_ave.setCellValueFactory(new PropertyValueFactory<>("ave"));
		u_ave.setCellFactory(TextFieldTableCell.forTableColumn());
		u_ave.setOnEditCommit(event -> {
		    UserManagementTable ave = event.getRowValue();
		    ave.setAve(event.getNewValue());
		    updateData("ave", event.getNewValue());
		});
		
		u_city.setCellValueFactory(new PropertyValueFactory<>("city"));
		u_city.setCellFactory(TextFieldTableCell.forTableColumn());
		u_city.setOnEditCommit(event -> {
		    UserManagementTable city = event.getRowValue();
		    city.setCity(event.getNewValue());
		    updateData("city", event.getNewValue());
		});
				
		// Add data to the table //
		table.setItems(uTable);
			
		table.getSelectionModel().selectedItemProperty().addListener(
			    (observable, oldValue, newValue) -> {
			    	userNameValue = newValue.getUserName();
			    }
			);		
		System.out.println(userNameValue);
	}

	// Updates cell values to SQL database //
	public void updateData(String column, String newValue) {
		try {
			Statement stmt4=con.createConnection();
			String sqlQuery4="UPDATE employee SET " + column + " = '" + newValue + "' WHERE userName = '" + userNameValue + "'";
		
			int rs4 = stmt4.executeUpdate(sqlQuery4);
			
			if (rs4 == 1) {
				System.out.println("Update Successful");
			} else {
				System.out.println("Update Failed");
			}
		} catch(Exception e) {e.printStackTrace();}
	}
	
	public void addBtnClicked(ActionEvent ev) {
		try {
			Statement stmt2=con.createConnection();
			String sqlQuery2 ="insert into employee ( userName, Pass, fName, lName, email, ph_1, ph_2, stAddress, ave, city, admin_ID )"
					+ "VALUES ('" + tf_user_ID.getText() + 
					"','" +	tf_pass.getText() + 
					"','"+ tf_firstName.getText() + 
					"','" +	tf_lastName.getText() + 
					"','" + tf_email.getText() + 
					"','" + tf_ph1.getText() + 
					"','" + tf_ph2.getText() + 
					"','" +	tf_st_No.getText() + 
					"','" + tf_ave.getText() + 
					"','" +	tf_city.getText() +
					"','" + CurrentUser.getUserID() + "')";
			
			
			int rs2= stmt2.executeUpdate(sqlQuery2);
			
			if(rs2 ==1) {
				System.out.println("Successfull");
				}
			else {
				System.out.println("Failed");
			}
			
				// Reloads Page //
				try {
					Parent parent =FXMLLoader.load(getClass().getResource("UserManagement.fxml"));
					Scene UserManagement = new Scene(parent);
					Stage dashBoardStage =(Stage)((Node)ev.getSource()).getScene().getWindow();
					dashBoardStage.setScene(UserManagement);
					dashBoardStage.setTitle("User Management");
				} catch(Exception e){ e.printStackTrace();	}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error Adding Items");
		}
		// Clear all text //
		tf_user_ID.clear();
		tf_pass.clear();
		tf_firstName.clear();
		tf_lastName.clear();
		tf_email.clear();
		tf_ph1.clear();
		tf_ph2.clear();
		tf_st_No.clear();
		tf_ave.clear();
		tf_city.clear();
	}
	
	public void deleteBtnClicked(ActionEvent ev) {
		try {

			
			Statement stmt3=con.createConnection();
			String sqlQuery3 = "DELETE FROM employee WHERE userName = '" + userNameValue + "'";
			int rs3 = stmt3.executeUpdate(sqlQuery3);

			if(rs3 ==1) {
				System.out.println("Successfully deleted");
				}
			else {
				System.out.println("Failed to delete");
			}
			
				// Reloads Page //
				try {
					Parent parent =FXMLLoader.load(getClass().getResource("UserManagement.fxml"));
					Scene UserManagement = new Scene(parent);
					Stage dashBoardStage =(Stage)((Node)ev.getSource()).getScene().getWindow();
					dashBoardStage.setScene(UserManagement);
					dashBoardStage.setTitle("User Management");
				} catch(Exception e){ e.printStackTrace(); }
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void backBtnClicked(ActionEvent ev) {
		try {
		Parent parent=FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
		Scene AdminDashboard=new Scene(parent);
		Stage dashBoardStage=(Stage)((Node)ev.getSource()).getScene().getWindow();
		dashBoardStage.setScene(AdminDashboard);
		dashBoardStage.setTitle("Administrator Dashboard");
		}
		catch(Exception e) {
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
}
