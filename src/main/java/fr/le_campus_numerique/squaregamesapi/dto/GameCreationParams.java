package fr.le_campus_numerique.squaregamesapi.dto;

public class GameCreationParams {

    private String typeGame;
    private int playerCount;
    private int boardSize;


    public String getTypeGame() {
        return typeGame;
    }


    public int getPlayerCount() {
        return playerCount;
    }

    public int getBoardSize() {
        return boardSize;
    }

}
