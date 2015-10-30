package app.games.controller;

import app.games.db.dao.GamerDAO;
import app.games.db.model.Gamer;
import app.games.db.model.Operation;
import app.games.game.card.BlackJack;
import app.games.game.card.BlackJackProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * @author abogaichuk
 */
@Controller
@RequestMapping("/blackjack")
@SessionAttributes(value = "blackJackProcess", types = BlackJackProcess.class)
public class BlackJackController {

    @Autowired
    private GamerDAO gamerDAO;

    @Autowired
    @Qualifier("blackJackValidator")
    private Validator validator;

    @InitBinder("blackJackProcess")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/play/{account}", method = RequestMethod.GET)
    public String play(@PathVariable long account, ModelMap model) {
        try {
            Gamer gamer = gamerDAO.getById(account);
            Long balance = gamerDAO.getTotalBalance(gamer.getAccount());
            if (balance <= 0)
                return "redirect:/info/"+account;
            model.addAttribute("blackJackProcess", new BlackJackProcess(account, balance, new BlackJack()));
            return "blackjack";
        } catch (Exception e) {
            model.addAttribute("error", "gamer with account "+account+" does not exist");
            return "error";
        }
    }

    @RequestMapping(value = "/play", method = RequestMethod.POST)
    public String setBet(@ModelAttribute("blackJackProcess") @Validated BlackJackProcess blackJackProcess,
                         BindingResult result) {
        if (!result.hasErrors())
            blackJackProcess.play();
        return "blackjack";
    }

    @RequestMapping(value = "/hit", method = RequestMethod.POST)
    public String hit(@ModelAttribute("blackJackProcess") BlackJackProcess blackJackProcess) {
        if (blackJackProcess == null)
            return "redirect:/";
        if (blackJackProcess.hit()) {
            setOperation(blackJackProcess.getAccount(), false, blackJackProcess.getRate(), blackJackProcess.getBalance());
            blackJackProcess.setResultMessage(getMessage(false)+blackJackProcess.getRate()+"$   ");
        }
        return "blackjack";
    }

    @RequestMapping(value = "/stand", method = RequestMethod.POST)
    public String stand(@ModelAttribute("blackJackProcess") BlackJackProcess blackJackProcess) {
        if (blackJackProcess == null)
            return "redirect:/";
        if (blackJackProcess.stand()) {
            setOperation(blackJackProcess.getAccount(), true, blackJackProcess.getRate(), blackJackProcess.getBalance());
            blackJackProcess.setResultMessage(getMessage(true)+blackJackProcess.getRate()+"$   ");
        } else {
            endProcessWithOutBust(blackJackProcess);
        }
        return "blackjack";
    }

    private void endProcessWithOutBust(BlackJackProcess blackJackProcess) {
        int win = blackJackProcess.getWin();
        if (win == 0) {
            blackJackProcess.setResultMessage("no winners!!");
        } else if (win < 0) {
            setOperation(blackJackProcess.getAccount(), false, win, blackJackProcess.getBalance());
            blackJackProcess.setResultMessage(getMessage(false)+win+"$   ");
        } else {
            setOperation(blackJackProcess.getAccount(), true, win, blackJackProcess.getBalance());
            blackJackProcess.setResultMessage(getMessage(true)+win+"$   ");
        }
    }

    private String getMessage(boolean isWinner) {
        return isWinner ? "congratulation, u win " : "u lose ";
    }

    private void setOperation(long account, boolean win, int value, long balance) {
        Operation operation = new Operation();
        operation.setWin(win);
        operation.setTransactionDate(new Date());
        operation.setValue(value);
        operation.setBalance(balance - value);
        gamerDAO.addOperation(operation, account);
    }
}
