package org.testing.project_2_1.Agents;

import org.testing.project_2_1.GameLogic.GameLogic;
import org.testing.project_2_1.Moves.Move;

import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.PauseTransition;

public class BaselineAgent implements Agent {
    private GameLogic gameLogic;
    private boolean isWhite;

    public BaselineAgent(boolean isWhite) {
        this.isWhite = isWhite;
    }
    
    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public void makeMove() {
        System.out.println("Baseline agent making move");
        PauseTransition pause = new PauseTransition(Duration.seconds(Agent.delay));
        pause.setOnFinished(event -> {
        while (gameLogic.isWhiteTurn == isWhite && !gameLogic.isGameOver()) {
            ArrayList<Move> legalMoves = gameLogic.getLegalMoves();
            System.out.println("Legal moves: " + legalMoves.size());
            int randomIndex = new Random().nextInt(legalMoves.size());
            Move move = legalMoves.get(randomIndex);
            System.out.println("Take turn with move " + move);
            gameLogic.takeMove(move);
            gameLogic.evaluateBoard();
        }
    });
    pause.play();
    }

    @Override
    public BaselineAgent reset() {
        return new BaselineAgent(isWhite);
    }
    
}
