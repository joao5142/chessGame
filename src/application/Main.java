package application;

import gui.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pieces.Piece;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Main extends Application {
	private static Scene scene;

	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Splash.fxml"));
			AnchorPane pane = loader.load();

			scene = new Scene(pane);
			scene.setFill(Color.TRANSPARENT);
			
			
			stage.setScene(scene);
			stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
			stage.show();
     
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getMainScene() {
		return scene;
	}


	
}
