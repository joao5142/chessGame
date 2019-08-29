package pieces;

import static utils.MovimentoPeca.bolinha;
import static utils.MovimentoPeca.quadrado;
import static utils.MovimentoPeca.validacao;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.LUSHR;

import chess.Chess;
import gui.MainViewController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import utils.MovimentoPeca;
import utils.MovimentosEspeciais;

public class Pawn extends Piece {

	private boolean v = true;
	private static int qtdCasas; // usada para verificar o en passant
	private static int p[]; // ficará com a casa do en passant e a que jogou
	private static boolean passant = false;

	private static boolean movimentouOuComeu = false;

	public Pawn(String chess[][], int vez) {
		super(chess, vez);

	}

	public Pawn(int vez) {
		super(vez);

	}

	public boolean movimentouDoXeque;

	int vez2;

	@Override
	public List<String> sairXeque(int vez, String pecaQueDeuXeque, String posiPecaQueDeuXeque, String[][] chess,
			ImageView img, String posiRei) {

		String posiA = null;
		String posiC;
		Piece p;
		List<String> l = new ArrayList<String>();
		String[][] chessVerdadeiro = Piece.clonar(chess);
		vez2 = vez;
		System.out.println("entrei no sair xeque !");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (vez % 2 == 1) {
					if (chess[i][j].equals(String.valueOf(Chess.whitePawn))) {
						p = new Pawn(vez);
						try {
							posiA = MovimentoPeca.reConverterPosicao(i, j);
						} catch (Exception e) {
							e.printStackTrace();
						}

						for (int a = 1; a <= 2; a++) {
							try {

								posiC = MovimentoPeca.reConverterPosicao(i - a, j);

								p.movimentarPeca1(posiA, posiC, chess);

								//System.out.println("Xadrez na verificacao");
								//p.mostrarXadrez(chess);

								Piece pa = MovimentoPeca.jogar1(pecaQueDeuXeque);

								pa.movimentoDaPeca1(posiPecaQueDeuXeque, chess);
								//System.out.println("Movimentando A peca\n\n");
								//mostrarXadrez(chess);
								int[] b = MovimentoPeca.converterPosicao(posiRei);
								int li = b[0];
								int co = b[1];

								if (chess[li][co].equals(String.valueOf(Chess.bolinha))) {

								} else {
									l.add(posiC);
								}
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								chess = Piece.clonar(chessVerdadeiro);
							}
						}

					}

				} else if (chess[i][j].equals(String.valueOf(Chess.blackPawn))) {

					p = new Pawn(vez);
					try {
						posiA = MovimentoPeca.reConverterPosicao(i, j);
					} catch (Exception e) {
						e.printStackTrace();
					}
					for (int a = 1; a <= 2; a++) {
						try {

							posiC = MovimentoPeca.reConverterPosicao(i + a, j);

							p.movimentarPeca1(posiA, posiC, chess);

							//System.out.println("Xadrez na verificacao");
							//p.mostrarXadrez(chess);

							Piece pa = MovimentoPeca.jogar1(pecaQueDeuXeque);

							pa.movimentoDaPeca1(posiPecaQueDeuXeque, chess);
							//System.out.println("Movimentando A peca\n\n");
							//mostrarXadrez(chess);
							int[] b = MovimentoPeca.converterPosicao(posiRei);
							int li = b[0];
							int co = b[1];

							if (chess[li][co].equals(String.valueOf(Chess.bolinha))) {

							} else {
								l.add(posiC);
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

					}

				}
			}

		}
		return l;
	}

	@Override
	public boolean xeque(String posiChegada) {
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pChegada[0];
		int c = pChegada[1];
		String p = (vez % 2 == 1) ? String.valueOf(Chess.blackKing) : String.valueOf(Chess.whiteKing);

		try {
			if (vez % 2 == 1) {
				if (chess[l - 1][c + 1].equals(p) || chess[l - 1][c - 1].equals(p)) {
					return true;
				}
			} else {
				if (chess[l + 1][c + 1].equals(p) || chess[l + 1][c - 1].equals(p)) {
					return true;
				}
			}
		} catch (Exception e) {

		}

		return false;

	}

	public boolean xeque(String posiChegada, String chess[][]) {
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pChegada[0];
		int c = pChegada[1];
		String p = (vez % 2 == 1) ? String.valueOf(Chess.blackKing) : String.valueOf(Chess.whiteKing);

		try {
			if (vez % 2 == 1) {
				if (chess[l - 1][c + 1].equals(p) || chess[l - 1][c - 1].equals(p)) {
					return true;
				}
			} else {
				if (chess[l + 1][c + 1].equals(p) || chess[l + 1][c - 1].equals(p)) {
					return true;
				}
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
		boolean verdade;
		String posiChegada;

 
			if (vez % 2 == 1) {
				if (l == 6) { // se o peao estiver na mesma linha do começo
					int v = 0;

					// COlocando a bolinha na peça inimiga
					try {
						posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c - 1);
						verdade = pecaInimiga(posiAtual, posiChegada);

						System.out.println(verdade);
						if (verdade) {
							setPecaAmeaçada(l - 1, c - 1, this.getImageView(posiChegada));
							chess[l - 1][c - 1] = String.valueOf(bolinha);
						}
					} catch (Exception e) {

					}

					try {

						posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c + 1);
						verdade = pecaInimiga(posiAtual, posiChegada);

						if (verdade) {
							setPecaAmeaçada(l - 1, c + 1, this.getImageView(posiChegada));
							chess[l - 1][c + 1] = String.valueOf(bolinha);
						}
					} catch (Exception e) {

					}

					// Fim

					if (chess[l - 1][c].equals(String.valueOf(quadrado))) {
						v = 1;
						chess[l - 1][c] = String.valueOf(bolinha); // colocando a bola uma casa a frente da posição
																	// atual na

						setBolinha(l - 1, c);

					}

					if (v == 1) {
						if (chess[l - 2][c].equals(String.valueOf(quadrado))) {
							chess[l - 2][c] = String.valueOf(bolinha);

							setBolinha(l - 2, c);
						}
					}

				} else {
					// COlocando a bolinha na peça inimiga
					try {
						posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c - 1);
						verdade = pecaInimiga(posiAtual, posiChegada);

						if (verdade) {
							setPecaAmeaçada(l - 1, c - 1, this.getImageView(posiChegada));
							// chess[l - 1][c - 1] = String.valueOf(bolinha);
						}
					} catch (Exception e) {

					}

					try {
						posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c + 1);
						verdade = pecaInimiga(posiAtual, posiChegada);

						if (verdade) {
							setPecaAmeaçada(l - 1, c + 1, this.getImageView(posiChegada));

							// chess[l - 1][c + 1] = String.valueOf(bolinha);
						}
					} catch (Exception e) {

					}

					// fim

					if (chess[l - 1][c].equals(String.valueOf(quadrado))) {
						chess[l - 1][c] = String.valueOf(bolinha);

						setBolinha(l - 1, c);

					}
				}

			} else {
				if (l == 1) { // se o peao preto estiver na mesma linha do começo
					// colocando a bola nas posiçoes posiveis para jogar
					int v = 0;

					// COlocando a bolinha na peça inimiga
					try {
						posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c - 1);
						verdade = pecaInimiga(posiAtual, posiChegada);

						if (verdade) {
							setPecaAmeaçada(l + 1, c - 1, getImageView(posiChegada));
							// chess[l + 1][c - 1] = String.valueOf(bolinha);
						}
					} catch (Exception e) {

					}

					try {
						posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c + 1);
						verdade = pecaInimiga(posiAtual, posiChegada);

						if (verdade) {
							setPecaAmeaçada(l + 1, c + 1, getImageView(posiChegada));
							// chess[l + 1][c + 1] = String.valueOf(bolinha);
						}
					} catch (Exception e) {

					}

					// fim

					if (chess[l + 1][c].equals(String.valueOf(quadrado))) {

						chess[l + 1][c] = String.valueOf(bolinha); // colocando a bola uma casa a frente da posição
																	// atual na
																	// primmeira jogada
						v = 1;

						setBolinha(l + 1, c);
					}

					if (v == 1) {
						if (chess[l + 2][c].equals(String.valueOf(quadrado))) {
							chess[l + 2][c] = String.valueOf(bolinha);

							setBolinha(l + 2, c);
						}
					}

				} else {
					// COlocando a bolinha na peça inimiga

					try {
						posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c - 1);
						verdade = pecaInimiga(posiAtual, posiChegada);

						if (verdade) {
							setPecaAmeaçada(l + 1, c - 1, getImageView(posiChegada));
							// chess[l + 1][c - 1] = String.valueOf(bolinha);
						}
					} catch (Exception e) {

					}

					try {
						posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c + 1);
						verdade = pecaInimiga(posiAtual, posiChegada);

						if (verdade) {
							setPecaAmeaçada(l + 1, c + 1, getImageView(posiChegada));
							// chess[l + 1][c + 1] = String.valueOf(bolinha);
						}
					} catch (Exception e) {

					}

					// fim

					if (chess[l + 1][c].equals(String.valueOf(quadrado))) {
						chess[l + 1][c] = String.valueOf(bolinha);

						setBolinha(l + 1, c);
					}
				}

			}
		}

 

	@Override
	public void movimentoDaPeca1(String posiAtual, String chess[][]) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);

		int l = pAtual[0];
		int c = pAtual[1];
		boolean verdade = false;
		String posiChegada;
		if (vez % 2 == 1) {
			if (l == 6) { // se o peao estiver na mesma linha do começo
				int v = 0;

				// COlocando a bolinha na peça inimiga
				try {
					posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c - 1);

					if (vez % 2 == 0) {
						if (chess[l - 1][c - 1].equals(String.valueOf(Chess.blackKing))) {
							verdade = true;
						}
					} else {
						if (chess[l - 1][c - 1].equals(String.valueOf(Chess.whiteKing))) {
							verdade = true;
						}
					}

					if (verdade) {
						// setPecaAmeaçada(l - 1, c - 1, this.getImageView(posiChegada));
						chess[l - 1][c - 1] = String.valueOf(bolinha);
					}
				} catch (Exception e) {

				}

				try {

					posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c + 1);

					if (vez % 2 == 0) {
						if (chess[l - 1][c + 1].equals(String.valueOf(Chess.blackKing))) {
							verdade = true;
						}
					} else {
						if (chess[l - 1][c + 1].equals(String.valueOf(Chess.whiteKing))) {
							verdade = true;
						}
					}

					if (verdade) {
						// setPecaAmeaçada(l - 1, c + 1, this.getImageView(posiChegada));
						chess[l - 1][c + 1] = String.valueOf(bolinha);
					}
				} catch (Exception e) {

				}

				// Fim

				if (chess[l - 1][c].equals(String.valueOf(quadrado))) {
					v = 1;
					chess[l - 1][c] = String.valueOf(bolinha); // colocando a bola uma casa a frente da posição atual na

					// setBolinha(l - 1, c);

				}

				if (v == 1) {
					if (chess[l - 2][c].equals(String.valueOf(quadrado))) {
						chess[l - 2][c] = String.valueOf(bolinha);

						// setBolinha(l - 2, c);
					}
				}

			} else {
				// COlocando a bolinha na peça inimiga
				try {
					posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c - 1);
					if (vez % 2 == 0) {
						if (chess[l - 1][c - 1].equals(String.valueOf(Chess.blackKing))) {
							verdade = true;
						}
					} else {
						if (chess[l - 1][c - 1].equals(String.valueOf(Chess.whiteKing))) {
							verdade = true;
						}
					}

					if (verdade) {
						// setPecaAmeaçada(l - 1, c - 1, this.getImageView(posiChegada));
						chess[l - 1][c - 1] = String.valueOf(bolinha);
					}
				} catch (Exception e) {

				}

				try {
					posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c + 1);

					if (vez % 2 == 0) {
						if (chess[l - 1][c + 1].equals(String.valueOf(Chess.blackKing))) {
							verdade = true;
						}
					} else {
						if (chess[l - 1][c + 1].equals(String.valueOf(Chess.whiteKing))) {
							verdade = true;
						}
					}

					if (verdade) {
						// setPecaAmeaçada(l - 1, c + 1, this.getImageView(posiChegada));

						chess[l - 1][c + 1] = String.valueOf(bolinha);
					}
				} catch (Exception e) {

				}

				// fim

				if (chess[l - 1][c].equals(String.valueOf(quadrado))) {
					chess[l - 1][c] = String.valueOf(bolinha);

					// setBolinha(l - 1, c);

				}
			}

		} else {
			if (l == 1) { // se o peao preto estiver na mesma linha do começo
				// colocando a bola nas posiçoes posiveis para jogar
				int v = 0;

				// COlocando a bolinha na peça inimiga
				try {
					posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c - 1);

					if (vez % 2 == 0) {
						if (chess[l + 1][c - 1].equals(String.valueOf(Chess.blackKing))) {
							verdade = true;
						}
					} else {
						if (chess[l + 1][c - 1].equals(String.valueOf(Chess.whiteKing))) {
							verdade = true;
						}
					}

					if (verdade) {
						// setPecaAmeaçada(l + 1, c - 1, getImageView(posiChegada));
						chess[l + 1][c - 1] = String.valueOf(bolinha);
					}
				} catch (Exception e) {

				}

				try {
					posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c + 1);

					if (vez % 2 == 0) {
						if (chess[l + 1][c + 1].equals(String.valueOf(Chess.blackKing))) {
							verdade = true;
						}
					} else {
						if (chess[l + 1][c + 1].equals(String.valueOf(Chess.whiteKing))) {
							verdade = true;
						}
					}

					if (verdade) {
						// setPecaAmeaçada(l + 1, c + 1, getImageView(posiChegada));
						chess[l + 1][c + 1] = String.valueOf(bolinha);
					}
				} catch (Exception e) {

				}

				// fim

				if (chess[l + 1][c].equals(String.valueOf(quadrado))) {

					chess[l + 1][c] = String.valueOf(bolinha); // colocando a bola uma casa a frente da posição atual na
																// primmeira jogada
					v = 1;

					// setBolinha(l + 1, c);
				}

				if (v == 1) {
					if (chess[l + 2][c].equals(String.valueOf(quadrado))) {
						chess[l + 2][c] = String.valueOf(bolinha);

						// setBolinha(l + 2, c);
					}
				}

			} else {
				// COlocando a bolinha na peça inimiga

				try {
					posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c - 1);

					if (vez % 2 == 0) {
						if (chess[l + 1][c - 1].equals(String.valueOf(Chess.blackKing))) {
							verdade = true;
						}
					} else {
						if (chess[l + 1][c - 1].equals(String.valueOf(Chess.whiteKing))) {
							verdade = true;
						}
					}

					if (verdade) {
						// setPecaAmeaçada(l + 1, c - 1, getImageView(posiChegada));
						chess[l + 1][c - 1] = String.valueOf(bolinha);
					}
				} catch (Exception e) {

				}

				try {
					posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c + 1);

					if (vez % 2 == 0) {
						if (chess[l - 1][c + 1].equals(String.valueOf(Chess.blackKing))) {
							verdade = true;
						}
					} else {
						if (chess[l - 1][c + 1].equals(String.valueOf(Chess.whiteKing))) {
							verdade = true;
						}
					}

					if (verdade) {
						// setPecaAmeaçada(l + 1, c - 1, getImageView(posiChegada));
						chess[l - 1][c + 1] = String.valueOf(bolinha);
					}
				} catch (Exception e) {

				}

				try {
					posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c - 1);

					if (vez % 2 == 0) {
						if (chess[l - 1][c - 1].equals(String.valueOf(Chess.blackKing))) {
							verdade = true;
						}
					} else {
						if (chess[l - 1][c - 1].equals(String.valueOf(Chess.whiteKing))) {
							verdade = true;
						}
					}

					if (verdade) {
						// setPecaAmeaçada(l + 1, c - 1, getImageView(posiChegada));
						chess[l - 1][c - 1] = String.valueOf(bolinha);
					}
				} catch (Exception e) {

				}

				try {
					posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c + 1);

					if (vez % 2 == 0) {
						if (chess[l + 1][c + 1].equals(String.valueOf(Chess.blackKing))) {
							verdade = true;
						}
					} else {
						if (chess[l + 1][c + 1].equals(String.valueOf(Chess.whiteKing))) {
							verdade = true;
						}
					}

					if (verdade) {
						// setPecaAmeaçada(l + 1, c + 1, getImageView(posiChegada));
						chess[l + 1][c + 1] = String.valueOf(bolinha);
					}
				} catch (Exception e) {

				}

				// fim

				if (chess[l + 1][c].equals(String.valueOf(quadrado))) {
					chess[l + 1][c] = String.valueOf(bolinha);

					// setBolinha(l + 1, c);
				}
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
		peca = (vez % 2 == 1) ? String.valueOf(Chess.whitePawn) : String.valueOf(Chess.blackPawn);

		boolean entrouPromo = false;
		if (lChegada == 0 || lChegada == 7) {
			try {
				MovimentosEspeciais m = new MovimentosEspeciais();
				peca = m.promocao(vez);
				System.out.println("A peca é : " + peca);
				setPromocao(posiAtual, posiChegada, peca);
				entrouPromo = true;
			} catch (Exception e) {

			}
		}
		passant = false;
		movimentouOuComeu = true;

		if (this.getAnchorPane() != null && !entrouPromo) {
			AnchorPane pane = this.getAnchorPane();

			ImageView i = (ImageView) pane.lookup("#" + posiChegada);

			ImageView e = (ImageView) pane.lookup("#" + posiAtual);

			i.setImage(e.getImage());
			e.setImage(null);

			/*
			 * if(vez%2==1) { m.setPecaComida1(m.getpBlack(),m.getContPB()); }else {
			 * m.setPecaComida1( m.getpWhite(),m.getContPW()); }
			 */

		}
		System.out.println("Entrei no comer peça");

		qtdCasas = 0;
		passant = false;

		chess[lChegada][cChegada] = peca;
		chess[l][c] = String.valueOf(quadrado);

		try {
			verificaBolinha();
		} catch (NullPointerException e) {

		}

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
		peca = (vez % 2 == 1) ? String.valueOf(Chess.whitePawn) : String.valueOf(Chess.blackPawn);

		chess[lChegada][cChegada] = peca;
		chess[l][c] = String.valueOf(quadrado);

		try {
			verificaBolinha();
		} catch (NullPointerException e) {

		}

	}

	public boolean comerPassante(String posiAtual, String posiChegada) {
		movimentouOuComeu = false;

		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pAtual[0];
		int c = pAtual[1];

		int lChegada = pChegada[0];
		int cChegada = pChegada[1];

		boolean v = (chess[lChegada][cChegada].equals(String.valueOf(quadrado))) ? true : false;
		System.out.println("Pode passante! " + passant + v);
		try {

			if (passant) {
				System.out.println("Entrei");
				if (vez % 2 == 1) {
					if (chess[p[0]][p[1]].equals(String.valueOf(Chess.blackPawn))) {
						if (v && lChegada == l - 1 && cChegada == p[1]) {
							System.out.println("comi" + p[0] + " " + p[1]);
							chess[l][c] = String.valueOf(quadrado);
							chess[l][p[1]] = String.valueOf(quadrado);
							chess[lChegada][cChegada] = String.valueOf(Chess.whitePawn);

							qtdCasas = 0;
							passant = false;

							String posicaoChegadaConvertida = MovimentoPeca.reConverterPosicao(lChegada, cChegada);
							String posicaoAtualConvertida = MovimentoPeca.reConverterPosicao(l, c);
							String posicaoPecaPassanteConvertida = MovimentoPeca.reConverterPosicao(l, p[1]);

							if (this.getAnchorPane() != null) {
								AnchorPane pane = this.getAnchorPane();

								ImageView i = (ImageView) pane.lookup("#" + posicaoChegadaConvertida);

								ImageView e = (ImageView) pane.lookup("#" + posicaoAtualConvertida);

								ImageView u = (ImageView) pane.lookup("#" + posicaoPecaPassanteConvertida);

								i.setImage(e.getImage());
								e.setImage(null);
								u.setImage(null);

								if (vez % 2 == 1) {
									m.setPecaComida1(m.getpBlack(), m.getContPB());
								} else {
									m.setPecaComida1(m.getpWhite(), m.getContPW());
								}

								try {
									verificaBolinha();
								} catch (Exception a) {

								}

							}
							movimentouOuComeu = true;

							return true;
						}
					}

				} else {

					if (chess[l][p[1]].equals(String.valueOf(Chess.whitePawn))) {
						if (v && lChegada == l + 1 && cChegada == p[1]) {
							chess[l][c] = String.valueOf(quadrado);

							chess[l][p[1]] = String.valueOf(quadrado);
							chess[lChegada][cChegada] = String.valueOf(Chess.blackPawn);

							qtdCasas = 0;
							passant = false;

							String posicaoChegadaConvertida = MovimentoPeca.reConverterPosicao(lChegada, cChegada);
							String posicaoAtualConvertida = MovimentoPeca.reConverterPosicao(l, c);
							String posicaoPecaPassanteConvertida = MovimentoPeca.reConverterPosicao(l, p[1]);

							if (this.getAnchorPane() != null) {
								AnchorPane pane = this.getAnchorPane();

								ImageView i = (ImageView) pane.lookup("#" + posicaoChegadaConvertida);

								ImageView e = (ImageView) pane.lookup("#" + posicaoAtualConvertida);

								ImageView u = (ImageView) pane.lookup("#" + posicaoPecaPassanteConvertida);

								i.setImage(e.getImage());
								e.setImage(null);
								u.setImage(null);

								try {
									verificaBolinha();
								} catch (Exception a) {

								}

							}

							movimentouOuComeu = true;

							return true;

						}
					}

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.setMovimentou(movimentouOuComeu);
		return false;
	}

	public int[] verificaPassante(String posiAtual, String posiChegada) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		String pecaSelecionada = chess[pAtual[0]][pAtual[1]];

		int linhaAtual = pAtual[0];
		int colunAtual = pAtual[1];
		int linhaChegada = pChegada[0];
		int colunChegada = pChegada[1];

		int v[] = new int[2];
		v[0] = 0;
		v[1] = 0;

		if (pecaSelecionada.equals(String.valueOf(Chess.whitePawn))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackPawn))) {
			if (vez % 2 == 1) {
				if (linhaAtual == 6) {
					if (linhaChegada == linhaAtual - 2) {
						qtdCasas = 2;
						passant = true;
						v[0] = linhaChegada;
						v[1] = colunChegada;
						return v;
					}
				}
			} else {
				if (linhaAtual == 1) {
					if (linhaChegada == linhaAtual + 2) {
						qtdCasas = 2;
						passant = true;

						v[0] = linhaChegada;
						v[1] = colunChegada;
						return v;
					}

				}

			}
		}
		passant = false;
		return null;

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

		if (vez % 2 == 1) {
			if (verificaMovimentoPreto1(lChegada, cChegada)
					&& ((lChegada == l - 1 && cChegada == c - 1) || (lChegada == l - 1 && cChegada == c + 1))) {

				return true;

			}

		} else if (verificaMovimentoBranca1(lChegada, cChegada)
				&& ((lChegada == l + 1 && cChegada == c - 1) || (lChegada == l + 1 && cChegada == c + 1))) {

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

		if (vez % 2 == 1) {
			if (verificaMovimentoPreto2(lChegada, cChegada, chess)
					&& ((lChegada == l - 1 && cChegada == c - 1) || (lChegada == l - 1 && cChegada == c + 1))) {

				return true;

			}

		} else if (verificaMovimentoBranca2(lChegada, cChegada, chess)
				&& ((lChegada == l + 1 && cChegada == c - 1) || (lChegada == l + 1 && cChegada == c + 1))) {

			return true;

		}

		return false;
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

		if (vez % 2 == 1) {
			if (verificaMovimentoPreto1(lChegada, cChegada)
					&& ((lChegada == l - 1 && cChegada == c - 1) || (lChegada == l - 1 && cChegada == c + 1))) {

				return true;

			}

		} else if (verificaMovimentoBranca1(lChegada, cChegada)
				&& ((lChegada == l + 1 && cChegada == c - 1) || (lChegada == l + 1 && cChegada == c + 1))) {

			return true;

		}

		return false;
	}

	public boolean verificaJogada(String posiAtual, String posiChegada, String chess[][]) {

		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pAtual[0];
		int c = pAtual[1];

		int lChegada = pChegada[0];
		int cChegada = pChegada[1];

		if (vez % 2 == 1) {
			if (l == 6) { // se o peao estiver na mesma linha do começo
				if ((lChegada == l - 1 || lChegada == l - 2)
						&& chess[lChegada][cChegada].equals(String.valueOf(Chess.quadrado))) {
					return true;
				}
			} else if (lChegada == l - 1 && chess[lChegada][cChegada].equals(String.valueOf(Chess.quadrado))) {
				return true;
			}

		} else {
			if (l == 1) { // se o peao preto estiver na mesma linha do começo
				if ((lChegada == l + 1 || lChegada == l + 2)
						&& chess[lChegada][cChegada].equals(String.valueOf(Chess.quadrado))) {
					return true;
				}

			} else if (lChegada == l + 1 && chess[lChegada][cChegada].equals(String.valueOf(Chess.quadrado))) {
				return true;
			}
		}

		return false;
	}

	public boolean verificaJogada(String posiChegada) {

		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);
		int linhaChegada = pChegada[0];
		int colunChegada = pChegada[1];

		return chess[linhaChegada][colunChegada].equals(String.valueOf(bolinha));
	}

	@Override
	public void movimentarPeca1(String posiAtual, String posiChegada, String chess[][]) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		String pecaSelecionada = chess[pAtual[0]][pAtual[1]];

		int linhaAtual = pAtual[0];
		int colunAtual = pAtual[1];
		int linhaChegada = pChegada[0];
		int colunChegada = pChegada[1];

		if (verificaJogada(posiAtual, posiChegada, chess)) {

			chess[linhaChegada][colunChegada] = pecaSelecionada;
			chess[linhaAtual][colunAtual] = String.valueOf(quadrado);
			movimentouDoXeque = true;
			verificaBolinha();

		} else {
			System.out.println("Nao movimentou");

			movimentouDoXeque = false;
		}

	}

	int conta = 0;

	@Override
	public void movimentarPeca(String posiAtual, String posiChegada) {

		movimentouOuComeu = false;
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		String pecaSelecionada = chess[pAtual[0]][pAtual[1]];

		int linhaAtual = pAtual[0];
		int colunAtual = pAtual[1];
		int linhaChegada = pChegada[0];
		int colunChegada = pChegada[1];

		conta++;
		System.out.println("Entrei pela " + conta + "ª vez");

		/* System.out.println("Quantidade : " + qtdCasas); */

		if (verificaJogada(posiChegada)) {
			qtdCasas = 0;
			passant = false;

			p = verificaPassante(posiAtual, posiChegada);
			System.out.println(passant);
			movimentouOuComeu = true;
			super.setMovimentou(movimentouOuComeu);
			if (linhaChegada == 7 || linhaChegada == 0) {
				MovimentosEspeciais m = new MovimentosEspeciais();
				pecaSelecionada = m.promocao(vez);
				setPromocao(posiAtual, posiChegada, pecaSelecionada);
				movimentouOuComeu = true;
			}
			String posicaoChegadaConvertida = MovimentoPeca.reConverterPosicao(linhaChegada, colunChegada);
			String posicaoAtualConvertida = MovimentoPeca.reConverterPosicao(linhaAtual, colunAtual);

			if (this.getAnchorPane() != null) {
				AnchorPane pane = this.getAnchorPane();

				ImageView i = (ImageView) pane.lookup("#" + posicaoChegadaConvertida);

				ImageView e = (ImageView) pane.lookup("#" + posicaoAtualConvertida);

				i.setImage(e.getImage());
				e.setImage(null);

			}

			chess[linhaChegada][colunChegada] = pecaSelecionada;
			chess[linhaAtual][colunAtual] = String.valueOf(quadrado);
			verificaBolinha();

		} else {
			MovimentoPeca.validacao = false;
			System.out.println("Jogada Invalida!");
			movimentouOuComeu = false;
			super.setMovimentou(movimentouOuComeu);

		}

	}

	@Override

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

	public boolean getMovimentou() {
		return movimentouOuComeu;
	}

	public void setPromocao(String posiAtual, String posiChegada, String peca) {
		if (this.getAnchorPane() != null) {

			AnchorPane pane = this.getAnchorPane();
			ImageView e = (ImageView) pane.lookup("#" + posiAtual);
			ImageView i = (ImageView) pane.lookup("#" + posiChegada);
			MainViewController main = new MainViewController();

			Image pecaSelecionada = null;
			if (peca.equals(String.valueOf(Chess.whiteKnight)) || peca.equals(String.valueOf(Chess.blackKnight))) {

				pecaSelecionada = (vez % 2 == 1) ? main.getkWhite() : main.getkBlack();

			} else if (peca.equals(String.valueOf(Chess.whiteBishop))
					|| peca.equals(String.valueOf(Chess.blackBishop))) {
				pecaSelecionada = (vez % 2 == 1) ? main.getBishopW() : main.getBishopBlack();

			} else if (peca.equals(String.valueOf(Chess.whiteQueen)) || peca.equals(String.valueOf(Chess.blackQueen))) {

				pecaSelecionada = (vez % 2 == 1) ? main.getQueenWhite() : main.getQueenBlack();

			} else if (peca.equals(String.valueOf(Chess.whiteRook)) || peca.equals(String.valueOf(Chess.blackRook))) {

				pecaSelecionada = (vez % 2 == 1) ? main.getRookWhite() : main.getRookBlack();

			}

			i.setImage(pecaSelecionada);
			e.setImage(null);
		}
	}
	
	public boolean estaEmXeque(String posiChegada) {
		String copiaChess[][] = Piece.clonar(chess);
		String[][] chessVerdadeiro = Piece.clonar(copiaChess);

		String posiRei = null;

		for (int l = 0; l <= 7; l++) {
			for (int c = 0; c <= 7; c++) {
				if (vez % 2 == 0) {
					if (copiaChess[l][c].equals(String.valueOf(Chess.blackKing))) {
						copiaChess[l][c] = String.valueOf(Chess.quadrado);
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				} else {
					if (copiaChess[l][c].equals(String.valueOf(Chess.whiteKing))) {
						copiaChess[l][c] = String.valueOf(Chess.quadrado);
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				}

			}

		}

		Piece p = new Piece(vez);

		Piece p1 = null;
		String peca;
		String posi;

		//System.out.println("Verificação do esta em xeque!");

		int a[] = MovimentoPeca.converterPosicao(posiChegada);
		int l = a[0];
		int c = a[1];

		String rei = (vez % 2 == 1) ? String.valueOf(Chess.whiteKing) : String.valueOf(Chess.blackKing);

		Piece peca1 = new King(vez);

		if (copiaChess[l][c].equals(String.valueOf(Chess.quadrado)) || pecaInimiga1(posiRei, posiChegada, copiaChess)) {
			copiaChess[l][c] = rei;
		}

		chessVerdadeiro = Piece.clonar(copiaChess);

	//	mostrarXadrez(copiaChess);

		List<String> list = new ArrayList<>();

		try {
			verificaBolinha(copiaChess);
			verificaBolinha(chessVerdadeiro);

		} catch (Exception e) {

		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (vez % 2 == 1) {
					if (copiaChess[l][c].equals(String.valueOf(Chess.blackPawn))) {
						try {
							peca = copiaChess[i][j];
							posi = MovimentoPeca.reConverterPosicao(i, j);
							p1 = MovimentoPeca.jogar1(peca);

							p1.movimentoDaPeca1(posi, copiaChess);
							//System.out.println("\n\nO xadrez Na verificação do esta em xeque");
							//mostrarXadrez(copiaChess);
							if (copiaChess[l][c].equals(String.valueOf(Chess.bolinha))) {
								return true;
							} else {
								if (p1.pecaInimiga2(posi, posiChegada, copiaChess)) {
									return true;
								}
							}

						} catch (Exception e) {

						} finally {
							try {
								verificaBolinha(copiaChess);
								p1.verificaBolinha(copiaChess);
							} catch (Exception e) {

							}

							copiaChess = Piece.clonar(chessVerdadeiro);
						}

					}
				} else {
					if (copiaChess[l][c].equals(String.valueOf(Chess.whitePawn))) {

						try {
							peca = copiaChess[i][j];
							posi = MovimentoPeca.reConverterPosicao(i, j);
							p1 = MovimentoPeca.jogar1(peca);

							p1.movimentoDaPeca1(posi, copiaChess);

							if (copiaChess[l][c].equals(String.valueOf(Chess.bolinha))) {
								return true;
							} else {
								if (p1.pecaInimiga2(posi, posiChegada, copiaChess)) {
									return true;
								}
							}
							// veri3(p2, posiRei, list, i, j, 0, 0, copiaChess, chessVerdadeiro, posi, peca,
							// posiRei);
						} catch (Exception e) {

						} finally {
							try {
								verificaBolinha(copiaChess);
								p1.verificaBolinha(copiaChess);
							} catch (Exception e) {

							}
							copiaChess = Piece.clonar(chessVerdadeiro);
						}

					}
				}
			}
		}

		return false;
	}


	public static void setQtdCasas(int qtdCasas) {
		Pawn.qtdCasas = qtdCasas;
	}

	public static void setP(int[] p) {
		Pawn.p = p;
	}

}
