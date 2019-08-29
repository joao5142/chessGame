package pieces;

import chess.Chess;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import utils.MovimentoPeca;

import static pieces.Piece.chess;
import static utils.MovimentoPeca.bolinha;
import static utils.MovimentoPeca.quadrado;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

	private static boolean movimentouOuComeu = false;

	public Bishop(String chess[][], int vez) {
		super(chess, vez);

	}

	public Bishop(int vezDejogar) {
		super(vezDejogar);
	}

	@Override
	public boolean xeque(String posiChegada) {

		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pChegada[0];
		int c = pChegada[1];

		boolean pecas;
		int coluna = c;
		int qtd = 0;

		String p = (vez % 2 == 1) ? String.valueOf(Chess.blackKing) : String.valueOf(Chess.whiteKing);
		try {
			for (int i = l - 1; i >= 0; i--) {
				int col = --coluna;
				pecas = (vez % 2 == 1) ? verificaMovimentoPreto(i, col) : verificaMovimentoBranca(i, col);
				if (pecas) {
					qtd++;
					if (qtd == 1) {
						if (chess[i][col].equals(p)) {
							int b = c;
							for (int a = l - 1; a >= 0; a--) {
								b--;
								boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(a, b)
										: verificaMovimentoPreto1(a, b);
								if (veri) {
									return false;
								}

							}

							return true;
						}
					}
				}
			}
		} catch (Exception e) {

		}

		coluna = c;
		qtd = 0;
		try {

			for (int i = l - 1; i >= 0; i--) {
				int col = ++coluna;

				pecas = (vez % 2 == 1) ? verificaMovimentoPreto(i, col) : verificaMovimentoBranca(i, col);

				if (pecas) {
					qtd++;

					if (qtd == 1) {
						if (chess[i][col].equals(p)) {

							int b = c;
							for (int a = l - 1; a >= 0; a--) {
								b++;
								boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(a, b)
										: verificaMovimentoPreto1(a, b);
								if (veri) {
									return false;
								}

							}
							return true;
						}
					}
				}
			}
		} catch (Exception e) {

		}

		coluna = c;
		qtd = 0;
		try {
			for (int i = l + 1; i <= 7; i++) {
				int col = --coluna;
				pecas = (vez % 2 == 1) ? verificaMovimentoPreto(i, col) : verificaMovimentoBranca(i, col);

				if (pecas) {
					qtd++;
					if (qtd == 1) {
						if (chess[i][col].equals(p)) {
							int b = c;
							for (int a = l + 1; a <= 7; a++) {
								b--;
								boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(a, b)
										: verificaMovimentoPreto1(a, b);
								if (veri) {
									return false;
								}
							}
							return true;

						}
					}
				}
			}
		} catch (Exception e) {

		}
		coluna = c;
		qtd = 0;
		for (int i = l + 1; i <= 7; i++) {
			int col = ++coluna;

			pecas = (vez % 2 == 1) ? verificaMovimentoPreto(i, col) : verificaMovimentoBranca(i, col);

			if (pecas) {
				qtd++;
				if (qtd == 1) {
					if (chess[i][col].equals(p)) {
						int b = c;
						for (int a = l + 1; a <= 7; a++) {
							b++;
							boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(a, b)
									: verificaMovimentoPreto1(a, b);
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

	// metodo para verificar se uma peça está cravada ou n !

	public boolean xeque(String posiChegada, String chess[][]) {

		int pChegada[] = MovimentoPeca.converterPosicao(posiChegada);

		int l = pChegada[0];
		int c = pChegada[1];

		boolean pecas;
		int coluna = c;
		int qtd = 0;

		String p = (vez % 2 == 1) ? String.valueOf(Chess.blackKing) : String.valueOf(Chess.whiteKing);
		try {
			for (int i = l - 1; i >= 0; i--) {
				int col = --coluna;
				pecas = (vez % 2 == 1) ? verificaMovimentoPreto(i, col) : verificaMovimentoBranca(i, col);
				if (pecas) {
					qtd++;
					if (qtd == 1) {
						if (chess[i][col].equals(p)) {
							int b = c;
							for (int a = l - 1; a >= 0; a--) {
								b--;
								boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(a, b)
										: verificaMovimentoPreto1(a, b);
								if (veri) {
									return false;
								}

							}

							return true;
						}
					}
				}
			}
		} catch (Exception e) {

		}

		coluna = c;
		qtd = 0;
		try {

			for (int i = l - 1; i >= 0; i--) {
				int col = ++coluna;

				pecas = (vez % 2 == 1) ? verificaMovimentoPreto(i, col) : verificaMovimentoBranca(i, col);

				if (pecas) {
					qtd++;

					if (qtd == 1) {
						if (chess[i][col].equals(p)) {

							int b = c;
							for (int a = l - 1; a >= 0; a--) {
								b++;
								boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(a, b)
										: verificaMovimentoPreto1(a, b);
								if (veri) {
									return false;
								}

							}
							return true;
						}
					}
				}
			}
		} catch (Exception e) {

		}

		coluna = c;
		qtd = 0;
		try {
			for (int i = l + 1; i <= 7; i++) {
				int col = --coluna;
				pecas = (vez % 2 == 1) ? verificaMovimentoPreto(i, col) : verificaMovimentoBranca(i, col);

				if (pecas) {
					qtd++;
					if (qtd == 1) {
						if (chess[i][col].equals(p)) {
							int b = c;
							for (int a = l + 1; a <= 7; a++) {
								b--;
								boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(a, b)
										: verificaMovimentoPreto1(a, b);
								if (veri) {
									return false;
								}
							}
							return true;

						}
					}
				}
			}
		} catch (Exception e) {

		}
		coluna = c;
		qtd = 0;
		try {
			for (int i = l + 1; i <= 7; i++) {
				int col = ++coluna;

				pecas = (vez % 2 == 1) ? verificaMovimentoPreto(i, col) : verificaMovimentoBranca(i, col);

				if (pecas) {
					qtd++;
					if (qtd == 1) {
						if (chess[i][col].equals(p)) {
							int b = c;
							for (int a = l + 1; a <= 7; a++) {
								b++;
								boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(a, b)
										: verificaMovimentoPreto1(a, b);
								if (veri) {
									return false;
								}

							}

							return true;

						}
					}
				}
			}

		} catch (Exception e) {

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
					if (chess[i][j].equals(String.valueOf(Chess.whiteBishop))
							|| chess[i][j].equals(String.valueOf(Chess.whiteQueen))) {
						p = new Bishop(vez);
						int coluna = 0;
						int c = 0;

						try {
							posiA = MovimentoPeca.reConverterPosicao(i, j);

						} catch (Exception e) {
							e.printStackTrace();
						}

						c = j;
						coluna = c;

						try {

							for (int i1 = i - 1; i1 >= 0; i1--) {
								int col = ++coluna;
								try {
									veri3(p, posiA, l, i1, col, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
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

						coluna = c;
						try {
							for (int i1 = i - 1; i1 >= 0; i1--) {
								int col = --coluna;
								try {
									veri3(p, posiA, l, i1, col, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
											pecaQueDeuXeque, posiRei);
								} catch (Exception e) {

								} finally {
									chess = Piece.clonar(chessVerdadeiro);
								}
							}
						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						coluna = c;
						try {

							for (int i1 = i + 1; i1 < 9; i1++) {
								int col = ++coluna;
								try {
									veri3(p, posiA, l, i1, col, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
											pecaQueDeuXeque, posiRei);
								} catch (Exception e) {

								} finally {

									chess = Piece.clonar(chessVerdadeiro);

								}

							}
						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

						coluna = c;
						try {
							for (int i1 = i + 1; i1 < 9; i1++) {
								int col = --coluna;
								try {
									veri3(p, posiA, l, i1, col, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
											pecaQueDeuXeque, posiRei);
								} catch (Exception e) {

								} finally {

									chess = Piece.clonar(chessVerdadeiro);

								}
							}
						} catch (Exception e) {

						} finally {
							chess = Piece.clonar(chessVerdadeiro);
						}

					}

				} else if (chess[i][j].equals(String.valueOf(Chess.blackBishop))
						|| chess[i][j].equals(String.valueOf(Chess.blackQueen))) {

					p = new Bishop(vez);
					int coluna = 0;
					int c = 0;

					try {
						posiA = MovimentoPeca.reConverterPosicao(i, j);

					} catch (Exception e) {
						e.printStackTrace();
					}

					c = j;
					coluna = c;

					try {

						for (int i1 = i - 1; i1 >= 0; i1--) {
							int col = ++coluna;
							try {
								veri3(p, posiA, l, i1, col, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
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

					coluna = c;
					try {
						for (int i1 = i - 1; i1 >= 0; i1--) {
							int col = --coluna;
							try {
								veri3(p, posiA, l, i1, col, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
										pecaQueDeuXeque, posiRei);
							} catch (Exception e) {

							} finally {
								chess = Piece.clonar(chessVerdadeiro);
							}
						}
					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					coluna = c;
					try {

						for (int i1 = i + 1; i1 < 9; i1++) {
							int col = ++coluna;
							try {
								veri3(p, posiA, l, i1, col, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
										pecaQueDeuXeque, posiRei);
							} catch (Exception e) {

							} finally {

								chess = Piece.clonar(chessVerdadeiro);

							}

						}
					} catch (Exception e) {

					} finally {
						chess = Piece.clonar(chessVerdadeiro);
					}

					coluna = c;
					try {
						for (int i1 = i + 1; i1 < 9; i1++) {
							int col = --coluna;
							try {
								veri3(p, posiA, l, i1, col, 0, 0, chess, chessVerdadeiro, posiPecaQueDeuXeque,
										pecaQueDeuXeque, posiRei);
							} catch (Exception e) {

							} finally {

								chess = Piece.clonar(chessVerdadeiro);

							}
						}
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

		String posiC = MovimentoPeca.reConverterPosicao(i, j);

		p.movimentoDaPeca2(posiA, chess);
		//System.out.println("O xadrez com o movmento do bispo\n\n\n");
		//mostrarXadrez(chess);
		p.movimentarPeca1(posiA, posiC, chess);

		try {
			p.verificaBolinha(chess);
			verificaBolinha(chess);
			verificaBolinha();

		} catch (Exception e) {

		}

		//System.out.println("\n\nXadrez na verificacao do Bispo\n\n");
		//mostrarXadrez(chess);

		Piece pa = MovimentoPeca.jogar1(pecaQueDeuXeque);

		pa.movimentoDaPeca1(posiPecaQueDeuXeque, chess);
		//System.out.println("\n\nMovimentando a peça contra o bispo\n\n");
		//mostrarXadrez(chess);
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

		int coluna = c;
		String posiChegada;
		boolean verdade;
	 
			try {

				for (int i = l - 1; i >= 0; i--) {
					int col = ++coluna;

					try {
						posiChegada = MovimentoPeca.reConverterPosicao(i, col);
						verdade = pecaInimiga(posiAtual, posiChegada);

						if (verdade) {
							setPecaAmeaçada(i, col, this.getImageView(posiChegada));
							// chess[l - 2][c - 1] = String.valueOf(bolinha);
						}
					} catch (Exception e) {

					}

					if (chess[i][col].equals(String.valueOf(quadrado))) {
						chess[i][col] = String.valueOf(bolinha);
						setBolinha(i, col);
					} else {
						break;
					}

				}
			} catch (ArrayIndexOutOfBoundsException e) {

			}
			coluna = c;
			try {
				for (int i = l - 1; i >= 0; i--) {
					int col = --coluna;

					try {
						posiChegada = MovimentoPeca.reConverterPosicao(i, col);
						verdade = pecaInimiga(posiAtual, posiChegada);

						if (verdade) {
							setPecaAmeaçada(i, col, this.getImageView(posiChegada));
							// chess[l - 2][c - 1] = String.valueOf(bolinha);
						}
					} catch (Exception e) {

					}

					if (chess[i][col].equals(String.valueOf(quadrado))) {
						chess[i][col] = String.valueOf(bolinha);
						setBolinha(i, col);
					} else {
						break;
					}
				}
			} catch (Exception e) {

			}

			coluna = c;
			try {

				for (int i = l + 1; i < 9; i++) {
					int col = ++coluna;

					try {
						posiChegada = MovimentoPeca.reConverterPosicao(i, col);
						verdade = pecaInimiga(posiAtual, posiChegada);

						if (verdade) {
							setPecaAmeaçada(i, col, this.getImageView(posiChegada));
							// chess[l - 2][c - 1] = String.valueOf(bolinha);
						}
					} catch (Exception e) {

					}

					if (chess[i][col].equals(String.valueOf(quadrado))) {
						chess[i][col] = String.valueOf(bolinha);
						setBolinha(i, col);
					} else {
						break;
					}
				}
			} catch (Exception e) {

			}
			coluna = c;
			try {
				for (int i = l + 1; i < 9; i++) {
					int col = --coluna;

					try {
						posiChegada = MovimentoPeca.reConverterPosicao(i, col);
						verdade = pecaInimiga(posiAtual, posiChegada);

						if (verdade) {
							setPecaAmeaçada(i, col, this.getImageView(posiChegada));
							// chess[l - 2][c - 1] = String.valueOf(bolinha);
						}
					} catch (Exception e) {

					}

					if (chess[i][col].equals(String.valueOf(quadrado))) {
						chess[i][col] = String.valueOf(bolinha);
						setBolinha(i, col);
					} else {
						break;
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

		int coluna = c;
		String posiChegada;
		boolean verdade = false;

		try {

			for (int i = l - 1; i >= 0; i--) {
				int col = ++coluna;

				try {
					posiChegada = MovimentoPeca.reConverterPosicao(i, col);

					if (vez % 2 == 0) {
						if (chess[i][col].equals(String.valueOf(Chess.blackKing))) {
							verdade = true;
						}
					} else {
						if (chess[i][col].equals(String.valueOf(Chess.whiteKing))) {
							verdade = true;
						}
					}

					if (verdade) {
						// setPecaAmeaçada(i, col, this.getImageView(posiChegada));
						chess[i][col] = String.valueOf(bolinha);
					}
				} catch (Exception e) {

				}

				if (chess[i][col].equals(String.valueOf(quadrado))) {
					chess[i][col] = String.valueOf(bolinha);
					// setBolinha(i, col);
				} else {
					break;
				}

			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		coluna = c;
		try {
			for (int i = l - 1; i >= 0; i--) {
				int col = --coluna;

				try {
					posiChegada = MovimentoPeca.reConverterPosicao(i, col);
					if (vez % 2 == 0) {
						if (chess[i][col].equals(String.valueOf(Chess.blackKing))) {
							verdade = true;
						}
					} else {
						if (chess[i][col].equals(String.valueOf(Chess.whiteKing))) {
							verdade = true;
						}
					}
					if (verdade) {
						// setPecaAmeaçada(i, col, this.getImageView(posiChegada));
						chess[i][col] = String.valueOf(bolinha);
					}
				} catch (Exception e) {

				}

				if (chess[i][col].equals(String.valueOf(quadrado))) {
					chess[i][col] = String.valueOf(bolinha);
					// setBolinha(i, col);
				} else {
					break;
				}
			}
		} catch (Exception e) {

		}

		coluna = c;
		try {

			for (int i = l + 1; i < 9; i++) {
				int col = ++coluna;

				try {
					posiChegada = MovimentoPeca.reConverterPosicao(i, col);
					if (vez % 2 == 0) {
						if (chess[i][col].equals(String.valueOf(Chess.blackKing))) {
							verdade = true;
						}
					} else {
						if (chess[i][col].equals(String.valueOf(Chess.whiteKing))) {
							verdade = true;
						}
					}
					if (verdade) {
						// setPecaAmeaçada(i, col, this.getImageView(posiChegada));
						chess[i][col] = String.valueOf(bolinha);
					}
				} catch (Exception e) {

				}

				if (chess[i][col].equals(String.valueOf(quadrado))) {
					chess[i][col] = String.valueOf(bolinha);
					// setBolinha(i, col);
				} else {
					break;
				}
			}
		} catch (Exception e) {

		}
		coluna = c;
		try {
			for (int i = l + 1; i < 9; i++) {
				int col = --coluna;

				try {
					posiChegada = MovimentoPeca.reConverterPosicao(i, col);
					if (vez % 2 == 0) {
						if (chess[i][col].equals(String.valueOf(Chess.blackKing))) {
							verdade = true;
						}
					} else {
						if (chess[i][col].equals(String.valueOf(Chess.whiteKing))) {
							verdade = true;
						}
					}

					if (verdade) {
						// setPecaAmeaçada(i, col, this.getImageView(posiChegada));
						chess[i][col] = String.valueOf(bolinha);
					}
				} catch (Exception e) {

				}

				if (chess[i][col].equals(String.valueOf(quadrado))) {
					chess[i][col] = String.valueOf(bolinha);
					// setBolinha(i, col);
				} else {
					break;
				}
			}
		} catch (Exception e) {

		}

	}

	public void movimentoDaPeca2(String posiAtual, String chess[][]) {
		int pAtual[] = MovimentoPeca.converterPosicao(posiAtual);

		int l = pAtual[0];
		int c = pAtual[1];

		int coluna = c;
		String posiChegada;
		boolean verdade = false;

		try {

			for (int i = l - 1; i >= 0; i--) {
				int col = ++coluna;

				if (chess[i][col].equals(String.valueOf(quadrado))) {
					chess[i][col] = String.valueOf(bolinha);
					// setBolinha(i, col);
				} else {
					break;
				}

			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		coluna = c;
		try {
			for (int i = l - 1; i >= 0; i--) {
				int col = --coluna;

				if (chess[i][col].equals(String.valueOf(quadrado))) {
					chess[i][col] = String.valueOf(bolinha);
					// setBolinha(i, col);
				} else {
					break;
				}
			}
		} catch (Exception e) {

		}

		coluna = c;
		try {

			for (int i = l + 1; i < 9; i++) {
				int col = ++coluna;

				if (chess[i][col].equals(String.valueOf(quadrado))) {
					chess[i][col] = String.valueOf(bolinha);
					// setBolinha(i, col);
				} else {
					break;
				}
			}
		} catch (Exception e) {

		}
		coluna = c;
		try {
			for (int i = l + 1; i < 9; i++) {
				int col = --coluna;

				if (chess[i][col].equals(String.valueOf(quadrado))) {
					chess[i][col] = String.valueOf(bolinha);
					// setBolinha(i, col);
				} else {
					break;
				}
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

		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteBishop) : String.valueOf(Chess.blackBishop);
		movimentouOuComeu = true;

		if (this.getAnchorPane() != null) {
			AnchorPane pane = this.getAnchorPane();

			ImageView i = (ImageView) pane.lookup("#" + posiChegada);

			ImageView e = (ImageView) pane.lookup("#" + posiAtual);

			i.setImage(e.getImage());
			e.setImage(null);

			Pawn.setQtdCasas(0);
			Pawn.setP(null);

			/*
			 * if(vez%2==1) { m.setPecaComida1(m.getbBlack(),m.getContBB()); }else {
			 * m.setPecaComida1( m.getbWhite(),m.getContBW()); }
			 */
		}

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
		String peca = null;

		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteBishop) : String.valueOf(Chess.blackBishop);

		chess[lChegada][cChegada] = peca;
		chess[l][c] = String.valueOf(quadrado);

		try {
			verificaBolinha();
		} catch (Exception e) {

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

		boolean pecas;

		int coluna = 0;

		if (l > linhaChegada) {

			coluna = c;
			qtd = 0;

			for (int i = l - 1; i >= linhaChegada; i--) {
				int col = --coluna;

				pecas = (vez % 2 == 1) ? verificaMovimentoPreto1(i, col) : verificaMovimentoBranca1(i, col);

				if (pecas) {
					qtd++;

					if (qtd == 1) {
						linha1 = i;
						coluna1 = col;

						if (linha1 == linhaChegada && coluna1 == colunChegada) {
							int b = c;
							for (int a = l - 1; a >= linhaChegada; a--) {
								b--;
								boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(a, b)
										: verificaMovimentoPreto1(a, b);
								if (veri) {
									return false;
								}

							}

							return true;
						}
					}
				}

			}
			coluna = c;
			qtd = 0;
			for (int i = l - 1; i >= linhaChegada; i--) {
				int col = ++coluna;

				pecas = (vez % 2 == 1) ? verificaMovimentoPreto1(i, col) : verificaMovimentoBranca1(i, col);

				if (pecas) {
					qtd++;

					if (qtd == 1) {
						linha1 = i;
						coluna1 = col;

						if (linha1 == linhaChegada && coluna1 == colunChegada) {
							int b = c;
							for (int a = l - 1; a >= linhaChegada; a--) {
								b++;
								boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(a, b)
										: verificaMovimentoPreto1(a, b);
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

			coluna = c;
			qtd = 0;

			for (int i = l + 1; i <= linhaChegada; i++) {
				int col = --coluna;

				pecas = (vez % 2 == 1) ? verificaMovimentoPreto1(i, col) : verificaMovimentoBranca1(i, col);

				if (pecas) {
					qtd++;

					if (qtd == 1) {
						linha1 = i;
						coluna1 = col;

						if (linha1 == linhaChegada && coluna1 == colunChegada) {
							int b = c;
							for (int a = l + 1; a <= linhaChegada; a++) {
								b--;
								boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(a, b)
										: verificaMovimentoPreto1(a, b);
								if (veri) {
									return false;
								}

							}

							return true;
						}
					}
				}

			}
			coluna = c;
			qtd = 0;
			for (int i = l + 1; i <= linhaChegada; i++) {
				int col = ++coluna;

				pecas = (vez % 2 == 1) ? verificaMovimentoPreto1(i, col) : verificaMovimentoBranca1(i, col);

				if (pecas) {
					qtd++;

					if (qtd == 1) {
						linha1 = i;
						coluna1 = col;

						if (linha1 == linhaChegada && coluna1 == colunChegada) {
							int b = c;
							for (int a = l + 1; a <= linhaChegada; a++) {
								b++;
								boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca1(a, b)
										: verificaMovimentoPreto1(a, b);
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
		int qtd = 0;
		int linha1 = 0, coluna1 = 0;

		boolean pecas;

		int coluna = 0;

		if (l > linhaChegada) {
			try {
				coluna = c;
				qtd = 0;

				for (int i = l - 1; i >= linhaChegada; i--) {
					int col = --coluna;

					pecas = (vez % 2 == 1) ? verificaMovimentoPreto(i, col) : verificaMovimentoBranca(i, col);

					if (pecas) {
						qtd++;

						if (qtd == 1) {
							linha1 = i;
							coluna1 = col;

							if (linha1 == linhaChegada && coluna1 == colunChegada) {
								int b = c;
								for (int a = l - 1; a >= linhaChegada; a--) {
									b--;
									boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca(a, b)
											: verificaMovimentoPreto(a, b);
									if (veri) {
										return false;
									}

								}

								return true;
							}
						}
					}

				}

			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				coluna = c;
				qtd = 0;
				for (int i = l - 1; i >= linhaChegada; i--) {
					int col = ++coluna;

					pecas = (vez % 2 == 1) ? verificaMovimentoPreto(i, col) : verificaMovimentoBranca(i, col);

					if (pecas) {
						qtd++;

						if (qtd == 1) {
							linha1 = i;
							coluna1 = col;

							if (linha1 == linhaChegada && coluna1 == colunChegada) {
								int b = c;
								for (int a = l - 1; a >= linhaChegada; a--) {
									b++;
									boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca(a, b)
											: verificaMovimentoPreto(a, b);
									if (veri) {
										return false;
									}

								}

								return true;
							}
						}
					}
				}

			} catch (Exception e) {

			}
		} else {

			try {

				coluna = c;
				qtd = 0;

				for (int i = l + 1; i <= linhaChegada; i++) {
					int col = --coluna;

					pecas = (vez % 2 == 1) ? verificaMovimentoPreto(i, col) : verificaMovimentoBranca(i, col);

					if (pecas) {
						qtd++;

						if (qtd == 1) {
							linha1 = i;
							coluna1 = col;

							if (linha1 == linhaChegada && coluna1 == colunChegada) {
								int b = c;
								for (int a = l + 1; a <= linhaChegada; a++) {
									b--;
									boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca(a, b)
											: verificaMovimentoPreto(a, b);
									if (veri) {
										return false;
									}

								}

								return true;
							}
						}
					}

				}

			} catch (Exception e) {

			}

			try {

				coluna = c;
				qtd = 0;
				for (int i = l + 1; i <= linhaChegada; i++) {
					int col = ++coluna;

					pecas = (vez % 2 == 1) ? verificaMovimentoPreto(i, col) : verificaMovimentoBranca(i, col);

					if (pecas) {
						qtd++;

						if (qtd == 1) {
							linha1 = i;
							coluna1 = col;

							if (linha1 == linhaChegada && coluna1 == colunChegada) {
								int b = c;
								for (int a = l + 1; a <= linhaChegada; a++) {
									b++;
									boolean veri = (vez % 2 == 1) ? verificaMovimentoBranca(a, b)
											: verificaMovimentoPreto(a, b);
									if (veri) {
										return false;
									}

								}

								return true;
							}
						}
					}
				}
			} catch (Exception e) {

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
		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteBishop) : String.valueOf(Chess.blackBishop);
		boolean verificaMovimento = (vez % 2 == 1) ? verificaMovimentoBranca1(linhaChegada, colunChegada)
				: verificaMovimentoPreto1(linhaChegada, colunChegada);

		boolean verdade = verificaJogada(posiChegada);

		if (verificaMovimento) {
			System.out.println("Jogada Invalida!");
			MovimentoPeca.validacao = false;

		} else if (this.verificaJogada(posiChegada)) {
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
		peca = (vez % 2 == 1) ? String.valueOf(Chess.whiteBishop) : String.valueOf(Chess.blackBishop);

		boolean verificaMovimento = (vez % 2 == 1) ? verificaMovimentoBranca(linhaChegada, colunChegada)
				: verificaMovimentoPreto(linhaChegada, colunChegada);

		if (verificaMovimento) {

		} else if (verificaJogada1(posiChegada, chess)) {

			chess[linhaChegada][colunChegada] = peca;
			chess[l][c] = String.valueOf(quadrado);
			try {
				verificaBolinha(chess);
				verificaBolinha();
			} catch (Exception e) {

			}

		} else {
			System.out.println("Jogada Invalida bispo!");
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
