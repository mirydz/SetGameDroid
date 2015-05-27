package me.rydz.setgame;

import java.util.ArrayList;
import java.util.Collections;
import static me.rydz.setgame.Utils.Color;
import static me.rydz.setgame.Utils.Shading;
import static me.rydz.setgame.Utils.Symbol;

public class Deck {
    private ArrayList<Card> allCards;

    public Deck() {
        this.allCards = new ArrayList<Card>();
        this.createFullSortedDeck();
    }

    private void createFullSortedDeck() {
        for ( int number = 1; number <= 3; number++ ) {
            for ( Symbol symbol : Symbol.values() ) {
                for (Shading shading : Shading.values() ) {
                    for ( Color color : Color.values() ) {
                        this.allCards.add(new Card(number, symbol, shading, color));
                    }

                }
            }
        }
    }

    public ArrayList<Card> getHand() {
        final int HANDSIZE = 12;
        this.shuffleDeck();
        return new ArrayList<>(this.allCards.subList(0, HANDSIZE));
    }

    public void shuffleDeck() {
        Collections.shuffle(this.allCards);
    }


    public ArrayList<Card> getAllCards() {
        return allCards;
    }
}
