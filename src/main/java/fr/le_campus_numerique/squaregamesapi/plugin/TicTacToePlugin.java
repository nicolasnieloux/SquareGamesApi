package fr.le_campus_numerique.squaregamesapi.plugin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TicTacToePlugin implements GamePlugin{

    @Autowired
    private MessageSource messageSource;
    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.tictactoe.factory-id", null, locale);
    }
}
