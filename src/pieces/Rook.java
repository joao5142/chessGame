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

public class Rook extends Piece {
	private static boolean movimentouOuComeu = false;

	private boolean v = true;
	boolean veri = false;

	String copiaChess[][];

	public Rook(String chess[][], int vez) {
		super(chess, vez);
	}

	public Rook(int vez) {
		super(vez);
	}

	@Override
	public boolean xeque(String posiChegada) {
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pChegada[0];
		int c = pChegada[1];

		boolean peca;

		int qtd = 0;
		String p = (vez % 2 == 1) ? String.valueOf(Chess.blackKing) : String.valueOf(Chess.whiteKing);

		for (int i = l; i >= 0; i--) {
			peca = (vez % 2 == 1) ? verificaMovimentoPreto(i, c) : verificaMovimentoBranca(i, c);
			if (peca) {
				qtd++;
				if (qtd == 1) {
					if (chess[i][c].equals(p)) {

						for (int b = l - 1; b >= 0; b--) {
							boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(b, c)
									: verificaMovimentoPreto1(b, c);
							if (veri) {
								return false;
							}
						}
						return true;
					}
				}
			}

		}

		for (int i = l; i <= 7; i++) {
			peca = (vez % 2 == 1) ? verificaMovimentoPreto(i, c) : verificaMovimentoBranca(i, c);
			if (peca) {
				qtd++;
				if (qtd == 1) {
					if (chess[i][c].equals(p)) {

						for (int b = l + 1; b <= 7; b++) {
							boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(b, c)
									: verificaMovimentoPreto1(b, c);
							if (veri) {
								return false;
							}
						}
						return true;
					}
				}

			}
		}

		for (int i = c; i >= 0; i--) {
			peca = (vez % 2 == 1) ? verificaMovimentoPreto(l, i) : verificaMovimentoBranca(l, i);
			if (peca) {
				qtd++;
				if (qtd == 1) {
					if (chess[l][i].equals(p)) {

						for (int b = c - 1; b >= 0; b--) {
							boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(l, b)
									: verificaMovimentoPreto1(l, b);
							if (veri) {
								return false;
							}
						}
						return true;
					}
				}

			}
		}

		for (int i = c; i <= 7; i++) {
			peca = (vez % 2 == 1) ? verificaMovimentoPreto(l, i) : verificaMovimentoBranca(l, i);
			if (peca) {
				qtd++;
				if (qtd == 1) {
					if (chess[l][i].equals(p)) {

						for (int b = c + 1; b <= 7; b++) {
							boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(l, b)
									: verificaMovimentoPreto1(l, b);
							if (veri) {
								return false;
							}
						}
						return true;
					}
				}
			}
		}

		return false;

	}

	@Override
	public boolean xeque(String posiChegada, String chess[][]) {
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pChegada[0];
		int c = pChegada[1];

		boolean peca;

		int qtd = 0;
		String p = (vez % 2 == 1) ? String.valueOf(Chess.blackKing) : String.valueOf(Chess.whiteKing);

		for (int i = l; i >= 0; i--) {
			peca = (vez % 2 == 1) ? verificaMovimentoPreto(i, c) : verificaMovimentoBranca(i, c);
			if (peca) {
				qtd++;
				if (qtd == 1) {
					if (chess[i][c].equals(p)) {

						for (int b = l - 1; b >= 0; b--) {
							boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(b, c)
									: verificaMovimentoPreto1(b, c);
							if (veri) {
								return false;
							}
						}
						return true;
					}
				}
			}

		}

		for (int i = l; i <= 7; i++) {
			peca = (vez % 2 == 1) ? verificaMovimentoPreto(i, c) : verificaMovimentoBranca(i, c);
			if (peca) {
				qtd++;
				if (qtd == 1) {
					if (chess[i][c].equals(p)) {

						for (int b = l + 1; b <= 7; b++) {
							boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(b, c)
									: verificaMovimentoPreto1(b, c);
							if (veri) {
								return false;
							}
						}
						return true;
					}
				}

			}
		}

		for (int i = c; i >= 0; i--) {
			peca = (vez % 2 == 1) ? verificaMovimentoPreto(l, i) : verificaMovimentoBranca(l, i);
			if (peca) {
				qtd++;
				if (qtd == 1) {
					if (chess[l][i].equals(p)) {

						for (int b = c - 1; b >= 0; b--) {
							boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(l, b)
									: verificaMovimentoPreto1(l, b);
							if (veri) {
								return false;
							}
						}
						return true;
					}
				}

			}
		}

		for (int i = c; i <= 7; i++) {
			peca = (vez % 2 == 1) ? verificaMovimentoPreto(l, i) : verificaMovimentoBranca(l, i);
			if (peca) {
				qtd++;
				if (qtd == 1) {
					if (chess[l][i].equals(p)) {

						for (int b = c + 1; b <= 7; b++) {
							boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(l, b)
									: verificaMovimentoPreto1(l, b);
							if (veri) {
								return false;
							}
						}
						return true;
					}
				}
			}
		}

		return false;

	}

	@Override
	public List<String> sairXeque(int vez, String pecaQueDeuXeque, String posiPecaQueDeuXeque, String[][] chess,
			ImageView img, String posiRei) {

		String posiA = null;
		String posiC;
		Piece p;
		List<String> l = new ArrayList<String>();
		String[][] chessVerdadeiro = Piece.clonar(chess);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (vez % 2 == 1) {
					if (chess[i][j].equals(String.valueOf(Chess.whiteRook))
							|| chess[i][j].equals(String.valueOf(Chess.whiteQueen))) {
						p = new Rook(vez);
						int coluna = 0;
						int c = 0;

						try {
							posiA = MovimentoPeca.reConverterPosicao(i, j);

						} catch (Exception e) {
							// e.printStackTrace();
						}

						c = j;
						try {
							for (int i1 = i - 1; i1 >= 0; i1--) { // linha diminui , coluna permanece a mesma
								try {
									veri3(p, posiA, l, i1, c, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
											pecaQueDeuXeque, posiRei);
								} catch (Exception e) {

								} finally {
									chess = Piece.clonar(chessVerdadeiro);

								}

							}

						} catch (ArrayIndexOutOfBoundsException e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {

							for (int i1 = i + 1; i1 < 9; i1++) { // linha aumenta , coluna permamece a mesma
								try {
									veri3(p, posiA, l, i1, c, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
											pecaQueDeuXeque, posiRei);
								} catch (Exception e) {

								} finally {
									chess = Piece.clonar(chessVerdadeiro);

								}

							}
						} catch (ArrayIndexOutOfBoundsException e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {

							for (int i1 = c + 1; i1 < 9; i1++) { // coluna aumenta
								try {
									veri3(p, posiA, l, i, i1, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
											pecaQueDeuXeque, posiRei);
								} catch (Exception e) {

								} finally {
									chess = Piece.clonar(chessVerdadeiro);

								}

							}
						} catch (ArrayIndexOutOfBoundsException e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {

							for (int i1 = c - 1; i1 >= 0; i1--) { // coluna diminui,linha é a mesma
								try {
									veri3(p, posiA, l, i, i1, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
											pecaQueDeuXeque, posiRei);
								} catch (Exception e) {

								} finally {
									chess = Piece.clonar(chessVerdadeiro);

								}

							}
						} catch (ArrayIndexOutOfBoundsException e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

					}

				} else if (chess[i][j].equals(String.valueOf(Chess.blackRook))
						|| chess[i][j].equals(String.valueOf(Chess.blackQueen))) {

					p = new Rook(vez);
					int coluna = 0;
					int c = 0;

					try {
						posiA = MovimentoPeca.reConverterPosicao(i, j);

					} catch (Exception e) {
						// e.printStackTrace();
					}

					c = j;
					try {
						for (int i1 = i - 1; i1 >= 0; i1--) { // linha diminui , coluna permanece a mesma
							try {
								veri3(p, posiA, l, i1, c, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
										pecaQueDeuXeque, posiRei);
							} catch (Exception e) {

							} finally {
								chess = Piece.clonar(chessVerdadeiro);

							}

						}

					} catch (ArrayIndexOutOfBoundsException e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {

						for (int i1 = i + 1; i1 < 9; i1++) { // linha aumenta , coluna permamece a mesma
							try {
								veri3(p, posiA, l, i1, c, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
										pecaQueDeuXeque, posiRei);
							} catch (Exception e) {

							} finally {
								chess = Piece.clonar(chessVerdadeiro);

							}

						}
					} catch (ArrayIndexOutOfBoundsException e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {

						for (int i1 = c + 1; i1 < 9; i1++) { // coluna aumenta
							try {
								veri3(p, posiA, l, i, i1, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
										pecaQueDeuXeque, posiRei);
							} catch (Exception e) {

							} finally {
								chess = Piece.clonar(chessVerdadeiro);

							}

						}
					} catch (ArrayIndexOutOfBoundsException e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {

						for (int i1 = c - 1; i1 >= 0; i1--) { // coluna diminui,linha é a mesma
							try {
								veri3(p, posiA, l, i, i1, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
										pecaQueDeuXeque, posiRei);
							} catch (Exception e) {

							} finally {
								chess = Piece.clonar(chessVerdadeiro);

							}

						}
					} catch (ArrayIndexOutOfBoundsException e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

				}

			}

		}
		return l;

	}

	public void veri3(Piece p, String posiA, List<String> l, int i, int j, int a, int b, String[][] chess,
			String[][] chessVerdadeiro, String posiPecaQueDeuXeque, String pecaQueDeuXeque, String posiRei) {

		String posiC = MovimentoPeca.reConverterPosicao(i, j);

		p.movimentoDaPeca2(posiA, chess);
		// System.out.println("O xadrez com o movmento da torre\n\n\n");
		mostrarXadrez(chess);
		p.movimentarPeca1(posiA, posiC, chess);

		try {
			p.verificaBolinha(chess);
			verificaBolinha(chess);
			verificaBolinha();

		} catch (Exception e) {

		}

		// System.out.println("\n\nXadrez na verificacao do Bispo\n\n");
		// mostrarXadrez(chess);

		Piece pa = MovimentoPeca.jogar1(pecaQueDeuXeque);

		pa.movimentoDaPeca1(posiPecaQueDeuXeque, chess);
		// System.out.println("\n\nMovimentando a peça contra o bispo\n\n");
	    mostrarXadrez(chess);
		int[] c = MovimentoPeca.converterPosicao(posiRei);
		int li = c[0];
		int co = c[1];

		if (chess[li][co].equals(String.valueOf(Chess.bolinha))) {

		} else {
			l.add(posiC);
		}

		try {
			pa.verificaBolinha(chess);
			verificaBolinha(chess);
			verificaBolinha();

		} catch (Exception e) {

		}

	}

	@Override
	public void movimentoDaPeca(String posiAtual, List<String> posisSairXeque) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);

		int l = pAtual[0];
		int c = pAtual[1];
		int a = 0;

		String posiChegada;
		boolean verdade;

		for (int i = l - 1; i >= 0; i--) { // linha diminui , coluna permanece a mesma
			try {
				posiChegada = MovimentoPeca.reConverterPosicao(i, c);
				verdade = pecaInimiga(posiAtual, posiChegada);

				if (verdade) {
					setPecaAmeaçada(i, c, this.getImageView(posiChegada));
					// chess[l - 2][c - 1] = String.valueOf(bolinha);
				}
			} catch (Exception e) {

			}

			if (chess[i][c].equals(String.valueOf(quadrado))) {
				chess[i][c] = String.valueOf(bolinha);
				setBolinha(i, c);
				a = i;
			} else {
				break;
			}

		}

		for (int i = l + 1; i < 9; i++) { // linha aumenta , coluna permamece a mesma

			try {
				posiChegada = MovimentoPeca.reConverterPosicao(i, c);
				verdade = pecaInimiga(posiAtual, posiChegada);

				if (verdade) {
					setPecaAmeaçada(i, c, this.getImageView(posiChegada));
					// chess[l - 2][c - 1] = String.valueOf(bolinha);
				}
			} catch (Exception e) {

			}

			if (chess[i][c].equals(String.valueOf(quadrado))) {
				chess[i][c] = String.valueOf(bolinha);
				setBolinha(i, c);
			} else {
				break;
			}

		}

		for (int i = c + 1; i < 9; i++) { // coluna aumenta

			try {

				posiChegada = MovimentoPeca.reConverterPosicao(l, i);
				verdade = pecaInimiga(posiAtual, posiChegada);

				if (verdade) {
					setPecaAmeaçada(l, i, this.getImageView(posiChegada));
					// chess[l - 2][c - 1] = String.valueOf(bolinha);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (chess[l][i].equals(String.valueOf(quadrado))) {
				chess[l][i] = String.valueOf(bolinha);
				setBolinha(l, i);

			} else {
				break;
			}

		}

		for (int i = c - 1; i >= 0; i--) { // coluna diminui,linha é a mesma
			try {
				posiChegada = MovimentoPeca.reConverterPosicao(l, i);
				verdade = pecaInimiga(posiAtual, posiChegada);

				if (verdade) {
					setPecaAmeaçada(l, i, this.getImageView(posiChegada));
					// chess[l - 2][c - 1] = String.valueOf(bolinha);
				}
			} catch (Exception e) {

			}

			if (chess[l][i].equals(String.valueOf(quadrado))) {
				chess[l][i] = String.valueOf(bolinha);
				setBolinha(l, i);
			} else {
				break;
			}

		}
	}

	@Override
	public void movimentoDaPeca1(String posiAtual, String chess[][]) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);

		int l = pAtual[0];
		int c = pAtual[1];
		int a = 0;

		String posiChegada;
		boolean verdade = false;

		for (int i = l - 1; i >= 0; i--) { // linha diminui , coluna permanece a mesma
			try {
				posiChegada = MovimentoPeca.reConverterPosicao(i, c);
				if (vez % 2 == 0) {
					if (chess[i][c].equals(String.valueOf(Chess.blackKing))) {
						verdade = true;
					}
				} else {
					if (chess[i][c].equals(String.valueOf(Chess.whiteKing))) {
						verdade = true;
					}
				}

				if (verdade) {
					// setPecaAmeaçada(i, c, this.getImageView(posiChegada));
					chess[i][c] = String.valueOf(bolinha);
				}
			} catch (Exception e) {

			}

			if (chess[i][c].equals(String.valueOf(quadrado))) {
				chess[i][c] = String.valueOf(bolinha);
				// setBolinha(i, c);
				a = i;
			} else {
				break;
			}

		}

		for (int i = l + 1; i < 9; i++) { // linha aumenta , coluna permamece a mesma

			try {
				posiChegada = MovimentoPeca.reConverterPosicao(i, c);
				if (vez % 2 == 0) {
					if (chess[i][c].equals(String.valueOf(Chess.blackKing))) {
						verdade = true;
					}
				} else {
					if (chess[i][c].equals(String.valueOf(Chess.whiteKing))) {
						verdade = true;
					}
				}

				if (verdade) {
					// setPecaAmeaçada(i, c, this.getImageView(posiChegada));
					chess[i][c] = String.valueOf(bolinha);
				}
			} catch (Exception e) {

			}

			if (chess[i][c].equals(String.valueOf(quadrado))) {
				chess[i][c] = String.valueOf(bolinha);
				// setBolinha(i, c);
			} else {
				break;
			}

		}

		for (int i = c + 1; i < 9; i++) { // coluna aumenta

			try {

				posiChegada = MovimentoPeca.reConverterPosicao(l, i);
				if (vez % 2 == 0) {
					if (chess[l][i].equals(String.valueOf(Chess.blackKing))) {
						verdade = true;
					}
				} else {
					if (chess[l][i].equals(String.valueOf(Chess.whiteKing))) {
						verdade = true;
					}
				}
				if (verdade) {
					// setPecaAmeaçada(l, i, this.getImageView(posiChegada));
					chess[l][i] = String.valueOf(bolinha);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (chess[l][i].equals(String.valueOf(quadrado))) {
				chess[l][i] = String.valueOf(bolinha);
				// setBolinha(l, i);

			} else {
				break;
			}

		}

		for (int i = c - 1; i >= 0; i--) { // coluna diminui,linha é a mesma
			try {
				posiChegada = MovimentoPeca.reConverterPosicao(l, i);
				if (vez % 2 == 0) {
					if (chess[l][i].equals(String.valueOf(Chess.blackKing))) {
						verdade = true;
					}
				} else {
					if (chess[l][i].equals(String.valueOf(Chess.whiteKing))) {
						verdade = true;
					}
				}

				if (verdade) {
					// setPecaAmeaçada(l, i, this.getImageView(posiChegada));
					chess[l][i] = String.valueOf(bolinha);
				}
			} catch (Exception e) {

			}

			if (chess[l][i].equals(String.valueOf(quadrado))) {
				chess[l][i] = String.valueOf(bolinha);
				// setBolinha(l, i);
			} else {
				break;
			}

		}

	}

	@Override
	public void movimentoDaPeca2(String posiAtual, String chess[][]) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);

		int l = pAtual[0];
		int c = pAtual[1];
		int a = 0;

		String posiChegada;
		boolean verdade = false;

		for (int i = l - 1; i >= 0; i--) { // linha diminui , coluna permanece a mesma

			if (chess[i][c].equals(String.valueOf(quadrado))) {
				chess[i][c] = String.valueOf(bolinha);
				// setBolinha(i, c);
				a = i;
			} else {
				break;
			}

		}

		for (int i = l + 1; i < 9; i++) { // linha aumenta , coluna permamece a mesma

			if (chess[i][c].equals(String.valueOf(quadrado))) {
				chess[i][c] = String.valueOf(bolinha);
				// setBolinha(i, c);
			} else {
				break;
			}

		}

		for (int i = c + 1; i < 9; i++) { // coluna aumenta

			if (chess[l][i].equals(String.valueOf(quadrado))) {
				chess[l][i] = String.valueOf(bolinha);
				// setBolinha(l, i);

			} else {
				break;
			}

		}

		for (int i = c - 1; i >= 0; i--) { // coluna diminui,linha é a mesma

			if (chess[l][i].equals(String.valueOf(quadrado))) {
				chess[l][i] = String.valueOf(bolinha);
				// setBolinha(l, i);
			} else {
				break;
			}

		}

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

		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteRook) : String.valueOf(Chess.blackRook);

		if (this.getAnchorPane() != null) {
			AnchorPane pane = this.getAnchorPane();

			ImageView i = (ImageView) pane.lookup("#" + posiChegada);

			ImageView e = (ImageView) pane.lookup("#" + posiAtual);

			i.setImage(e.getImage());
			e.setImage(null);

			Pawn.setQtdCasas(0);
			Pawn.setP(null);

			if (vez % 2 == 1) {
				if (posiAtual.equals("a1")) {
					MovimentoPeca.setPodeRookGrandeBranco(false);
				} else if (posiAtual.equals("h1")) {
					MovimentoPeca.setPodeRookPequenoBranco(false);
				}
			} else {
				if (posiAtual.equals("a8")) {
					MovimentoPeca.setPodeRookGrandePreto(false);
				} else if (posiAtual.equals("h8")) {
					MovimentoPeca.setPodeRookPequenoPreto(false);
				}
			}

			/*
			 * if(vez%2==1) { m.setPecaComida1(m.getrBlack(),m.getContRB()); }else {
			 * m.setPecaComida1( m.getrWhite(),m.getContRW()); }
			 */
		}

		movimentouOuComeu = true;

		chess[lChegada][cChegada] = peca;
		chess[l][c] = String.valueOf(quadrado);

		verificaBolinha();
	}

	@Override
	public void comerPeca1(String posiAtual, String posiChegada, String chess[][]) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pAtual[0];
		int c = pAtual[1];

		int lChegada = pChegada[0];
		int cChegada = pChegada[1];
		String peca;

		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteRook) : String.valueOf(Chess.blackRook);

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

		boolean ver = (vez % 2 == 1) ? verificaMovimentoPreto1(lChegada, cChegada)
				: verificaMovimentoBranca1(lChegada, cChegada);

		boolean ver1 = verificaJogada(posiAtual, posiChegada);
		if (ver && ver1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean pecaInimiga1(String posiAtual, String posiChegada, String chess[][]) {

		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pAtual[0];
		int c = pAtual[1];

		int lChegada = pChegada[0];
		int cChegada = pChegada[1];

		boolean ver = (vez % 2 == 1) ? verificaMovimentoPreto1(lChegada, cChegada)
				: verificaMovimentoBranca1(lChegada, cChegada);

		boolean ver1 = verificaJogada(posiAtual, posiChegada);
		if (ver && ver1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean pecaInimiga2(String posiAtual, String posiChegada, String chess[][]) {

		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pAtual[0];
		int c = pAtual[1];

		int lChegada = pChegada[0];
		int cChegada = pChegada[1];

		boolean ver = (vez % 2 == 1) ? verificaMovimentoPreto2(lChegada, cChegada, chess)
				: verificaMovimentoBranca2(lChegada, cChegada, chess);

		boolean ver1 = verificaJogada(posiAtual, posiChegada);
		if (ver && ver1) {
			return true;
		}
		return false;
	}

	public boolean verificaJogada(String posiChegada) {
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);
		int linhaChegada = pChegada[0];
		int colunChegada = pChegada[1];

		return chess[linhaChegada][colunChegada].equals(String.valueOf(bolinha));
	}

	public boolean verificaJogada1(String posiChegada, String chess[][]) {
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

		int qtd = 0;
		int linha1 = 0, coluna1 = 0;
		boolean peca;
		if (linhaChegada < l) {

			for (int i = l; i >= linhaChegada; i--) {
				peca = (vez % 2 == 1) ? verificaMovimentoPreto1(i, c) : verificaMovimentoBranca1(i, c);
				if (peca) {
					qtd++;
					if (qtd == 1) {
						linha1 = i;
						if (linhaChegada == linha1 && colunChegada == c) {
							for (int b = l - 1; b >= linhaChegada; b--) {
								boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(b, c)
										: verificaMovimentoPreto1(b, c);
								if (veri) {
									return false;
								}
							}
							return true;
						}
					}

				}

			}
		} else {
			for (int i = l; i <= linhaChegada; i++) {
				peca = (vez % 2 == 1) ? verificaMovimentoPreto1(i, c) : verificaMovimentoBranca1(i, c);
				if (peca) {
					qtd++;
					if (qtd == 1) {
						linha1 = i;
						if (linhaChegada == linha1 && colunChegada == c) {
							for (int b = l + 1; b <= linhaChegada; b++) {
								boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(b, c)
										: verificaMovimentoPreto1(b, c);
								if (veri) {
									return false;
								}
							}
							return true;
						}

					}
				}

			}

		}

		if (linhaChegada == l) {
			if (colunChegada < c) {
				for (int i = c; i >= colunChegada; i--) {
					peca = (vez % 2 == 1) ? verificaMovimentoPreto1(l, i) : verificaMovimentoBranca1(l, i);
					if (peca) {
						qtd++;
						if (qtd == 1) {
							coluna1 = i;
							if (colunChegada == coluna1) {
								for (int b = c - 1; b >= colunChegada; b--) {
									boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(l, b)
											: verificaMovimentoPreto1(l, b);
									if (veri) {
										return false;
									}
								}
								return true;
							}

						}
					}
				}
			} else {
				for (int i = c; i <= colunChegada; i++) {
					peca = (vez % 2 == 1) ? verificaMovimentoPreto1(l, i) : verificaMovimentoBranca1(l, i);
					if (peca) {
						qtd++;
						if (qtd == 1) {
							coluna1 = i;
							if (colunChegada == coluna1) {
								for (int b = c + 1; b <= colunChegada; b++) {
									boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(l, b)
											: verificaMovimentoPreto1(l, b);
									if (veri) {
										return false;
									}
								}
								return true;
							}

						}
					}
				}
			}

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
		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteRook) : String.valueOf(Chess.blackRook);
		boolean verificaMovimento = (vez % 2 == 1) ? verificaMovimentoBranca1(linhaChegada, colunChegada)
				: verificaMovimentoPreto1(linhaChegada, colunChegada);

		if (verificaMovimento) {
			System.out.println("Jogada Invalida!");
			MovimentoPeca.validacao = false;

		} else if (verificaJogada(posiChegada)) {
			Pawn.setQtdCasas(0);
			Pawn.setP(null);

			if (vez % 2 == 1) {
				if (posiAtual.equals("a1")) {
					MovimentoPeca.setPodeRookGrandeBranco(false);
				} else if (posiAtual.equals("h1")) {
					MovimentoPeca.setPodeRookPequenoBranco(false);
				}
			} else {
				if (posiAtual.equals("a8")) {
					MovimentoPeca.setPodeRookGrandePreto(false);
				} else if (posiAtual.equals("h8")) {
					MovimentoPeca.setPodeRookPequenoPreto(false);
				}
			}

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

	@Override
	public void movimentarPeca1(String posiAtual, String posiChegada, String chess[][]) {

		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		String pecaSelecionada = chess[pAtual[0]][pAtual[1]];

		int l = pAtual[0];
		int c = pAtual[1];
		int linhaChegada = pChegada[0];
		int colunChegada = pChegada[1];

		String peca;
		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteRook) : String.valueOf(Chess.blackRook);
		boolean verificaMovimento = (vez % 2 == 1) ? verificaMovimentoBranca1(linhaChegada, colunChegada)
				: verificaMovimentoPreto1(linhaChegada, colunChegada);

		if (verificaMovimento) {
			System.out.println("Jogada Invalida!");

		} else if (verificaJogada1(posiChegada, chess)) {

			chess[linhaChegada][colunChegada] = peca;
			chess[l][c] = String.valueOf(quadrado);
			try {
				verificaBolinha(chess);
				verificaBolinha();
			} catch (Exception e) {

			}
		} else {
			System.out.println("Jogada Invalida!");
			try {
				verificaBolinha(chess);
				verificaBolinha();
			} catch (Exception e) {

			}

		}

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

}
