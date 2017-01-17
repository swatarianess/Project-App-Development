package application;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	
	private static Stage primaryStage;
	private AnchorPane root;

	@Override
	public void start(Stage primaryStage) {
		this.setPrimaryStage(primaryStage);
        this.getPrimaryStage().setTitle("Oostvarders Predictor - Application 2");
        initRoot();

	}
	
	private void initRoot() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getClassLoader().getResource("application/view/AppGUI.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			getPrimaryStage().setScene(scene);
			getPrimaryStage().show();
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static void display(File selectedFile) {
	
		
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


}
