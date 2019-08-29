package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import chess.Chess;
import gui.listener.DataChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PromocaoController implements Initializable {
	private String peca;
	
	private List<DataChangeListener>listenners=new ArrayList<DataChangeListener>();
	
	private int vez;

	@FXML
	private ImageView queen;

	@FXML
	private ImageView cavalo;

	@FXML
	private ImageView torre;

	@FXML
	private ImageView bispo;

	@FXML
	void onImageClick(MouseEvent event) {
		String pecaSelecionada = ((ImageView) event.getSource()).getId();

		if (vez % 2 == 1) {
			if (pecaSelecionada.equals("queen")) {
				setPeca(String.valueOf(Chess.whiteQueen));
			} else if (pecaSelecionada.equals("torre")) {
				setPeca(String.valueOf(Chess.whiteRook));

			} else if (pecaSelecionada.equals("bispo")) {
				setPeca(String.valueOf(Chess.whiteBishop));

			} else {
				setPeca(String.valueOf(Chess.whiteKnight));

			}
		} else {
			if (pecaSelecionada.equals("queen")) {
				setPeca(String.valueOf(Chess.blackQueen));
			} else if (pecaSelecionada.equals("torre")) {
				setPeca(String.valueOf(Chess.blackRook));

			} else if (pecaSelecionada.equals("bispo")) {
				setPeca(String.valueOf(Chess.blackBishop));

			} else {
				setPeca(String.valueOf(Chess.blackKnight));

			}
		}
		System.out.println(peca);
		 notifyDataChanged(peca);
		Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}

	private void notifyDataChanged(String peca) {
		for(DataChangeListener liste:listenners) {
			liste.onDataChange(peca);
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	private static final MainViewController main = new MainViewController();

	public void initializeNodes(int vez) {
		this.vez = vez;
		if (vez % 2 == 1) {
			cavalo.setImage(main.getkWhite());
			queen.setImage(main.getQueenWhite());
			torre.setImage(main.getRookWhite());
			bispo.setImage(main.getBishopW());
		} else {
			cavalo.setImage(main.getkBlack());
			queen.setImage(main.getQueenBlack());
			torre.setImage(main.getRookBlack());
			bispo.setImage(main.getBishopBlack());
		}
	}

	public void setPeca(String peca) {
		this.peca = peca;
	}
	
	public void setListenners(DataChangeListener listener) {
		listenners.add(listener);
	}
}
