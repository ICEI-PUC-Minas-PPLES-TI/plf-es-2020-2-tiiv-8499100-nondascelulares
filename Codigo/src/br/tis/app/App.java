package br.tis.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		 launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(); 
	        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
	        loader.setLocation(getClass().getResource("FXML.fxml"));
	        
	        Scene scene = new Scene(root);
	        
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Nondas Celulares");
	        primaryStage.show();
		
	}

}
