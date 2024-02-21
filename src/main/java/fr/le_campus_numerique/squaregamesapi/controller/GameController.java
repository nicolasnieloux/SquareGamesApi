package fr.le_campus_numerique.squaregamesapi.controller;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.squaregamesapi.dto.GameCreationParams;
import fr.le_campus_numerique.squaregamesapi.dto.GameDTO;
import fr.le_campus_numerique.squaregamesapi.repository.GameCatalog;
import fr.le_campus_numerique.squaregamesapi.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class GameController {


    @Autowired
    private GameService gameService;

    @Autowired
    private GameCatalog gameCatalogDummy;


    private GameDTO gameToDto(Game entry) {
        return new GameDTO(entry.getId().toString(), entry.getFactoryId());
    }

    private List<GameDTO> DtoToList(Collection<Game> games) {
        return games.stream()
                .map(this::gameToDto)
                .toList();
    }

    @GetMapping("/gameCatalog")
    public Collection<String> getGameCatalog() {
        return gameCatalogDummy.getGameIdentifiers();
    }

    @GetMapping("/games")
    public List<GameDTO> displayAllGAmes() {
        return DtoToList(gameService.getAllGames());
    }

    @PostMapping("/games")
    public GameDTO createGame(@RequestBody GameCreationParams params) {
        return gameToDto(gameService.createGame(params));
    }

    @GetMapping("/games/{gameId}")
    public GameDTO getGame(@PathVariable String gameId) {
        return gameToDto(gameService.getGame(gameId));
    }

    @DeleteMapping("/games/{gameId}/")
    public String deleteGame(@PathVariable String gameId) {
        return gameService.deleteGame(gameId);
    }

    @GetMapping("/games/{gameId}/possiblemoves")
    public Object getPossibleMoves(@PathVariable String gameId) {
        return gameService.getPossibleMoves(gameId);
    }


}
