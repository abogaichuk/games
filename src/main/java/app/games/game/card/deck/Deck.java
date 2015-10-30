package app.games.game.card.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author abogaichuk
 */
public class Deck {
    private List<Card> deck;

    public Deck() {
        deck = new ArrayList<>();

        for (int i = 0; i < CardValue.values().length; i++) {
            CardValue cardValue = CardValue.values()[i];
            for (int j = 0; j < Suite.values().length; j++) {
                deck.add(new Card(Suite.values()[j], cardValue));
            }
        }
        Collections.shuffle(deck);
    }

    public List<Card> getDeck() {
        return deck;
    }

    private Card getCard() {
        return deck.remove(0);
    }

    public List<Card> handOut(int count) {
        if (count > 52)
            throw new IllegalArgumentException("count > deck.size()");

        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < count; i++)
            cards.add(getCard());
        return cards;
    }
}
