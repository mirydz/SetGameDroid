package me.rydz.setgame;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static me.rydz.setgame.Utils.Color;
import static me.rydz.setgame.Utils.Shading;
import static me.rydz.setgame.Utils.Symbol;

public class GameManager {

    private Deck deck;
    private ArrayList<Card> hand;
    private ArrayList<Card[]> possibleSets;
    private ArrayList<Card[]> identifiedSets;
    private boolean isGameOver;

    public void newGame() {
        final int minNumberOfPossibleSets = 4;
        this.deck = new Deck();
        do {
            this.hand = this.deck.getHand();
            this.possibleSets = fetchPossibleSets(this.hand);
        } while (this.getNumberOfPossibleSets() < minNumberOfPossibleSets);

        this.identifiedSets = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card[]> fetchPossibleSets(ArrayList<Card> hand) {
        ArrayList<Card[]> possibleSets = new ArrayList<>();

        for (int indexA = 0; indexA < hand.size(); indexA++) {
            for (int indexB = indexA + 1; indexB < hand.size(); indexB++) {
                for (int indexC = indexB + 1; indexC < hand.size(); indexC++) {
                    Card firstCard = hand.get(indexA);
                    Card secondCard = hand.get(indexB);
                    Card thirdCard = hand.get(indexC);

                    if (isValidSet(firstCard, secondCard, thirdCard)) {
                        Card[] possibleSet = {firstCard, secondCard, thirdCard};
                        Arrays.sort(possibleSet);
                        possibleSets.add(possibleSet);
                    }
                }
            }
        }

        return possibleSets;
    }

    public int getNumberOfPossibleSets() {
        return this.possibleSets.size();
    }
    public int getNumberOfIdentifiedSets() {
        return this.identifiedSets.size();
    }

    public boolean isValidSet(ArrayList<Card> selectedCards) {
        Card[] selectedCardsArr = new Card[selectedCards.size()];
        selectedCardsArr = selectedCards.toArray(selectedCardsArr);
        Arrays.sort(selectedCardsArr);

        boolean isFoundMatch = false;
        for (Card[] possibleSet : this.possibleSets) {
            if (Arrays.equals(possibleSet, selectedCardsArr)) {
                isFoundMatch = true;
            }
        }
        return isFoundMatch;
    }

    public boolean isAlreadyIdentifiedSet(ArrayList<Card> selectedCards) {
        Card[] selectedCardsArr = new Card[selectedCards.size()];
        selectedCardsArr = selectedCards.toArray(selectedCardsArr);
        Arrays.sort(selectedCardsArr);

        for (Card[] identifiedSet : this.identifiedSets) {
            if(Arrays.equals(identifiedSet, selectedCardsArr))
                return true;
        }

        this.identifiedSets.add(selectedCardsArr);
        return false;

    }

    private boolean isValidSet(Card firstCard, Card secondCard, Card thirdCard) {
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
