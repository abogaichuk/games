package app.games.controller;

import app.games.db.dao.GamerDAO;
import app.games.db.model.Gamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static app.games.commons.utils.GamesUtils.isNotEmpty;

/**
 * @author abogaichuk
 */
@Controller
public class WelcomeController {
    @Autowired
    private GamerDAO gamerDAO;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private Gamer gamer;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String hello(ModelMap model) {
        List<Gamer> gamers = gamerDAO.getAll();
        if (isNotEmpty(gamers)) {
            model.addAttribute("gamers", gamers);
        } else {
            //Gamer gamer = new Gamer("test", "test");
            gamerDAO.addGamer(gamer);
            model.addAttribute("gamers", Collections.singletonList(gamer));
        }
        return "hello";
    }

    @RequestMapping(value = "/delete/{account}", method = RequestMethod.GET)
    public String delete(@PathVariable long account) {
        gamerDAO.deleteGamer(account);
        return "redirect:/";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newEmployee(ModelMap model) {
        model.addAttribute("gamer", new Gamer());
        return "registration";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String add(@Valid Gamer gamer, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            return "registration";
        }
        if (gamerDAO.isAlreadyLoginUse(gamer.getLogin())) {
            FieldError loginError = new FieldError("gamer", "login", gamer.getLogin()+" "
                    +messageSource.getMessage("alreadyUse", null, locale));
            result.addError(loginError);
            return "registration";
        }
        gamerDAO.addGamer(gamer);
        return "redirect:/";
    }
}
