package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pieces.Piece;

public class SplashController implements Initializable {

	private static Scene scene;

	private void setPositionsRook(AnchorPane pane) {
		ImageView i = (ImageView) pane.lookup("#g1");
		ImageView a = (ImageView) pane.lookup("#c1");

		ImageView e = (ImageView) pane.lookup("#g8");

		ImageView o = (ImageView) pane.lookup("#c8");

		Piece.setWhitePosition1(i);
		Piece.setWhitePosition2(a);

		Piece.setBlackPosition1(e);
		Piece.setBlackPosition2(o);
	}

	public static Scene getMainScene() {
		return scene;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loaderMainView();

	}

	public void loaderMainView() {
		Task<Void> t = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				Thread.sleep(2000);
				Platform.runLater(() -> {
					try {

						FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
						AnchorPane pane = loader.load();

						scene = new Scene(pane);
						scene.setFill(Color.TRANSPARENT);
						Stage stage = new Stage();

						stage.setScene(scene);
						stage.initStyle(StageStyle.TRANSPARENT);
						stage.setResizable(false);
						stage.show();

						MainViewController c = loader.getController();
						c.showPaneHide();
						c.mouseMove();
						c.initNodes();
						c.setxInicial(stage.getX());
						c.setyInicial(stage.getY());
						Piece.setMainView(loader.getController());

						setPositionsRook(pane);

						Stage splash = (Stage) Main.getMainScene().getWindow();
						splash.close();

					} catch (Exception e) {
						e.printStackTrace();
					}

				});

				return null;
			}

		};

		new Thread(t).start();
	}

}
