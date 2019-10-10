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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class AdminInventoryController {

	@FXML private TextField searchBox;
	@FXML private TableView<InventoryTable> inventoryTable;
	@FXML private TableColumn<InventoryTable, String> i_productID;
	@FXML private TableColumn<InventoryTable, String> i_type;
	@FXML private TableColumn<InventoryTable, String> i_name;
	@FXML private TableColumn<InventoryTable, String> i_company;
	@FXML private TableColumn<InventoryTable, String> i_model;
	@FXML private TableColumn<InventoryTable, String> i_serial;
	@FXML private TableColumn<InventoryTable, String> i_qty;
	@FXML private TableColumn<InventoryTable, String> i_comments;
	@FXML private TextField tf_productID;
	@FXML private TextField tf_type;
	@FXML private TextField tf_name;
	@FXML private TextField tf_company;
	@FXML private TextField tf_model;
	@FXML private TextField tf_serial;
	@FXML private TextField tf_qty;
	@FXML private TextField tf_comments;
	@FXML Label userLabel;
	
	static String itemValue = "";

	ObservableList<InventoryTable> iTable = FXCollections.observableArrayList();
	
	CConnection con = new CConnection();

public void initialize() {
	
		userLabel.setText(CurrentUser.getUser());
	
		inventoryTable.setEditable(true);
		
		try {
			Statement stmt=con.createConnection();
			String sqlQuery = "SELECT * FROM inventory";
			ResultSet rs=stmt.executeQuery(sqlQuery);
			
			while (rs.next()) {
				
				// Column names from MySQL database //
				iTable.add(new InventoryTable(rs.getString("productID"), rs.getString("type"),rs.getString("name"), rs.getString("company"), rs.getString("model"), 
						rs.getString("serialNo"), rs.getString("quantity"), rs.getString("comments")));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		
		
		// Initialize Columns
		i_productID.setCellValueFactory(new PropertyValueFactory<>("product_ID"));
		
		i_type.setCellValueFactory(new PropertyValueFactory<>("type"));
		i_type.setCellFactory(TextFieldTableCell.forTableColumn());
		i_type.setOnEditCommit(event -> {
		    InventoryTable type = event.getRowValue();
		    type.setType(event.getNewValue());
		    updateData("type", event.getNewValue());
		});
		
		i_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		i_name.setCellFactory(TextFieldTableCell.forTableColumn());
		i_name.setOnEditCommit(event -> {
		    InventoryTable name = event.getRowValue();
		    name.setName(event.getNewValue());
		    updateData("name", event.getNewValue());
		});
		
		i_company.setCellValueFactory(new PropertyValueFactory<>("company"));
		i_company.setCellFactory(TextFieldTableCell.forTableColumn());
		i_company.setOnEditCommit(event -> {
		    InventoryTable company = event.getRowValue();
		    company.setCompany(event.getNewValue());
		    updateData("company", event.getNewValue());
		});
		
		i_model.setCellValueFactory(new PropertyValueFactory<>("model"));
		i_model.setCellFactory(TextFieldTableCell.forTableColumn());
		i_model.setOnEditCommit(event -> {
		    InventoryTable model = event.getRowValue();
		    model.setModel(event.getNewValue());
		    updateData("model", event.getNewValue());
		});
		
		i_serial.setCellValueFactory(new PropertyValueFactory<>("serial"));
		i_serial.setCellFactory(TextFieldTableCell.forTableColumn());
		i_serial.setOnEditCommit(event -> {
		    InventoryTable serial = event.getRowValue();
		    serial.setSerial(event.getNewValue());
		    updateData("serialNo", event.getNewValue());
		});
		
		i_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		i_qty.setCellFactory(TextFieldTableCell.forTableColumn());
		i_qty.setOnEditCommit(event -> {
		    InventoryTable qty = event.getRowValue();
		    qty.setQuantity(event.getNewValue());
		    updateData("quantity", event.getNewValue());
		});
		
		i_comments.setCellValueFactory(new PropertyValueFactory<>("comments"));
		i_comments.setCellFactory(TextFieldTableCell.forTableColumn());
		i_comments.setOnEditCommit(event -> {
		    InventoryTable comments = event.getRowValue();
		    comments.setComments(event.getNewValue());
		    updateData("comments", event.getNewValue());
		});
		
		// 1. Wrapping the ObservableList in a FilteredList (initially display all data).
		FilteredList<InventoryTable> filteredData = new FilteredList<>(iTable, p -> true);
		
        // 2. Set the filter Predicate whenever the filter changes.
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare items with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (item.getProduct_ID().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (item.getType().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (item.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (item.getCompany().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (item.getModel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;                    
                } else if (item.getSerial().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (item.getQuantity().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (item.getComments().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<InventoryTable> sortedData = new SortedList<>(filteredData);
		
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(inventoryTable.comparatorProperty());
        
		inventoryTable.setItems(sortedData);
		
		// Sends current row's productID value to itemValue variable //
		inventoryTable.getSelectionModel().selectedItemProperty().addListener(
			    (observable, oldValue, newValue) -> {
			    	itemValue = newValue.getProduct_ID();
			    }
			);
			
			System.out.println(itemValue);
		
	}
	
	public void updateData(String column, String newValue) {
		try {
			Statement stmt4=con.createConnection();
			String sqlQuery4="UPDATE inventory SET " + column + " = '" + newValue + "' WHERE productID = '" + itemValue + "'";
		
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
			String sqlQuery2 ="insert into inventory ( productID, type, name, company, model, serialNo, quantity, comments, admin_ID )"
					+ "VALUES ('" + tf_productID.getText() + 
					"','" +	tf_type.getText() + 
					"','"+ tf_name.getText() + 
					"','" +	tf_company.getText() + 
					"','" + tf_model.getText() + 
					"','" + tf_serial.getText() + 
					"','" + tf_qty.getText() + 
					"','" +	tf_comments.getText() + 
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
					Parent parent =FXMLLoader.load(getClass().getResource("AdminInventory.fxml"));
					Scene inventoryList = new Scene(parent);
					Stage dashBoardStage =(Stage)((Node)ev.getSource()).getScene().getWindow();
					dashBoardStage.setScene(inventoryList);
					dashBoardStage.setTitle("SkyNet Inventory Management");
				}catch(Exception e){ e.printStackTrace(); }
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error Adding Items");
		}
		
		// Clear all text //
		tf_productID.clear();
		tf_type.clear();
		tf_name.clear();
		tf_company.clear();
		tf_model.clear();
		tf_serial.clear();
		tf_qty.clear();
		tf_comments.clear();
	
	}
	
	public void deleteBtnClicked(ActionEvent ev) {
		try {
	
			Statement stmt3=con.createConnection();
			String sqlQuery3 = "DELETE FROM inventory WHERE productID = '" + itemValue + "'";
			int rs3 = stmt3.executeUpdate(sqlQuery3);
	
			if(rs3 ==1) {
				System.out.println("Successfully deleted");
				}
			else {
				System.out.println("Failed to delete");
			}
			
				// Reloads Page //
				try {
					Parent parent =FXMLLoader.load(getClass().getResource("AdminInventory.fxml"));
					Scene inventoryList = new Scene(parent);
					Stage dashBoardStage =(Stage)((Node)ev.getSource()).getScene().getWindow();
					dashBoardStage.setScene(inventoryList);
					dashBoardStage.setTitle("SkyNet Inventory Management");
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
		dashBoardStage.setTitle("Login Page");
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
