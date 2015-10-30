package app.games.db.model;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

/**
 * @author abogaichuk
 */
@Entity
@Table(name = "OPERATION")
public class Operation {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    private boolean win;
    private int value;
    private long balance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(unique = true, nullable = false, length = 15)
    private Date transactionDate;

    @ManyToOne
    private Gamer gamer;

    public long getId() {
        return id;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Gamer getGamer() {
        return gamer;
    }

    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
