package app.games.game.card;

import app.games.game.card.deck.Card;
import app.games.game.card.deck.CardValue;
import app.games.game.card.deck.Suite;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author abogaichuk
 */

public class BlackJackTest {
    private CardGame blackJack;

    @Before
    public void setUp() {
        blackJack = new BlackJack();
    }

    @Test
    public void shouldBeCountResult() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Card> cards = Arrays.asList(new Card(Suite.DIAMONDS, CardValue.FIVE),
                new Card(Suite.DIAMONDS, CardValue.QUEEN));
        assertEquals(15, callCountResult(cards));
    }

    @Test
    public void shouldBeCountAceAs1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Card> cards = Arrays.asList(new Card(Suite.DIAMONDS, CardValue.FIVE),
                new Card(Suite.DIAMONDS, CardValue.QUEEN), new Card(Suite.DIAMONDS, CardValue.ACE));
        assertEquals(16, callCountResult(cards));
    }

    private int callCountResult(List<Card> cards) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = BlackJack.class.getDeclaredMethod("countResult", List.class);
        method.setAccessible(true);
        return (int) method.invoke(blackJack, cards);
    }
}
