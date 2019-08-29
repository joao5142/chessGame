package utils;

import java.io.IOException;
import java.util.function.Consumer;

import application.Main;
import chess.Chess;
import gui.PromocaoController;
import gui.listener.DataChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class MovimentosEspeciais implements DataChangeListener {
 

	public static String[][] clonar(String chess[][]) {
		String[][]	copyChess = new String[chess.length][chess.length];
		for (int i = 0; i < chess.length; i++) {

			for (int j = 0; j < chess.length; j++) {
				copyChess[i][j] = chess[i][j];
			}

		}

		return copyChess;
	}

	private String peca;

	public String promocao(int vez) {
		Stage stage = (Stage) Main.getMainScene().getWindow();

		loaderPromocaoView(vez, (PromocaoController p) -> {
			p.initializeNodes(vez);

			p.setPeca(peca);
			p.setListenners(this);
		}, stage);

		return peca;
	}

	private <T> void loaderPromocaoView(int vez, Consumer<T> initialAction, Stage parent) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/PromocaoView.fxml"));

			AnchorPane pane = loader.load();

			Scene scene = new Scene(pane);
			scene.setFill(Color.TRANSPARENT);
			Stage stage = new Stage();

			stage.setScene(scene);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(parent);

			T controller = loader.getController();
			initialAction.accept(controller);
			stage.showAndWait();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean cravada(String posiAtual, String posiChegada, String chess[][], int vez) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		String pecaSelecionada = null;
		String peca2;
		int l = pAtual[0];
		int c = pAtual[1];

		String copiaChess[][] = clonar(chess);

		try {

			int[] pChegada = MovimentoPeca.converterPosicao(posiChegada);
			int lC = pChegada[0];
			int cC = pChegada[1];

			pecaSelecionada = chess[lC][cC];
		} catch (Exception e) {

		}

		System.out.println("A peça selecionada foi : " + pecaSelecionada);

		Piece p = null;
		if (pecaSelecionada.equals(String.valueOf(Chess.whitePawn))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackPawn))) {
			p = new Pawn(chess, vez);
		} else if (pecaSelecionada.equals(String.valueOf(Chess.whiteBishop))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackBishop))) {
			p = new Bishop(chess, vez);
		} else if (pecaSelecionada.equals(String.valueOf(Chess.whiteKnight))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackKnight))) {
			p = new Knight(chess, vez);
		} else if (pecaSelecionada.equals(String.valueOf(Chess.whiteRook))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackRook))) {
			p = new Rook(chess, vez);
		} else if (pecaSelecionada.equals(String.valueOf(Chess.whiteQueen))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackQueen))) {
			p = new Queen(chess, vez);
		}
		peca2 = chess[l][c];

		chess[l][c] = String.valueOf(Chess.quadrado); // tiro a peça pra verificar se existe xeque
		try {
			if (p.xeque(posiChegada)) {
				System.out.println("Esta em xeque!");
				chess[l][c] = peca2;
				return true;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		chess[l][c] = peca2;
		return false;
	}

	private void setPeca(String peca) {
		this.peca = peca;
	}

	@Override
	public void onDataChange(String peca) {
		setPeca(peca);

	}

	public static void mostrarXadrez(String[][] chess) {
		System.out.println("Copia Chess:::");
		for (int l = 0; l < 9; l++) {
			for (int c = 0; c < 9; c++) {

				System.out.print(chess[l][c] + " ");
			}
			System.out.println();
		}
	}
}
