package fr.le_campus_numerique.squaregamesapi.plugin;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;

import java.util.Locale;

public interface GamePlugin {
    String getName(Locale locale);

    GameFactory getGameFactory();
}
