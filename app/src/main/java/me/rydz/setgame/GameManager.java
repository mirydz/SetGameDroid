package me.rydz.setgame;

import java.util.ArrayList;
import static me.rydz.setgame.Utils.Color;
import static me.rydz.setgame.Utils.Shading;
import static me.rydz.setgame.Utils.Symbol;

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

    //<editor-fold desc="Property Comparisons">
    private boolean areSameOrDifferentColor(Card cardA, Card cardB, Card cardC) {
        return areSameOrDifferent(cardA.getColor(), cardB.getColor(), cardC.getColor());
    }

    private boolean areSameOrDifferentShading(Card cardA, Card cardB, Card cardC) {
        return areSameOrDifferent(cardA.getShading(), cardB.getShading(), cardC.getShading());
    }

    private boolean areSameOrDifferentSymbol(Card cardA, Card cardB, Card cardC) {
        return areSameOrDifferent(cardA.getSymbol(), cardB.getSymbol(), cardC.getSymbol());
    }

    private boolean areSameOrDifferentQuantity(Card cardA, Card cardB, Card cardC) {
        return areSameOrDifferent(cardA.getNumber(), cardB.getNumber(), cardC.getNumber()) ;
    }

    private boolean areSameOrDifferent(Object propertyA, Object propertyB, Object propertyC) {
        return areSame(propertyA, propertyB, propertyC) || areDifferent(propertyA, propertyB, propertyC);
    }

    private boolean areSame(Object propertyA, Object propertyB, Object propertyC) {
        boolean result = propertyA.equals(propertyB) && propertyB.equals(propertyC)
                         && propertyA.equals(propertyC);
        return result;
    }

    private boolean areDifferent(Object propertyA, Object propertyB, Object propertyC) {
        boolean result = !propertyA.equals(propertyB) && !propertyB.equals(propertyC)
                && !propertyA.equals(propertyC);
        return result;
    }
    //</editor-fold>

}
