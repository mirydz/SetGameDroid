package me.rydz.setgame;

import static me.rydz.setgame.Utils.Color;
import static me.rydz.setgame.Utils.Shading;
import static me.rydz.setgame.Utils.Symbol;

public class Card {

    private int number;
    private Symbol symbol;
    private Shading shading;
    private Color color;

    public Card (int number, Symbol symbol, Shading shading, Color color) {
        this.number = number;
        this.symbol = symbol;
        this.shading = shading;
        this.color = color;
    }

    public Card() {

    }


    public Symbol getSymbol() {
        return symbol;
    }

    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }

    public Shading getShading() {
        return shading;
    }
}
