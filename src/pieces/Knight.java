package pieces;

import chess.Chess;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import utils.MovimentoPeca;
import utils.MovimentosEspeciais;

import javax.swing.JOptionPane;

import static pieces.Piece.vez;
import static utils.MovimentoPeca.bolinha;
import static utils.MovimentoPeca.quadrado;
import static utils.MovimentoPeca.validacao;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

	private boolean v = true;
	boolean veri = false;
	private static boolean movimentouOuComeu = false;

	public Knight(String chess[][], int vez) {
		super(chess, vez);

	}

	public Knight(int vezDejogar) {
		super(vezDejogar);
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
					if (chess[i][j].equals(String.valueOf(Chess.whiteKnight))) {
						p = new Knight(vez);
						try {
							posiA = MovimentoPeca.reConverterPosicao(i, j);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							veri3(p, posiA, l, i, j, 2, -1, chess, chessVerdadeiro, posiPecaQueDeuXeque,
									pecaQueDeuXeque, posiRei);

						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {

							veri3(p, posiA, l, i, j, 2, 1, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
									posiRei);
						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {

							veri3(p, posiA, l, i, j, -2, 1, chess, chessVerdadeiro, posiPecaQueDeuXeque,
									pecaQueDeuXeque, posiRei);

						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {

							veri3(p, posiA, l, i, j, -2, -1, chess, chessVerdadeiro, posiPecaQueDeuXeque,
									pecaQueDeuXeque, posiRei);
						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {

							veri3(p, posiA, l, i, j, 1, 2, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
									posiRei);

						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {

							veri3(p, posiA, l, i, j, 1, -2, chess, chessVerdadeiro, posiPecaQueDeuXeque,
									pecaQueDeuXeque, posiRei);

						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {

							veri3(p, posiA, l, i, j, -1, -2, chess, chessVerdadeiro, posiPecaQueDeuXeque,
									pecaQueDeuXeque, posiRei);

						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}
						try {

							veri3(p, posiA, l, i, j, -1, 2, chess, chessVerdadeiro, posiPecaQueDeuXeque,
									pecaQueDeuXeque, posiRei);
						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}
					}

				} else if (chess[i][j].equals(String.valueOf(Chess.blackKnight))) {

					p = new Knight(vez);
					try {
						posiA = MovimentoPeca.reConverterPosicao(i, j);
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						veri3(p, posiA, l, i, j, 2, -1, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);

					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {

						veri3(p, posiA, l, i, j, 2, 1, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);
					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {

						veri3(p, posiA, l, i, j, -2, 1, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);

					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {

						veri3(p, posiA, l, i, j, -2, -1, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);
					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {

						veri3(p, posiA, l, i, j, 1, 2, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);

					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {

						veri3(p, posiA, l, i, j, 1, -2, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);

					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {

						veri3(p, posiA, l, i, j, -1, -2, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);

					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}
					try {

						veri3(p, posiA, l, i, j, -1, 2, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);
					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					/*
					 * p.movimentarPeca1(posiA, posiC, chess);
					 * 
					 * System.out.println("Xadrez na verificacao"); p.mostrarXadrez(chess);
					 * 
					 * Piece pa = MovimentoPeca.jogar1(pecaQueDeuXeque);
					 * 
					 * pa.movimentoDaPeca1(posiPecaQueDeuXeque, chess);
					 * System.out.println("Movimentando A peca\n\n"); mostrarXadrez(chess); int[] b
					 * = MovimentoPeca.converterPosicao(posiRei); int li = b[0]; int co = b[1];
					 * 
					 * if (chess[li][co].equals(String.valueOf(Chess.bolinha))) {
					 * 
					 * } else { l.add(posiC); }
					 */

				}

			}

		}
		return l;

	}

	public void veri3(Piece p, String posiA, List<String> l, int i, int j, int a, int b, String[][] chess,
			String[][] chessVerdadeiro, String posiPecaQueDeuXeque, String pecaQueDeuXeque, String posiRei) {
		try {

			String posiC = MovimentoPeca.reConverterPosicao(i + a, j + b);

			p.movimentarPeca1(posiA, posiC, chess);

			//System.out.println("Xadrez na verificacao do cavalo");
			//p.mostrarXadrez(chess);

			Piece pa = MovimentoPeca.jogar1(pecaQueDeuXeque);

			pa.movimentoDaPeca1(posiPecaQueDeuXeque, chess);
			//System.out.println("Movimentando A peca do cavalo\n\n");
			//mostrarXadrez(chess);
			int[] c = MovimentoPeca.converterPosicao(posiRei);
			int li = c[0];
			int co = c[1];

			if (chess[li][co].equals(String.valueOf(Chess.bolinha))) {

			} else {
				l.add(posiC);
			}
		} catch (Exception e) {

		} finally {
			chess = Piece.clonar(chessVerdadeiro);
		}
	}

	@Override
	public boolean xeque(String posiChegada) {
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pChegada[0];
		int c = pChegada[1];

		String p = (vez % 2 == 1) ? String.valueOf(Chess.blackKing) : String.valueOf(Chess.whiteKing);

		try {
			if (chess[l - 2][c - 1].equals(p)) {

				return true;

			}
		} catch (Exception e) {

		}
		try {
			if (chess[l - 2][c + 1].equals(p)) {

				return true;

			}
		} catch (Exception e) {

		}

		try {
			if (chess[l + 2][c - 1].equals(p)) {
				return true;

			}
		} catch (Exception e) {

		}

		try {
			if (chess[l + 2][c + 1].equals(p)) {
				return true;

			}
		} catch (Exception e) {

		}

		try {
			if (chess[l - 1][c + 2].equals(p)) {
				return true;

			}
		} catch (Exception e) {

		}

		try {
			if (chess[l - 1][c - 2].equals(p)) {
				return true;

			}
		} catch (Exception e) {

		}

		try {
			if (chess[l + 1][c - 2].equals(p)) {
				return true;

			}

		} catch (Exception e) {

		}

		try {
			if (chess[l + 1][c + 2].equals(p)) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	@Override
	public void movimentoDaPeca(String posiAtual, List<String> posisSairXeque) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);

		int l = pAtual[0];
		int c = pAtual[1];

		String posiChegada;
		boolean verdade;

		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l - 2, c - 1);
			verdade = pecaInimiga(posiAtual, posiChegada);

			System.out.println(verdade);
			if (verdade) {
				setPecaAmeaçada(l - 2, c - 1, this.getImageView(posiChegada));
				// chess[l - 2][c - 1] = String.valueOf(bolinha);
			}

			if (chess[l - 2][c - 1].equals(String.valueOf(quadrado))) {
				chess[l - 2][c - 1] = String.valueOf(bolinha);
				setBolinha(l - 2, c - 1);
			}
		} catch (Exception e) {

		}
		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l - 2, c + 1);
			verdade = pecaInimiga(posiAtual, posiChegada);

			System.out.println(verdade);
			if (verdade) {
				setPecaAmeaçada(l - 2, c + 1, this.getImageView(posiChegada));
				// chess[l - 2][c + 1] = String.valueOf(bolinha);
			}

			if (chess[l - 2][c + 1].equals(String.valueOf(quadrado))) {
				chess[l - 2][c + 1] = String.valueOf(bolinha);
				setBolinha(l - 2, c + 1);
			}
		} catch (Exception e) {

		}

		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l + 2, c - 1);
			verdade = pecaInimiga(posiAtual, posiChegada);
			if (verdade) {
				setPecaAmeaçada(l + 2, c - 1, this.getImageView(posiChegada));
				// chess[l - 2][c - 1] = String.valueOf(bolinha);
			}

			if (chess[l + 2][c - 1].equals(String.valueOf(quadrado))) {
				chess[l + 2][c - 1] = String.valueOf(bolinha);
				setBolinha(l + 2, c - 1);

			}
		} catch (Exception e) {

		}

		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l + 2, c + 1);
			verdade = pecaInimiga(posiAtual, posiChegada);
			if (verdade) {
				setPecaAmeaçada(l + 2, c + 1, this.getImageView(posiChegada));
				// chess[l - 2][c - 1] = String.valueOf(bolinha);
			}

			if (chess[l + 2][c + 1].equals(String.valueOf(quadrado))) {
				chess[l + 2][c + 1] = String.valueOf(bolinha);
				setBolinha(l + 2, c + 1);

			}
		} catch (Exception e) {

		}

		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c + 2);
			verdade = pecaInimiga(posiAtual, posiChegada);
			if (verdade) {
				setPecaAmeaçada(l - 1, c + 2, this.getImageView(posiChegada));
				// chess[l - 2][c - 1] = String.valueOf(bolinha);
			}

			if (chess[l - 1][c + 2].equals(String.valueOf(quadrado))) {
				chess[l - 1][c + 2] = String.valueOf(bolinha);
				setBolinha(l - 1, c + 2);
			}
		} catch (Exception e) {

		}

		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c - 2);
			verdade = pecaInimiga(posiAtual, posiChegada);
			if (verdade) {
				setPecaAmeaçada(l - 1, c - 2, this.getImageView(posiChegada));
				// chess[l - 2][c - 1] = String.valueOf(bolinha);
			}

			if (chess[l - 1][c - 2].equals(String.valueOf(quadrado))) {
				chess[l - 1][c - 2] = String.valueOf(bolinha);
				setBolinha(l - 1, c - 2);
			}
		} catch (Exception e) {

		}

		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c - 2);
			verdade = pecaInimiga(posiAtual, posiChegada);
			if (verdade) {
				setPecaAmeaçada(l + 1, c - 2, this.getImageView(posiChegada));
				// chess[l - 2][c - 1] = String.valueOf(bolinha);
			}

			if (chess[l + 1][c - 2].equals(String.valueOf(quadrado))) {
				chess[l + 1][c - 2] = String.valueOf(bolinha);
				setBolinha(l + 1, c - 2);

			}

		} catch (Exception e) {

		}

		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c + 2);
			verdade = pecaInimiga(posiAtual, posiChegada);
			if (verdade) {
				setPecaAmeaçada(l + 1, c + 2, this.getImageView(posiChegada));
				// chess[l - 2][c - 1] = String.valueOf(bolinha);
			}

			if (chess[l + 1][c + 2].equals(String.valueOf(quadrado))) {
				chess[l + 1][c + 2] = String.valueOf(bolinha);
				setBolinha(l + 1, c + 2);
			}
		} catch (Exception e) {

		}

	}

	@Override
	public void movimentoDaPeca1(String posiAtual, String chess[][]) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);

		int l = pAtual[0];
		int c = pAtual[1];

		String posiChegada;
		boolean verdade = false;

		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l - 2, c - 1);
			if (vez % 2 == 0) {
				if (chess[l - 2][c - 1].equals(String.valueOf(Chess.blackKing))) {
					verdade = true;
				}
			} else {
				if (chess[l - 2][c - 1].equals(String.valueOf(Chess.whiteKing))) {
					verdade = true;
				}
			}
			if (verdade) {
				// setPecaAmeaçada(l - 2, c - 1, this.getImageView(posiChegada));
				chess[l - 2][c - 1] = String.valueOf(bolinha);
			}

			if (chess[l - 2][c - 1].equals(String.valueOf(quadrado))) {
				chess[l - 2][c - 1] = String.valueOf(bolinha);
				// setBolinha(l - 2, c - 1);
			}
		} catch (Exception e) {

		}
		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l - 2, c + 1);
			if (vez % 2 == 0) {
				if (chess[l - 2][c + 1].equals(String.valueOf(Chess.blackKing))) {
					verdade = true;
				}
			} else {
				if (chess[l - 2][c + 1].equals(String.valueOf(Chess.whiteKing))) {
					verdade = true;
				}
			}
			if (verdade) {
				// setPecaAmeaçada(l - 2, c + 1, this.getImageView(posiChegada));
				chess[l - 2][c + 1] = String.valueOf(bolinha);
			}

			if (chess[l - 2][c + 1].equals(String.valueOf(quadrado))) {
				chess[l - 2][c + 1] = String.valueOf(bolinha);
				// setBolinha(l - 2, c + 1);
			}
		} catch (Exception e) {

		}

		try {
			if (vez % 2 == 0) {
				if (chess[l + 2][c - 1].equals(String.valueOf(Chess.blackKing))) {
					verdade = true;
				}
			} else {
				if (chess[l + 2][c - 1].equals(String.valueOf(Chess.whiteKing))) {
					verdade = true;
				}
			}

			if (verdade) {
				// setPecaAmeaçada(l + 2, c - 1, this.getImageView(posiChegada));
				chess[l + 2][c - 1] = String.valueOf(bolinha);
			}

			if (chess[l + 2][c - 1].equals(String.valueOf(quadrado))) {
				chess[l + 2][c - 1] = String.valueOf(bolinha);
				// setBolinha(l + 2, c - 1);

			}
		} catch (Exception e) {

		}

		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l + 2, c + 1);
			if (vez % 2 == 0) {
				if (chess[l + 2][c + 1].equals(String.valueOf(Chess.blackKing))) {
					verdade = true;
				}
			} else {
				if (chess[l + 2][c + 1].equals(String.valueOf(Chess.whiteKing))) {
					verdade = true;
				}
			}

			if (verdade) {
				// setPecaAmeaçada(l + 2, c - 1, this.getImageView(posiChegada));
				chess[l + 2][c + 1] = String.valueOf(bolinha);
			}

			if (chess[l + 2][c + 1].equals(String.valueOf(quadrado))) {
				chess[l + 2][c + 1] = String.valueOf(bolinha);
				// setBolinha(l + 2, c + 1);

			}
		} catch (Exception e) {

		}

		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c + 2);
			if (vez % 2 == 0) {
				if (chess[l - 1][c + 2].equals(String.valueOf(Chess.blackKing))) {
					verdade = true;
				}
			} else {
				if (chess[l - 1][c + 2].equals(String.valueOf(Chess.whiteKing))) {
					verdade = true;
				}
			}

			if (verdade) {
				// setPecaAmeaçada(l + 2, c - 1, this.getImageView(posiChegada));
				chess[l - 1][c + 2] = String.valueOf(bolinha);
			} // chess[l - 2][c - 1] = String.valueOf(bolinha);

			if (chess[l - 1][c + 2].equals(String.valueOf(quadrado))) {
				chess[l - 1][c + 2] = String.valueOf(bolinha);
				// setBolinha(l - 1, c + 2);
			}
		} catch (Exception e) {

		}

		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c - 2);
			if (vez % 2 == 0) {
				if (chess[l - 1][c - 2].equals(String.valueOf(Chess.blackKing))) {
					verdade = true;
				}
			} else {
				if (chess[l - 1][c - 2].equals(String.valueOf(Chess.whiteKing))) {
					verdade = true;
				}
			}

			if (verdade) {
				// setPecaAmeaçada(l + 2, c - 1, this.getImageView(posiChegada));
				chess[l - 1][c - 2] = String.valueOf(bolinha);
			} // chess[l - 2][c - 1] = String.valueOf(bolinha);

			if (chess[l - 1][c - 2].equals(String.valueOf(quadrado))) {
				chess[l - 1][c - 2] = String.valueOf(bolinha);
				// setBolinha(l - 1, c - 2);
			}
		} catch (Exception e) {

		}

		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c - 2);
			if (vez % 2 == 0) {
				if (chess[l + 1][c - 2].equals(String.valueOf(Chess.blackKing))) {
					verdade = true;
				}
			} else {
				if (chess[l + 1][c - 2].equals(String.valueOf(Chess.whiteKing))) {
					verdade = true;
				}
			}

			if (verdade) {
				// setPecaAmeaçada(l + 2, c - 1, this.getImageView(posiChegada));
				chess[l + 1][c - 2] = String.valueOf(bolinha);
			} // chess[l - 2][c - 1] = String.valueOf(bolinha);

			if (chess[l + 1][c - 2].equals(String.valueOf(quadrado))) {
				chess[l + 1][c - 2] = String.valueOf(bolinha);
				// setBolinha(l + 1, c - 2);

			}

		} catch (Exception e) {

		}

		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c + 2);
			if (vez % 2 == 0) {
				if (chess[l + 1][c + 2].equals(String.valueOf(Chess.blackKing))) {
					verdade = true;
				}
			} else {
				if (chess[l + 1][c + 2].equals(String.valueOf(Chess.whiteKing))) {
					verdade = true;
				}
			}

			if (verdade) {
				// setPecaAmeaçada(l + 2, c - 1, this.getImageView(posiChegada));
				chess[l + 1][c + 2] = String.valueOf(bolinha);
			} // chess[l - 2][c - 1] = String.valueOf(bolinha);

			if (chess[l + 1][c + 2].equals(String.valueOf(quadrado))) {
				chess[l + 1][c + 2] = String.valueOf(bolinha);
				// setBolinha(l + 1, c + 2);
			}
		} catch (Exception e) {

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

		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteKnight) : String.valueOf(Chess.blackKnight);

		if (this.getAnchorPane() != null) {
			AnchorPane pane = this.getAnchorPane();

			ImageView i = (ImageView) pane.lookup("#" + posiChegada);

			ImageView e = (ImageView) pane.lookup("#" + posiAtual);

			i.setImage(e.getImage());
			e.setImage(null);

			Pawn.setQtdCasas(0);
			Pawn.setP(null);

			/*
			 * if(vez%2==1) { m.setPecaComida1(m.getKiBlack(),m.getContKB()); }else {
			 * m.setPecaComida1( m.getKiWhite(),m.getContKW()); }
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

		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteKnight) : String.valueOf(Chess.blackKnight);

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
		return (ver && ver1);
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
		return (ver && ver1);
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
		return (ver && ver1);
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

		veri = false;

		if (linhaChegada == (l - 2) && colunChegada == (c - 1)) {
			veri = true;
		} else if (linhaChegada == (l - 2) && colunChegada == (c + 1)) {
			veri = true;
		} else if (linhaChegada == (l + 2) && colunChegada == (c - 1)) {
			veri = true;
		} else if (linhaChegada == (l + 2) && colunChegada == (c + 1)) {
			veri = true;
		} else if (linhaChegada == (l - 1) && colunChegada == (c + 2)) {
			veri = true;
		} else if (linhaChegada == (l - 1) && colunChegada == (c - 2)) {
			veri = true;
		} else if (linhaChegada == (l + 1) && colunChegada == (c - 2)) {
			veri = true;
		} else if (linhaChegada == (l + 1) && colunChegada == (c + 2)) {
			veri = true;
		}
		return veri;
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
		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteKnight) : String.valueOf(Chess.blackKnight);
		boolean verificaMovimento = (vez % 2 == 1) ? verificaMovimentoBranca(linhaChegada, colunChegada)
				: verificaMovimentoPreto(linhaChegada, colunChegada);

		if (verificaMovimento) {
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
		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteKnight) : String.valueOf(Chess.blackKnight);
		boolean verificaMovimento = (vez % 2 == 1) ? verificaMovimentoBranca(linhaChegada, colunChegada)
				: verificaMovimentoPreto(linhaChegada, colunChegada);

		if (verificaMovimento) {

		} else if (verificaJogada(posiAtual, posiChegada)) {

			chess[linhaChegada][colunChegada] = peca;
			chess[l][c] = String.valueOf(quadrado);
			verificaBolinha();
		} else {
			System.out.println("Jogada Invalida!");

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

}
