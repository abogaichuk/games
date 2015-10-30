package app.games.game.card;

import app.games.game.Game;

import java.util.List;

/**
 * @author abogaichuk
 */
public interface CardGame extends Game {
    List<String> getPlayerCards();
    List<String> getDealerCards();
    boolean hit();
    boolean stand();
    int getWin(int bet);
    boolean isEnd();
}
