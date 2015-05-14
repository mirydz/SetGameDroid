package me.rydz.setgame;

enum Number { ONE, TWO, THREE}
enum Symbol { DIAMOND, SQUIGGLE, OVAL }
enum Shading { SOLID, STRIPED, OPEN }
enum Color { RED, GREEN, PURPLE }


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
