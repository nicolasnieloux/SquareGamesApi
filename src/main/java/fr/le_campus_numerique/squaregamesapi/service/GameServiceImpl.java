package fr.le_campus_numerique.squaregamesapi.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.squaregamesapi.dto.GameCreationParams;
import fr.le_campus_numerique.squaregamesapi.repository.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
class GameServiceImpl implements GameService{
    @Autowired
    private GameCatalog gameCatalog;


    private Map<String, Game> gameMap = new HashMap<>();

    public Game createGame(GameCreationParams params){
        GameFactory gameFactory = gameCatalog.getGameFactoryById(params.getTypeGame());
        Game game = gameFactory.createGame(params.getPlayerCount(), params.getBoardSize());
        gameMap.put(game.getId().toString(), game);
        return game;
    }

    public Collection<Game> getAllGames(){
        return gameMap.values();
    }

    public Game getGame(String gameId){
        return gameMap.get(gameId);
    }

    public String deleteGame (String gameId){
        gameMap.remove(gameId);
        return "Le jeu a été supprimé";
    }

    public Object getPossibleMoves(String gameId){
        return gameMap.get(gameId).getRemainingTokens();
    }

}
