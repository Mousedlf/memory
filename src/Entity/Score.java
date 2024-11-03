package Entity;

import java.util.ArrayList;
import java.util.List;


public class Score {

    private final int attempts;
    // nom du joueur String
    // temps
    // nb de cartes

    public Score(int attempts) {
        this.attempts = attempts;
    }

    public int getAttempts() {
        return attempts;
    }

   /* public static void displayScores() {
        if (scoreList.isEmpty()) {
            System.out.println("Aucun score enregistré.");
        } else {
            System.out.println("Scores des joueurs :");
            for (Score score : scoreList) {
                System.out.println("Tentatives : " + score.getAttempts());
            }
        }
    }*/

}
