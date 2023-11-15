package net.urtzi.olimpiadas.application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
	@Override
    public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("/fxml/olimpiadas.fxml"));
	    	Scene scene = new Scene( root );
	    	scene.getStylesheets().add(this.getClass().getResource("/css/main.css").toExternalForm());
	    	
	        stage.setTitle("Olimpiadas - Gestion");
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public static void main(String[] args) {
		launch(args);
	}
}
