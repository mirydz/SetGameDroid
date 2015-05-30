package me.rydz.setgame;

import java.util.ArrayList;
import java.util.Arrays;

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


    public String getMessageForInvalidSet(ArrayList<Card> selectedCards) {
        StringBuilder messageBuilder = new StringBuilder();
        Card firstCard = selectedCards.get(0);
        Card secondCard = selectedCards.get(1);
        Card thirdCard = selectedCards.get(2);

        if ( ! areSameOrDifferentQuantity(firstCard, secondCard, thirdCard)) {
            messageBuilder.append("Wrong quantity: ");
            messageBuilder.append(Integer.toString(firstCard.getNumber()));
            messageBuilder.append(", ");
            messageBuilder.append(Integer.toString(secondCard.getNumber()));
            messageBuilder.append(" and ");
            messageBuilder.append(Integer.toString(thirdCard.getNumber()));
            messageBuilder.append('\n');
            messageBuilder.append("Quantity must be all the same or all different!");
            messageBuilder.append("\n\n");

        }
        if ( ! areSameOrDifferentSymbol(firstCard, secondCard, thirdCard)) {
            messageBuilder.append("Wrong symbol: ");
            messageBuilder.append(firstCard.getSymbol().toString());
            messageBuilder.append(", ");
            messageBuilder.append(secondCard.getSymbol().toString());
            messageBuilder.append(" and ");
            messageBuilder.append(thirdCard.getSymbol().toString());
            messageBuilder.append('\n');
            messageBuilder.append("Symbols must be all the same or all different!");
            messageBuilder.append("\n\n");
        }
        if ( ! areSameOrDifferentColor(firstCard, secondCard, thirdCard)) {
            messageBuilder.append("Wrong color: ");
            messageBuilder.append(firstCard.getColor().toString());
            messageBuilder.append(", ");
            messageBuilder.append(secondCard.getColor().toString());
            messageBuilder.append(" and ");
            messageBuilder.append(thirdCard.getColor().toString());
            messageBuilder.append('\n');
            messageBuilder.append("Colors must be all the same or all different!");
            messageBuilder.append("\n\n");
        }
        if ( ! areSameOrDifferentShading(firstCard, secondCard, thirdCard)) {
            messageBuilder.append("Wrong shading: ");
            messageBuilder.append(firstCard.getShading().toString());
            messageBuilder.append(", ");
            messageBuilder.append(secondCard.getShading().toString());
            messageBuilder.append(" and ");
            messageBuilder.append(thirdCard.getShading().toString());
            messageBuilder.append('\n');
            messageBuilder.append("Shading must be all the same or all different!");
        }





        return messageBuilder.toString();
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
