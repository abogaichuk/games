package app.games.game.card.deck;

/**
 * @author abogaichuk
 */
public class Card {
    private Suite suite;
    private CardValue cardValue;

    public Card(Suite suite, CardValue cardValue) {
        this.suite = suite;
        this.cardValue = cardValue;
    }

    public Suite getSuite() {
        return suite;
    }

    public CardValue getCardValue() {
        return cardValue;
    }
}
