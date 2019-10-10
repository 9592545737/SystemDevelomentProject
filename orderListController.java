package application;

import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.stage.Stage;

public class orderListController {
	
	// Configure the TableView columns
	@FXML private TextField searchBox;
	@FXML private TableView<orderListTable> table;
	@FXML private TableColumn<orderListTable, String> o_orderID;
	@FXML private TableColumn<orderListTable, String> o_clientID;
	@FXML private TableColumn<orderListTable, String> o_firstName;
	@FXML private TableColumn<orderListTable, String> o_lastName;
	@FXML private TableColumn<orderListTable, String> o_dateIn;
	@FXML private TableColumn<orderListTable, String> o_deadline;
	@FXML private TableColumn<orderListTable, String> o_email;
	@FXML private TableColumn<orderListTable, String> o_phone;
	@FXML private TableColumn<orderListTable, String> o_paid;
	@FXML Label userLabel;
	
	ObservableList<orderListTable> olTable = FXCollections.observableArrayList();
	
	CConnection con = new CConnection();
	
	public void initialize() {
//		------------------------------------------------------------------------------------------------------------------
//		------------------------------------------------------------------------------------------------------------------
//		 This code will allow to load data from database and view into the tableview
			
		userLabel.setText(CurrentUser.getUser());
			
		try {
			Statement stmt=con.createConnection();
			String sqlQuery = "SELECT o.order_ID, c.client_ID, c.fName, c.lName, o.date_in, o.date_out, c.email, c.ph_1, o.PaymentStatus FROM cclient c, orders o WHERE c.client_ID = o.f_client_ID";
			ResultSet rs=stmt.executeQuery(sqlQuery);
			
			while (rs.next()) {
				olTable.add(new orderListTable(rs.getString("order_ID"), rs.getString("client_ID"),rs.getString("fName"), rs.getString("lName"), rs.getString("date_in"), 
						rs.getString("date_out"), rs.getString("email"), rs.getString("ph_1"), rs.getString("PaymentStatus")));
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		
		// Initialize Columns
		o_orderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
		o_clientID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
		o_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		o_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		o_dateIn.setCellValueFactory(new PropertyValueFactory<>("dateIn"));
		o_deadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
		o_email.setCellValueFactory(new PropertyValueFactory<>("email"));
		o_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		o_paid.setCellValueFactory(new PropertyValueFactory<>("paid"));		
		
		// 1. Wrapping the ObservableList in a FilteredList (initially display all data).
		FilteredList<orderListTable> filteredData = new FilteredList<>(olTable, p -> true);
		
        // 2. Set the filter Predicate whenever the filter changes.
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare items with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (item.getOrderID().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (item.getClientID().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (item.getPhone().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (item.getPaid().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (item.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;                    
                } else if (item.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (item.getDateIn().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (item.getDeadline().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (item.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<orderListTable> sortedData = new SortedList<>(filteredData);
		
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
       
		table.setItems(sortedData);	
	}

//	------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------------------------------------------------------------
//	 This code will allow to Edit Orders.
		@FXML
		private void EditOrderTable(ActionEvent ev) {
			try {
				Parent parent=FXMLLoader.load(getClass().getResource("EditTable.fxml"));
				Scene TableScene=new Scene(parent);
				Stage EditTableStage=(Stage)((Node)ev.getSource()).getScene().getWindow();
				EditTableStage.setScene(TableScene);
				EditTableStage.setTitle("Edit Order Table");
				
				}
				
				catch(Exception e) {
					e.printStackTrace();
				}
		}
			
//		------------------------------------------------------------------------------------------------------------------
//		------------------------------------------------------------------------------------------------------------------
//		 This code will allow to load Back Page.

	public void backBtnClicked(ActionEvent ev) {
		try {
			Parent parent=FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			Scene Dashboard=new Scene(parent);
			Stage dashBoardStage=(Stage)((Node)ev.getSource()).getScene().getWindow();
			dashBoardStage.setScene(Dashboard);
			dashBoardStage.setTitle("Dashboard");
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