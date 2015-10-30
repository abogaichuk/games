package app.games.game.card.deck;

/**
 * @author abogaichuk
 */
public enum CardValue {
    ACE(10),
    KING(10),
    QUEEN(10),
    JACK(10),
    TEN(10),
    NINE(9),
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2);

    private int value;

    CardValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
