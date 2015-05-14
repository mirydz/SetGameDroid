package me.rydz.setgame;

import java.util.ArrayList;

public class GameManager {

    private Deck deck;
    private ArrayList<Card> hand;
    private boolean isGameOver;

    public void newGame() {
        this.deck = new Deck();
        this.hand = this.deck.getHand();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }


    public boolean isValidSet() {
        return false;
    }

}
