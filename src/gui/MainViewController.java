package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import chess.Chess;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;
import utils.MovimentoPeca;

public class MainViewController implements Initializable {
	// Peças
	private static final Image pawnW = new Image("/img/piece/chessPiece/pawnWhite.png");
	private static final Image pawnW2 = new Image("/img/piece/chessPiece/pawnWhite1.png");

	private static final Image bishopW = new Image("/img/piece/chessPiece/bishopWhite.png");
	private static final Image bishopW2 = new Image("/img/piece/chessPiece/bishopWhite1.png");

	private static final Image kWhite = new Image("/img/piece/chessPiece/kWhite.png");
	private static final Image kWhite2 = new Image("/img/piece/chessPiece/kWhite1.png");

	private static final Image kingWhite = new Image("/img/piece/chessPiece/kingWhite.png");
	private static final Image kingWhiteV = new Image("/img/piece/chessPiece/kingWhiteV.png");

	private static final Image queenWhite = new Image("/img/piece/chessPiece/queenWhite.png");
	private static final Image queenWhite2 = new Image("/img/piece/chessPiece/queenWhite1.png");

	private static final Image rookWhite = new Image("/img/piece/chessPiece/rookWhite.png");
	private static final Image rookWhite2 = new Image("/img/piece/chessPiece/rookWhite1.png");

	private static final Image pawnBlack = new Image("/img/piece/chessPiece/pawn.png");
	private static final Image pawnBlack2 = new Image("/img/piece/chessPiece/pawn1.png");

	private static final Image bishopBlack = new Image("/img/piece/chessPiece/bishop.png");
	private static final Image bishopBlack2 = new Image("/img/piece/chessPiece/bishop1.png");

	private static final Image kBlack = new Image("/img/piece/chessPiece/k.png");
	private static final Image kBlack2 = new Image("/img/piece/chessPiece/k1.png");

	private static final Image kingBlack = new Image("/img/piece/chessPiece/king.png");
	private static final Image kingBlackV = new Image("/img/piece/chessPiece/kingV.png");

	private static final Image queenBlack = new Image("/img/piece/chessPiece/queen.png");
	private static final Image queenBlack2 = new Image("/img/piece/chessPiece/queen1.png");

	private static final Image rookBlack = new Image("/img/piece/chessPiece/rook.png");
	private static final Image rookBlack2 = new Image("/img/piece/chessPiece/rook1.png");

	private double xInicial;
	private double yInicial;

	static String chess[][] = Chess.chess();
	private boolean jogoAcabou = true;

	private int vezDejogar = 1;
	private String pecaSelecionada = null;
	private boolean jogadaValida;
	private int jogar = 0;
	String lugarAserJogado = null;
	private int cont = 1;
	private String peca;
	private Piece piece;
	private String posiAtual;
	private AnchorPane pane;
	private boolean podePassant;
	private boolean estaEmXeque = false;
	private boolean xeque;
	private String pecaAnterior;

	private List<String> pecasCravadas = new ArrayList<>();
	private Map<String, String> pecaCravadaEqueCravou = new HashMap<>();

	private List<Image> pecasBrancasComidas = new ArrayList<>();
	private List<Image> pecasPretasComidas = new ArrayList<>();

	// Botoes para selecionar o tempo
	@FXML
	private Button t1;

	@FXML
	private Button t2;

	@FXML
	private Button t3;

	@FXML
	private Button t4;

	@FXML
	private Button t5;

	@FXML
	private Button t6;

	@FXML
	private Label lblW;

	// Label para o cronometro
	@FXML
	private Label timeBrancas;

	@FXML
	private Label timePreto;

	// Peças que ficarao na tela apos serem comidas
	@FXML
	private ImageView pBlack;

	@FXML
	private ImageView kiBlack;

	@FXML
	private ImageView rBlack;

	@FXML
	private ImageView bBlack;

	@FXML
	private ImageView qBlack;

	@FXML
	private ImageView pWhite;

	@FXML
	private ImageView kiWhite;

	@FXML
	private ImageView rWhite;

	@FXML
	private ImageView bWhite;

	@FXML
	private ImageView qWhite;

	// CONTADOR de PEÇAS COMIDA
	@FXML
	private Label contPW;

	@FXML
	private Label contKW;

	@FXML
	private Label contRW;

	@FXML
	private Label contBW;

	@FXML
	private Label contQW;

	@FXML
	private Label contKB;

	@FXML
	private Label contRB;

	@FXML
	private Label contBB;

	@FXML
	private Label contQB;

	@FXML
	private Label contPB;

	// AnchorPane e outros;

	@FXML
	private AnchorPane panePai;

	@FXML
	private AnchorPane panePrincipal;

	@FXML
	private AnchorPane paneLateral;

	@FXML
	private ImageView showPane;

	List<String> posiSairXequeMovimentando = new ArrayList<>();

	@FXML
	void onSurrenderClick(MouseEvent event) {
		if (vezDejogar % 2 == 1) {
			lblW.setText("Blacks Winner");
			jogoAcabou = true;
			if (threadTempo1.isAlive()) {
				threadTempo1.interrupt();
			}
		} else {
			lblW.setText("Whites Winner");
			jogoAcabou = true;
			if (threadTempo2.isAlive()) {
				threadTempo2.interrupt();
			}
		}

		lblW.setVisible(true);
	}

	@FXML
	void onDrawnClick(MouseEvent event) {
		if (!jogoAcabou) {
			lblW.setText("Drawn");
			lblW.setVisible(true);
			jogoAcabou = true;

			if (vezDejogar % 2 == 1) {

				if (threadTempo1.isAlive()) {
					threadTempo1.interrupt();
				}
			} else {

				if (threadTempo2.isAlive()) {
					threadTempo2.interrupt();
				}
			}
		}

	}

	@FXML
	void onTClck(ActionEvent event) {
		Button bt = (Button) event.getSource();

		String time = bt.getText();
		if (time.equals("3min")) {
			timeBrancas.setText("03:00");
			timePreto.setText("03:00");
		} else if (time.equals("5min")) {
			timeBrancas.setText("05:00");
			timePreto.setText("05:00");
		} else if (time.equals("10min")) {
			timeBrancas.setText("10:00");
			timePreto.setText("10:00");
		} else if (time.equals("15min")) {
			timeBrancas.setText("15:00");
			timePreto.setText("15:00");
		} else if (time.equals("20min")) {
			timeBrancas.setText("20:00");
			timePreto.setText("20:00");
		} else if (time.equals("30min")) {
			timeBrancas.setText("30:00");
			timePreto.setText("30:00");
		}

	}

	@FXML
	void onPlayClick(MouseEvent event) {

		if (timeBrancas.getText().equals("00:00")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Coloque o tempo!");
		} else {
			jogoAcabou = false;
			initThread1();
			ImageView v = (ImageView) event.getSource();
			v.setVisible(false);
			t1.setVisible(false);
			t2.setVisible(false);
			t3.setVisible(false);
			t4.setVisible(false);
			t5.setVisible(false);
			t6.setVisible(false);
		}

	}

	@FXML
	void onHidePaneClick(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		double x = paneLateral.getWidth();
		// System.out.println("A largura de x é : "+x);
		paneLateral.setVisible(false);
		showPane.setVisible(true);
		stage.setX(xInicial + (x / 2));

	}

	@FXML
	void onCloseClick(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void onShowPaneClick(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		double x = paneLateral.getWidth();

		paneLateral.setVisible(true);

		showPane.setVisible(false);

		stage.setX(xInicial);
		stage.setY(yInicial);
	}

	String pecaQueDeuXeque = null;
	String posicaoDaPecaQueDeuXeque = null;

	boolean estaSaindodoXeque = false;
	boolean sairXequeComendo = false;
	boolean sairXequeMovimentando = false;

	List<String> sairCravadaB = null;

	//onde ocorre toda logica do jogo
	@FXML
	void onMouseClick(MouseEvent event) throws Exception {

		if (piece != null) {
			piece.setMainView(this);
		}
		if (xeque && !sairXequeComendo && !sairXequeMovimentando) {
			if (pecaQueDeuXeque == null) {
				pecaQueDeuXeque = pecaSelecionada;
				posicaoDaPecaQueDeuXeque = lugarAserJogado;
			}

			if (posiPecaXequeDescoberto != null) {
				int vet[] = MovimentoPeca.converterPosicao(posiPecaXequeDescoberto);
				int l1 = vet[0];
				int c1 = vet[1];
				String pecaXeque = chess[l1][c1];

				pecaQueDeuXeque = pecaXeque;
				posicaoDaPecaQueDeuXeque = posiPecaXequeDescoberto;
			}

			System.out.println("A peça q deu xeque foi :" + pecaQueDeuXeque + " " + posicaoDaPecaQueDeuXeque);
			String pecaParaSair = ((ImageView) event.getSource()).getId();
			ImageView imag = (ImageView) event.getSource();

			if (!sairXequeComendo && !sairXequeMovimentando) {
				try {
					// primeira Verificacao de sair do xeque
					if (sairXeque(pecaQueDeuXeque, posicaoDaPecaQueDeuXeque, pecaParaSair, imag)) {
						System.out.println("Pode Sair");
						sairXequeComendo = true;
					} else {
						sairXequeComendo = false;
						// throw new Exception("Está em xeque");
					}

				} catch (NullPointerException e) {

				}

				try {
					// verificacao do xeque movimentando a peca
					if (!sairXequeComendo) {
						if (sairXeque1(pecaQueDeuXeque, posicaoDaPecaQueDeuXeque, pecaParaSair, imag)) {
							System.out.println("Pode Sair do xeque ");
							sairXequeMovimentando = true;
						} else {
							sairXequeMovimentando = false;
							throw new Exception("Está em xeque22");
						}
					}
				} catch (NullPointerException e) {
				}

			}
		}

		if (!jogoAcabou) {

			ImageView imageView = (ImageView) event.getSource();// pegando a referencia da peça clicada
			Image image = imageView.getImage();

			if (cont == 1) {
				pane = (AnchorPane) ((Node) event.getSource()).getScene().getRoot();

			}

			if (cont % 2 == 1) {// indica a peça que vai ser jogada
				try {
					piece.verificaBolinha(); // limpa as bolinhas possiveis

				} catch (NullPointerException e) {

				}

				try {
					pecaAnterior = pecaSelecionada; // peçaAnterior ira conter a ultima peça clicada
				} catch (Exception e) {
					e.printStackTrace();
				}

				pecaSelecionada = descobrirPeca(imageView);

				if (MovimentoPeca.verificaVez(pecaSelecionada)) { // verificando se o oponente esta jogando na vez do
																	// outro
					posiAtual = imageView.getId(); // pega a posição atual da peça
					// Verificao se a peca Esta cravada !

					if (!xeque) {
						System.out.println("Entrei no Verifica Cravada");

						if (verificaCravada1(posiAtual, pecaSelecionada)) {
							System.out.println("Entrei no Verifica Cravada");

							if (cravadaDoBispo) {
								ImageView im = (ImageView) pane.lookup("#" + posiAtual);
								System.out.println("A peça que esta cravando é " + pecaQueEstaCravando + " posicao"
										+ posiDaPecaCravando);
								sairCravadaB = sairCravadaBispo(pecaQueEstaCravando, posiDaPecaCravando, posiAtual, im);

								if (pecaSelecionada.equals(String.valueOf(Chess.whiteRook))
										|| pecaSelecionada.equals(String.valueOf(Chess.blackRook))
										|| pecaSelecionada.equals(String.valueOf(Chess.whiteKnight))
										|| pecaSelecionada.equals(String.valueOf(Chess.blackKnight))) {
									throw new Exception("A peça esta cravada");
								}
							} else if (cravadaDaTorre) {
								if (pecaSelecionada.equals(String.valueOf(Chess.whiteBishop))
										|| pecaSelecionada.equals(String.valueOf(Chess.blackBishop))
										|| pecaSelecionada.equals(String.valueOf(Chess.whiteKnight))
										|| pecaSelecionada.equals(String.valueOf(Chess.blackKnight))) {
									throw new Exception("A peça esta cravada");

								}
								System.out.println("SomenteCravadaDa torre");
							}
						} // Fim da Verificao da
					}

					MovimentoPeca.setChess(chess);
					MovimentoPeca.setVez(vezDejogar);

					piece = MovimentoPeca.jogar(pecaSelecionada);
					piece.setAnchorPane(pane);
					try {
						if (cravadaDaTorre) { // se a cravada for da torre só pode mover peao,torre e rainha!
							System.out.println("Ta cravado da Torre");

							if (pecaSelecionada.equals(String.valueOf(Chess.whiteBishop))
									|| pecaSelecionada.equals(String.valueOf(Chess.blackBishop))
									|| pecaSelecionada.equals(String.valueOf(Chess.whiteKnight))
									|| pecaSelecionada.equals(String.valueOf(Chess.blackKnight))) {

							} else {
								if (!xeque) {
									piece.movimentoDaPeca(posiAtual, null);

								} else {
									piece.movimentoDaPeca(posiAtual, posiSairXequeMovimentando);
								}

							}

						} else {
							if (!xeque) {
								piece.movimentoDaPeca(posiAtual, null);

							} else {
								piece.movimentoDaPeca(posiAtual, posiSairXequeMovimentando);
							}
						}

					} catch (Exception e) {

					}
					if (pecaSelecionada != null) {
						cont++;

					}

					for (int i = 0; i < 15; i++) {
						System.out.println();
					}
					mostrarXadrez();
				}

				// System.out.println(pecaSelecionada);
				piece.setMovimentou(false);

			} else {// indica o lugar a ser jogado!

				// Verificando possiveis erros , como , tentar mover a peça do oponente ,ou
				// clicar na propria peça

				if (vezDejogar % 2 == 1) {
					if (comerPecaBranca(imageView)) {
						sairXequeMovimentando = false;
						sairXequeComendo = false;
						piece.setMovimentou(false);
						cont--;
						piece.verificaBolinha();
						throw new Exception();
					}
				} else {
					if (comerPecaPreta(imageView)) {
						sairXequeMovimentando = false;
						sairXequeComendo = false;
						piece.setMovimentou(false);
						cont--;
						piece.verificaBolinha();
						throw new Exception();
					}
				}

				String pecaJogada = "alc";

				try {

					pecaJogada = descobrirPeca(imageView);
				} catch (NullPointerException e) {

				}
				if (pecaJogada != null) {
					if (pecaJogada.equals(pecaSelecionada)) {
						sairXequeMovimentando = false;
						sairXequeComendo = false;
						piece.setMovimentou(false);
						cont--;
						piece.verificaBolinha();
						throw new Exception("Nao pode jogar na mesma posição");
					}
				}

				try {
					int[] pos = MovimentoPeca.converterPosicao(lugarAserJogado);

					if (vezDejogar % 2 == 1) {

						if (MovimentoPeca.verificaMovimentoBranca(pos[0], pos[1])) { // verificando
							sairXequeMovimentando = false;
							sairXequeComendo = false;
							piece.setMovimentou(false);
							cont--;
							piece.verificaBolinha();
							throw new Exception("A Jogada é invalida!");
						}

					} else if (vezDejogar % 2 == 0) {

						if (MovimentoPeca.verificaMovimentoPreto(pos[0], pos[1])) {
							sairXequeMovimentando = false;
							sairXequeComendo = false;
							piece.setMovimentou(false);
							cont--;
							piece.verificaBolinha();
							throw new Exception("A Jogada é invalida!");
						}

					}
				} catch (NullPointerException e) {

				} catch (Exception e) {

				}

				// Fim da verificação de Erros !

				// Verificação de Peão Passante

				if (pecaSelecionada != null) {
					lugarAserJogado = imageView.getId();

					if (cravadaDaTorre) {

					} else {
						if (pecaSelecionada.equals(String.valueOf(Chess.whitePawn))
								|| pecaSelecionada.equals(String.valueOf(Chess.blackPawn))) {

							if (piece.comerPassante(posiAtual, lugarAserJogado)) {
								if (piece.getMovimentou()) {// se a peça movimentou
									setPecaComida(imageView.getImage());
									setTime();
									vezDejogar++; // passa a vez
									cont++;
									MovimentoPeca.setChess(chess);
									MovimentoPeca.setVez(vezDejogar);
									xeque = piece.xeque(lugarAserJogado);

									if (xeque) {
										setReiAmeacado(vezDejogar);
										System.out.println("Rei em Xeque!");
									}
									int vet[] = MovimentoPeca.converterPosicao(lugarAserJogado);
									int l1 = vet[0];
									int c1 = vet[1];
									String pecaXeque = chess[l1][c1];

									if (verificaMate(pecaXeque, posiPecaXequeDescoberto)) {
										if (vezDejogar % 2 == 1) {
											lblW.setText("Blacks Winner");
											lblW.setVisible(true);

										} else {
											lblW.setText("Whites Winner");
											lblW.setVisible(true);

										}
										Alert al = new Alert(AlertType.CONFIRMATION);
										al.setContentText("Jogo acabou!");
										al.show();

										jogoAcabou = true;

										if (threadTempo1.isAlive()) {
											threadTempo1.interrupt();
										}
										if (threadTempo2.isAlive()) {
											threadTempo2.interrupt();
										}
									}

									if (!xeque) {

										xeque = verificaXequeDescoberto(chess);
										try {
											if (xeque) {
												System.out.println("Xeque Descoberto!");
												setReiAmeacado(vezDejogar);

												int vet2[] = MovimentoPeca.converterPosicao(posiPecaXequeDescoberto);
												int l2 = vet2[0];
												int c2 = vet2[1];
												String pecaXeque1 = chess[l2][c2];

												if (verificaMate(pecaXeque1, posiPecaXequeDescoberto)) {
													if (vezDejogar % 2 == 1) {
														lblW.setText("Blacks Winner");
														lblW.setVisible(true);

													} else {
														lblW.setText("Whites Winner");
														lblW.setVisible(true);

													}
													Alert al = new Alert(AlertType.CONFIRMATION);
													al.setContentText("Jogo acabou!");
													al.show();

													jogoAcabou = true;

													if (threadTempo1.isAlive()) {
														threadTempo1.interrupt();
													}
													if (threadTempo2.isAlive()) {
														threadTempo2.interrupt();
													}
												}

											}
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
									throw new Exception("Comeu O Passante!");
								}

							}

							// System.out.println("O total de casas Jogadas pelo peao foi de :" + qtdCasas);
						}
					}

				} else {
					throw new Exception("Nenhuma Peca Selecionada!");
				}
				// Fim da verificação de peão passante

				// Verificação p ver se é para comer uma peça
				if (cravadaDaTorre) {
					System.out.println("EstaCravada");
					String coluna2 = null;
					String coluna3 = "das";
					try {
						coluna2 = lugarAserJogado.substring(0, 1);
						coluna3 = colunaQuePodeSeMover.substring(0, 1);
						System.out.println(coluna2 + " " + coluna3);
					} catch (Exception e) {
						// e.printStackTrace();
					}

					if (cravadaDaTorre && !coluna2.equals(coluna3)) {
						cont--;
						sairXequeMovimentando = false;
						sairXequeComendo = false;
						piece.setMovimentou(false);
						piece.verificaBolinha();
						throw new Exception("A posição nao é certa");
					}
				}
				if ((cravadaDaTorre && !lugarAserJogado.equals(posiDaPecaCravando))
						|| ((cravadaDoBispo && !lugarAserJogado.equals(posiDaPecaCravando))
								&& sairCravadaB.get(0) == null)) {

				} else {
					if (vezDejogar % 2 == 1 & piece.pecaInimiga(posiAtual, lugarAserJogado)) {
						if (xeque) {
							if (sairXequeComendo) {
								String posiPraJogar = imageView.getId();

								if (!posiPraJogar.equals(posicaoDaPecaQueDeuXeque)) {
									cont--;
									sairXequeMovimentando = false;
									sairXequeComendo = false;
									piece.setMovimentou(false);
									piece.verificaBolinha();
									throw new Exception("A posição nao é certa");
								} else if (comerPecaPreta(imageView)) {
									sairXequeMovimentando = false;
									sairXequeComendo = false;
									xeque = false;
									setPecaComida(imageView.getImage());
									piece.comerPeca(posiAtual, lugarAserJogado);
									piece.setMovimentou(true);
									pecaQueDeuXeque = null;
									posicaoDaPecaQueDeuXeque = null;
									tirarReiAmeacado(vezDejogar);
								}
							}

						} else if (comerPecaPreta(imageView)) {

							setPecaComida(imageView.getImage());
							piece.comerPeca(posiAtual, lugarAserJogado);
							piece.setMovimentou(true);

						}

					} else {
						if (xeque) {
							if (sairXequeComendo) {
								String posiPraJogar = imageView.getId();

								if (!posiPraJogar.equals(posicaoDaPecaQueDeuXeque)) {
									cont--;
									sairXequeMovimentando = false;
									sairXequeComendo = false;
									piece.setMovimentou(false);
									piece.verificaBolinha();

									throw new Exception("A posição nao é certa");
								} else if (comerPecaBranca(imageView)) {
									sairXequeMovimentando = false;
									sairXequeComendo = false;
									xeque = false;
									setPecaComida(imageView.getImage());
									piece.comerPeca(posiAtual, lugarAserJogado);
									piece.setMovimentou(true);
									pecaQueDeuXeque = null;
									posicaoDaPecaQueDeuXeque = null;
									tirarReiAmeacado(vezDejogar);

								}
							}

						} else if (comerPecaBranca(imageView) && piece.pecaInimiga(posiAtual, lugarAserJogado)) {
							setPecaComida(imageView.getImage());
							piece.comerPeca(posiAtual, lugarAserJogado);
							piece.setMovimentou(true);

						}
					}
				}
				// FIm da verificação de comer peça!

				if (piece.getMovimentou()) {// se a peça movimentou
					piece.setMovimentou(false);
					setTime();
					vezDejogar++; // passa a vez
					cont++;
					MovimentoPeca.setChess(chess);
					MovimentoPeca.setVez(vezDejogar);
					posiPecaXequeDescoberto = null;

					xeque = piece.xeque(lugarAserJogado);
					if (xeque) {
						setReiAmeacado(vezDejogar);

						int vet[] = MovimentoPeca.converterPosicao(lugarAserJogado);
						int l1 = vet[0];
						int c1 = vet[1];
						String pecaXeque = chess[l1][c1];

						if (verificaMate(pecaXeque, lugarAserJogado)) {
							System.out.println("Jogo Acabou");
							if (vezDejogar % 2 == 1) {
								lblW.setText("Blacks Winner");
								lblW.setVisible(true);
							} else {
								lblW.setText("Whites Winner");
								lblW.setVisible(true);
							}

							jogoAcabou = true;
							Alert al = new Alert(AlertType.CONFIRMATION);
							al.setContentText("Jogo acabou!");
							al.show();
							if (threadTempo1.isAlive()) {
								threadTempo1.interrupt();
							}
							if (threadTempo2.isAlive()) {
								threadTempo2.interrupt();
							}
						}

						if (!xeque) { // verificação do xequedescoberto
							xeque = verificaXequeDescoberto(chess);
							try {
								if (xeque) {
									System.out.println("Xeque Descoberto!");
									setReiAmeacado(vezDejogar);

									int vet4[] = MovimentoPeca.converterPosicao(posiPecaXequeDescoberto);
									int l2 = vet4[0];
									int c2 = vet4[1];
									String pecaXeque4 = chess[l2][c2];

									if (verificaMate(pecaXeque4, posiPecaXequeDescoberto)) {
										if (vezDejogar % 2 == 1) {
											lblW.setText("Blacks Winner");
											lblW.setVisible(true);

										} else {
											lblW.setText("Whites Winner");
											lblW.setVisible(true);

										}
										Alert al = new Alert(AlertType.CONFIRMATION);
										al.setContentText("Jogo acabou!");
										al.show();

										jogoAcabou = true;

										if (threadTempo1.isAlive()) {
											threadTempo1.interrupt();
										}
										if (threadTempo2.isAlive()) {
											threadTempo2.interrupt();
										}
									}

								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}

					throw new Exception("Uma Peça foi comida!"); // gera uma exeção interrompendo
				}

				// se passar pela exeção a jogada é um movimento normal
				String coluna = null;
				String coluna1 = "das";
				try {
					coluna = lugarAserJogado.substring(0, 1);
					coluna1 = colunaQuePodeSeMover.substring(0, 1);
					System.out.println(coluna + " " + coluna1);
				} catch (Exception e) {
					// e.printStackTrace();
				}

				if (cravadaDoBispo && !sairCravadaB.contains(lugarAserJogado)) {
					cont--;
					sairXequeMovimentando = false;
					sairXequeComendo = false;
					piece.setMovimentou(false);
					piece.verificaBolinha();
					throw new Exception("A posição nao é certa");
				}
				if (cravadaDaTorre && !coluna1.equals(coluna)) {
					cont--;
					sairXequeMovimentando = false;
					sairXequeComendo = false;
					piece.setMovimentou(false);
					piece.verificaBolinha();
					throw new Exception("A posição nao é certa");
				} else {
					if (xeque) {
						if ((posiSairXequeMovimentando.contains(lugarAserJogado))) {
							piece.movimentarPeca(posiAtual, lugarAserJogado);
							piece.verificaBolinha();
							xeque = false;
							posicaoDaPecaQueDeuXeque = null;
							sairXequeMovimentando = false;
							sairXequeComendo = false;

							pecaQueDeuXeque = null;
							posicaoDaPecaQueDeuXeque = null;
							tirarReiAmeacado(vezDejogar);

						} else {
							piece.verificaBolinha();

							sairXequeMovimentando = false;
							sairXequeComendo = false;
							piece.setMovimentou(false);
							cont--;
							throw new Exception("jogada Invalida");
						}
					} else {
						piece.movimentarPeca(posiAtual, lugarAserJogado);
						piece.verificaBolinha();
					}
				}
				if (piece.getMovimentou()) {// se a peça movement
					piece.setMovimentou(false);

					setTime();
					vezDejogar++; // passa a vez
					cont++;
					posiPecaXequeDescoberto = null;
					xeque = piece.xeque(lugarAserJogado);

					MovimentoPeca.setChess(chess);
					MovimentoPeca.setVez(vezDejogar);

					if (xeque) {
						setReiAmeacado(vezDejogar);
						System.out.println("Rei em Xeque!");
						int vet[] = MovimentoPeca.converterPosicao(lugarAserJogado);
						int l1 = vet[0];
						int c1 = vet[1];
						String pecaXeque = chess[l1][c1];

						if (verificaMate(pecaXeque, lugarAserJogado)) {
							if (vezDejogar % 2 == 1) {
								lblW.setText("Blacks Winner");
								lblW.setVisible(true);

							} else {
								lblW.setText("Whites Winner");
								lblW.setVisible(true);

							}
							Alert al = new Alert(AlertType.CONFIRMATION);
							al.setContentText("Jogo acabou!");
							al.show();

							jogoAcabou = true;

							if (threadTempo1.isAlive()) {
								threadTempo1.interrupt();
							}
							if (threadTempo2.isAlive()) {
								threadTempo2.interrupt();
							}
						}

					}

					if (!xeque) { // verificação do xequedescoberto
						xeque = verificaXequeDescoberto(chess);
						try {
							if (xeque) {
								System.out.println("Xeque Descoberto!");
								setReiAmeacado(vezDejogar);

								int vet[] = MovimentoPeca.converterPosicao(posiPecaXequeDescoberto);
								int l1 = vet[0];
								int c1 = vet[1];
								String pecaXeque = chess[l1][c1];

								if (verificaMate(pecaXeque, posiPecaXequeDescoberto)) {
									if (vezDejogar % 2 == 1) {
										lblW.setText("Blacks Winner");
										lblW.setVisible(true);

									} else {
										lblW.setText("Whites Winner");
										lblW.setVisible(true);

									}
									Alert al = new Alert(AlertType.CONFIRMATION);
									al.setContentText("Jogo acabou!");
									al.show();

									jogoAcabou = true;

									if (threadTempo1.isAlive()) {
										threadTempo1.interrupt();
									}
									if (threadTempo2.isAlive()) {
										threadTempo2.interrupt();
									}
								}

							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}

				for (int i = 0; i < 15; i++) {
					System.out.println();
				}
				mostrarXadrez();

			}

		}

	}

	String posiPecaXequeDescoberto;

	private boolean verificaXequeDescoberto(String[][] chess2) {
		posiPecaXequeDescoberto = null;
		String copiaChess[][] = Piece.clonar(chess);
		String[][] chessVerdadeiro = Piece.clonar(copiaChess);

		String posiRei = null;

		for (int l = 0; l <= 7; l++) {
			for (int c = 0; c <= 7; c++) {
				if (vezDejogar % 2 == 0) {
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

		Piece p = new Piece(vezDejogar);

		Piece p1 = null;
		String peca;
		String posi;

		int a[] = MovimentoPeca.converterPosicao(posiRei);
		int l = a[0];
		int c = a[1];

		chessVerdadeiro = Piece.clonar(copiaChess);

		mostrarXadrez(copiaChess);

		List<String> list = new ArrayList<>();

		try {
			p.verificaBolinha(copiaChess);
			p.verificaBolinha(chessVerdadeiro);

		} catch (Exception e) {

		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (vezDejogar % 2 == 1) {
					if (p.verificaMovimentoPreto(i, j, copiaChess)) {
						try {
							peca = copiaChess[i][j];
							posi = MovimentoPeca.reConverterPosicao(i, j);
							p1 = MovimentoPeca.jogar1(peca);

							p1.movimentoDaPeca1(posi, copiaChess);
							System.out.println("\n\nO xadrez Na verificação do esta em xeque");
							mostrarXadrez(copiaChess);
							if (copiaChess[l][c].equals(String.valueOf(Chess.bolinha))) {
								posiPecaXequeDescoberto = MovimentoPeca.reConverterPosicao(i, j);

								return true;
							}

						} catch (Exception e) {

						} finally {
							try {
								p.verificaBolinha(copiaChess);
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

								posiPecaXequeDescoberto = MovimentoPeca.reConverterPosicao(i, j);

								return true;
							}

						} catch (Exception e) {

						} finally {
							try {
								p.verificaBolinha(copiaChess);
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

	private void setReiAmeacado(int vezDejogar) {

		String posiRei = null;
		for (int l = 0; l <= 7; l++) {
			for (int c = 0; c <= 7; c++) {
				if (vezDejogar % 2 == 0) {
					if (chess[l][c].equals(String.valueOf(Chess.blackKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				} else {
					if (chess[l][c].equals(String.valueOf(Chess.whiteKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				}

			}

		}

		if (pane != null) {
			System.out.println("entrei");
			AnchorPane pane = this.pane;

			ImageView i = (ImageView) pane.lookup("#" + posiRei);

			if (vezDejogar % 2 == 1) {
				i.setImage(this.getKingwhitev());

			} else {
				i.setImage(this.getKingblackv());
			}

		}

	}

	private void tirarReiAmeacado(int vezDejogar) {

		String posiRei = null;
		for (int l = 0; l <= 7; l++) {
			for (int c = 0; c <= 7; c++) {
				if (vezDejogar % 2 == 0) {
					if (chess[l][c].equals(String.valueOf(Chess.blackKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				} else {
					if (chess[l][c].equals(String.valueOf(Chess.whiteKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				}

			}

		}

		if (pane != null) {
			System.out.println("entrei");
			AnchorPane pane = this.pane;

			ImageView i = (ImageView) pane.lookup("#" + posiRei);

			if (vezDejogar % 2 == 1) {
				i.setImage(this.getKingWhite());

			} else {
				i.setImage(this.getKingBlack());
			}

		}

	}

	private boolean verificaMate(String pecaQueDeuXeque2, String posicaoDaPecaQueDeuXeque2) {
		String[][] copiaChess = clonar(chess);

		String posiRei;
		for (int l = 0; l <= 7; l++) {
			for (int c = 0; c <= 7; c++) {
				if (vezDejogar % 2 == 0) {
					if (copiaChess[l][c].equals(String.valueOf(Chess.blackKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				} else {
					if (copiaChess[l][c].equals(String.valueOf(Chess.whiteKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				}

			}

		}

		Piece p = new Piece(vezDejogar);
		String peca;
		String posi;
		int cont = 0;
		ImageView i;
		for (int l = 0; l <= 8; l++) {
			for (int c = 0; c <= 8; c++) {
				if (vezDejogar % 2 == 1) {
					try {
						if (p.verificaMovimentoBranca(l, c, copiaChess)) {
							// peca = copiaChess[l][c];
							posi = MovimentoPeca.reConverterPosicao(l, c);
							i = (ImageView) pane.lookup("#" + posi);

							try {

								if (sairXeque(pecaQueDeuXeque2, posicaoDaPecaQueDeuXeque2, posi, i)) {
									return false;
								}
							} catch (Exception e) {
								// e.printStackTrace();

							}

							try {
								try {

									if (sairXeque(pecaQueDeuXeque2, posicaoDaPecaQueDeuXeque2, posi, i)) {
										return false;
									}
								} catch (Exception e) {
									// e.printStackTrace();

								}

								if (sairXeque1(pecaQueDeuXeque2, posicaoDaPecaQueDeuXeque2, posi, i)) {
									return false;
								}
							} catch (Exception e) {
								// e.printStackTrace();

							}
						}

					} catch (Exception e) {
						// e.printStackTrace();
					} finally {
						copiaChess = Piece.clonar(chess);
					}
				} else {
					try {
						if (p.verificaMovimentoPreto(l, c, copiaChess)) {
							// peca = copiaChess[l][c];
							posi = MovimentoPeca.reConverterPosicao(l, c);
							i = (ImageView) pane.lookup("#" + posi);
							// System.out.println("Entrei no verificarMate");

							try {

								if (sairXeque(pecaQueDeuXeque2, posicaoDaPecaQueDeuXeque2, posi, i)) {
									return false;
								}
							} catch (Exception e) {
								// e.printStackTrace();

							}

							try {

								if (sairXeque1(pecaQueDeuXeque2, posicaoDaPecaQueDeuXeque2, posi, i)) {
									return false;
								}
							} catch (Exception e) {
								// e.printStackTrace();

							}
						}
					} catch (Exception e) {
						// e.printStackTrace();
					} finally {
						copiaChess = Piece.clonar(chess);
					}
				}

			}

		}

		return true;

	}

	private boolean sairXeque1(String pecaQueDeuXeque, String posicaoDaPecaQueDeuXeque, String pecaParaSair,
			ImageView imag) {

		String[][] copiaChess = clonar(chess);

		System.out.println("Entrei no sairXeque1\n\n");
		mostrarXadrez(copiaChess);

		for (int l = 0; l <= 7; l++) {
			for (int c = 0; c <= 7; c++) {
				if (vezDejogar % 2 == 0) {
					if (copiaChess[l][c].equals(String.valueOf(Chess.blackKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				} else {
					if (copiaChess[l][c].equals(String.valueOf(Chess.whiteKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				}

			}

		}

		int[] pi = MovimentoPeca.converterPosicao(pecaParaSair);
		int l = pi[0];
		int c = pi[1];

		String pecaSelecionada = descobrirPeca(imag);

		int posic[] = MovimentoPeca.converterPosicao(lugarAserJogado);
		int linha = posic[0];
		int coluna = posic[1];

		List<String> list = new ArrayList<>();
		if (pecaSelecionada.equals(String.valueOf(Chess.whitePawn))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackPawn))) {
			piece = new Pawn(vezDejogar);

			try {
				list = piece.sairXeque(vezDejogar, pecaQueDeuXeque, posicaoDaPecaQueDeuXeque, copiaChess, imag,
						posiRei);

				if (list.get(0) != null) {

					String a = pecaParaSair.substring(0, 1);

					System.out.println("Pode sair do xeque nas posicoes:\n ");

					for (String s : list) {
						String d = s.substring(0, 1);
						if (a.equals(d)) {
							posiSairXequeMovimentando = list;
							return true;
						}
						System.out.println(s);
					}

				}
			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				copiaChess = clonar(chess);

			}

		} else if (pecaSelecionada.equals(String.valueOf(Chess.blackBishop))
				|| (pecaSelecionada.equals(String.valueOf(Chess.whiteBishop)))) {
			piece = new Bishop(vezDejogar);

			try {
				list = piece.sairXeque(vezDejogar, pecaQueDeuXeque, posicaoDaPecaQueDeuXeque, copiaChess, imag,
						posiRei);

				if (list.get(0) != null) {

					String a = pecaParaSair.substring(0, 1);
					int po[] = MovimentoPeca.converterPosicao(pecaParaSair);

					int l1 = po[0];
					int c1 = po[1];

					System.out.println("Pode sair do xeque nas posicoes:\n ");

					for (String s : list) {
						System.out.println(s);

					}
					posiSairXequeMovimentando = list;
					return true;
				}
			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				copiaChess = clonar(chess);

			}
		} else if (pecaSelecionada.equals(String.valueOf(Chess.blackKnight))
				|| pecaSelecionada.equals(String.valueOf(Chess.whiteKnight))) {
			piece = new Knight(vezDejogar);

			try {
				list = piece.sairXeque(vezDejogar, pecaQueDeuXeque, posicaoDaPecaQueDeuXeque, copiaChess, imag,
						posiRei);

				if (list.get(0) != null) {

					String a = pecaParaSair.substring(0, 1);
					int po[] = MovimentoPeca.converterPosicao(pecaParaSair);

					int l1 = po[0];
					int c1 = po[1];

					System.out.println("Pode sair do xeque nas posicoes:\n ");

					for (String s : list) {
						if (piece.verificaJogada(pecaParaSair, s)) {
							posiSairXequeMovimentando = list;
							System.out.println(s);
							return true;
						}

					}

				}
			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				copiaChess = clonar(chess);

			}
		} else if (pecaSelecionada.equals(String.valueOf(Chess.blackRook))
				|| (pecaSelecionada.equals(String.valueOf(Chess.whiteRook)))) {
			piece = new Rook(vezDejogar);

			try {
				list = piece.sairXeque(vezDejogar, pecaQueDeuXeque, posicaoDaPecaQueDeuXeque, copiaChess, imag,
						posiRei);

				if (list.get(0) != null) {

					String a = pecaParaSair.substring(0, 1);
					int po[] = MovimentoPeca.converterPosicao(pecaParaSair);

					int l1 = po[0];
					int c1 = po[1];

					System.out.println("Pode sair do xeque nas posicoes:\n ");

					for (String s : list) {

						System.out.println(s);
					}

					posiSairXequeMovimentando = list;
					return true;

				}
			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				copiaChess = clonar(chess);

			}
		} else if (pecaSelecionada.equals(String.valueOf(Chess.blackQueen))
				|| (pecaSelecionada.equals(String.valueOf(Chess.whiteQueen)))) {
			piece = new Queen(vezDejogar);

			try {
				list = piece.sairXeque(vezDejogar, pecaQueDeuXeque, posicaoDaPecaQueDeuXeque, copiaChess, imag,
						posiRei);

				if (list.get(0) != null) {

					String a = pecaParaSair.substring(0, 1);
					int po[] = MovimentoPeca.converterPosicao(pecaParaSair);

					int l1 = po[0];
					int c1 = po[1];

					System.out.println("Pode sair do xeque nas posicoes:\n ");

					for (String s : list) {
						System.out.println(s);

					}

					posiSairXequeMovimentando = list;
					return true;

				}
			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				copiaChess = clonar(chess);

			}
		} else if (pecaSelecionada.equals(String.valueOf(Chess.blackKing))
				|| pecaSelecionada.equals(String.valueOf(Chess.whiteKing))) {
			piece = new King(vezDejogar);

			try {

				list = piece.sairXeque(vezDejogar, pecaQueDeuXeque, posicaoDaPecaQueDeuXeque, copiaChess, imag,
						posiRei);

				for (String s : list) {
					System.out.println(s);

				}
				if (list.get(0) != null) {

					System.out.println("Pode sair nas posiçoes :");

					posiSairXequeMovimentando = list;
					return true;
				}

			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				copiaChess = clonar(chess);

			}
		}

		return false;
	}

	private List<String> sairCravadaBispo(String pecaQueDeuXeque, String posicaoDaPecaQueDeuXeque, String pecaParaSair,
			ImageView imag) {
		sairCravadaB = null;
		String[][] copiaChess = clonar(chess);

		mostrarXadrez(copiaChess);

		for (int l = 0; l <= 7; l++) {
			for (int c = 0; c <= 7; c++) {
				if (vezDejogar % 2 == 0) {
					if (copiaChess[l][c].equals(String.valueOf(Chess.blackKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				} else {
					if (copiaChess[l][c].equals(String.valueOf(Chess.whiteKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				}

			}

		}

		int[] pi = MovimentoPeca.converterPosicao(pecaParaSair);
		int l = pi[0];
		int c = pi[1];

		String pecaSelecionada = descobrirPeca(imag);

		int posic[] = MovimentoPeca.converterPosicao(lugarAserJogado);
		int linha = posic[0];
		int coluna = posic[1];

		List<String> list = new ArrayList<>();

		if (pecaSelecionada.equals(String.valueOf(Chess.blackBishop))
				|| (pecaSelecionada.equals(String.valueOf(Chess.whiteBishop)))) {
			piece = new Bishop(vezDejogar);

			try {
				list = piece.sairXeque(vezDejogar, pecaQueDeuXeque, posicaoDaPecaQueDeuXeque, copiaChess, imag,
						posiRei);

				if (list.get(0) != null) {

					String a = pecaParaSair.substring(0, 1);
					int po[] = MovimentoPeca.converterPosicao(pecaParaSair);

					int l1 = po[0];
					int c1 = po[1];

					System.out.println("Cravada Bispo Pode sair do xeque nas posicoes:\n ");

					for (String s : list) {
						System.out.println(s);

					}
					// posiSairXequeMovimentando = list;
					return list;
				}
			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				copiaChess = clonar(chess);

			}

		} else if (pecaSelecionada.equals(String.valueOf(Chess.blackQueen))
				|| (pecaSelecionada.equals(String.valueOf(Chess.whiteQueen)))) {
			piece = new Queen(vezDejogar);

			try {
				list = piece.sairXeque(vezDejogar, pecaQueDeuXeque, posicaoDaPecaQueDeuXeque, copiaChess, imag,
						posiRei);

				if (list.get(0) != null) {

					String a = pecaParaSair.substring(0, 1);
					int po[] = MovimentoPeca.converterPosicao(pecaParaSair);

					int l1 = po[0];
					int c1 = po[1];

					System.out.println("Cravada Bispo Pode sair do xeque nas posicoes:\n ");

					for (String s : list) {
						System.out.println(s);

					}

					// posiSairXequeMovimentando = list;
					return list;

				}
			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				copiaChess = clonar(chess);

			}
		}

		return null;
	}

	private boolean sairXeque3(String pecaSelecionada1, String lugarAserJogado, String pecaParaSair,
			ImageView imageView) {
		String[][] copiaChess = clonar(chess);

		System.out.println("Entrei no sairXeque1\n\n");
		mostrarXadrez(copiaChess);

		for (int l = 0; l <= 7; l++) {
			for (int c = 0; c <= 7; c++) {
				if (vezDejogar % 2 == 0) {
					if (copiaChess[l][c].equals(String.valueOf(Chess.blackKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				} else {
					if (copiaChess[l][c].equals(String.valueOf(Chess.whiteKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				}

			}

		}

		int[] pi = MovimentoPeca.converterPosicao(pecaParaSair);
		int l = pi[0];
		int c = pi[1];

		String pecaSelecionada = descobrirPeca(imageView);

		int posic[] = MovimentoPeca.converterPosicao(lugarAserJogado);
		int linha = posic[0];
		int coluna = posic[1];
		List<String> list = new ArrayList<>();

		if (pecaSelecionada.equals(String.valueOf(Chess.blackKing))
				|| pecaSelecionada.equals(String.valueOf(Chess.whiteKing))) {
			piece = new King(vezDejogar);

			try {

				list = piece.sairXeque(vezDejogar, pecaQueDeuXeque, posicaoDaPecaQueDeuXeque, copiaChess, imageView,
						posiRei);

				for (String s : list) {
					System.out.println(s);

				}
				if (list.get(0) != null) {

					System.out.println("Pode sair nas posiçoes :");

					posiSairXequeMovimentando = list;
					return true;
				}

			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				copiaChess = clonar(chess);

			}
		}
		return false;
	}

	private boolean sairXeque(String pecaSelecionada1, String lugarAserJogado, String pecaParaSair,
			ImageView imageView) {
		// pecaSelecionada = peca que deu o xeque;
		// lugarAserJogado= posição da peça que deu o xeque
		// pecaParaSair - a peca que eu clique para tirar o xeque;

		String[][] copiaChess = clonar(chess);

		System.out.println("Entrei no sairXeque \n\n");
		mostrarXadrez(copiaChess);

		for (int l = 0; l <= 7; l++) {
			for (int c = 0; c <= 7; c++) {
				if (vezDejogar % 2 == 0) {
					if (copiaChess[l][c].equals(String.valueOf(Chess.blackKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				} else {
					if (copiaChess[l][c].equals(String.valueOf(Chess.whiteKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				}

			}

		}
		int[] pi;
		int l;
		int c;

		try {
			pi = MovimentoPeca.converterPosicao(pecaParaSair);
			l = pi[0];
			c = pi[1];
		} catch (Exception e) {
			// e.printStackTrace();
		}

		String pecaSelecionada = descobrirPeca(imageView);

		System.out.println(pecaSelecionada + " " + lugarAserJogado + "  " + pecaParaSair);

		// pecaSelecionada1 = peca que deu o xeque;
		// lugarAserJogado= posição da peça que deu o xeque
		// pecaParaSair - posicao da que eu clique para tirar o xeque;
		Piece piece;

		int posic[] = MovimentoPeca.converterPosicao(lugarAserJogado);
		int linha = posic[0];
		int coluna = posic[1];

		if (pecaSelecionada != null) {

			if (pecaSelecionada.equals(String.valueOf(Chess.whitePawn))
					|| pecaSelecionada.equals(String.valueOf(Chess.blackPawn))) {
				piece = new Pawn(vezDejogar);

				try {

					if (piece.pecaInimiga1(pecaParaSair, lugarAserJogado, copiaChess)) {
						piece.comerPeca1(pecaParaSair, lugarAserJogado, copiaChess);
						System.out.println("Entrou em xeque!\n\n");
						mostrarXadrez(copiaChess);
					}

					if (!copiaChess[linha][coluna].equals(pecaSelecionada1)) {
						System.out.println("Movimentou");
						return true;
					}

				} catch (Exception e) {
					// e.printStackTrace();
				}

			} else if (pecaSelecionada.equals(String.valueOf(Chess.blackBishop))
					|| (pecaSelecionada.equals(String.valueOf(Chess.whiteBishop)))) {
				piece = new Bishop(vezDejogar);

				try {

					if (piece.pecaInimiga1(pecaParaSair, lugarAserJogado, copiaChess)) {
						piece.comerPeca1(pecaParaSair, lugarAserJogado, copiaChess);
						System.out.println("Entrou em xeque!\n\n");
						mostrarXadrez(copiaChess);
					}

					if (!copiaChess[linha][coluna].equals(pecaSelecionada1)) {
						System.out.println("Movimentou");
						return true;
					}

				} catch (Exception e) {
					// e.printStackTrace();
				}
			} else if (pecaSelecionada.equals(String.valueOf(Chess.blackKnight))
					|| pecaSelecionada.equals(String.valueOf(Chess.whiteKnight))) {
				piece = new Knight(vezDejogar);

				try {

					if (piece.pecaInimiga1(pecaParaSair, lugarAserJogado, copiaChess)) {
						piece.comerPeca1(pecaParaSair, lugarAserJogado, copiaChess);
						System.out.println("Entrou em xeque!\n\n");
						mostrarXadrez(copiaChess);
					}

					if (!copiaChess[linha][coluna].equals(pecaSelecionada1)) {
						System.out.println("Movimentou");
						return true;
					}

				} catch (Exception e) {
					// e.printStackTrace();
				}
			} else if (pecaSelecionada.equals(String.valueOf(Chess.blackRook))
					|| (pecaSelecionada.equals(String.valueOf(Chess.whiteRook)))) {
				piece = new Rook(vezDejogar);

				try {

					if (piece.pecaInimiga1(pecaParaSair, lugarAserJogado, copiaChess)) {
						piece.comerPeca1(pecaParaSair, lugarAserJogado, copiaChess);
						System.out.println("Entrou em xeque!\n\n");
						mostrarXadrez(copiaChess);
					}

					if (!copiaChess[linha][coluna].equals(pecaSelecionada1)) {
						System.out.println("Movimentou");
						return true;
					}

				} catch (Exception e) {
					// e.printStackTrace();
				}
			} else if (pecaSelecionada.equals(String.valueOf(Chess.blackQueen))
					|| (pecaSelecionada.equals(String.valueOf(Chess.whiteQueen)))) {
				piece = new Queen(vezDejogar);

				try {

					if (piece.pecaInimiga1(pecaParaSair, lugarAserJogado, copiaChess)) {
						piece.comerPeca1(pecaParaSair, lugarAserJogado, copiaChess);
						System.out.println("Entrou em xeque!\n\n");
						mostrarXadrez(copiaChess);
					}

					if (!copiaChess[linha][coluna].equals(pecaSelecionada1)) {
						System.out.println("Movimentou");
						return true;
					}

				} catch (Exception e) {
					// e.printStackTrace();
				}
			} else if (pecaSelecionada.equals(String.valueOf(Chess.blackKing))
					|| pecaSelecionada.equals(String.valueOf(Chess.whiteKing))) {
				piece = new King(vezDejogar);

				try {

					if (piece.pecaInimiga1(pecaParaSair, lugarAserJogado, copiaChess)) {
						piece.comerPeca1(pecaParaSair, lugarAserJogado, copiaChess);
						System.out.println("Entrou em xeque!\n\n");
						mostrarXadrez(copiaChess);
					}

					if (!copiaChess[linha][coluna].equals(pecaSelecionada1)) {
						System.out.println("Movimentou");
						return true;
					}

				} catch (Exception e) {
					// e.printStackTrace();
				}
			}
		}
		return false;
	}

	int numero;

	public void setPecaComida1(ImageView peca, Label cont) {
		String n;

		if (peca.isVisible()) {
			n = cont.getText();

			numero = Integer.parseInt(n.substring(0, 1));

			numero++;

			cont.setText(String.valueOf(numero) + "x");
			cont.setVisible(true);
		}

		if (!peca.isVisible()) {
			peca.setVisible(true);
		}
	}

	private void setPecaComida(Image image) {
		String imageSelecionada = image.impl_getUrl().toString();
		String n;

		if (vezDejogar % 2 == 1) {
			if (imageSelecionada.equals(pawnBlack.impl_getUrl().toString())) {
				System.out.println("Comi um peao preto");

				if (pBlack.isVisible()) {
					n = contPB.getText();

					numero = Integer.parseInt(n.substring(0, 1));

					numero++;

					contPB.setText(String.valueOf(numero) + "x");
					contPB.setVisible(true);
				}

				if (!pBlack.isVisible()) {
					pBlack.setVisible(true);
				}
			} else if (imageSelecionada.equals(bishopBlack.impl_getUrl().toString())) {
				System.out.println("Comi um bispo preto");
				if (bBlack.isVisible()) {
					n = contBB.getText();

					numero = Integer.parseInt(n.substring(0, 1));

					numero++;

					contBB.setText(String.valueOf(numero) + "x");
					contBB.setVisible(true);
				}
				if (!bBlack.isVisible()) {
					bBlack.setVisible(true);
				}
			} else if (imageSelecionada.equals(kBlack.impl_getUrl().toString())) {
				System.out.println("Comi um cavalo preto");
				if (kiBlack.isVisible()) {
					n = contKB.getText();

					numero = Integer.parseInt(n.substring(0, 1));

					numero++;

					contKB.setText(String.valueOf(numero) + "x");
					contKB.setVisible(true);
				}
				if (!kiBlack.isVisible()) {
					kiBlack.setVisible(true);
				}
			} else if (imageSelecionada.equals(queenBlack.impl_getUrl().toString())) {
				System.out.println("Comi uma rainha preto");
				if (qBlack.isVisible()) {
					n = contQB.getText();

					numero = Integer.parseInt(n.substring(0, 1));

					numero++;

					contQB.setText(String.valueOf(numero) + "x");
					contQB.setVisible(true);
				}
				if (!qBlack.isVisible()) {
					qBlack.setVisible(true);
				}
			} else if (imageSelecionada.equals(rookBlack.impl_getUrl().toString())) {
				System.out.println("Comi um torre preto");
				if (rBlack.isVisible()) {
					n = contRB.getText();

					numero = Integer.parseInt(n.substring(0, 1));

					numero++;

					contRB.setText(String.valueOf(numero) + "x");
					contRB.setVisible(true);
				}
				if (!rBlack.isVisible()) {
					rBlack.setVisible(true);
				}
			}

		} else {
			pecasPretasComidas.add(image);

			if (imageSelecionada.equals(pawnW.impl_getUrl().toString())) {
				System.out.println("Comi um  peao white");
				if (pWhite.isVisible()) {
					n = contPW.getText();

					numero = Integer.parseInt(n.substring(0, 1));

					numero++;

					contPW.setText(String.valueOf(numero) + "x");
					contPW.setVisible(true);
				}
				if (!pWhite.isVisible()) {
					pWhite.setVisible(true);
				}

			} else if (imageSelecionada.equals(bishopW.impl_getUrl().toString())) {
				System.out.println("Comi um  bispo branco");
				if (bWhite.isVisible()) {
					n = contBW.getText();

					numero = Integer.parseInt(n.substring(0, 1));

					numero++;

					contBW.setText(String.valueOf(numero) + "x");
					contBW.setVisible(true);
				}
				if (!bWhite.isVisible()) {
					bWhite.setVisible(true);
				}
			} else if (imageSelecionada.equals(kWhite.impl_getUrl().toString())) {
				System.out.println("Comi um cavalo branco");
				if (kiWhite.isVisible()) {
					n = contKW.getText();

					numero = Integer.parseInt(n.substring(0, 1));

					numero++;

					contKW.setText(String.valueOf(numero) + "x");
					contKW.setVisible(true);
				}
				if (!kiWhite.isVisible()) {
					kiWhite.setVisible(true);
				}
			} else if (imageSelecionada.equals(queenWhite.impl_getUrl().toString())) {
				System.out.println("Comi um rainha branco");
				if (qWhite.isVisible()) {
					n = contQW.getText();

					numero = Integer.parseInt(n.substring(0, 1));

					numero++;

					contQW.setText(String.valueOf(numero) + "x");
					contQW.setVisible(true);
				}
				if (!qWhite.isVisible()) {
					qWhite.setVisible(true);
				}
			} else if (imageSelecionada.equals(rookWhite.impl_getUrl().toString())) {
				System.out.println("Comi uma torre branco");
				if (rWhite.isVisible()) {
					n = contRW.getText();

					numero = Integer.parseInt(n.substring(0, 1));

					numero++;

					contRW.setText(String.valueOf(numero) + "x");
					contRW.setVisible(true);
				}
				if (!rWhite.isVisible()) {
					rWhite.setVisible(true);
				}
			}
		}

	}

	private void setTime() {

		if (vezDejogar % 2 == 1) {
			if (threadTempo1.isAlive()) {
				threadTempo1.interrupt();
			}
			initThread2();
		} else {
			if (threadTempo2.isAlive()) {
				threadTempo2.interrupt();
			}
			initThread1();
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

	String posiRei = null;

	boolean cravadaDoBispo;
	boolean cravadaDaTorre;
	String colunaQuePodeSeMover;
	String posiDaPecaCravando;
	String pecaQueEstaCravando;

	private boolean verificaCravada1(String posiAtual, String pecaSel) {
		cravadaDoBispo = false;
		cravadaDaTorre = false;
		colunaQuePodeSeMover = null;
		posiDaPecaCravando = null;
		pecaQueEstaCravando = null;
		int p[] = MovimentoPeca.converterPosicao(posiAtual);

		int l1 = p[0];
		int c1 = p[1];

		String[][] copiaChess = clonar(chess);

		copiaChess[l1][c1] = String.valueOf(Chess.quadrado);
		String[][] chess = Piece.clonar(copiaChess);
		// se a peça nao for um bispo ou um cavalo , e o xeque vinher de uma torre ou
		// rainha
		// posso mover o peao e a torre e a rainha
		// System.out.println("Entrei na cravada, o xadrez atual é ");
		mostrarXadrez(copiaChess);

		for (int l = 0; l <= 7; l++) {
			for (int c = 0; c <= 7; c++) {
				if (vezDejogar % 2 == 0) {
					if (copiaChess[l][c].equals(String.valueOf(Chess.blackKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				} else {
					if (copiaChess[l][c].equals(String.valueOf(Chess.whiteKing))) {
						posiRei = MovimentoPeca.reConverterPosicao(l, c);
					}
				}

			}

		}

		// System.out.println("A posicao do rei é " + posiRei);

		Piece piece;
		for (int l = 0; l <= 8; l++) {
			for (int c = 0; c <= 8; c++) {
				if (vezDejogar % 2 == 1) {
					if (copiaChess[l][c].equals(String.valueOf(Chess.blackBishop))
							|| copiaChess[l][c].equals(String.valueOf(Chess.blackQueen))) {
						try {
							String posicao = MovimentoPeca.reConverterPosicao(l, c);
							piece = new Bishop(vezDejogar);

							piece.movimentoDaPeca1(posicao, copiaChess);
							int[] po = MovimentoPeca.converterPosicao(posiRei);

							int linha = po[0];
							int coluna = po[1];

							// System.out.println("Xadrez copia chess\n\n" + "linha " + linha + " coluna " +
							// coluna);
							// System.out.println("Posicao do rei branco : " + posiRei);

							// mostrarXadrez(copiaChess);

							if (copiaChess[linha][coluna].equals(String.valueOf(Chess.bolinha))) {
								cravadaDoBispo = true;
								pecaQueEstaCravando = copiaChess[l][c];
								posiDaPecaCravando = MovimentoPeca.reConverterPosicao(l, c);

								return true;
							}
						} catch (Exception e) {
							// e.printStackTrace();
						} finally {
							copiaChess = Piece.clonar(chess);
						}
					}
					if (copiaChess[l][c].equals(String.valueOf(Chess.blackRook))
							|| copiaChess[l][c].equals(String.valueOf(Chess.blackQueen))) {
						try {

							String posicao = MovimentoPeca.reConverterPosicao(l, c);
							piece = new Rook(vezDejogar);

							piece.movimentoDaPeca1(posicao, copiaChess);
							int[] po = MovimentoPeca.converterPosicao(posiRei);

							int linha = po[0];
							int coluna = po[1];

							// System.out.println("Xadrez copia chess\n\n" + "linha " + linha + " coluna " +
							// coluna);
							// System.out.println("Posicao do rei branco : " + posiRei);

							// mostrarXadrez(copiaChess);

							if (copiaChess[linha][coluna].equals(String.valueOf(Chess.bolinha))) {
								colunaQuePodeSeMover = MovimentoPeca.reConverterPosicao(l, c);
								cravadaDaTorre = true;
								posiDaPecaCravando = MovimentoPeca.reConverterPosicao(l, c);

								return true;
							}
						} catch (Exception e) {
							// e.printStackTrace();
						} finally {
							copiaChess = Piece.clonar(chess);
						}
					}

				} else {

					if (copiaChess[l][c].equals(String.valueOf(Chess.whiteBishop))
							|| copiaChess[l][c].equals(String.valueOf(Chess.whiteQueen))) {
						try {
							String posicao = MovimentoPeca.reConverterPosicao(l, c);

							piece = new Bishop(vezDejogar);
							piece.movimentoDaPeca1(posicao, copiaChess);

							int[] po = MovimentoPeca.converterPosicao(posiRei);

							int linha = po[0];
							int coluna = po[1];

							// System.out.println("Xadrez copia chess\n\n" + "linha " + linha + " coluna " +
							// coluna);
							// System.out.println("Posicao do rei preto : " + posiRei);
							// mostrarXadrez(copiaChess);

							if (copiaChess[linha][coluna].equals(String.valueOf(Chess.bolinha))) {
								cravadaDoBispo = true;
								pecaQueEstaCravando = copiaChess[l][c];
								posiDaPecaCravando = MovimentoPeca.reConverterPosicao(l, c);

								return true;
							}
						} catch (Exception e) {
							// e.printStackTrace();
						} finally {
							copiaChess = Piece.clonar(chess);
						}
					}

					if (copiaChess[l][c].equals(String.valueOf(Chess.whiteRook))
							|| copiaChess[l][c].equals(String.valueOf(Chess.whiteQueen))) {
						try {
							System.out.println("Entrei na verificação da torre");
							String posicao = MovimentoPeca.reConverterPosicao(l, c);
							piece = new Rook(vezDejogar);

							piece.movimentoDaPeca1(posicao, copiaChess);
							int[] po = MovimentoPeca.converterPosicao(posiRei);

							int linha = po[0];
							int coluna = po[1];

							// System.out.println("Xadrez copia chess\n\n" + "linha " + linha + " coluna " +
							// coluna);
							// System.out.println("Posicao do rei branco : " + posiRei);

							mostrarXadrez(copiaChess);

							if (copiaChess[linha][coluna].equals(String.valueOf(Chess.bolinha))) {
								cravadaDaTorre = true;
								colunaQuePodeSeMover = MovimentoPeca.reConverterPosicao(l, c);

								posiDaPecaCravando = MovimentoPeca.reConverterPosicao(l, c);

								return true;
							}
						} catch (Exception e) {
							// e.printStackTrace();
						} finally {
							copiaChess = Piece.clonar(chess);
						}
					}

				}

			}

		}

		return false;
	}

	public static void mostrarXadrez() {
		for (int l = 0; l < 9; l++) {
			for (int c = 0; c < 9; c++) {

				System.out.print(chess[l][c] + " ");
			}
			System.out.println();
		}
	}

	public static void mostrarXadrez(String[][] chess) {
		for (int l = 0; l < 9; l++) {
			for (int c = 0; c < 9; c++) {

				System.out.print(chess[l][c] + " ");
			}
			System.out.println();
		}
	}

	public boolean comerPecaPreta(ImageView image) {
		try {
			String imageSelecionada = image.getImage().impl_getUrl().toString();

			if (imageSelecionada.equals(pawnBlack2.impl_getUrl().toString())) {
				return true;
			} else if (imageSelecionada.equals(bishopBlack2.impl_getUrl().toString())) {
				return true;
			} else if (imageSelecionada.equals(kBlack2.impl_getUrl().toString())) {
				return true;
			} else if (imageSelecionada.equals(queenBlack2.impl_getUrl().toString())) {
				return true;
			} else if (imageSelecionada.equals(rookBlack2.impl_getUrl().toString())) {
				return true;
			}
		} catch (NullPointerException e) {

		}

		return false;

	}

	public boolean comerPecaBranca(ImageView image) {
		try {
			String imageSelecionada = image.getImage().impl_getUrl().toString();

			if (imageSelecionada.equals(pawnW2.impl_getUrl().toString())) {
				return true;
			} else if (imageSelecionada.equals(bishopW2.impl_getUrl().toString())) {
				return true;
			} else if (imageSelecionada.equals(kWhite2.impl_getUrl().toString())) {
				return true;
			} else if (imageSelecionada.equals(queenWhite2.impl_getUrl().toString())) {
				return true;
			} else if (imageSelecionada.equals(rookWhite2.impl_getUrl().toString())) {
				return true;

			}
		} catch (NullPointerException e) {

		}

		return false;

	}

	private String descobrirPeca(ImageView image) {
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
		} else if (imageSelecionada.equals(kingBlackV.impl_getUrl().toString())) {
			return String.valueOf(Chess.blackKing);
		} else if (imageSelecionada.equals(kingWhiteV.impl_getUrl().toString())) {
			return String.valueOf(Chess.whiteKing);

		}

		return null;
	}

	public void initNodes() {
		contBB.setVisible(false);
		contBW.setVisible(false);

		contKB.setVisible(false);
		contKW.setVisible(false);

		contPW.setVisible(false);
		contPB.setVisible(false);

		contQB.setVisible(false);
		contQW.setVisible(false);

		contRB.setVisible(false);
		contRW.setVisible(false);

		pBlack.setVisible(false);
		pWhite.setVisible(false);

		bWhite.setVisible(false);
		bBlack.setVisible(false);

		qBlack.setVisible(false);
		qWhite.setVisible(false);

		rWhite.setVisible(false);
		rBlack.setVisible(false);

		kiBlack.setVisible(false);
		kiWhite.setVisible(false);

		lblW.setVisible(false);

	}

	// Threads do cronometro
	private Thread threadTempo1;
	private Thread threadTempo2;

	private String minuto1;
	private String segundo1;

	int c1 = 1;

	public void initThread1() {

		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				int min = Integer.parseInt(timeBrancas.getText().substring(0, 2));
				int sec = Integer.parseInt(timeBrancas.getText().substring(3, 5));

				// System.out.println(min + " : " + sec);

				if (c1 == 1 && sec == 0) {
					sec = 60;

				}
				if (c1 == 1) {
					min -= 1;
				}
				c1 = 2;

				for (int m = min; m >= 0; m--) {
					minuto1 = String.valueOf(m);

					if (m == 0) {
						minuto1 = String.valueOf("00");
					} else if (m < 10) {
						minuto1 = String.valueOf("0" + m);
					}

					for (int s = sec; s >= 0; s--) {

						segundo1 = String.valueOf(s);
						if (s == 0) {
							segundo1 = String.valueOf("00");
						} else if (s < 10) {
							segundo1 = String.valueOf("0" + m);
						}

						Platform.runLater(() -> {
							timeBrancas.setText(minuto1 + ":" + segundo1);
						});

						Thread.sleep(1000);

					}
					sec = 60;
				}
				Platform.runLater(() -> {
					lblW.setText("Blacks Winner");
					lblW.setVisible(true);
					jogoAcabou = true;
				});

				return null;
			}

		};

		threadTempo1 = new Thread(task);
		threadTempo1.start();
	}

	private String minuto2;
	private String segundo2;

	int c2 = 1;

	public void initThread2() {

		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				int min = Integer.parseInt(timePreto.getText().substring(0, 2));
				int sec = Integer.parseInt(timePreto.getText().substring(3, 5));

				// System.out.println(min + " : " + sec);

				if (c2 == 1 && sec == 0) {
					sec = 60;

				}
				if (c2 == 1) {
					min -= 1;
				}
				c2 = 2;

				for (int m = min; m >= 0; m--) {
					minuto2 = String.valueOf(m);

					if (m == 0) {
						minuto2 = String.valueOf("00");
					} else if (m < 10) {
						minuto2 = String.valueOf("0" + m);
					}

					for (int s = sec; s >= 0; s--) {

						segundo2 = String.valueOf(s);
						if (s == 0) {
							segundo2 = String.valueOf("00");
						} else if (s < 10) {
							segundo2 = String.valueOf("0" + m);
						}

						Platform.runLater(() -> {
							timePreto.setText(minuto2 + ":" + segundo2);
						});

						Thread.sleep(1000);

					}
					sec = 60;
				}
				Platform.runLater(() -> {
					lblW.setText("Whites Winner");
					lblW.setVisible(true);
					jogoAcabou = true;
				});

				return null;
			}

		};

		threadTempo2 = new Thread(task);
		threadTempo2.start();
	}

	public void pauseThread1() {

		threadTempo1.interrupt();
	}

	public void pauseThread2() {
		threadTempo2.interrupt();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void showPaneHide() {
		showPane.setVisible(false);
	}

	double x, y;

	public void mouseMove() {
		paneLateral.setOnMousePressed(event -> {
			x = event.getSceneX();
			y = event.getSceneY();
		});

		paneLateral.setOnMouseDragged(event -> {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			stage.setX(event.getScreenX() - x);
			stage.setY(event.getScreenY() - y);
			stage.setOpacity(0.7f);
		});

		paneLateral.setOnDragDone(event -> {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setOpacity(1f);
		});

		paneLateral.setOnMouseReleased(event -> {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setOpacity(1f);
		});
	}

	// Getters
	public Image getBishopW() {
		return bishopW;
	}

	public Image getkWhite() {
		return kWhite;
	}

	public Image getQueenWhite() {
		return queenWhite;
	}

	public Image getRookWhite() {
		return rookWhite;
	}

	public Image getBishopBlack() {
		return bishopBlack;
	}

	public Image getkBlack() {
		return kBlack;
	}

	public Image getQueenBlack() {
		return queenBlack;
	}

	public Image getRookBlack() {
		return rookBlack;
	}

	public Image getKingBlack() {
		return kingBlack;
	}

	public Image getKingWhite() {
		return kingWhite;
	}

	public double getxInicial() {
		return xInicial;
	}

	public void setxInicial(double xInicial) {
		this.xInicial = xInicial;
	}

	public double getyInicial() {
		return yInicial;
	}

	public void setyInicial(double yInicial) {
		this.yInicial = yInicial;
	}

	public ImageView getpBlack() {
		return pBlack;
	}

	public ImageView getKiBlack() {
		return kiBlack;
	}

	public ImageView getrBlack() {
		return rBlack;
	}

	public ImageView getbBlack() {
		return bBlack;
	}

	public ImageView getqBlack() {
		return qBlack;
	}

	public ImageView getpWhite() {
		return pWhite;
	}

	public ImageView getKiWhite() {
		return kiWhite;
	}

	public ImageView getrWhite() {
		return rWhite;
	}

	public ImageView getbWhite() {
		return bWhite;
	}

	public ImageView getqWhite() {
		return qWhite;
	}

	public void setpBlack(ImageView pBlack) {
		this.pBlack = pBlack;
	}

	public void setKiBlack(ImageView kiBlack) {
		this.kiBlack = kiBlack;
	}

	public void setrBlack(ImageView rBlack) {
		this.rBlack = rBlack;
	}

	public void setbBlack(ImageView bBlack) {
		this.bBlack = bBlack;
	}

	public void setqBlack(ImageView qBlack) {
		this.qBlack = qBlack;
	}

	public void setpWhite(ImageView pWhite) {
		this.pWhite = pWhite;
	}

	public void setKiWhite(ImageView kiWhite) {
		this.kiWhite = kiWhite;
	}

	public void setrWhite(ImageView rWhite) {
		this.rWhite = rWhite;
	}

	public void setbWhite(ImageView bWhite) {
		this.bWhite = bWhite;
	}

	public void setqWhite(ImageView qWhite) {
		this.qWhite = qWhite;
	}

	public Label getContPW() {
		return contPW;
	}

	public Label getContKW() {
		return contKW;
	}

	public Label getContRW() {
		return contRW;
	}

	public Label getContBW() {
		return contBW;
	}

	public Label getContQW() {
		return contQW;
	}

	public Label getContKB() {
		return contKB;
	}

	public Label getContRB() {
		return contRB;
	}

	public Label getContBB() {
		return contBB;
	}

	public Label getContQB() {
		return contQB;
	}

	public Label getContPB() {
		return contPB;
	}

	public void setContPW(Label contPW) {
		this.contPW = contPW;
	}

	public void setContKW(Label contKW) {
		this.contKW = contKW;
	}

	public void setContRW(Label contRW) {
		this.contRW = contRW;
	}

	public void setContBW(Label contBW) {
		this.contBW = contBW;
	}

	public void setContQW(Label contQW) {
		this.contQW = contQW;
	}

	public void setContKB(Label contKB) {
		this.contKB = contKB;
	}

	public void setContRB(Label contRB) {
		this.contRB = contRB;
	}

	public void setContBB(Label contBB) {
		this.contBB = contBB;
	}

	public void setContQB(Label contQB) {
		this.contQB = contQB;
	}

	public void setContPB(Label contPB) {
		this.contPB = contPB;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public static Image getPawnW() {
		return pawnW;
	}

	public static Image getPawnw2() {
		return pawnW2;
	}

	public static Image getPawnBlack() {
		return pawnBlack;
	}

	public static Image getPawnblack2() {
		return pawnBlack2;
	}

	public static Image getBishopw2() {
		return bishopW2;
	}

	public static Image getKwhite2() {
		return kWhite2;
	}

	public static Image getQueenwhite2() {
		return queenWhite2;
	}

	public static Image getRookwhite2() {
		return rookWhite2;
	}

	public static Image getBishopblack2() {
		return bishopBlack2;
	}

	public static Image getKblack2() {
		return kBlack2;
	}

	public static Image getQueenblack2() {
		return queenBlack2;
	}

	public static Image getRookblack2() {
		return rookBlack2;
	}

	public static Image getKingblackv() {
		return kingBlackV;
	}

	public static Image getKingwhitev() {
		return kingWhiteV;
	}

}
