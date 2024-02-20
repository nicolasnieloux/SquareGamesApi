package fr.le_campus_numerique.squaregamesapi.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.squaregamesapi.dto.GameCreationParams;

public interface GameService {
    Game createGame(GameCreationParams params);

}
