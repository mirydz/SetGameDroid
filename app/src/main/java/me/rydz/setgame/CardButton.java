package me.rydz.setgame;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import static me.rydz.setgame.Utils.Color;
import static me.rydz.setgame.Utils.Shading;
import static me.rydz.setgame.Utils.Symbol;


public class CardButton extends ImageButton {

    private Card card;

    public CardButton(Context context) {
        super(context);
    }

    public CardButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCard(Card card) { this.card = card; }


    public Card getCard() {
        return card;
    }

    public Symbol getSymbol() {
        return this.card.getSymbol();
    }

    public int getNumber() {
        return this.card.getNumber();
    }

    public Color getColor() {
        return this.card.getColor();
    }

    public Shading getShading() {
        return this.card.getShading();
    }

    @Override
    public String toString() {
        return String.valueOf(this.card.getNumber()) + this.card.getColor() + this.card.getShading()
                            + this.card.getSymbol();
    }

}
