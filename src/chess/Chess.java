package chess;

public class Chess {

	public static String chess[][] = new String[9][9];
	public final static char whiteKing = '\u2654';
	public final static char whiteQueen = '\u2655';
	public final static char whiteRook = '\u2656';
	public final static char whiteBishop = '\u2657';
	public final static char whiteKnight = '\u2658';
	public final static char whitePawn = '\u2659';

	public final static char blackKing = '\u265A';
	public final static char blackQueen = '\u265B';
	public final static char blackRook = '\u265C';
	public final static char blackBishop = '\u265D';
	public final static char blackKnight = '\u265E';
	public final static char blackPawn = '\u265F';

	public final static char quadrado = '\u26DD';
	public final static char bolinha = '\u26AB';

	private static void colocarIcons() {
		// colocando icones no xadrez;
		for (int c = 0; c < 8; c++) {
			// pawn
			chess[6][c] = String.valueOf(whitePawn);
			chess[1][c] = String.valueOf(blackPawn);

			// coluna/linha
			// rook
			chess[7][0] = String.valueOf(whiteRook);
			chess[7][7] = String.valueOf(whiteRook);

			chess[0][0] = String.valueOf(blackRook);
			chess[0][7] = String.valueOf(blackRook);

			// Knight
			chess[7][1] = String.valueOf(whiteKnight);
			chess[7][6] = String.valueOf(whiteKnight);

			chess[0][1] = String.valueOf(blackKnight);
			chess[0][6] = String.valueOf(blackKnight);

			// bishop
			chess[7][2] = String.valueOf(whiteBishop);
			chess[7][5] = String.valueOf(whiteBishop);

			chess[0][2] = String.valueOf(blackBishop);
			chess[0][5] = String.valueOf(blackBishop);

			// queen
			chess[7][3] = String.valueOf(whiteQueen);
			chess[0][3] = String.valueOf(blackQueen);

			// king
			chess[7][4] = String.valueOf(whiteKing);
			chess[0][4] = String.valueOf(blackKing);

		}

		// colocando os quadrados
		for (int l = 0; l < 9; l++) {
			for (int c = 0; c < 9; c++) {
				if (chess[l][c] == null) {
					chess[l][c] = String.valueOf(quadrado);
				}
			}

		}

	}

	private static void configurarPosicoes() {
		for (int c = 0; c < 9; c++) {
			int aa = 96 + (c + 1); // tabela ascii indo de a ate h

			chess[8][c] = String.valueOf((char) aa);

		}

		// colocando os numeros de 1 ate 8
		int b = 9;
		for (int l = 0; l < 8; l++) {
			int aa = --b;
			chess[l][8] = "    " + String.valueOf(aa);
		}

		chess[8][8] = ""; // o que seria o i

	}
	 

	public static String[][] chess() {
		colocarIcons();
		configurarPosicoes();
		return chess;
	}
}
