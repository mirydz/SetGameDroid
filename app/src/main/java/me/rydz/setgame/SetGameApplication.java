package me.rydz.setgame;


import android.app.Application;

public class SetGameApplication extends Application {
    private static SetGameApplication setGameApplicationInstance;

    private GameManager game;

    public static SetGameApplication getInstance() {
        return setGameApplicationInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setGameApplicationInstance = this;

        this.game = new GameManager();
        this.game.newGame();

    }

    public GameManager getGame() {
        return this.game;
    }

    public void setGame(GameManager game) {
        this.game = game;
    }
}
