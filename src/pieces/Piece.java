package pieces;

import static utils.MovimentoPeca.bolinha;
import static utils.MovimentoPeca.quadrado;

import java.util.List;

import chess.Chess;
import gui.MainViewController;
import gui.listener.DataChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import utils.MovimentoPeca;

public class Piece {
	protected static ImageView blackPosition1;
	protected static ImageView blackPosition2;
	protected static ImageView whitePosition1;
	protected static ImageView whitePosition2;

	protected static MainViewController m;
	protected static String chess[][];
	protected static int vez;
	protected static boolean xeque;
	protected static Image bolinha2 = new Image("/img/piece/chessPiece/bola.png");

	private static AnchorPane pane;

	private static boolean movimentouOuComeu;
	private static final Image pawnW = new Image("/img/piece/chessPiece/pawnWhite.png");

	private static final Image bishopW = new Image("/img/piece/chessPiece/bishopWhite.png");

	private static final Image kWhite = new Image("/img/piece/chessPiece/kWhite.png");

	private static final Image kingWhite = new Image("/img/piece/chessPiece/kingWhite.png");

	private static final Image queenWhite = new Image("/img/piece/chessPiece/queenWhite.png");

	private static final Image rookWhite = new Image("/img/piece/chessPiece/rookWhite.png");

	private static final Image pawnBlack = new Image("/img/piece/chessPiece/pawn.png");

	private static final Image bishopBlack = new Image("/img/piece/chessPiece/bishop.png");

	private static final Image kBlack = new Image("/img/piece/chessPiece/k.png");

	private static final Image kingBlack = new Image("/img/piece/chessPiece/king.png");

	private static final Image queenBlack = new Image("/img/piece/chessPiece/queen.png");

	private static final Image rookBlack = new Image("/img/piece/chessPiece/rook.png");

	public Piece(String chess[][], int vez) {
		this.chess = chess;
		this.vez = vez;
	}

	public Piece(int vez) {
		this.vez = vez;
	}

	public Piece(boolean xeque) {
		this.xeque = xeque;
	}

	public void setMovimentou(boolean movimentou) {
		movimentouOuComeu = movimentou;
	}

	public void movimentoDaPeca(String posiAtual,List<String>posisSairXeque) {

	}

	public void movimentarPeca(String posiAtual, String posiChegada) {

	}

	public boolean pecaInimiga(String posiAtual, String posiChegada) {

		return true;
	}

	public void comerPeca(String posiAtual, String posiChegada) {

	}

	public void comerPeca1(String posiAtual, String posiChegada, String chess[][]) {

	}

	public boolean verificaJogada(String posiAtual, String posiChegada) {
		return false;
	}

	public boolean verificaJogada(String posiAtual, String posiChegada, String chess[][]) {
		return false;
	}

	public boolean xeque(String pChegada) {
		return false;
	}

	public boolean verificaMovimentoPreto(int lChegada, int cChegada) {
		try {
			if (chess[lChegada][cChegada].equals(String.valueOf(Chess.blackPawn))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackBishop))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackKnight))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackRook))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackKing))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackQueen))) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public boolean verificaMovimentoPreto(int lChegada, int cChegada, String chess[][]) {
		try {
			if (chess[lChegada][cChegada].equals(String.valueOf(Chess.blackPawn))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackBishop))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackKnight))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackRook))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackKing))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackQueen))) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public boolean verificaMovimentoPreto2(int lChegada, int cChegada, String chess[][]) {
		try {
			if (chess[lChegada][cChegada].equals(String.valueOf(Chess.blackKing))) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public boolean verificaMovimentoBranca(int lChegada, int cChegada) {
		try {
			if (chess[lChegada][cChegada].equals(String.valueOf(Chess.whitePawn))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteBishop))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteKnight))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteRook))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteKing))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteQueen))) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public boolean verificaMovimentoBranca2(int lChegada, int cChegada,String chess[][]) {
		try {
			if (chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteKing))) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public boolean verificaMovimentoBranca(int lChegada, int cChegada, String chess[][]) {
		try {
			if (chess[lChegada][cChegada].equals(String.valueOf(Chess.whitePawn))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteBishop))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteKnight))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteRook))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteKing))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteQueen))) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public boolean pecaInimiga1(String posiAtual, String posiChegada, String chess[][]) {
		return false;

	}

	public boolean verificaJogada1(String posiAtual, String posiChegada) {
		return false;

	}

	public boolean verificaMovimentoPreto1(int lChegada, int cChegada) {
		try {
			if (chess[lChegada][cChegada].equals(String.valueOf(Chess.blackPawn))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackBishop))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackKnight))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackRook))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackQueen))) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public boolean verificaMovimentoPreto1(int lChegada, int cChegada, String chess[][]) {
		try {
			if (chess[lChegada][cChegada].equals(String.valueOf(Chess.blackPawn))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackBishop))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackKnight))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackRook))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.blackQueen))) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public boolean verificaMovimentoBranca1(int lChegada, int cChegada) {
		try {
			if (chess[lChegada][cChegada].equals(String.valueOf(Chess.whitePawn))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteBishop))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteKnight))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteRook))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteQueen))) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public boolean verificaMovimentoBranca1(int lChegada, int cChegada, String chess[][]) {
		try {
			if (chess[lChegada][cChegada].equals(String.valueOf(Chess.whitePawn))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteBishop))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteKnight))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteRook))
					|| chess[lChegada][cChegada].equals(String.valueOf(Chess.whiteQueen))) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public boolean xeque(String posiChegada, String[][] copiaChess) {
		return false;
	}

	public void verificaBolinha() {
		for (int l = 0; l < 8; l++) {
			for (int c = 0; c < 8; c++) {
				try {
					tirarPecaASerComida(l, c);
				} catch (NullPointerException e) {

				}
				if (chess[l][c].equals(String.valueOf(Chess.bolinha))) {
					chess[l][c] = String.valueOf(quadrado);
				}
			}
		}
	}

	public boolean getMovimentou() {
		return movimentouOuComeu;
	}

	public void setAnchorPane(AnchorPane pane) {
		this.pane = pane;
	}

	public AnchorPane getAnchorPane() {
		return this.pane;
	}

	public void setBolinha(int l, int c) {
		String posicaoAtualConvertida = MovimentoPeca.reConverterPosicao(l, c);

		if (this.getAnchorPane() != null) {
			System.out.println("entrei");
			AnchorPane pane = this.getAnchorPane();

			ImageView i = (ImageView) pane.lookup("#" + posicaoAtualConvertida);

			i.setImage(this.bolinha2);

		}
	}

	public void setPecaAmeaçada(int l, int c, Image im) {
		String posicaoAtualConvertida = MovimentoPeca.reConverterPosicao(l, c);

		if (this.getAnchorPane() != null) {
			System.out.println("entrei");
			AnchorPane pane = this.getAnchorPane();

			ImageView i = (ImageView) pane.lookup("#" + posicaoAtualConvertida);

			i.setImage(im);

		}
	}

	public Image getImageView(String posichegada) {

		int pAtual[] = MovimentoPeca.converterPosicao(posichegada);

		int l = pAtual[0];
		int c = pAtual[1];

		if (vez % 2 == 1) {
			if (chess[l][c].equals(String.valueOf(Chess.blackPawn))) {
				return m.getPawnblack2();
			} else if (chess[l][c].equals(String.valueOf(Chess.blackBishop))) {
				return m.getBishopblack2();
			} else if (chess[l][c].equals(String.valueOf(Chess.blackKnight))) {
				return m.getKblack2();
			} else if (chess[l][c].equals(String.valueOf(Chess.blackRook))) {
				return m.getRookblack2();
			} else if (chess[l][c].equals(String.valueOf(Chess.blackQueen))) {
				return m.getQueenblack2();
			}
		} else {
			if (chess[l][c].equals(String.valueOf(Chess.whitePawn))) {
				return m.getPawnw2();
			} else if (chess[l][c].equals(String.valueOf(Chess.whiteBishop))) {
				return m.getBishopw2();
			} else if (chess[l][c].equals(String.valueOf(Chess.whiteKnight))) {
				return m.getKwhite2();
			} else if (chess[l][c].equals(String.valueOf(Chess.whiteRook))) {
				return m.getRookwhite2();
			} else if (chess[l][c].equals(String.valueOf(Chess.whiteQueen))) {
				return m.getQueenwhite2();
			}
		}

		return null;
	}

	private ImageView im;
	private Image i;

	public void tirarPecaASerComida(int l, int c) {
		String posicaoAtualConvertida = MovimentoPeca.reConverterPosicao(l, c);
		String imageSelecionada;

		AnchorPane pane = this.getAnchorPane();

		im = (ImageView) pane.lookup("#" + posicaoAtualConvertida);

		imageSelecionada = im.getImage().impl_getUrl().toString();

		if (vez % 2 == 0) {
			if (imageSelecionada.equals(m.getPawnw2().impl_getUrl().toString())) {
				im.setImage(m.getPawnW());
			} else if (imageSelecionada.equals(m.getBishopw2().impl_getUrl().toString())) {
				im.setImage(m.getBishopW());
			} else if (imageSelecionada.equals(m.getKwhite2().impl_getUrl().toString())) {
				im.setImage(m.getkWhite());
			} else if (imageSelecionada.equals(m.getQueenwhite2().impl_getUrl().toString())) {
				im.setImage(m.getQueenWhite());
			} else if (imageSelecionada.equals(m.getRookwhite2().impl_getUrl().toString())) {
				im.setImage(m.getRookWhite());
			}
		} else {
			if (imageSelecionada.equals(m.getPawnblack2().impl_getUrl().toString())) {
				im.setImage(m.getPawnBlack());
			} else if (imageSelecionada.equals(m.getBishopblack2().impl_getUrl().toString())) {
				im.setImage(m.getBishopBlack());
			} else if (imageSelecionada.equals(m.getKblack2().impl_getUrl().toString())) {
				im.setImage(m.getkBlack());
			} else if (imageSelecionada.equals(m.getQueenblack2().impl_getUrl().toString())) {
				im.setImage(m.getQueenBlack());
			} else if (imageSelecionada.equals(m.getRookblack2().impl_getUrl().toString())) {
				im.setImage(m.getRookBlack());
			}
		}

	}

	public void tirarBolinha(int l, int c) {
		String posicaoAtualConvertida = MovimentoPeca.reConverterPosicao(l, c);

		if (this.getAnchorPane() != null) {
			AnchorPane pane = this.getAnchorPane();

			ImageView i = (ImageView) pane.lookup("#" + posicaoAtualConvertida);

			String imageSelecionada = i.getImage().impl_getUrl().toString();

			if (imageSelecionada.equals(this.bolinha2.impl_getUrl().toString())) {
				i.setImage(null);
			}

		}

	}

	public boolean comerPassante(String posiAtual, String posiChegada) {
		return false;

	}

	public static void setMainView(MainViewController ma) {
		m = ma;
	}

	public static ImageView getBlackPosition1() {
		return blackPosition1;
	}

	public static ImageView getBlackPosition2() {
		return blackPosition2;
	}

	public static ImageView getWhitePosition1() {
		return whitePosition1;
	}

	public static ImageView getWhitePosition2() {
		return whitePosition2;
	}

	public static void setBlackPosition1(ImageView blackPosition1) {
		Piece.blackPosition1 = blackPosition1;
	}

	public static void setBlackPosition2(ImageView blackPosition2) {
		Piece.blackPosition2 = blackPosition2;
	}

	public static void setWhitePosition1(ImageView whitePosition1) {
		Piece.whitePosition1 = whitePosition1;
	}

	public static void setWhitePosition2(ImageView whitePosition2) {
		Piece.whitePosition2 = whitePosition2;
	}

	public void movimentoDaPeca1(String posiAtual, String chess[][]) {

	}

	public void comerPeca1(String posiAtual, String posiChegada, String chess) {
		// TODO Auto-generated method stub

	}

	public void movimentarPeca1(String posiAtual, String posiChegada, String chess[][]) {
		// TODO Auto-generated method stub

	}

	public List<String> sairXeque(int vez, String posiAtual, String posiChegada, String[][] chess, ImageView img,
			String posiRei) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String descobrirPeca(ImageView image) {
		String imageSelecionada = image.getImage().impl_getUrl().toString();

		if (imageSelecionada.equals(pawnW.impl_getUrl().toString())) {
			return String.valueOf(Chess.whitePawn);
		} else if (imageSelecionada.equals(bishopW.impl_getUrl().toString())) {
			return String.valueOf(Chess.whiteBishop);
		} else if (imageSelecionada.equals(kWhite.impl_getUrl().toString())) {
			return String.valueOf(Chess.whiteKnight);
		} else if (imageSelecionada.equals(kingWhite.impl_getUrl().toString())) {
			return String.valueOf(Chess.whiteKing);
		} else if (imageSelecionada.equals(queenWhite.impl_getUrl().toString())) {
			return String.valueOf(Chess.whiteQueen);
		} else if (imageSelecionada.equals(rookWhite.impl_getUrl().toString())) {
			return String.valueOf(Chess.whiteRook);
		} else if (imageSelecionada.equals(pawnBlack.impl_getUrl().toString())) {
			return String.valueOf(Chess.blackPawn);
		} else if (imageSelecionada.equals(bishopBlack.impl_getUrl().toString())) {
			return String.valueOf(Chess.blackBishop);
		} else if (imageSelecionada.equals(kBlack.impl_getUrl().toString())) {
			return String.valueOf(Chess.blackKnight);
		} else if (imageSelecionada.equals(queenBlack.impl_getUrl().toString())) {
			return String.valueOf(Chess.blackQueen);
		} else if (imageSelecionada.equals(kingBlack.impl_getUrl().toString())) {
			return String.valueOf(Chess.blackKing);
		} else if (imageSelecionada.equals(rookBlack.impl_getUrl().toString())) {
			return String.valueOf(Chess.blackRook);
		}

		return null;
	}

	public static void mostrarXadrez(String[][] chess) {
		for (int l = 0; l < 9; l++) {
			for (int c = 0; c < 9; c++) {

				System.out.print(chess[l][c] + " ");
			}
			System.out.println();
		}
	}

	public static String[][] clonar(String chess[][]) {
		String[][] copyChess = new String[chess.length][chess.length];
		for (int i = 0; i < chess.length; i++) {
			for (int j = 0; j < chess.length; j++) {
				copyChess[i][j] = chess[i][j];
			}

		}

		return copyChess;
	}

	public void movimentoDaPeca2(String posiA, String[][] chess2) {
		// TODO Auto-generated method stub

	}

	public void verificaBolinha(String chess[][]) {
		for (int l = 0; l < 8; l++) {
			for (int c = 0; c < 8; c++) {
				try {
					// tirarPecaASerComida(l, c);
				} catch (NullPointerException e) {

				}
				if (chess[l][c].equals(String.valueOf(bolinha))) {
					chess[l][c] = String.valueOf(quadrado);
					// tirarBolinha(l, c);
				}
			}
		}
	}

	public boolean pecaInimiga2(String posiAtual, String posiChegada, String[][] chess) {
		// TODO Auto-generated method stub
		return false;
	}

 

}
