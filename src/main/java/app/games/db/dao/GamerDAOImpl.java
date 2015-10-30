package app.games.db.dao;

import app.games.commons.utils.GamesUtils;
import app.games.db.model.Gamer;
import app.games.db.model.Operation;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static app.games.commons.utils.GamesUtils.isNotEmpty;

/**
 * @author abogaichuk
 */
@Repository
public class GamerDAOImpl implements GamerDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Gamer getById(long account) {
        return (Gamer) sessionFactory.getCurrentSession().load(Gamer.class, account);
    }

    @Override
    @Transactional
    public Long addGamer(Gamer gamer) {
        sessionFactory.getCurrentSession().save(gamer);
        return gamer.getAccount();
    }

    @Override
    @Transactional
    public void deleteGamer(long account) {
        Gamer gamer = (Gamer) sessionFactory.getCurrentSession().load(Gamer.class, account);
        if (gamer != null)
            sessionFactory.getCurrentSession().delete(gamer);
    }

    @Override
    @Transactional
    public Long getTotalBalance(long account) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("Gamer.getLastOperationBalance");
        query.setLong("account", account);
        List list = query.list();
        return list != null && list.size() > 0 ? (Long) list.get(0) : 0;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false/*, rollbackFor = ConstraintViolationException.class*/)
    public Long addOperation(Operation o, long account)/* throws ConstraintViolationException */{
        Gamer gamer = (Gamer) sessionFactory.getCurrentSession().load(Gamer.class, account);
        if (gamer != null) {
            o.setGamer(gamer);
            gamer.addOperation(o);
            sessionFactory.getCurrentSession().update(gamer);
            sessionFactory.getCurrentSession().flush();
        }
        return o.getId();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Gamer> getAll() {
        return (List<Gamer>) sessionFactory.getCurrentSession().createQuery("from Gamer").list();
    }

    @Override
    @Transactional
    public boolean isAlreadyLoginUse(String login) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Gamer g where g.login = :login");
        query.setString("login", login);
        return isNotEmpty(query.list());
    }
}
