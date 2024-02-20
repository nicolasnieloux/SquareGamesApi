package fr.le_campus_numerique.squaregamesapi.service;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGame;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import fr.le_campus_numerique.squaregamesapi.dto.GameCreationParams;
import fr.le_campus_numerique.squaregamesapi.repository.GameCatalog;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
class GameCatalogDummyImpl implements GameCatalog {

    TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();
    TaquinGameFactory taquinGameFactory = new TaquinGameFactory();
    ConnectFourGameFactory connectFourGameFactory = new ConnectFourGameFactory();

    public GameFactory getGameFactoryById(String id) {
        return switch (id) {
            case "tictactoe" -> ticTacToeGameFactory;
            case "15 puzzle" -> taquinGameFactory;
            case "connect4" -> connectFourGameFactory;
            default -> null;
        };
    }
    @Override
    public Collection<String> getGameIdentifiers() {
        return List.of(ticTacToeGameFactory.getGameFactoryId(),taquinGameFactory.getGameFactoryId(),connectFourGameFactory.getGameFactoryId());
    }


}
