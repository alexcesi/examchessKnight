package com.jad;

import com.jad.chessknight.*;

public class Main {

    public static void main(String[] args) {
        // Création d'un objet Board de 8x8
        Board<Square> board = new Board<>(8, 8);

        // Création d'un objet ChessKnightProblem avec le Board et les coordonnées de départ
        ChessKnightProblem problem = new ChessKnightProblem(board, 0, 0);

        // Appel de la méthode solve()
        problem.solve();
    }
}
