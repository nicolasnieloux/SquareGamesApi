package fr.le_campus_numerique.squaregamesapi;

import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class GameCatalogDummyImpl implements GameCatalog{

    TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();

    @Override
    public Collection<String> getGameIdentifiers() {
        return List.of(ticTacToeGameFactory.getGameId());
    }
}
