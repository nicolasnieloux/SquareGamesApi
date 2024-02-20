package fr.le_campus_numerique.squaregamesapi.controller;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.squaregamesapi.dto.GameCreationParams;
import fr.le_campus_numerique.squaregamesapi.dto.GameDTO;
import fr.le_campus_numerique.squaregamesapi.repository.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class GameController {

    @Autowired
    GameCatalog gameCatalogDummy;
    Map<String, Game> gameMap = new HashMap<>();

    private static GameDTO gameToDto(Game entry) {
        return new GameDTO(entry.getId().toString(), entry.getFactoryId());
    }

    @GetMapping("/gameCatalog")
    public Collection<String> getGameCatalog() {

        return gameCatalogDummy.getGameIdentifiers();
    }

    @GetMapping("/games")

    public List<GameDTO> displayAllGAmes() {
        List<GameDTO> gameDTOList = new ArrayList<>();

        // Utilisation de Stream pour mapper les entrées de la map à des DTO
        gameMap.values().stream()
                .map(GameController::gameToDto)
                .forEach(gameDTOList::add);

        return gameDTOList;
    }

    @PostMapping("/games")
    public GameDTO createGame(@RequestBody GameCreationParams params) {

        GameFactory gameFactory = gameCatalogDummy.getGameFactoryById(params.getTypeGame());
        Game game = gameFactory.createGame(params.getPlayerCount(), params.getBoardSize());
        gameMap.put(game.getId().toString(), game);
        return gameToDto(game);

    }

    @GetMapping("/games/{gameId}")
    public GameDTO getGame(@PathVariable String gameId) {
// TODO - actually get and return game with id 'gameId'
        Game game = gameMap.get(gameId);
        return gameToDto(game);
    }

    @DeleteMapping("/games/{gameId}/")
    public String deleteGame(@PathVariable String gameId) {
        gameMap.remove(gameId);
        return "Le jeu a été supprimé";
    }

    @GetMapping("/games/{gameId}/possiblemoves")
    public Object getPossibleMoves(@PathVariable String gameId) {

        return gameMap.get(gameId).getRemainingTokens();
    }


}
