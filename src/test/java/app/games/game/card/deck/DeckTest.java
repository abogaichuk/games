package app.games.game.card.deck;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author abogaichuk
 */
public class DeckTest {
    private Deck deck = new Deck();

    @Test
    public void shouldBeFullDeck() {
        assertEquals(52, deck.getDeck().size());
    }

    @Test
    public void shouldBeAllSuites() {
        int clubs = 0, hearts = 0, spades = 0, diamonds = 0;
        for (Card card : deck.getDeck()) {
            switch (card.getSuite()) {
                case CLUBS:
                    clubs++;
                    break;
                case HEARTS:
                    hearts++;
                    break;
                case SPADES:
                    spades++;
                    break;
                case DIAMONDS:
                    diamonds++;
                    break;
            }
        }

        int quarter = deck.getDeck().size() / 4;
        assertTrue(clubs == quarter && hearts == quarter && spades == quarter
                && diamonds == quarter);
    }

    @Test
    public void shouldBeAllCardValues() {
        Map<CardValue, Integer> count = new HashMap<>();
        for (int i = 0; i < CardValue.values().length; i++)
            count.put(CardValue.values()[i], 0);

        for (Card card : deck.getDeck()) {
            int i = count.get(card.getCardValue());
            count.replace(card.getCardValue(), i, i+1);
        }

        for (int i : count.values())
            assertTrue(4 == i);
    }

    @Test
    public void shouldBeAbleHandOutUniqueCard() {
        shouldBeFullDeck();
        List<Card> testCards = deck.handOut(2);
        assertNotNull(testCards);
        assertEquals(2, testCards.size());
        assertEquals(50, deck.getDeck().size());

        boolean unique = true;
        for (Card card : deck.getDeck())
            if (card.equals(testCards.get(0)) || card.equals(testCards.get(1)))
                unique = false;
        assertTrue(unique);
    }
}
