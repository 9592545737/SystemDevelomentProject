package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.prism.paint.Color;

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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
public class TaskPageController {

	@FXML private TextField searchBox;
	@FXML private TableView<TaskTable> taskTable;
	@FXML private TableColumn<TaskTable, String> t_orderID;
	@FXML private TableColumn<TaskTable, String> t_clientID;
	@FXML private TableColumn<TaskTable, String>  t_lName;
	@FXML private TableColumn<TaskTable, String> t_deadline;
	@FXML private TableColumn<TaskTable, String> t_payment;
	@FXML private TableColumn<TaskTable, String> t_comments;
	@FXML private TableColumn<TaskTable, String> t_order_Status;
	@FXML Label userLabel;
	
	@FXML ProgressBar pb;
   
		ObservableList<TaskTable> tTable = FXCollections.observableArrayList();
		
		CConnection con = new CConnection();
//		static String status = "Completed..";
//		static String status1 = "Pending..";
		static String  UserNameValue ="";
		static String status_for_color="";
		
	@FXML
		public void initialize() {
		
		userLabel.setText(CurrentUser.getUser());		// This gets the first name last name string from CurrentUser class
		
		
		taskTable.setEditable(true);
		try {
			Statement stmt=con.createConnection();
			String sqlQuery = "SELECT o.order_ID, c.client_ID, c.lName, o.date_out, o.PaymentStatus , e.comments,  o.order_Status FROM cclient c, orders o, equipment e WHERE c.client_ID = o.f_client_ID AND c.client_ID =  e.f_client_ID";
			ResultSet rs=stmt.executeQuery(sqlQuery);
			while (rs.next()) {
						tTable.add(new TaskTable(rs.getString("order_ID"), rs.getString("client_ID"),rs.getString("lName"), rs.getString("date_out"), rs.getString("PaymentStatus"), rs.getString("comments"), rs.getString("order_Status")));
			}
			
			}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}

			// Initialize Columns
			t_orderID.setCellValueFactory(new PropertyValueFactory<>("order_ID"));
			t_clientID.setCellValueFactory(new PropertyValueFactory<>("client_ID"));
			t_lName.setCellValueFactory(new PropertyValueFactory<>("lName"));
			t_deadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
			t_payment.setCellValueFactory(new PropertyValueFactory<>("payment"));
			t_comments.setCellValueFactory(new PropertyValueFactory<>("comments"));
			t_order_Status.setCellValueFactory(new PropertyValueFactory<>("order_Status"));

			// 1. Wrapping the ObservableList in a FilteredList (initially display all data).
			FilteredList<TaskTable> filteredData = new FilteredList<>(tTable, p -> true);
			
			
	        // 2. Set the filter Predicate whenever the filter changes.
	        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
	            filteredData.setPredicate(item -> {
	                // If filter text is empty, display all persons.
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }
	                
	                // Compare items with filter text.
	                String lowerCaseFilter = newValue.toLowerCase();
	                
	                if (item.getOrder_ID().toLowerCase().contains(lowerCaseFilter)) {
	                    return true; 
	                } else if (item.getClient_ID().toLowerCase().contains(lowerCaseFilter)) {
	                    return true; 
	                } else if (item.getLName().toLowerCase().contains(lowerCaseFilter)) {
	                    return true; 
	                } else if (item.getDeadline().toLowerCase().contains(lowerCaseFilter)) {
	                    return true;
	                } else if (item.getPayment().toLowerCase().contains(lowerCaseFilter)) {
	                    return true;    
	                } else if (item.getComments().toLowerCase().contains(lowerCaseFilter)) {
	                    return true;  
	                } else if (item.getOrder_Status().toLowerCase().contains(lowerCaseFilter)) {
		                    return true;  
		                 
	                }
	                return false; // Does not match.
	            });
	        });
	        
	        
	        t_order_Status.setCellFactory(TextFieldTableCell.forTableColumn());
	      
	        
	        // 3. Wrap the FilteredList in a SortedList. 
	        SortedList<TaskTable> sortedData = new SortedList<>(filteredData);
			
	        // 4. Bind the SortedList comparator to the TableView comparator.
	        sortedData.comparatorProperty().bind(taskTable.comparatorProperty());
	        
			
			taskTable.setItems(sortedData);
			
			taskTable.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> {
					UserNameValue = newValue.getClient_ID();
						} );
			
		
			
			t_order_Status.setOnEditCommit(event -> {
				TaskTable status = event.getRowValue();
				status.setOrder_Status(event.getNewValue());
			    update_orderStatus_Data("order_Status", event.getNewValue(), status.getOrder_Status());
			    
			});
			
			taskTable.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> {
					status_for_color = newValue.getOrder_Status();
						} );
			if(status_for_color.contains("Pending")) {
				
			}
//			
//			taskTable.setRowFactory(tv -> new TableRow<taskTable>() {
//			    @Override
//			    public void updateItem(FaDeal item, boolean empty) {
//			        super.updateItem(item, empty) ;
//			        if (item == null) {
//			            setStyle("");
//			        } else if (item.getInstrumentId().equals("1070")) {
//			            setStyle("-fx-background-color: tomato;");
//			        } else {
//			            setStyle("");
//			        }
//			    }
//			});
			// Create the boolean column
		
//			 	TableColumn<t_order_Status, Boolean> yourBooleanColumn;
//			// Set a custom cell factory for Boolean column
//			 	t_order_Status.setCellFactory(col -> {
//			    TableCell<t_order_Status, Boolean> cell = new TableCell<t_order_Status, Boolean>() {
//			        @Override
//			        public void updateItem(Boolean item, boolean empty) {
//			            super.updateItem(item, empty);
//			 
//			            if (item.equals(status_for_color)) {
//			                this.setBackgroundColor(Color.RED);
//			            } else {
//			                this.setBackgroundColor(Color.GREEN);
//			            }
//			        }
//			    };
//			    return cell;
//			});
			
		
			
					        
// ---------------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------------	
			
	}

			

		private void update_orderStatus_Data(String col, String newValue, String id) {
		// TODO Auto-generated method stub
			Statement stmt=con.createConnection();
			String sqlQuery = "UPDATE orders SET " + col + " = '" + newValue + "' WHERE f_client_ID = '" + UserNameValue + "' ";
			try {
				int  rs = stmt.executeUpdate(sqlQuery);
				if(rs ==1) {
					System.out.println("Update Sucessfull");
				}
				else {
					System.out.println("Update Failed");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}



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
		} }
		
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
