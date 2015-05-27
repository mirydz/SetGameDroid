package me.rydz.setgame;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static enum Symbol { DIAMOND, SQUIGGLE, OVAL}
    public static enum Shading { SOLID, STRIPED, OPEN }
    public static enum Color { RED, GREEN, PURPLE }

    public static void showToast(Context context, String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }

}
