package application;
	
import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;



public class Main extends Application {
	
	private static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setResizable(false);
			Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
			primaryStage.setTitle("16X16");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void showSecond() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("View1.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setResizable(false);
			dialogStage.setTitle("32X32");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static void showFirst() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("View.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setResizable(false);
			dialogStage.setTitle("16X16");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		}
	
	public static void showThird() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("View2.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("64X64");
			dialogStage.setResizable(false);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
