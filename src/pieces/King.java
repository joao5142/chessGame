package pieces;

import static utils.MovimentoPeca.bolinha;
import static utils.MovimentoPeca.quadrado;

import java.util.ArrayList;
import java.util.List;

import chess.Chess;
import gui.MainViewController;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import utils.MovimentoPeca;

public class King extends Piece {

	public static boolean xequeMate = false;
	public static List<Integer> list1;
	public static List<Integer> list2;
	private static boolean movimentouOuComeu = false;
	private boolean reiMovimentou;

	public King(String[][] chess, int vez) {
		super(chess, vez);

	}

	public King(int vezDejogar) {
		super(vezDejogar);
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
					if (p.verificaMovimentoPreto(i, j, copiaChess)) {
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
					if (p.verificaMovimentoBranca(i, j, copiaChess)) {

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

	@Override
	public void movimentoDaPeca(String posiAtual,List<String>posisSairXeque) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);

		int l = pAtual[0];
		int c = pAtual[1];

		String posiChegada;
		boolean verdade;

		String p;
		if (vez % 2 == 1) {

			if (!MovimentoPeca.getFezRooqueBranco() && MovimentoPeca.getPodeRookBranco()) {
				// rook pequeno
				p = MovimentoPeca.reConverterPosicao(l, c + 1);

				if (estaEmXeque(p)) {
					System.out.println("Nao pode movimentar pois vai ficar em xeque!");

				} else {
					p = MovimentoPeca.reConverterPosicao(l, c + 2);

					if (estaEmXeque(p)) {
						System.out.println("Nao pode movimentar pois vai ficar em xeque!");
					} else {
						if (chess[l][c + 1].equals(String.valueOf(Chess.quadrado))
								&& chess[l][c + 2].equals(String.valueOf(Chess.quadrado))
								&& MovimentoPeca.getPodeRookPequenoBranco()) {
							chess[l][c + 1] = String.valueOf(Chess.bolinha);
							setBolinha(l, c + 1);

							chess[l][c + 2] = String.valueOf(Chess.bolinha);
							setBolinha(l, c + 2);
							MovimentoPeca.setPodeRookBranco(true);
						}
					}
				}

				p = MovimentoPeca.reConverterPosicao(l, c - 1);

				if (estaEmXeque(p)) {
					System.out.println("Nao pode movimentar pois vai ficar em xeque!");

				} else {
					p = MovimentoPeca.reConverterPosicao(l, c - 2);

					if (estaEmXeque(p)) {
						System.out.println("Nao pode movimentar pois vai ficar em xeque!");
					} else {
						p = MovimentoPeca.reConverterPosicao(l, c - 3);

						if (estaEmXeque(p)) {

						} else {
							if (chess[l][c - 1].equals(String.valueOf(Chess.quadrado))
									&& chess[l][c - 2].equals(String.valueOf(Chess.quadrado))
									&& chess[l][c - 3].equals(String.valueOf(Chess.quadrado))
									&& MovimentoPeca.getPodeRookGrandeBranco()) {
								chess[l][c - 1] = String.valueOf(Chess.bolinha);
								setBolinha(l, c - 1);

								chess[l][c - 2] = String.valueOf(Chess.bolinha);
								setBolinha(l, c - 2);

								MovimentoPeca.setPodeRookBranco(true);
							}
						}
					}
				}

			}

		} else {

			if (!MovimentoPeca.getFezRooquePreto() && MovimentoPeca.getPodeRookPreto()) {
				// rook pequeno

				p = MovimentoPeca.reConverterPosicao(l, c + 1);
				if (estaEmXeque(p)) {
					System.out.println("Nao pode movimentar pois vai ficar em xeque!");

				} else {

					p = MovimentoPeca.reConverterPosicao(l, c + 2);

					if (estaEmXeque(p)) {

					} else {
						if (chess[l][c + 1].equals(String.valueOf(Chess.quadrado))
								&& chess[l][c + 2].equals(String.valueOf(Chess.quadrado))
								&& MovimentoPeca.getPodeRookPequenoPreto()) {
							chess[l][c + 1] = String.valueOf(Chess.bolinha);
							setBolinha(l, c + 1);

							chess[l][c + 2] = String.valueOf(Chess.bolinha);
							setBolinha(l, c + 2);
							MovimentoPeca.setPodeRookPreto(true);
							MovimentoPeca.setPodeRookPequenoPreto(true);
						}
					}
				}

				p = MovimentoPeca.reConverterPosicao(l, c - 1);

				if (estaEmXeque(p)) {

				} else {
					p = MovimentoPeca.reConverterPosicao(l, c - 2);

					if (estaEmXeque(p)) {

					} else {
						p = MovimentoPeca.reConverterPosicao(l, c - 3);

						if (estaEmXeque(p)) {

						} else {
							if (chess[l][c - 1].equals(String.valueOf(Chess.quadrado))
									&& chess[l][c - 2].equals(String.valueOf(Chess.quadrado))
									&& chess[l][c - 3].equals(String.valueOf(Chess.quadrado))
									&& MovimentoPeca.getPodeRookGrandePreto()) {
								chess[l][c - 1] = String.valueOf(Chess.bolinha);
								setBolinha(l, c - 1);

								chess[l][c - 2] = String.valueOf(Chess.bolinha);
								setBolinha(l, c - 2);

								MovimentoPeca.setPodeRookPreto(true);
								MovimentoPeca.setPodeRookGrandePreto(true);

							}
						}
					}
				}

			}
		}

		try

		{
			posiChegada = MovimentoPeca.reConverterPosicao(l, c + 1);
			verdade = pecaInimiga(posiAtual, posiChegada);

			if (estaEmXeque(posiChegada)) {

			} else {
				if (verdade) {
					setPecaAmeaçada(l, c + 1, this.getImageView(posiChegada));
					// chess[l - 2][c - 1] = String.valueOf(bolinha);
				}

				if (chess[l][c + 1].equals(String.valueOf(Chess.quadrado))) {
					chess[l][c + 1] = String.valueOf(Chess.bolinha);
					setBolinha(l, c + 1);
				}
			}

		} catch (Exception e) {

		}
		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l, c - 1);
			verdade = pecaInimiga(posiAtual, posiChegada);

			if (estaEmXeque(posiChegada)) {

			} else {
				if (verdade) {
					setPecaAmeaçada(l, c - 1, this.getImageView(posiChegada));
					// chess[l - 2][c - 1] = String.valueOf(bolinha);
				}

				if (chess[l][c - 1].equals(String.valueOf(Chess.quadrado))) {
					chess[l][c - 1] = String.valueOf(Chess.bolinha);
					setBolinha(l, c - 1);
				}
			}

		} catch (Exception e) {

		}
		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c - 1);
			verdade = pecaInimiga(posiAtual, posiChegada);

			if (estaEmXeque(posiChegada)) {

			} else {
				if (verdade) {
					setPecaAmeaçada(l - 1, c - 1, this.getImageView(posiChegada));
					// chess[l - 2][c - 1] = String.valueOf(bolinha);
				}
				if (chess[l - 1][c - 1].equals(String.valueOf(Chess.quadrado))) {
					chess[l - 1][c - 1] = String.valueOf(Chess.bolinha);
					setBolinha(l - 1, c - 1);
				}
			}

		} catch (Exception e) {

		}
		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c + 1);
			verdade = pecaInimiga(posiAtual, posiChegada);

			if (estaEmXeque(posiChegada)) {

			} else {
				if (verdade) {
					setPecaAmeaçada(l - 1, c + 1, this.getImageView(posiChegada));
					// chess[l - 2][c - 1] = String.valueOf(bolinha);
				}

				if (chess[l - 1][c + 1].equals(String.valueOf(Chess.quadrado))) {
					chess[l - 1][c + 1] = String.valueOf(Chess.bolinha);
					setBolinha(l - 1, c + 1);
				}
			}

		} catch (Exception e) {

		}
		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c - 1);
			verdade = pecaInimiga(posiAtual, posiChegada);

			if (estaEmXeque(posiChegada)) {

			} else {
				if (verdade) {
					setPecaAmeaçada(l + 1, c - 1, this.getImageView(posiChegada));
					// chess[l - 2][c - 1] = String.valueOf(bolinha);
				}
				if (chess[l + 1][c - 1].equals(String.valueOf(Chess.quadrado))) {
					chess[l + 1][c - 1] = String.valueOf(Chess.bolinha);
					setBolinha(l + 1, c - 1);
				}
			}

		} catch (Exception e) {

		}
		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c + 1);
			verdade = pecaInimiga(posiAtual, posiChegada);

			if (estaEmXeque(posiChegada)) {

			} else {
				if (verdade) {
					setPecaAmeaçada(l + 1, c + 1, this.getImageView(posiChegada));
					// chess[l - 2][c - 1] = String.valueOf(bolinha);
				}

				if (chess[l + 1][c + 1].equals(String.valueOf(Chess.quadrado))) {
					chess[l + 1][c + 1] = String.valueOf(Chess.bolinha);
					setBolinha(l + 1, c + 1);
				}
			}

		} catch (Exception e) {

		}

		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l + 1, c);
			verdade = pecaInimiga(posiAtual, posiChegada);

			if (estaEmXeque(posiChegada)) {

			} else {
				if (verdade) {
					setPecaAmeaçada(l + 1, c, this.getImageView(posiChegada));
					// chess[l - 2][c - 1] = String.valueOf(bolinha);
				}

				if (chess[l + 1][c].equals(String.valueOf(Chess.quadrado))) {
					chess[l + 1][c] = String.valueOf(Chess.bolinha);
					setBolinha(l + 1, c);
				}
			}

		} catch (Exception e) {

		}
		try {
			posiChegada = MovimentoPeca.reConverterPosicao(l - 1, c);
			verdade = pecaInimiga(posiAtual, posiChegada);

			if (estaEmXeque(posiChegada)) {

			} else {
				if (verdade) {
					setPecaAmeaçada(l - 1, c, this.getImageView(posiChegada));
					// chess[l - 2][c - 1] = String.valueOf(bolinha);
				}
				if (chess[l - 1][c].equals(String.valueOf(Chess.quadrado))) {
					chess[l - 1][c] = String.valueOf(Chess.bolinha);
					setBolinha(l - 1, c);
				}
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
		boolean verdade;

		try

		{
			if (chess[l][c + 1].equals(String.valueOf(Chess.quadrado))) {
				chess[l][c + 1] = String.valueOf(Chess.bolinha);
				// setBolinha(l, c + 1);
			}
		} catch (Exception e) {

		}
		try {

			if (chess[l][c - 1].equals(String.valueOf(Chess.quadrado))) {
				chess[l][c - 1] = String.valueOf(Chess.bolinha);
				// setBolinha(l, c - 1);
			}

		} catch (Exception e) {

		}
		try {

			if (chess[l - 1][c - 1].equals(String.valueOf(Chess.quadrado))) {
				chess[l - 1][c - 1] = String.valueOf(Chess.bolinha);
				// setBolinha(l - 1, c - 1);
			}
		} catch (Exception e) {

		}
		try {

			if (chess[l - 1][c + 1].equals(String.valueOf(Chess.quadrado))) {
				chess[l - 1][c + 1] = String.valueOf(Chess.bolinha);
				// setBolinha(l - 1, c + 1);
			}
		} catch (Exception e) {

		}
		try {

			if (chess[l + 1][c - 1].equals(String.valueOf(Chess.quadrado))) {
				chess[l + 1][c - 1] = String.valueOf(Chess.bolinha);
				// setBolinha(l + 1, c - 1);
			}
		} catch (Exception e) {

		}
		try {

			if (chess[l + 1][c + 1].equals(String.valueOf(Chess.quadrado))) {
				chess[l + 1][c + 1] = String.valueOf(Chess.bolinha);
				// setBolinha(l + 1, c + 1);
			}
		} catch (Exception e) {

		}

		try {

			if (chess[l + 1][c].equals(String.valueOf(Chess.quadrado))) {
				chess[l + 1][c] = String.valueOf(Chess.bolinha);
				// setBolinha(l + 1, c);
			}
		} catch (Exception e) {

		}
		try {

			if (chess[l - 1][c].equals(String.valueOf(Chess.quadrado))) {
				chess[l - 1][c] = String.valueOf(Chess.bolinha);
				// setBolinha(l - 1, c);
			}
		} catch (Exception e) {

		}

	}

	@Override
	public List<String> sairXeque(int vez, String pecaQueDeuXeque, String posiPecaQueDeuXeque, String[][] chess,
			ImageView img, String posiRei) {

		String posiA = null;
		String posiC;
		Piece p;
		List<String> l = new ArrayList<String>();
		String[][] chessVerdadeiro = Piece.clonar(chess);

		int[] pR = MovimentoPeca.converterPosicao(posiRei);
		int linha1 = pR[0];
		int coluna1 = pR[1];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (vez % 2 == 1) {
					if (i == linha1 && j == coluna1 && chess[i][j].equals(String.valueOf(Chess.whiteKing))) {
						p = new King(vez);

						try {
							posiA = MovimentoPeca.reConverterPosicao(i, j);
						} catch (Exception e) {
							e.printStackTrace();
						}
						p = new King(vez);

						try {
							posiA = MovimentoPeca.reConverterPosicao(i, j);
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {
							veri3(p, posiA, l, i, j, 0, 1, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
									posiRei);
						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {
							veri3(p, posiA, l, i, j, 0, -1, chess, chessVerdadeiro, posiPecaQueDeuXeque,
									pecaQueDeuXeque, posiRei);
						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {
							veri3(p, posiA, l, i, j, -1, -1, chess, chessVerdadeiro, posiPecaQueDeuXeque,
									pecaQueDeuXeque, posiRei);
						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {
							veri3(p, posiA, l, i, j, -1, 1, chess, chessVerdadeiro, posiPecaQueDeuXeque,
									pecaQueDeuXeque, posiRei);
						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {
							veri3(p, posiA, l, i, j, +1, 1, chess, chessVerdadeiro, posiPecaQueDeuXeque,
									pecaQueDeuXeque, posiRei);
						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {
							veri3(p, posiA, l, i, j, +1, -1, chess, chessVerdadeiro, posiPecaQueDeuXeque,
									pecaQueDeuXeque, posiRei);
						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {
							veri3(p, posiA, l, i, j, +1, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
									pecaQueDeuXeque, posiRei);
						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						try {
							veri3(p, posiA, l, i, j, -1, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
									pecaQueDeuXeque, posiRei);
						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}
					}

				} else if (i == linha1 && j == coluna1 && chess[i][j].equals(String.valueOf(Chess.blackKing))) {

					p = new King(vez);

					try {
						posiA = MovimentoPeca.reConverterPosicao(i, j);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {
						veri3(p, posiA, l, i, j, 0, 1, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);
					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {
						veri3(p, posiA, l, i, j, 0, -1, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);
					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {
						veri3(p, posiA, l, i, j, -1, -1, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);
					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {
						veri3(p, posiA, l, i, j, -1, 1, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);
					} catch (Exception e) {
                        e.printStackTrace();
					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {
						veri3(p, posiA, l, i, j, +1, 1, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);
					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {
						veri3(p, posiA, l, i, j, +1, -1, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);
					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {
						veri3(p, posiA, l, i, j, +1, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);
					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					try {
						veri3(p, posiA, l, i, j, -1, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque, pecaQueDeuXeque,
								posiRei);
					} catch (Exception e) {

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
		 
		try {
			String posiC = MovimentoPeca.reConverterPosicao(i + a, j + b);

			if(!estaEmXeque(posiC)) {
				p.movimentoDaPeca1(posiA, chess);
				p.movimentarPeca1(posiA, posiC, chess);
			}
		
			//System.out.println(reiMovimentou);

			try {
				p.verificaBolinha(chess);
				verificaBolinha(chess);
				verificaBolinha();

			} catch (Exception e) {

			}

			if (MovimentoPeca.getReiMovimentou()) {
				//System.out.println("A posição do rei ira ser trocaa");
				posiRei = posiC;
			}

			int l1 = 0;
			int c1 = 0;

			//System.out.println("A posição do rei é :" + posiRei);
			//System.out.println("Xadrez na verificacao do rei");
			//mostrarXadrez(chess);

			Piece pa = MovimentoPeca.jogar1(pecaQueDeuXeque);

			pa.movimentoDaPeca1(posiPecaQueDeuXeque, chess);
			//System.out.println("Movimentando A peca do rei\n\n");
		//	mostrarXadrez(chess);

			int[] c = MovimentoPeca.converterPosicao(posiRei);
			int li = c[0];
			int co = c[1];

			String rei = (vez % 2 == 1) ? String.valueOf(Chess.whiteKing) : String.valueOf(Chess.blackKing);

			if (chess[li][co].equals(String.valueOf(Chess.bolinha))) {

			} else {
				l.add(posiC);
			}
		} catch (Exception e) {

		} finally {
			chess = Piece.clonar(chessVerdadeiro);
		}
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
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);
		int linhaChegada = pChegada[0];
		int colunChegada = pChegada[1];

		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int l = pAtual[0];
		int c = pAtual[1];
		if (linhaChegada == l && colunChegada == (c + 1)) {
			return true;
		}

		if (linhaChegada == l && colunChegada == (c - 1)) {
			return true;
		}

		if (linhaChegada == (l - 1) && colunChegada == (c - 1)) {
			return true;
		}

		if (linhaChegada == (l - 1) && colunChegada == (c + 1)) {
			return true;
		}

		if (linhaChegada == (l + 1) && colunChegada == (c - 1)) {
			return true;
		}

		if (linhaChegada == (l + 1) && colunChegada == (c + 1)) {
			return true;
		}

		if (linhaChegada == (l + 1) && colunChegada == c) {
			return true;
		}

		if (linhaChegada == (l - 1) && colunChegada == c) {
			return true;
		}

		return false;

	}

	public static boolean jogValida(String posiChegada) {
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);
		int linhaChegada = pChegada[0];
		int colunChegada = pChegada[1];

		return chess[linhaChegada][colunChegada].equals(String.valueOf(bolinha));
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
		movimentouOuComeu = true;
		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteKing) : String.valueOf(Chess.blackKing);

		if (this.getAnchorPane() != null) {
			AnchorPane pane = this.getAnchorPane();

			ImageView i = (ImageView) pane.lookup("#" + posiChegada);

			ImageView e = (ImageView) pane.lookup("#" + posiAtual);

			i.setImage(e.getImage());
			e.setImage(null);

			if (vez % 2 == 1) {
				MovimentoPeca.setPodeRookBranco(false);
			} else {
				MovimentoPeca.setPodeRookPreto(false);
			}
		}

		chess[lChegada][cChegada] = peca;
		chess[l][c] = String.valueOf(quadrado);

		verificaBolinha();
	}

	@Override
	public void comerPeca1(String posiAtual, String posiChegada, String chess[][]) {
		if (!estaEmXeque(posiChegada)) {

			int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
			int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

			int l = pAtual[0];
			int c = pAtual[1];

			int lChegada = pChegada[0];
			int cChegada = pChegada[1];
			String peca;
			peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteKing) : String.valueOf(Chess.blackKing);

			chess[lChegada][cChegada] = peca;
			chess[l][c] = String.valueOf(quadrado);

			verificaBolinha();
		}
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

		boolean verificacao1 = (vez % 2 == 1) ? verificaMovimentoPreto(lChegada, cChegada)
				: verificaMovimentoBranca(lChegada, cChegada);

		boolean verificacao2 = verificaJogada(posiAtual, posiChegada);
		boolean veri3 = estaEmXeque(posiChegada);
		if (verificacao1) {
			if (verificacao2) {
				if (!veri3) {
					return true;
				}

			}
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

		boolean verificacao1 = (vez % 2 == 1) ? verificaMovimentoPreto1(lChegada, cChegada)
				: verificaMovimentoBranca1(lChegada, cChegada);

		boolean verificacao2 = verificaJogada(posiAtual, posiChegada);
		if (verificacao1) {
			if (verificacao2) {
				return true;
			}
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

		boolean verificacao1 = (vez % 2 == 1) ? verificaMovimentoPreto2(lChegada, cChegada, chess)
				: verificaMovimentoBranca2(lChegada, cChegada, chess);

		boolean verificacao2 = verificaJogada(posiAtual, posiChegada);
		if (verificacao1) {
			if (verificacao2) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void movimentarPeca(String posiAtual, String posiChegada) {
		movimentouOuComeu = false;
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pAtual[0];
		int c = pAtual[1];
		int linhaChegada = pChegada[0];
		int colunChegada = pChegada[1];

		String peca;
		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteKing) : String.valueOf(Chess.blackKing);
		boolean verificaMovimento = (vez % 2 == 1) ? verificaMovimentoBranca(linhaChegada, colunChegada)
				: verificaMovimentoPreto(linhaChegada, colunChegada);

		System.out.println("Pode rook preto" + MovimentoPeca.getPodeRookPreto());
		System.out.println("Entrou Aqui!");
		if (verificaMovimento) {
			System.out.println("Jogada Invalida!");
			MovimentoPeca.validacao = false;

		} else if (verificaJogada(posiChegada)) {
			movimentouOuComeu = true;
			super.setMovimentou(movimentouOuComeu);

			String posicaoChegadaConvertida = MovimentoPeca.reConverterPosicao(linhaChegada, colunChegada);
			String posicaoAtualConvertida = MovimentoPeca.reConverterPosicao(l, c);

			MainViewController ma = new MainViewController();
			System.out.println("A linha atual é :" + l + " " + linhaChegada);

			AnchorPane pane = this.getAnchorPane();
			if (vez % 2 == 1) {
				if (MovimentoPeca.getPodeRookBranco()) {
					// rook
					if (l == 7 && linhaChegada == 7) {
						if (posiChegada.equals("g1")) {
							if (MovimentoPeca.getPodeRookPequenoBranco()) {

								if (this.getAnchorPane() != null) {
									int[] posR = MovimentoPeca.converterPosicao("h1");

									ImageView i = (ImageView) pane.lookup("#g1");

									ImageView a = (ImageView) pane.lookup("#f1");
									ImageView u = (ImageView) pane.lookup("#h1");
									ImageView e = (ImageView) pane.lookup("#e1");

									this.getWhitePosition1().setImage(ma.getKingWhite());

									// e.setImage(null);
									a.setImage(ma.getRookWhite());
									u.setImage(null);

									MovimentoPeca.setFezRooqueBranco(true);

									chess[linhaChegada][colunChegada] = peca;
									chess[linhaChegada][colunChegada - 1] = String.valueOf(Chess.whiteRook);
									chess[posR[0]][posR[1]] = String.valueOf(quadrado);
									chess[l][c] = String.valueOf(quadrado);

									MovimentoPeca.setPodeRookBranco(false);
									MovimentoPeca.setPodeRookPequenoBranco(false);

									super.setMovimentou(true);
									verificaBolinha();

									try {
										throw new Exception();
									} catch (Exception e1) {
										e1.printStackTrace();
									}
								}
							}
						} else {

							if (posiChegada.equals("c1")) {
								if (MovimentoPeca.getPodeRookGrandePreto()) {
									if (this.getAnchorPane() != null) {
										System.out.println("entrei no c1");

										ImageView i = (ImageView) pane.lookup("#c1");

										ImageView a = (ImageView) pane.lookup("#d1");
										ImageView u = (ImageView) pane.lookup("#a1");
										ImageView e = (ImageView) pane.lookup("#e1");


										i.setImage(ma.getKingWhite());
										// e.setImage(null);
										a.setImage(u.getImage());
										u.setImage(null);

										MovimentoPeca.setFezRooqueBranco(true);

										chess[linhaChegada][colunChegada] = String.valueOf(Chess.whiteKing);
										chess[linhaChegada][colunChegada + 1] = String.valueOf(Chess.whiteRook);
										chess[linhaChegada][colunChegada - 2] = String.valueOf(quadrado);
										chess[l][c] = String.valueOf(quadrado);

										MovimentoPeca.setPodeRookBranco(false);
										MovimentoPeca.setPodeRookGrandeBranco(false);
										super.setMovimentou(true);
										verificaBolinha();

										try {
											throw new Exception();
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
								}
							}
						}
					}

				}
			} else if (vez % 2 == 0 && MovimentoPeca.getPodeRookPreto()) {
				System.out.println("Pode rook Preto!");
				if (l == 0 && linhaChegada == 0) {
					System.out.println("A linha Preta ta correta!");
					if (posiChegada.equals("g8")) {
						if (MovimentoPeca.getPodeRookPequenoPreto()) {
							System.out.println("Entrei no rook pequeno");
							if (this.getAnchorPane() != null) {
								System.out.println("Entrei no Rook!");
								int[] posRook = MovimentoPeca.converterPosicao("g8"); // com o rook

								int l1 = posRook[0];
								int c1 = posRook[1];

								ImageView i = (ImageView) pane.lookup("#g8");
								ImageView a = (ImageView) pane.lookup("#f8");
								ImageView u = (ImageView) pane.lookup("#h8");
								ImageView e = (ImageView) pane.lookup("#e8");

								// this.getBlackPosition1().setImage(ma.getKingBlack());
								i.setImage(ma.getKingBlack());
								// e.setImage(null);
								a.setImage(u.getImage());
								u.setImage(null);

								MovimentoPeca.setFezRooquePreto(true);

								chess[l1][c1] = String.valueOf(Chess.blackKing);
								chess[l1][c1 - 1] = String.valueOf(Chess.blackRook);
								chess[l1][c1 + 1] = String.valueOf(quadrado);
								chess[l][c] = String.valueOf(quadrado);

								MovimentoPeca.setPodeRookPreto(false);
								MovimentoPeca.setPodeRookPequenoPreto(false);
								super.setMovimentou(true);
								verificaBolinha();

								try {
									throw new Exception();
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						}
					} else

					{
						if (posiChegada.equals("c8")) {

							if (MovimentoPeca.getPodeRookGrandePreto()) {

								if (this.getAnchorPane() != null) {
									System.out.println("entrei no c8");
									int[] posRook = MovimentoPeca.converterPosicao("c8"); // com o rook

									int l1 = posRook[0];
									int c1 = posRook[1];

									ImageView i = (ImageView) pane.lookup("#c8");
									ImageView a = (ImageView) pane.lookup("#d8");
									ImageView u = (ImageView) pane.lookup("#a8");
									ImageView e = (ImageView) pane.lookup("#e8");

									i.setImage(ma.getKingBlack());
									// e.setImage(null);
									a.setImage(u.getImage());
									u.setImage(null);

									MovimentoPeca.setFezRooquePreto(true);

									chess[l1][c1] = String.valueOf(Chess.blackKing);
									chess[l1][c1 + 1] = String.valueOf(Chess.blackRook);
									chess[l1][c1 - 2] = String.valueOf(quadrado);
									chess[l][c] = String.valueOf(quadrado);

									MovimentoPeca.setPodeRookPreto(false);
									MovimentoPeca.setPodeRookGrandePreto(false);
									super.setMovimentou(true);
									verificaBolinha();
									try {
										throw new Exception();
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}
						}
					}
				}
			}

			if (vez % 2 == 1) {
				MovimentoPeca.setPodeRookBranco(false);
			} else {
				MovimentoPeca.setPodeRookPreto(false);
			}

			if (this.getAnchorPane() != null) {

				ImageView b = (ImageView) pane.lookup("#" + posicaoChegadaConvertida);

				ImageView n = (ImageView) pane.lookup("#" + posicaoAtualConvertida);

				b.setImage(n.getImage());
				n.setImage(null);

			}

			System.out.println("O rei Movimentou uma vez");
			chess[linhaChegada][colunChegada] = peca;
			chess[l][c] = String.valueOf(quadrado);
			verificaBolinha();

		} else

		{
			System.out.println("Jogada Invalida!");
			MovimentoPeca.validacao = false;
		}
		super.setMovimentou(movimentouOuComeu);

	}

	@Override
	public void movimentarPeca1(String posiAtual, String posiChegada, String chess[][]) {
		MovimentoPeca.setReiMovimentou(false);

		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);
		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pAtual[0];
		int c = pAtual[1];
		int linhaChegada = pChegada[0];
		int colunChegada = pChegada[1];

		String peca;
		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteKing) : String.valueOf(Chess.blackKing);
		boolean verificaMovimento = (vez % 2 == 1) ? verificaMovimentoBranca(linhaChegada, colunChegada)
				: verificaMovimentoPreto(linhaChegada, colunChegada);

		if (verificaMovimento) {
			System.out.println("Jogada Invalida!");

		} else if (verificaJogada1(posiChegada, chess)) {
			System.out.println("Entrei no MovimentarPEca1");

			MovimentoPeca.setReiMovimentou(true);
			chess[linhaChegada][colunChegada] = peca;
			chess[l][c] = String.valueOf(quadrado);
			verificaBolinha(chess);

		} else

		{

			System.out.println("Jogada Invalida!");
			verificaBolinha(chess);
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
