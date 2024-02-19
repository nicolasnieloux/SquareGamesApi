package fr.le_campus_numerique.squaregamesapi.controller;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.squaregamesapi.dto.GameCreationParams;
import fr.le_campus_numerique.squaregamesapi.dto.GameDTO;
import fr.le_campus_numerique.squaregamesapi.repository.GameCatalog;
import fr.le_campus_numerique.squaregamesapi.service.GameCatalogDummyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class GameController {

    @Autowired
    GameCatalog gameCatalogDummy;
    Map<String, Game> gameMap = new HashMap<>();

    @GetMapping("/gameCatalog")
    public Collection<String> getGameCatalog() {

        return gameCatalogDummy.getGameIdentifiers();
    }

    @PostMapping("/games")
    public GameDTO createGame(@RequestBody GameCreationParams params) {

        GameFactory gameFactory = gameCatalogDummy.getGameFactoryById(params.getTypeGame());
        Game game = gameFactory.createGame(params.getPlayerCount(), params.getBoardSize());
        String id = UUID.randomUUID().toString();
        gameMap.put(id, game);
        return new GameDTO( id, game.getFactoryId());

    }

    @GetMapping("/games/{gameId}")
    public Object getGame(@PathVariable String gameId) {
// TODO - actually get and return game with id 'gameId'
        Game game = gameMap.get(gameId);
        return game;
    }
}
