package pokemon.controllers;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    String getName() {
        return name;
    }

    int getScore() {
        return score;
    }

    void incrementScore() {
        score++;
    }
}
