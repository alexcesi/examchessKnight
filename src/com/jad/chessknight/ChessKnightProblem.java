package com.jad.chessknight;

/**
 * The type Chess knight problem.
 */
public class ChessKnightProblem {
    /**
     * Solve.
     *
     * @throws CloneNotSupportedException the clone not supported exception
     */
    public final static void solve() throws CloneNotSupportedException {
        ChessBoard chessBoard = new ChessBoard();
        ChessKnight chessKnight = new ChessKnight(chessBoard,0,0);

        for (int y = 0; y < chessBoard.getHeight(); y++) {
            for (int x = 0; x < chessBoard.getWidth(); x++) {
                chessKnight.moveTo(x, y);
            }
        }

        System.out.println(chessBoard);
    }
}
