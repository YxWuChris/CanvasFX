package application;

//import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

//import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

//import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
	@FXML
	private ToggleGroup brushOrEraser;
	//@FXML
	//private RadioButton eraser;
	@FXML
	private RadioButton eraser;
	
	@FXML
	private RadioButton brush;
	
	GraphicsContext g;
	GraphicsContext gcCanvas;
	@FXML
	private ColorPicker cp;
	@FXML
	private Canvas canvas; 
	@FXML private Canvas canvasMain;
	
	int cellSize = 20;
	int gridSize = 16;
	
	@FXML
	private void initialize() {
	 g = canvas.getGraphicsContext2D();
	 gcCanvas = canvasMain.getGraphicsContext2D();
	 drawGrid();
	 
	 canvasMain.setOnMouseDragged(e -> {
			//System.out.println("!!!!!!!!!!!!!!!!!!!!!");			
			double x = Math.floor(e.getX() / 20) * 20;
			double y = Math.floor(e.getY() / 20) * 20;
			System.out.println("??????????");	
	
			if (eraser.isSelected()) {
				gcCanvas.clearRect(x, y, cellSize, cellSize);
				g.clearRect(x, y, cellSize, cellSize);
			}else {
				gcCanvas.setFill(cp.getValue());
				gcCanvas.fillRect(x, y, cellSize, cellSize);
				g.setFill(cp.getValue());
				g.fillRect(x, y, cellSize, cellSize);
			}
			drawGrid();
		});
		
		
	 canvasMain.setOnMouseClicked(e -> {
			//System.out.println("!!!!!!!!!!!!!!!!!!!!!");			
			double x = Math.floor(e.getX() / 20) * 20;
			double y = Math.floor(e.getY() / 20) * 20;
			System.out.println("??????????");	
	
			if (eraser.isSelected()) {
				gcCanvas.clearRect(x, y, cellSize, cellSize);
				g.clearRect(x, y, cellSize, cellSize);
			}else {
				gcCanvas.setFill(cp.getValue());
				gcCanvas.fillRect(x, y, cellSize, cellSize);
				g.setFill(cp.getValue());
				g.fillRect(x, y, cellSize, cellSize);
			}
			drawGrid();
		});
		
	}
	private void drawGrid() {
		//System.out.println("??????????");	
		for (int i = 0; i <= canvasMain.getHeight()/cellSize; i++) {
			for (int j = 0; j <= canvasMain.getWidth()/cellSize; j++) {
				gcCanvas.setStroke(Paint.valueOf("black"));
				gcCanvas.strokeLine(0, i*cellSize, canvasMain.getWidth(), i*cellSize);
				gcCanvas.strokeLine(j*cellSize, 0, j*cellSize, canvasMain.getHeight());
			}
		}
	}
	/*
	@FXML
	private void id00(ActionEvent event) {
		String[] array = ((Node) event.getSource()).getId().split("_");
		System.out.println(array[1]+"         "+array[2]);
		
		((Node) event.getSource()).setStyle("-fx-background-color: '" + cp.getValue().toString() +"'");
		if(cp.getValue().toString().equals("0x00000000")){
			
			g.clearRect(Integer.parseInt(array[2])*20, Integer.parseInt(array[1])*20, 20, 20);
			
		}else {
		
		g.setFill(Paint.valueOf(cp.getValue().toString()));
		System.out.println(cp.getValue().toString());
		g.fillRect(Integer.parseInt(array[2])*20, Integer.parseInt(array[1])*20, 20, 20);
	}}*/
	public void saveAsPng() {
	    WritableImage image = canvas.snapshot(new SnapshotParameters(), null);
	    FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Image");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("PNG", "*.png"));
	    // TODO: probably use a file chooser here
	    File file = fileChooser.showSaveDialog(null);
	    try {
	        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
	    } catch (IOException e) {
	        // TODO: handle exception here
	    }
	    
	}

	
	public void newPage() {
		//System.out.println("!!!!!!!!!!!!!");
		Main.showSecond();
	}
	
	public void newPage2() {
		//System.out.println("!!!!!!!!!!!!!");
		Main.showThird();
	}

	
	    @FXML
	    private void onOpen(ActionEvent event) throws FileNotFoundException {
	    	FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Image");
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("PNG", "*.png"));
//					new FileChooser.ExtensionFilter("JPG", "*.jpg"));
			File file = fileChooser.showOpenDialog(new Stage());
			
		   Image image = new Image(new FileInputStream(file),16,16,false,false);
			
			
			try {			
				//image = ImageIO.read(file);
				PixelReader pr = image.getPixelReader();
				
				for (int i = 0; i < image.getHeight(); i++) {
					for (int j = 0; j < image.getWidth(); j++) {
						//System.out.println(pr.getColor(j,i));
						
						g.setFill(Paint.valueOf(pr.getColor(j,i).toString()));
						g.fillRect(j*20, i*20, 20, 20);
						gcCanvas.setFill(Paint.valueOf(pr.getColor(j,i).toString()));
						gcCanvas.fillRect(j*cellSize, i*cellSize, cellSize, cellSize);
					}
				}

			} catch (Exception e) {
				System.out.println("Failed to open image" + e);
				e.printStackTrace(System.out);
			}
	    }
	    @FXML
		private void clearCanvas(ActionEvent event) {
			gcCanvas.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			drawGrid();
		}
}
