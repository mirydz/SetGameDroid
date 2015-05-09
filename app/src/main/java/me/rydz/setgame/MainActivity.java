package me.rydz.setgame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    private void onClickMenuSettings(MenuItem item) {
        Toast toast = Toast.makeText(this, "Settings clicked!", Toast.LENGTH_SHORT);
        toast.show();
    }


    private void onClickMenuNewGame(MenuItem item) {
        Toast toast = Toast.makeText(this, "New game clicked!", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void onClickMenuExit(MenuItem item) {
        finish();
    }


}
