package me.rydz.setgame;

import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ArrayList<CardButton> cardButtons;
    private ArrayList<CardButton> selectedCardButtons;
    private GameManager game;
    private TextView numberOfSetsIdentified;
    private TextView numberOfPossibleSets;
    private Button hintButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.cardButtons = new ArrayList<>();
        this.selectedCardButtons = new ArrayList<>();
        this.game = new GameManager();
        game.newGame();

        this.numberOfSetsIdentified = (TextView)findViewById(R.id.sets_found_number);
        this.numberOfPossibleSets = (TextView)findViewById(R.id.sets_possible_number);

        this.numberOfSetsIdentified.setText(Integer.toString(this.game.getNumberOfIdentifiedSets()));
        this.numberOfPossibleSets.setText(Integer.toString(this.game.getNumberOfPossibleSets()));

        cardButtons.add( (CardButton)findViewById(R.id.card_0_0) );
        cardButtons.add( (CardButton)findViewById(R.id.card_0_1) );
        cardButtons.add( (CardButton)findViewById(R.id.card_0_2) );
        cardButtons.add( (CardButton)findViewById(R.id.card_1_0) );
        cardButtons.add( (CardButton)findViewById(R.id.card_1_1) );
        cardButtons.add( (CardButton)findViewById(R.id.card_1_2) );
        cardButtons.add( (CardButton)findViewById(R.id.card_2_0) );
        cardButtons.add( (CardButton)findViewById(R.id.card_2_1) );
        cardButtons.add( (CardButton)findViewById(R.id.card_2_2) );
        cardButtons.add( (CardButton)findViewById(R.id.card_3_0) );
        cardButtons.add( (CardButton)findViewById(R.id.card_3_1) );
        cardButtons.add( (CardButton)findViewById(R.id.card_3_2) );

        this.populateView();

        for (ImageButton cardButton : cardButtons) {
            cardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCardClicked(v);
                }
            });
        }

        this.hintButton = (Button)findViewById(R.id.hint_button);
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHintButton(v);
            }
        });

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
            CardButton currentCardButton = this.cardButtons.get(cardIndex);
            Card currentCard = hand.get(cardIndex);
            int imgFileId = this.resolveFileId(currentCard);

            currentCardButton.setCard(currentCard);
            this.cardButtons.get(cardIndex).setBackgroundResource(imgFileId);
            this.cardButtons.get(cardIndex).invalidate();
        }
    }

    private int resolveFileId(Card card) {
        String fileName = card.getShading().toString().toLowerCase()
                        + card.getSymbol().toString().toLowerCase()
                        + card.getColor().toString().toLowerCase()
                        + card.getNumber();

        return getResources().getIdentifier(fileName, "drawable", getApplicationContext().getPackageName());
    }

    private void onCardClicked(View view) {
        int maxNumberOfCardsSelected = 3;
        Resources res = getResources();
        LinearLayout wrapper = (LinearLayout) view.getParent();

        if ( ! isAlreadySelected(view)  ) {
            if (this.isPossibleSelectCard()) {
                this.selectedCardButtons.add((CardButton) view);
                //Utils.showToast(this, view.toString());
                wrapper.setBackgroundColor(res.getColor(R.color.selected));
                if (this.selectedCardButtons.size() == maxNumberOfCardsSelected) {
                    handleThreeCardsSelected();
                }
            }
        } else {
            this.selectedCardButtons.remove(view);
            wrapper.setBackgroundColor(res.getColor(R.color.neutral));
        }

    }

    private boolean isPossibleSelectCard() {
        return (this.selectedCardButtons.size() < 3);
    }

    private boolean isAlreadySelected(View view) {
        return this.selectedCardButtons.contains(view);
    }

    private void handleThreeCardsSelected() {

        int delayTime = 1000; // in milliseconds
        ArrayList<Card> selectedCards = new ArrayList<>();
        for (CardButton cardButton : this.selectedCardButtons ) {
            selectedCards.add(cardButton.getCard());
        }
        Object[] sc = this.selectedCardButtons.toArray();

        if (this.game.isValidSet(selectedCards)) {
            if ( ! this.game.isAlreadyIdentifiedSet(selectedCards)) {
                this.game.markAsIdentifiedSet(selectedCards);
                Utils.showToast(this, "It's a set!");
            } else {
                Utils.showToast(this, "You already found this set, remember?");
            }
            this.handleValidSet();
            highlightSelection(R.color.valid);
        } else {
            String errorMsg = this.game.getMessageForInvalidSet(selectedCards);
            highlightSelection(R.color.invalid);
            Utils.showToast(this, errorMsg, 6000);
        }
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        highlightSelection(R.color.neutral);
                        selectedCardButtons.clear();
                    }
                },
                delayTime);
    }

    private void handleValidSet() {
        this.numberOfSetsIdentified.setText(Integer.toString(this.game.getNumberOfIdentifiedSets()));
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

    private void onClickHintButton(View hintButton) {
        Card[] set = this.game.getUnidentifiedSet();
        if (set.length > 0) {
            StringBuilder msg = new StringBuilder();
            msg.append(set[0].toString());
            msg.append('\n');
            msg.append(set[1].toString());
            msg.append('\n');
            msg.append(set[2].toString());

            Utils.showToast(this, msg.toString(), 10000);
        }
    }

    private void onClickMenuNewGame(MenuItem item) {
        this.game.newGame();
        this.populateView();

        this.numberOfSetsIdentified.setText(Integer.toString(this.game.getNumberOfIdentifiedSets()));
        this.numberOfPossibleSets.setText(Integer.toString(this.game.getNumberOfPossibleSets()));
    }

    private void onClickMenuExit(MenuItem item) {
        finish();
    }


}
