package pokemon.controllers;

import java.io.Serializable;

public class Position implements Serializable {
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x;
    public int y;
}
