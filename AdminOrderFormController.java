package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminOrderFormController implements Initializable {

@FXML private TextField order_ID;
@FXML private TextField lName;
@FXML private TextField P_Code;
@FXML private TextArea comments;
@FXML private TextField color;
@FXML private TextArea accessories;
@FXML private TextField city;
@FXML private TextField client_ID;
@FXML private TextField eqp_ID;
@FXML private DatePicker date_in;
@FXML private TextField fName;
@FXML private TextField St_No;
@FXML private TextField model;
@FXML private TextField company;
@FXML private DatePicker date_out;
@FXML private TextField serial_no;
@FXML private TextField email;
@FXML private TextField ph_1;
@FXML private TextField ph_2 ;
@FXML private TextField ave;
@FXML private ComboBox<String> payment;
@FXML Label userLabel;

String c_ID ;
String status = "Pending";

ObservableList<String> List = FXCollections.observableArrayList("Paid", "Not Paid");

public void initialize() {
	userLabel.setText(CurrentUser.getUser());		// This gets the first name last name string from CurrentUser class
	
}


@FXML public void submitBtnClicked() {
	// To add client & Equipment information into PHPAdmin ----------------------------------------------------------------------------------------------------
	try {
	
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/computer-repair-shop-database","root","");
		Statement stmt=connection.createStatement();
			String sqlQuery = "insert into cclient ( client_ID,fName, lName, email, ph_1, ph_2, St_No, ave, P_Code, city)"
				+ " VALUES (DEFAULT,'"+fName.getText()+"','"+lName.getText()+"','"+email.getText()+"','"+ph_1.getText()+"','"+ph_2.getText()+"','"+St_No.getText()+"','"+ave.getText()+"','"+P_Code.getText()+"','"+city.getText()+"')";
		
		
		int rs= stmt.executeUpdate(sqlQuery);
	
		if(rs ==1) {
			System.out.println("Successfull");
			}
		else {
			System.out.println("Failed");
		}
		
		String sqlQueryC_ID = "select client_ID from cclient WHERE email = '"+email.getText()+"' ";
		ResultSet re_ID = stmt.executeQuery(sqlQueryC_ID);
		
		if(re_ID.next()) {
	 c_ID =	re_ID.getString(1);
		
		}
	
		String sqlQueryOrder = "insert into orders ( order_ID,f_client_ID, date_in, date_out, PaymentStatus, order_Status)"
				+ " VALUES (DEFAULT,'"+c_ID+"','"+(date_in.getValue()!=null? date_in.getValue().toString():"")+"','"+(date_out.getValue()!=null? date_out.getValue().toString():"")+"','"+payment.getValue()+"','" +status+"')";
			
		int rs2= stmt.executeUpdate(sqlQueryOrder);
	
		if(rs2 ==1) {
			System.out.println("Successfull");
			}
		else {
			System.out.println("Failed");
		}
		
		String sqlQueryEqp = "insert into equipment ( eqp_ID, f_client_ID, serial_no, model , comments, company , color, accessories )"
				+ " VALUES ( DEFAULT, '"+c_ID+"', '"+serial_no.getText()+"','"+model.getText()+"','"+comments.getText()+"','"+company.getText()+"','"+color.getText()+"',"
						+ "'"+accessories.getText()+"');";
		
		int rs1= stmt.executeUpdate(sqlQueryEqp);
	
		if(rs1 ==1) {
			System.out.println("Successfull");
			//clearBtnClicked();
			}
		else {
			System.out.println("Failed");
		}
		
		// Alert box code //
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Order");
		alert.setHeaderText(null);
		alert.setContentText("Order Created!");
		alert.showAndWait();
		clearBtnClicked();
		// Copy this //
	}
	
	catch(Exception e) {
		e.printStackTrace();}
 	// End of Client Info into PHPAdmin ------------------------------------------------------------------------------------------------------------

 }

	public void clearBtnClicked() {
		fName.clear();
		lName.clear();
		email.clear();
		ph_1.clear();
		ph_2.clear();
		St_No.clear();
		ave.clear();
		P_Code.clear();
		city.clear();
		date_in.getEditor().clear();
		date_out.getEditor().clear();
		serial_no.clear();
		model.clear();
		comments.clear();
		company.clear();
		color.clear();
		accessories.clear();
	}

	public void backBtnClicked(ActionEvent ev) {
		try {
			Parent parent=FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
			Scene Dashboard=new Scene(parent);
			Stage dashBoardStage=(Stage)((Node)ev.getSource()).getScene().getWindow();
			dashBoardStage.setScene(Dashboard);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		payment.setItems(List);
	}

}

