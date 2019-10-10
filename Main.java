package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent parent=FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene LoginScene=new Scene(parent);
			primaryStage.setTitle("Login");
			primaryStage.setScene(LoginScene);
			primaryStage.getIcons().add(new Image("file:../../../icon.png"));
			primaryStage.show();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}