package org.testing.project_2_1;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class AlphaBetaSearch implements Agent {
    private GameLogic gameLogic;
    private int maxDepth;
    private int nodesVisited;
    private int nodesEvaluated;
    private int maxPruned;
    private int minPruned;
    private boolean isWhite;


    public AlphaBetaSearch(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void makeMove() {
        nodesVisited = 0;
        nodesEvaluated = 0;
        System.out.println("Alpha-Beta agent making move");
        PauseTransition pause = new PauseTransition(Duration.seconds(Agent.delay));
        // TODO: search algorithm implementation to finish, working on it...
    }

    public double maxValue(Double alpha, Double beta, int depth) {
        nodesVisited++;
        if (depth == 0 || gameLogic.isGameOver()) {
            nodesEvaluated++;
            return gameLogic.evaluateBoard();
        }
        Double value = Double.MIN_VALUE;
        ArrayList<Move> legalMoves = gameLogic.getLegalMoves();
        for (Move move : legalMoves) {
            gameLogic.takeTurn(move);
            value = Math.max(value, minValue(alpha, beta, depth - 1));
            gameLogic.undoLastMove();
            if (value >= beta) {
                maxPruned++;
                return value;
            }
            alpha = Math.max(alpha, value);
        }
        return value;
    }

    public double minValue(Double alpha, Double beta, int depth) {
        nodesVisited++;
        if (depth == 0 || gameLogic.isGameOver()) {
            nodesEvaluated++;
            return gameLogic.evaluateBoard();
        }
        double value = Integer.MAX_VALUE;
        ArrayList<Move> legalMoves = gameLogic.getLegalMoves();
        for (Move move : legalMoves) {
            gameLogic.takeTurn(move);
            value = Math.min(value, maxValue(alpha, beta, depth - 1));
            gameLogic.undoLastMove();
            if (value <= alpha) {
                minPruned++;
                return value;
            }
            beta = Math.min(beta, value);
        }
        return value;
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }
    
}
