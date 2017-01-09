package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private AnchorPane root;
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        initRoot();
		
		/*try {
			AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("application//view//AppGUI.fxml"));
			Scene scene = new Scene(root, 1040, 850);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	
	public void initRoot() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getClassLoader().getResource("application/view/AppGUI.fxml"));
			root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
