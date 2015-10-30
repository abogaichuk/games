package app.games.validator;

import app.games.game.card.Bet;
import app.games.game.card.BlackJackProcess;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author abogaichuk
 */
public class BlackJackValidator implements Validator {
    @Override
    public boolean supports(Class<?> paramClass) {
        return BlackJackProcess.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rate", "rate.required");

        BlackJackProcess process = (BlackJackProcess) obj;
        int rate = process.getRate();
        long balance = process.getBalance();
        if(rate <= 0 || rate > balance){
            errors.rejectValue("rate", "badValue", new Object[]{"'rate'"}, "rate can't be <= 0, and > balance");
        }
    }
}
