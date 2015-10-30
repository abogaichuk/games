package app.games.controller;

import app.games.db.dao.GamerDAO;
import app.games.db.model.Gamer;
import app.games.db.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * @author abogaichuk
 */
@Controller
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private GamerDAO gamerDAO;

    @RequestMapping(value = "/{account}", method = RequestMethod.GET)
    public String info(@PathVariable long account, ModelMap model) {
        try {
            Gamer gamer = gamerDAO.getById(account);
            Long balance = gamerDAO.getTotalBalance(gamer.getAccount());
            model.addAttribute("gamer", gamer);
            model.addAttribute("balance", balance);
            model.addAttribute("operations", gamer.getOperations());
            return "info";
        } catch (Exception e) {
            model.addAttribute("error", "gamer with account "+account+" does not exist");
            return "error";
        }
    }

    @RequestMapping(value = "/deposit/{account}", method = RequestMethod.GET)
    public String deposit(@PathVariable long account, ModelMap model) {
        Gamer gamer = gamerDAO.getById(account);
        setDepositOperation(account);
        model.addAttribute("gamer", gamer);
        model.addAttribute("balance", 1000);
        return "info";
    }

    private void setDepositOperation(long account) {
        Operation operation = new Operation();
        operation.setWin(true);
        operation.setTransactionDate(new Date());
        operation.setValue(1000);
        operation.setBalance(1000);
        gamerDAO.addOperation(operation, account);
    }
}
