package com.example.Model;

import com.example.OldModel.Board;
import com.example.OldModel.BoardElement;
import com.example.OldModel.Rules;

import java.util.List;

/**
 * Created by Thomas on 13.04.2016.
 */
public class DicyGame
{
    public String getId()
    {
        return id;
    }

    private String id;
    private Board board;
    private Rules rules;
    private List<Player> players;
    private int indexOfPlayingPlayer;

    public Board getBoard()
    {
        return board;
    }

    public Rules getRules()
    {
        return rules;
    }

    public List<Player> getPlayers()
    {
        return players;
    }

    public DicyGame(Board board, Rules rules, List<Player> players, int startingPlayer, String gameId)
    {
        this.id = gameId;
        this.board = board;
        this.rules = rules;
        this.players = players;
        this.indexOfPlayingPlayer = startingPlayer;
    }

    public boolean playingPlayerPlaysOnThisDevice()
    {
        return getPlayingPlayer().isPlayingOnThisDevice();
    }

    public Player getPlayingPlayer()
    {
        assert indexOfPlayingPlayer < players.size();
        return players.get(indexOfPlayingPlayer);
    }

    public int getIndexOfPlayingPlayer()
    {
        return indexOfPlayingPlayer;
    }

    public boolean playerIsPlaying(String playerId)
    {
        return getPlayingPlayer().getId().equals(playerId);
    }

    public void next()
    {
        indexOfPlayingPlayer = (indexOfPlayingPlayer + 1) % players.size();
    }

    public void synchronizeWith(DicyGame game)
    {
        // players
        for (int i = 0; i < game.getPlayers().size(); i++)
        {
            Player myPlayer = players.get(i);
            Player otherPlayer = game.getPlayers().get(i);
            // TODO: points
        }

        // index
        indexOfPlayingPlayer = game.getIndexOfPlayingPlayer();

        // board
        for (int i = 0; i < board.getNumberOfRows(); i++)
        {
            for (int j = 0; j < board.getNumberOfColumns(); j++)
            {
                BoardElement myElement = board.getElement(i,j);
                BoardElement otherElement = game.getBoard().getElement(i,j);
                myElement.setValue(otherElement.getValue());
            }
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DicyGame dicyGame = (DicyGame) o;

        if (indexOfPlayingPlayer != dicyGame.indexOfPlayingPlayer) return false;
        if (!id.equals(dicyGame.id)) return false;
        if (!board.equals(dicyGame.board)) return false;
        return players.equals(dicyGame.players);

    }

    @Override
    public int hashCode()
    {
        int result = id.hashCode();
        result = 31 * result + board.hashCode();
        result = 31 * result + players.hashCode();
        result = 31 * result + indexOfPlayingPlayer;
        return result;
    }
}
