package utils;

import chess.Chess;
import javax.swing.JOptionPane;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;

import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class MovimentoPeca {

	public final static char quadrado = '\u26DD';
	public final static char bolinha = '\u26AB';

	public static boolean validacao;
	private static String posicao;
	private static String chess[][];
	private static int vez = 1;
	private static int c = 0;
	private static boolean podeRookPreto = true;
	private static boolean podeRookBranco = true;

	private static boolean podeRookPequenoPreto = true;
	private static boolean podeRookGrandePreto = true;

	private static boolean podeRookPequenoBranco = true;
	private static boolean podeRookGrandeBranco = true;

	private static boolean fezRooquePreto = false;
	private static boolean fezRooqueBranco = false;
	
	private static boolean reiMovimentou= false;

	public static Piece jogar(String pecaSelecionada) {

		if (pecaSelecionada.equals(String.valueOf(Chess.whitePawn))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackPawn))) {
			return new Pawn(chess, vez);
		} else if (pecaSelecionada.equals(String.valueOf(Chess.whiteBishop))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackBishop))) {
			return new Bishop(chess, vez);
		} else if (pecaSelecionada.equals(String.valueOf(Chess.whiteKnight))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackKnight))) {
			return new Knight(chess, vez);
		} else if (pecaSelecionada.equals(String.valueOf(Chess.whiteRook))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackRook))) {
			return new Rook(chess, vez);
		} else if (pecaSelecionada.equals(String.valueOf(Chess.whiteKing))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackKing))) {
			return new King(chess, vez);
		} else if (pecaSelecionada.equals(String.valueOf(Chess.whiteQueen))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackQueen))) {
			return new Queen(chess, vez);
		}

		return null;
	}

	public static Piece jogar1(String pecaSelecionada) {

		if (pecaSelecionada.equals(String.valueOf(Chess.whitePawn))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackPawn))) {
			return new Pawn(vez);
		} else if (pecaSelecionada.equals(String.valueOf(Chess.whiteBishop))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackBishop))) {
			return new Bishop(vez);
		} else if (pecaSelecionada.equals(String.valueOf(Chess.whiteKnight))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackKnight))) {
			return new Knight(vez);
		} else if (pecaSelecionada.equals(String.valueOf(Chess.whiteRook))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackRook))) {
			return new Rook(vez);
		} else if (pecaSelecionada.equals(String.valueOf(Chess.whiteKing))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackKing))) {
			return new King(vez);
		} else if (pecaSelecionada.equals(String.valueOf(Chess.whiteQueen))
				|| pecaSelecionada.equals(String.valueOf(Chess.blackQueen))) {
			return new Queen(vez);
		}

		return null;
	}

	public static boolean verificaVez(String pecaSelecionada) {
		validacao = true;
		char q = '\u26DD'; // char do quadrado
		String quadradoVazio = String.valueOf(q);

		// Verificando de quem é a vez da jogada
		if (vez % 2 == 1) { // se for a vez do branco
			if (pecaSelecionada.equals(String.valueOf(Chess.blackBishop))
					|| pecaSelecionada.equals(String.valueOf(String.valueOf(Chess.blackKnight)))
					|| pecaSelecionada.equals(String.valueOf(Chess.blackRook))
					|| pecaSelecionada.equals(String.valueOf(Chess.blackKing))
					|| pecaSelecionada.equals(String.valueOf(Chess.blackQueen))
					|| pecaSelecionada.equals(String.valueOf(Chess.blackPawn))
					|| pecaSelecionada.equals(String.valueOf(Chess.blackKing))) {
				System.out.println("Vez Das Brancas!");
				validacao = false;

				return false;
			}
		} else { // se for a vez do preto
			if (pecaSelecionada.equals(String.valueOf(Chess.whiteBishop))
					|| pecaSelecionada.equals(String.valueOf(Chess.whiteKnight))
					|| pecaSelecionada.equals(String.valueOf(Chess.whiteRook))
					|| pecaSelecionada.equals(String.valueOf(Chess.whiteKing))
					|| pecaSelecionada.equals(String.valueOf(Chess.whiteQueen))
					|| pecaSelecionada.equals(String.valueOf(Chess.whitePawn))
					|| pecaSelecionada.equals(String.valueOf(Chess.whiteKing))) {
				System.out.println("Vez Das Pretas!");
				validacao = false;
				return false;
			}
		}

		if (validacao) { // verificando se ainda é verdadeira
			if (pecaSelecionada.equals(quadradoVazio)) {
				System.out.println("Não existe Peça nessa posição!");
				return false;
			}
		}

		return true;

	}

	public static boolean verificaMovimentoPreto(int lChegada, int cChegada) {
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

	public static boolean verificaMovimentoBranca(int lChegada, int cChegada) {
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

	public static void setChess(String c[][]) {
		chess = c;
	}

	public static void setVez(int v) {
		vez = v;
	}

	public static String[][] getChess() {
		return chess;
	}

	public static int getVez() {
		return vez;
	}

	public static int[] converterPosicao(String position) {
		int linha = 0;
		int coluna = 0;

		char posi[] = position.toCharArray();

		if (posi[0] == 'a') {
			coluna = 0;
		} else if (posi[0] == 'b') {
			coluna = 1;
		} else if (posi[0] == 'c') {
			coluna = 2;
		} else if (posi[0] == 'd') {
			coluna = 3;
		} else if (posi[0] == 'e') {
			coluna = 4;
		} else if (posi[0] == 'f') {
			coluna = 5;
		} else if (posi[0] == 'g') {
			coluna = 6;
		} else if (posi[0] == 'h') {
			coluna = 7;
		}

		if (posi[1] == '1') {
			linha = 7;
		} else if (posi[1] == '2') {
			linha = 6;
		} else if (posi[1] == '3') {
			linha = 5;
		} else if (posi[1] == '4') {
			linha = 4;
		} else if (posi[1] == '5') {
			linha = 3;
		} else if (posi[1] == '6') {
			linha = 2;
		} else if (posi[1] == '7') {
			linha = 1;
		} else if (posi[1] == '8') {
			linha = 0;
		}

		int vetPosi[] = { linha, coluna };
		return vetPosi;

	}

	public static String reConverterPosicao(int l, int c) {
		String po = null;

		int ascii = 96;
		for (int i = 0; i <= 7; i++) {
			ascii++;
			if (c == i) {
				po = String.valueOf((char) ascii);
			}
		}
		int n = 9;

		for (int i = 0; i <= 7; i++) {
			n--;
			if (l == i) {
				po += String.valueOf(n);
			}
		}

		return po;
	}

	public static boolean getFezRooqueBranco() {
		return fezRooqueBranco;
	}

	public static void setFezRooqueBranco(boolean fezRooqueBranco) {
		MovimentoPeca.fezRooqueBranco = fezRooqueBranco;
	}

	public static boolean getFezRooquePreto() {
		return fezRooquePreto;
	}

	public static void setFezRooquePreto(boolean fezRooquePreto) {
		MovimentoPeca.fezRooquePreto = fezRooquePreto;
	}

	public static boolean getPodeRookBranco() {
		return podeRookBranco;
	}

	public static void setPodeRookBranco(boolean podeRookBranco) {
		MovimentoPeca.podeRookBranco = podeRookBranco;
	}

	public static boolean getPodeRookPreto() {
		return podeRookPreto;
	}

	public static void setPodeRookPreto(boolean podeRookPreto) {
		MovimentoPeca.podeRookPreto = podeRookPreto;
	}

	public static boolean getPodeRookPequenoPreto() {
		return podeRookPequenoPreto;
	}

	public static void setPodeRookPequenoPreto(boolean podeRookPequenoPreto) {
		MovimentoPeca.podeRookPequenoPreto = podeRookPequenoPreto;
	}

	public static boolean getPodeRookGrandePreto() {
		return podeRookGrandePreto;
	}

	public static void setPodeRookGrandePreto(boolean podeRookGrandePreto) {
		MovimentoPeca.podeRookGrandePreto = podeRookGrandePreto;
	}

	public static boolean getPodeRookPequenoBranco() {
		return podeRookPequenoBranco;
	}

	public static void setPodeRookPequenoBranco(boolean podeRookPequenoBranco) {
		MovimentoPeca.podeRookPequenoBranco = podeRookPequenoBranco;
	}

	public static boolean getPodeRookGrandeBranco() {
		return podeRookGrandeBranco;
	}

	public static void setPodeRookGrandeBranco(boolean podeRookGrandeBranco) {
		MovimentoPeca.podeRookGrandeBranco = podeRookGrandeBranco;
	}

	public static boolean getReiMovimentou() {
		return reiMovimentou;
	}

	public static void setReiMovimentou(boolean reiMovimentou) {
		MovimentoPeca.reiMovimentou = reiMovimentou;
	}
}
