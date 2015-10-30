package app.games.game.card;

/**
 * @author abogaichuk
 */
public class Bet {
    private int rate;
    private long account;
    private long balance;

    public Bet() {}

    public Bet(long account, long balance) {
        this.account = account;
        this.balance = balance;
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
}
