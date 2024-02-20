package fr.le_campus_numerique.squaregamesapi.repository;

import fr.le_campus_numerique.square_games.engine.GameFactory;

import java.util.Collection;

public interface GameCatalog {
    Collection<String> getGameIdentifiers();

    GameFactory getGameFactoryById(String id);
}
