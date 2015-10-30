package app.games.game.card;

/**
 * @author abogaichuk
 */
public class BlackJackProcess {
    private CardGame game;
    private int rate;
    private long account;
    private long balance;
    private String resultMessage;

    public BlackJackProcess() {}

    public BlackJackProcess(long account, long balance, CardGame game) {
        this.account = account;
        this.balance = balance;
        this.game = game;
    }

    public CardGame getGame() {
        return game;
    }

    public void setGame(CardGame game) {
        this.game = game;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public int getWin() {
        return game.getWin(rate);
    }

    public boolean stand() {
        return game.stand();
    }

    public boolean hit() {
        return game.hit();
    }

    public void play() {
        game.play();
    }
}
