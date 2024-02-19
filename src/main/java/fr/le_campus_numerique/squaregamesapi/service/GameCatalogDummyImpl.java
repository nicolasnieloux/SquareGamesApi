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
public class GameCatalogDummyImpl implements GameCatalog {

    TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();
    TaquinGameFactory taquinGameFactory = new TaquinGameFactory();
    ConnectFourGameFactory connectFourGameFactory = new ConnectFourGameFactory();


    @Override
    public Collection<String> getGameIdentifiers() {
        return List.of(ticTacToeGameFactory.getGameId(),taquinGameFactory.getGameId(),connectFourGameFactory.getGameId());
    }

    @Override
    public GameFactory getGameFactoryById(String id) {
        switch (id){
            case "tictactoe":
            return ticTacToeGameFactory;

            case "15 puzzle":
                return taquinGameFactory;

            case "connect4":
                return connectFourGameFactory;
        }
        return null;
    }

}
