package app.games.db.dao;

import app.games.db.model.Gamer;
import app.games.db.model.Operation;

import java.util.List;

/**
 * @author abogaichuk
 */
public interface GamerDAO {
    Gamer getById(long account);
    Long addGamer(Gamer gamer);
    void deleteGamer(long account);
    Long getTotalBalance(long account);
    Long addOperation(Operation o, long account)/* throws ConstraintViolationException*/;
    List<Gamer> getAll();
    boolean isAlreadyLoginUse(String login);
}
