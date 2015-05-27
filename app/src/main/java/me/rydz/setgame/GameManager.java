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


    public boolean isValidSet(ArrayList<Card> selectedCards) {
        int maxSelectedCards = 3;
        if (selectedCards.size() != maxSelectedCards) {
            return false;
        }

        Card firstCard = selectedCards.get(0);
        Card secondCard = selectedCards.get(1);
        Card thirdCard = selectedCards.get(2);

        if (areSameOrDifferentQuantity(firstCard, secondCard, thirdCard)) {
            if (areSameOrDifferentSymbol(firstCard, secondCard, thirdCard)) {
                if (areSameOrDifferentShading(firstCard, secondCard, thirdCard)) {
                    if (areSameOrDifferentColor(firstCard, secondCard, thirdCard)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean areSameOrDifferentColor(Card a, Card b, Card c) {
        return false;
    }

    private boolean areSameOrDifferentShading(Card a, Card b, Card c) {
        return false;
    }

    private boolean areSameOrDifferentSymbol(Card a, Card b, Card c) {
        return false;
    }

    private boolean areSameOrDifferentQuantity(Card a, Card b, Card c) {
        return false;
    }


}
