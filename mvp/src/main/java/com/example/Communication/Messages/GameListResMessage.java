package com.example.Communication.Messages;

import com.example.Communication.DicyMessage;
import com.example.Communication.MessageConverter;
import com.example.Model.DicyGame;
import com.example.Model.User;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Thomas on 15.01.2016.
 */
public class GameListResMessage extends DicyMessage
{
    public final static String Name = "GameListResMessage";
    private List<DicyGame> games;

    @Override
    public String getName()
    {
        return Name;
    }

    public GameListResMessage(List<DicyGame> games)
    {
        this.contentLength = games.size() * MessageConverter.dicyGameLength;
        this.games = games;
    }

    public List<DicyGame> getGames()
    {
        return games;
    }

    public GameListResMessage(ByteBuffer buffer, int length)
    {
        this.contentLength = length - headerLength;
        this.games = new ArrayList<>();
        while(buffer.position() < length)
        {
            games.add(MessageConverter.byteToDicyGame(buffer));
        }
    }
    
    @Override
    protected byte[] contentToByte()
    {
        ByteBuffer buffer = ByteBuffer.allocate(contentLength);
        for(DicyGame game: games)
        {
            buffer.put(MessageConverter.dicyGameToByte(game));
        }
        return buffer.array();
    }
}
