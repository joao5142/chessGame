package pieces;

import chess.Chess;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import utils.MovimentoPeca;

import javax.swing.JOptionPane;

import static pieces.Piece.chess;
import static utils.MovimentoPeca.bolinha;
import static utils.MovimentoPeca.quadrado;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
	private AnchorPane pane;
	private boolean v = true;
	private static boolean movimentouOuComeu = false;

	public Queen(String chess[][], int vez) {
		super(chess, vez);
	}

	public Queen(int vez) {
		super(vez);
	}

	public boolean xeque(String posiChegada) {

		Bishop p = new Bishop(chess, vez);
		Rook p1 = new Rook(chess, vez);
		if (p.xeque(posiChegada) || p1.xeque(posiChegada)) {
			return true;
		}
		return false;
	}

	public boolean xeque(String posiChegada, String chess[][]) {

		Bishop p = new Bishop(vez);
		Rook p1 = new Rook(vez);
		if (p.xeque(posiChegada, chess) || p1.xeque(posiChegada, chess)) {
			return true;
		}
		return false;
	}

	@Override
	public List<String> sairXeque(int vez, String pecaQueDeuXeque, String posiPecaQueDeuXeque, String[][] chess,
			ImageView img, String posiRei) {

		String posiA = null;
		String posiC;
		Piece p;
		Piece p1;
		List<String> l = new ArrayList<String>();
		String[][] chessVerdadeiro = Piece.clonar(chess);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (vez % 2 == 1) {
					if (chess[i][j].equals(String.valueOf(Chess.whiteQueen))) {
						p1 = new Rook(vez);

						try {
							posiA = MovimentoPeca.reConverterPosicao(i, j);
						} catch (Exception e) {
							e.printStackTrace();
						}

						// List<String>l1=p.sairXeque(vez, pecaQueDeuXeque, posiPecaQueDeuXeque, chess,
						// img, posiRei);
						List<String> l2 = p1.sairXeque(vez, pecaQueDeuXeque, posiPecaQueDeuXeque, chess, img, posiRei);
						l.addAll(l2);

					}

				} else if (chess[i][j].equals(String.valueOf(Chess.blackQueen))) {

					// p = new Bishop(vez);
					p1 = new Rook(vez);

					try {
						posiA = MovimentoPeca.reConverterPosicao(i, j);
					} catch (Exception e) {
						e.printStackTrace();
					}

					// List<String>l1=p.sairXeque(vez, pecaQueDeuXeque, posiPecaQueDeuXeque, chess,
					// img, posiRei);
					List<String> l2 = p1.sairXeque(vez, pecaQueDeuXeque, posiPecaQueDeuXeque, chess, img, posiRei);

					// l.addAll(l1);
					l.addAll(l2);
				}

			}

		}
		return l;
	}

	@Override
	public void movimentoDaPeca(String posiAtual,List<String>posisSairXeque) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);

		int l = pAtual[0];
		int c = pAtual[1];
		int a = 0;

		Bishop b = new Bishop(chess, vez);
		b.setAnchorPane(super.getAnchorPane());
		b.movimentoDaPeca(posiAtual,posisSairXeque);

		Rook r = new Rook(chess, vez);
		r.setAnchorPane(super.getAnchorPane());
		r.movimentoDaPeca(posiAtual,posisSairXeque);

	}

	@Override
	public void movimentoDaPeca1(String posiAtual, String chess[][]) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);

		int l = pAtual[0];
		int c = pAtual[1];
		int a = 0;

		Bishop b = new Bishop(vez);
		// b.setAnchorPane(super.getAnchorPane());
		b.movimentoDaPeca1(posiAtual, chess);

		Rook r = new Rook(vez);
		// r.setAnchorPane(super.getAnchorPane());
		r.movimentoDaPeca1(posiAtual, chess);

	}

	@Override
	public void comerPeca(String posiAtual, String posiChegada) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pAtual[0];
		int c = pAtual[1];

		int lChegada = pChegada[0];
		int cChegada = pChegada[1];
		String peca;

		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteQueen) : String.valueOf(Chess.blackQueen);

		if (this.getAnchorPane() != null) {
			AnchorPane pane = this.getAnchorPane();

			ImageView i = (ImageView) pane.lookup("#" + posiChegada);

			ImageView e = (ImageView) pane.lookup("#" + posiAtual);

			i.setImage(e.getImage());
			e.setImage(null);

			Pawn.setQtdCasas(0);
			Pawn.setP(null);

			/*
			 * if(vez%2==1) { m.setPecaComida1(m.getqBlack(),m.getContQB()); }else {
			 * m.setPecaComida1( m.getqWhite(),m.getContQW()); }
			 */
		}

		movimentouOuComeu = true;

		chess[lChegada][cChegada] = peca;
		chess[l][c] = String.valueOf(quadrado);

		verificaBolinha();
	}

	@Override
	public boolean pecaInimiga(String posiAtual, String posiChegada) {
		movimentouOuComeu = false;

		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pAtual[0];
		int c = pAtual[1];

		int lChegada = pChegada[0];
		int cChegada = pChegada[1];

		boolean ver = (vez % 2 == 1) ? verificaMovimentoPreto(lChegada, cChegada)
				: verificaMovimentoBranca(lChegada, cChegada);

		boolean ver1 = verificaJogada(posiAtual, posiChegada);
		return ver && ver1;
	}

	@Override
	public boolean pecaInimiga1(String posiAtual, String posiChegada, String chess[][]) {
		movimentouOuComeu = false;

		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pAtual[0];
		int c = pAtual[1];

		int lChegada = pChegada[0];
		int cChegada = pChegada[1];

		boolean ver = (vez % 2 == 1) ? verificaMovimentoPreto1(lChegada, cChegada)
				: verificaMovimentoBranca1(lChegada, cChegada);

		boolean ver1 = verificaJogada1(posiAtual, posiChegada);
		return ver && ver1;
	}

	@Override
	public boolean pecaInimiga2(String posiAtual, String posiChegada, String chess[][]) {
		movimentouOuComeu = false;

		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pAtual[0];
		int c = pAtual[1];

		int lChegada = pChegada[0];
		int cChegada = pChegada[1];

		boolean ver = (vez % 2 == 1) ? verificaMovimentoPreto2(lChegada, cChegada, chess)
				: verificaMovimentoBranca2(lChegada, cChegada, chess);

		boolean ver1 = verificaJogada(posiAtual, posiChegada);
		return ver && ver1;
	}

	public boolean verificaJogada(String posiChegada) {
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);
		int linhaChegada = pChegada[0];
		int colunChegada = pChegada[1];

		return chess[linhaChegada][colunChegada].equals(String.valueOf(bolinha));
	}

	@Override
	public boolean verificaJogada(String posiAtual, String posiChegada) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pAtual[0];
		int c = pAtual[1];
		int linhaChegada = pChegada[0];
		int colunChegada = pChegada[1];

		Bishop b = new Bishop(chess, vez);
		boolean v1 = b.verificaJogada(posiAtual, posiChegada);
		Rook r = new Rook(chess, vez);
		boolean v2 = r.verificaJogada(posiAtual, posiChegada);

		if (v1 || v2) {
			return true;
		}
		return false;

	}
	@Override
	public boolean verificaJogada1(String posiAtual, String posiChegada) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pAtual[0];
		int c = pAtual[1];
		int linhaChegada = pChegada[0];
		int colunChegada = pChegada[1];

		Bishop b = new Bishop( vez);
		boolean v1 = b.verificaJogada(posiAtual, posiChegada);
		Rook r = new Rook(vez);
		boolean v2 = r.verificaJogada(posiAtual, posiChegada);

		if (v1 || v2) {
			return true;
		}
		return false;

	}

	@Override
	public void movimentarPeca(String posiAtual, String posiChegada) {
		movimentouOuComeu = false;

		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		String pecaSelecionada = chess[pAtual[0]][pAtual[1]];

		int l = pAtual[0];
		int c = pAtual[1];
		int linhaChegada = pChegada[0];
		int colunChegada = pChegada[1];

		String peca;
		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteQueen) : String.valueOf(Chess.blackQueen);
		boolean verificaMovimento = (vez % 2 == 1) ? verificaMovimentoBranca(linhaChegada, colunChegada)
				: verificaMovimentoPreto(linhaChegada, colunChegada);

		if (verificaMovimento) {
			System.out.println("Jogada Invalida!");
			MovimentoPeca.validacao = false;

		} else if (verificaJogada(posiChegada)) {
			Pawn.setQtdCasas(0);
			Pawn.setP(null);

			movimentouOuComeu = true;
			super.setMovimentou(movimentouOuComeu);

			String posicaoChegadaConvertida = MovimentoPeca.reConverterPosicao(linhaChegada, colunChegada);
			String posicaoAtualConvertida = MovimentoPeca.reConverterPosicao(l, c);

			if (this.getAnchorPane() != null) {
				AnchorPane pane = this.getAnchorPane();

				ImageView i = (ImageView) pane.lookup("#" + posicaoChegadaConvertida);

				ImageView e = (ImageView) pane.lookup("#" + posicaoAtualConvertida);

				i.setImage(e.getImage());
				e.setImage(null);

			}
			chess[linhaChegada][colunChegada] = peca;
			chess[l][c] = String.valueOf(quadrado);
			verificaBolinha();
		} else {
			System.out.println("Jogada Invalida!");
			MovimentoPeca.validacao = false;
		}

		super.setMovimentou(movimentouOuComeu);

	}

	public void verificaBolinha() {
		for (int l = 0; l < 8; l++) {
			for (int c = 0; c < 8; c++) {
				try {
					tirarPecaASerComida(l, c);
				} catch (NullPointerException e) {

				}
				if (chess[l][c].equals(String.valueOf(bolinha))) {
					chess[l][c] = String.valueOf(quadrado);
					tirarBolinha(l, c);
				}
			}
		}
	}

	public void setQueenPane(AnchorPane pane) {
		this.pane = pane;
		super.setAnchorPane(this.pane);
		;
	}
}
