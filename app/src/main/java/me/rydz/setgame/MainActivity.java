package me.rydz.setgame;

import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ArrayList<ImageButton> cardButtons;
    private ArrayList<ImageButton> selectedCardButtons;
    private GameManager game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.cardButtons = new ArrayList<>();
        this.selectedCardButtons = new ArrayList<>();
        this.game = new GameManager();
        game.newGame();



        cardButtons.add( (ImageButton)findViewById(R.id.card_0_0) );
        cardButtons.add( (ImageButton)findViewById(R.id.card_0_1) );
        cardButtons.add( (ImageButton)findViewById(R.id.card_0_2) );
        cardButtons.add( (ImageButton)findViewById(R.id.card_1_0) );
        cardButtons.add( (ImageButton)findViewById(R.id.card_1_1) );
        cardButtons.add( (ImageButton)findViewById(R.id.card_1_2) );
        cardButtons.add( (ImageButton)findViewById(R.id.card_2_0) );
        cardButtons.add( (ImageButton)findViewById(R.id.card_2_1) );
        cardButtons.add( (ImageButton)findViewById(R.id.card_2_2) );
        cardButtons.add( (ImageButton)findViewById(R.id.card_3_0) );
        cardButtons.add( (ImageButton)findViewById(R.id.card_3_1) );
        cardButtons.add( (ImageButton)findViewById(R.id.card_3_2) );

        this.populateView();

        for (ImageButton cardButton : cardButtons) {
            cardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCardClicked(v);
                }
            });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        boolean isHandled;

        switch(id) {
            case R.id.action_newGame:
                onClickMenuNewGame(item);
                isHandled = true;
                break;

           case R.id.action_exit:
               onClickMenuExit(item);
                isHandled = true;
                break;

            case R.id.action_settings:
                onClickMenuSettings(item);
                isHandled = true;
                break;

            default:
                isHandled =  super.onOptionsItemSelected(item);
        }

        return isHandled;
    }

    private void populateView() {
        ArrayList<Card> hand = game.getHand();

        for (int cardIndex = 0; cardIndex < hand.size(); cardIndex++) {
            int imgFileId = this.resolveFileId(hand.get(cardIndex));

            this.cardButtons.get(cardIndex).setBackgroundResource(imgFileId);
            this.cardButtons.get(cardIndex).invalidate();
        }
    }

    private int resolveFileId(Card card) {
        String file = "";
        file += card.getShading().toString().toLowerCase();
        file += card.getSymbol().toString().toLowerCase();
        file += card.getColor().toString().toLowerCase();
        file += card.getNumber();


        return getResources().getIdentifier(file, "drawable", getApplicationContext().getPackageName());
    }

    private void onCardClicked(View view) {
        int maxNumberOfCardsSelected = 3;
        Resources res = getResources();
        LinearLayout wrapper = (LinearLayout) view.getParent();

        if ( ! isAlreadySelected(view)  ) {
            if (this.isPossibleSelectCard()) {
                this.selectedCardButtons.add((ImageButton) view);

                wrapper.setBackgroundColor(res.getColor(R.color.selected));
                if (this.selectedCardButtons.size() == maxNumberOfCardsSelected) {
                    handleThreeCardsSelected();
                }
            }
        } else {
            this.selectedCardButtons.remove((ImageButton) view);
            wrapper.setBackgroundColor(res.getColor(R.color.neutral));
            String size = Integer.toString(this.selectedCardButtons.size());
            Toast toast = Toast.makeText(this, size, Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private boolean isPossibleSelectCard() {
        return (this.selectedCardButtons.size() < 3);
    }

    private boolean isAlreadySelected(View view) {
        return this.selectedCardButtons.contains(view);
    }

    private void handleThreeCardsSelected() {
        Toast toast = Toast.makeText(this, "3 cards selected", Toast.LENGTH_SHORT);
        int delayTime = 300; // in milliseconds

        if (this.game.isValidSet()) {
            this.handleValidSet();
            highlightSelection(R.color.valid);
        } else
            highlightSelection(R.color.invalid);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        highlightSelection(R.color.neutral);
                        selectedCardButtons.clear();
                    }
                },
                delayTime);


        toast.show();
    }

    private void handleValidSet() {

    }

    private void highlightSelection(int colorId) {
        Resources res = getResources();
        for ( View card : this.selectedCardButtons) {
            LinearLayout wrapper = (LinearLayout) card.getParent();
            wrapper.setBackgroundColor(res.getColor(colorId));
        }

    }

    private void displayBorder(ImageButton card) {
        Resources res = getResources();
        LinearLayout wrapper = (LinearLayout) card.getParent();
        wrapper.setBackgroundColor(res.getColor(R.color.selected));
    }

    private void onClickMenuSettings(MenuItem item) {
        Toast toast = Toast.makeText(this, "Settings clicked!", Toast.LENGTH_SHORT);
        toast.show();
    }


    private void onClickMenuNewGame(MenuItem item) {
        Toast toast = Toast.makeText(this, "New game clicked!", Toast.LENGTH_SHORT);
        toast.show();
        this.game.newGame();
        this.populateView();
    }

    private void onClickMenuExit(MenuItem item) {
        finish();
    }


}
