package com.jad.chessknight;

import java.util.ArrayList;
import java.util.Collections;

public class ChessKnightProblem {
    private int startRow, startCol;
    private Board<Square> board;
    private int[] rowMoves = {2, 2, -2, -2, 1, 1, -1, -1};
    private int[] colMoves = {1, -1, 1, -1, 2, -2, 2, -2};

    public ChessKnightProblem(Board<Square> board, int startRow, int startCol) {
        this.board = board;
        this.board.putAt(startCol, startRow, new Square(1));
    }

    public void solve() {
        // Initialisation de la case de départ
        int count = 2;
        int startRow = 0;
        int startCol = 0;

        // Trouver la position de départ du cavalier
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getAt(j, i) != null && board.getAt(j, i).getValue() == 1) {
                    startRow = i;
                    startCol = j;
                    break;
                }
            }
        }

        // Tant qu'il reste des cases à visiter
        while (count <= board.getHeight() * board.getWidth()) {
            ArrayList<int[]> possibleMoves = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                int nextRow = startRow + rowMoves[i];
                int nextCol = startCol + colMoves[i];

                if (isValidMove(nextRow, nextCol) && !hasVisited(nextRow, nextCol)) {
                    possibleMoves.add(new int[]{nextRow, nextCol});
                }
            }

            // Si aucun mouvement valide n'a été trouvé, la solution n'est pas possible
            if (possibleMoves.size() == 0) {
                System.out.println("Solution not possible");
                return;
            }

            // Trier les mouvements possibles par ordre croissant du nombre de mouvements valides qu'ils offrent
            Collections.sort(possibleMoves, (a, b) -> {
                int countA = 0;
                int countB = 0;
                for (int i = 0; i < 8; i++) {
                    int nextRowA = a[0] + rowMoves[i];
                    int nextColA = a[1] + colMoves[i];
                    if (isValidMove(nextRowA, nextColA) && !hasVisited(nextRowA, nextColA)) {
                        countA++;
                    }
                    int nextRowB = b[0] + rowMoves[i];
                    int nextColB = b[1] + colMoves[i];
                    if (isValidMove(nextRowB, nextColB) && !hasVisited(nextRowB, nextColB)) {
                        countB++;
                    }
                }
                return Integer.compare(countA, countB);
            });

            // Déplacer le cavalier vers le mouvement offrant le plus petit nombre de mouvements valides
            int[] nextMove = possibleMoves.get(0);
            startRow = nextMove[0];
            startCol = nextMove[1];
            board.putAt(startCol, startRow, new Square(count));
            count++;
        }
        // Affichage de la solution
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                System.out.print(board.getAt(j, i) + " ");
            }
            System.out.println();
        }
    }

    private boolean isValidMove(int row, int col) {
        return (row >= 0 && col >= 0 && row < board.getHeight() && col < board.getWidth());
    }

    private boolean hasVisited(int row, int col) {
        return (board.getAt(col, row) != null);
    }
}