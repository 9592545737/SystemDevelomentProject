package application;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class AdminEditTableController  {
	
	@FXML private TableView<eTable> Edit_Order_Table;
	@FXML private TableColumn<eTable, String> e_FName;
	@FXML private TableColumn<eTable, String> e_client_ID;
    @FXML private TableColumn<eTable, String> e_Accessories;
    @FXML private TableColumn<eTable, String> e_Ave;
    @FXML private TableColumn<eTable, String> e_Phone_C;
    @FXML private TableColumn<eTable, String> e_City;
    @FXML private TableColumn<eTable, String> e_Email;
    @FXML private TableColumn<eTable, String> e_Phone_H;
    @FXML private TableColumn<eTable, String> e_LName;
    @FXML private TableColumn<eTable, String> e_PCode;
    @FXML private TableColumn<eTable, String> e_St_No;
    @FXML private TableColumn<eTable, String> e_Comments;
	@FXML Label userLabel;
	
	static String userNameValue=""; 
	
	ObservableList<eTable> olTable = FXCollections.observableArrayList();
	
	CConnection con = new CConnection();
	
	public void initialize() {
		
//		------------------------------------------------------------------------------------------------------------------
//		------------------------------------------------------------------------------------------------------------------
//		 This code will allow to load data from datatbase and view into the tableview
			
		userLabel.setText(CurrentUser.getUser());		// This gets the first name last name string from CurrentUser class
			
		Edit_Order_Table.setEditable(true);
		
		try {
			Statement stmt=con.createConnection();
			String sqlQuery = "SELECT c.client_ID, c.fName, c.lName, c.email, c.ph_1, c.ph_2, c.St_No, c.ave, c.P_Code, c.city, e.accessories, e.comments FROM cclient c, equipment e WHERE c.client_ID = e.f_client_ID";
			ResultSet rs=stmt.executeQuery(sqlQuery);
			
			
			while (rs.next()) {
				olTable.add(new eTable(rs.getString("client_ID"),rs.getString("fName"), rs.getString("lName"),rs.getString("email"), rs.getString("ph_1"), rs.getString("ph_2")
						,rs.getString("St_No"), rs.getString("ave"), rs.getString("P_Code"), rs.getString("city"), rs.getString("accessories"),rs.getString("comments")));
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		
		// Initialize Columns
		e_client_ID.setCellValueFactory(new PropertyValueFactory<>("Client_ID"));
		e_FName.setCellValueFactory(new PropertyValueFactory<>("FName"));
		e_LName.setCellValueFactory(new PropertyValueFactory<>("LName"));
		e_Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
		e_Phone_H.setCellValueFactory(new PropertyValueFactory<>("Phone_H"));
		e_Phone_C.setCellValueFactory(new PropertyValueFactory<>("Phone_C"));
		e_St_No.setCellValueFactory(new PropertyValueFactory<>("St_No"));
		e_Ave.setCellValueFactory(new PropertyValueFactory<>("Ave"));
		e_PCode.setCellValueFactory(new PropertyValueFactory<>("PCode"));	
		e_City.setCellValueFactory(new PropertyValueFactory<>("City"));		
		e_Accessories.setCellValueFactory(new PropertyValueFactory<>("Accessories"));		
		e_Comments.setCellValueFactory(new PropertyValueFactory<>("Comments"));		
			
		//Columns to Select Textfield for Editing
		e_FName.setCellFactory(TextFieldTableCell.forTableColumn());
		e_LName.setCellFactory(TextFieldTableCell.forTableColumn());
		e_Email.setCellFactory(TextFieldTableCell.forTableColumn());
		e_Phone_H.setCellFactory(TextFieldTableCell.forTableColumn());
		e_Phone_C.setCellFactory(TextFieldTableCell.forTableColumn());
		e_St_No.setCellFactory(TextFieldTableCell.forTableColumn());
		e_Ave.setCellFactory(TextFieldTableCell.forTableColumn());
		e_PCode.setCellFactory(TextFieldTableCell.forTableColumn());
		e_City.setCellFactory(TextFieldTableCell.forTableColumn());
		e_Accessories.setCellFactory(TextFieldTableCell.forTableColumn());
		e_Comments.setCellFactory(TextFieldTableCell.forTableColumn());
		
		Edit_Order_Table.setItems(olTable);
		
		Edit_Order_Table.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
				userNameValue = newValue.getClient_ID();
					} );
		
//		--------------------------------------------------------------------------------------------------------- 
//		---------------------------------------------------------------------------------------------------------
//		=========================================================================================================
//		To edit Tableview By clicking on a cell
//		=========================================================================================================
//		---------------------------------------------------------------------------------------------------------
//      ---------------------------------------------------------------------------------------------------------
		
		e_FName.setOnEditCommit(event -> {
			eTable fName = event.getRowValue();
		    fName.setFName(event.getNewValue());
		    update_Fname_Data("fName", event.getNewValue(), fName.getFName());
		    
		});
		
		e_LName.setOnEditCommit(event -> {
			eTable lName = event.getRowValue();
		    lName.setFName(event.getNewValue());
		    update_Lname_Data("lName", event.getNewValue(), lName.getLName());
		    
		});
		
		e_Email.setOnEditCommit(event -> {
			eTable email = event.getRowValue();
			email.setFName(event.getNewValue());
		    update_email_Data("email", event.getNewValue(), email.getEmail());
		    
		});
		
		e_Phone_H.setOnEditCommit(event -> {
			eTable ph_h = event.getRowValue();
			ph_h.setFName(event.getNewValue());
		    update_ph_h_Data("ph_1", event.getNewValue(), ph_h.getPhone_H());
		    
		});
		
		e_Phone_C.setOnEditCommit(event -> {
			eTable ph_c = event.getRowValue();
			ph_c.setFName(event.getNewValue());
		    update_ph_c_Data("ph_2", event.getNewValue(), ph_c.getPhone_C());
		    
		});
		
		e_St_No.setOnEditCommit(event -> {
			eTable st_no = event.getRowValue();
			st_no.setFName(event.getNewValue());
		    update_st_no_Data("St_No", event.getNewValue(), st_no.getSt_No());
		    
		});
		
		e_Ave.setOnEditCommit(event -> {
			eTable ave = event.getRowValue();
			ave.setFName(event.getNewValue());
		    update_ave_Data("ave", event.getNewValue(), ave.getAve());
		    
		});
		
		e_PCode.setOnEditCommit(event -> {
			eTable pCode = event.getRowValue();
			pCode.setFName(event.getNewValue());
		    update_pCode_Data("P_Code", event.getNewValue(), pCode.getPCode());
		    
		});
		
		e_City.setOnEditCommit(event -> {
			eTable city = event.getRowValue();
			city.setFName(event.getNewValue());
		    update_city_Data("city", event.getNewValue(), city.getCity());
		    
		});
		
		e_Accessories.setOnEditCommit(event -> {
			eTable access = event.getRowValue();
			access.setFName(event.getNewValue());
		    update_accessories_Data("accessories", event.getNewValue(), access.getAccessories());
		    
		});
		
		e_Comments.setOnEditCommit(event -> {
			eTable comment = event.getRowValue();
			comment.setFName(event.getNewValue());
		    update_comments_Data("comments", event.getNewValue(), comment.getComments());
		    
		});	
	}


//	------------------------------------------------------------------------------------------
//	------------------------------------------------------------------------------------------
//	This code will the user to delete row in tableview
//	------------------------------------------------------------------------------------------
//	------------------------------------------------------------------------------------------
	
	public void DeleteBtnClicked(ActionEvent ev) {
		try {
			Statement stmt=con.createConnection();
			String sqlQuery = "DELETE  FROM cclient WHERE client_ID ='"+userNameValue+"'";
			stmt.executeUpdate(sqlQuery);
			System.out.println("Sucessfull.");
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
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
	
//	----------------------------------------------------------------------------------------------------------
//	---------------------------------------------------------------------------------------------------------
//	Code to Update the cell values in order tableview
//	----------------------------------------------------------------------------------------------------------
//	---------------------------------------------------------------------------------------------------------
	private void update_Fname_Data(String col, String newValue, String id) {
		Statement stmt=con.createConnection();
		String sqlQuery = "UPDATE  cclient SET " + col + " = '" + newValue + "' WHERE client_ID = '" + userNameValue + "' ";
		try {
				int  rs = stmt.executeUpdate(sqlQuery);
				if(rs ==1) {
					System.out.println("Update Sucessfull");
				}
				else {
					System.out.println("Update Failed");
				}
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	private void update_Lname_Data(String col, String newValue, String id) {
		Statement stmt=con.createConnection();
		String sqlQuery = "UPDATE  cclient SET " + col + " = '" + newValue + "' WHERE client_ID = '" + userNameValue + "' ";
		try {
				int  rs = stmt.executeUpdate(sqlQuery);
				if(rs ==1) {
					System.out.println("Update Sucessfull");
				}
				else {
					System.out.println("Update Failed");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	private void update_ph_h_Data(String col, String newValue, String id) {
		Statement stmt=con.createConnection();
		String sqlQuery = "UPDATE  cclient SET " + col + " = '" + newValue + "' WHERE client_ID = '" + userNameValue + "' ";
		try {
			int  rs = stmt.executeUpdate(sqlQuery);
			if(rs ==1) {
				System.out.println("Update Sucessfull");
			}
			else {
				System.out.println("Update Failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void update_email_Data(String col, String newValue, String id) {
		Statement stmt=con.createConnection();
		String sqlQuery = "UPDATE  cclient SET " + col + " = '" + newValue + "' WHERE client_ID = '" + userNameValue + "' ";
		try {
			int  rs = stmt.executeUpdate(sqlQuery);
			if(rs ==1) {
				System.out.println("Update Sucessfull");
			}
			else {
				System.out.println("Update Failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void update_ph_c_Data(String col, String newValue, String id) {
		Statement stmt=con.createConnection();
		String sqlQuery = "UPDATE  cclient SET " + col + " = '" + newValue + "' WHERE client_ID = '" + userNameValue + "' ";
		try {
			int  rs = stmt.executeUpdate(sqlQuery);
			if(rs ==1) {
				System.out.println("Update Sucessfull");
			}
			else {
				System.out.println("Update Failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void update_st_no_Data(String col, String newValue, String id) {
		Statement stmt=con.createConnection();
		String sqlQuery = "UPDATE  cclient SET " + col + " = '" + newValue + "' WHERE client_ID = '" + userNameValue + "' ";
		try {
			int  rs = stmt.executeUpdate(sqlQuery);
			if(rs ==1) {
				System.out.println("Update Sucessfull");
			}
			else {
				System.out.println("Update Failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void update_ave_Data(String col, String newValue, String id) {
		Statement stmt=con.createConnection();
		String sqlQuery = "UPDATE  cclient SET " + col + " = '" + newValue + "' WHERE client_ID = '" + userNameValue + "' ";
		try {
			int  rs = stmt.executeUpdate(sqlQuery);
			if(rs ==1) {
				System.out.println("Update Sucessfull");
			}
			else {
				System.out.println("Update Failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void update_pCode_Data(String col, String newValue, String id) {
		Statement stmt=con.createConnection();
		String sqlQuery = "UPDATE  cclient SET " + col + " = '" + newValue + "' WHERE client_ID = '" + userNameValue + "' ";
		try {
			int  rs = stmt.executeUpdate(sqlQuery);
			if(rs ==1) {
				System.out.println("Update Sucessfull");
			}
			else {
				System.out.println("Update Failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void update_city_Data(String col, String newValue, String id) {
		Statement stmt=con.createConnection();
		String sqlQuery = "UPDATE  cclient SET " + col + " = '" + newValue + "' WHERE client_ID = '" + userNameValue + "' ";
		try {
			int  rs = stmt.executeUpdate(sqlQuery);
			if(rs ==1) {
				System.out.println("Update Sucessfull");
			}
			else {
				System.out.println("Update Failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void update_accessories_Data(String col, String newValue, String id) {
		Statement stmt=con.createConnection();
		String sqlQuery = "UPDATE equipment SET " + col + " = '" + newValue + "' WHERE f_client_ID = '" + userNameValue + "' ";
		try {
			int  rs = stmt.executeUpdate(sqlQuery);
			if(rs==1) {
				System.out.println("Update Sucessfull");
			}
			else {
				System.out.println("Update Failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void update_comments_Data(String col, String newValue, String id) {
		Statement stmt=con.createConnection();
		String sqlQuery = "UPDATE equipment SET " + col + " = '" + newValue + "' WHERE f_client_ID = '" + userNameValue + "' ";
		try {
			int  rs = stmt.executeUpdate(sqlQuery);
			if(rs==1) {
				System.out.println("Update Sucessfull");
			}
			else {
				System.out.println("Update Failed");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	--------------------------------------------------------------------------------------------------------
//	--------------------------------------------------------------------------------------------------------
//	This code is used to go to back page or logout
//	--------------------------------------------------------------------------------------------------------
//	--------------------------------------------------------------------------------------------------------
	
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
	
	public void backBtnClicked(ActionEvent ev) {
		try {
		Parent parent=FXMLLoader.load(getClass().getResource("AdminOrderList.fxml"));
		Scene Dashboard=new Scene(parent);
		Stage dashBoardStage=(Stage)((Node)ev.getSource()).getScene().getWindow();
		dashBoardStage.setScene(Dashboard);
		dashBoardStage.setTitle("Order List");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
}