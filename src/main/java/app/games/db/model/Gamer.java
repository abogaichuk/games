package app.games.db.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

/**
 * @author abogaichuk
 */
@Entity
@Table(name = "GAMER")
@NamedQuery(name = "Gamer.getLastOperationBalance", query = "select o.balance from Gamer g inner join g.operations o " +
        "where g.account = :account and o.transactionDate =(select max(o1.transactionDate) from Operation as o1)")
public class Gamer implements Serializable {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long account;

    @NotNull
    @Size(min = 4, max = 15, message = "login must between 4 and 15 characters")
    @Column(name = "LOGIN", unique = true, nullable = false)
    private String login;

    @NotNull
    @Size(min = 4, max = 15, message = "password must between 4 and 15 characters")
    private String password;

    @OneToMany(cascade = CascadeType.ALL/*, fetch = FetchType.EAGER*/, fetch = FetchType.LAZY)
    private List<Operation> operations = new ArrayList<>();

    public Gamer() {}

    public Gamer(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public long getAccount() {
        return account;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public void addOperation(Operation operation) {
        if (operations == null)
            operations = new ArrayList<>();
        operations.add(operation);
    }
}
