package fr.le_campus_numerique.squaregamesapi.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.squaregamesapi.dto.GameCreationParams;

import java.util.Collection;

public interface GameService {
    Game createGame(GameCreationParams params);

    Collection<Game> getAllGames();

    Game getGame(String gameId);

    String deleteGame (String gameId);

    Object getPossibleMoves(String gameId);
}
