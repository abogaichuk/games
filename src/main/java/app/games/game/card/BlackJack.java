package app.games.game.card;

import app.games.game.card.deck.Card;
import app.games.game.card.deck.CardValue;
import app.games.game.card.deck.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abogaichuk
 */
public class BlackJack implements CardGame {
    private Deck deck;
    private List<Card> dealerCards = new ArrayList<>();
    private List<Card> playerCards = new ArrayList<>();
    private boolean endGame = false;

    @Override
    public void play() {
        deck = new Deck();
        dealerCards.addAll(deck.handOut(2));
        playerCards.addAll(deck.handOut(2));
    }

    @Override
    public boolean hit() {
        playerCards.addAll(deck.handOut(1));
        endGame = isBust(playerCards);
        return endGame;
    }

    @Override
    public boolean stand() {
        while (countResult(dealerCards) < 17) {
            dealerCards.addAll(deck.handOut(1));
        }
        endGame = isBust(dealerCards);
        return endGame;
    }

    @Override
    public int getWin(int bet) {
        endGame = true;
        int dealerResult = countResult(dealerCards);
        int gamerResult = countResult(playerCards);
        if (gamerResult == 21 && dealerResult != 21) {
            return (int) (bet * 1.5);
        } else if (gamerResult == dealerResult) {
            return 0;
        }  else {
            return gamerResult > dealerResult ? bet : -bet;
        }
    }

    @Override
    public boolean isEnd() {
        return endGame;
    }

    private boolean isBust(List<Card> cards) {
        return countResult(cards) > 21;
    }

    private int countResult(List<Card> cards) {
        int result = 0;
        int aceCount = 0;
        for (Card card : cards) {
            if (card.getCardValue() == CardValue.ACE)
                aceCount++;
            else
                result = result + card.getCardValue().getValue();
        }
        return aceCount > 0 ? countResultWithAces(result, aceCount) : result;
    }

    private int countResultWithAces(int result, int aceCount) {
        return result > 10 ? getBetterResult(result, aceCount) : result + 10;
    }

    private int getBetterResult(int result, int aceCount) {
        if (aceCount == 1)
            return result == 11 ? result + 10 : result + 1;
        else
            return result + aceCount;
    }

    @Override
    public List<String> getPlayerCards() {
        List<String> pathes = new ArrayList<>();
        for (Card card : playerCards)
            pathes.add(card.getCardValue().name()+"_"+card.getSuite().name()+".gif");
        return pathes;
    }

    @Override
    public List<String> getDealerCards() {
        List<String> pathes = new ArrayList<>();
        for (Card card : dealerCards)
            pathes.add(card.getCardValue().name()+"_"+card.getSuite().name()+".gif");
        return pathes;
    }


}
