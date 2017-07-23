package com.example.Communication.Messages;

import com.example.Communication.DicyGameMessage;
import com.example.Communication.DicyMessage;
import com.example.Communication.MessageConverter;
import com.example.Model.DicyGame;
import com.example.OldModel.Board;
import com.example.OldModel.Logger;
import com.example.OldModel.Rules;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 14.02.2015.
 */
public class StartGameMessage extends DicyGameMessage
{
    public static final String Name = "StartGameMessage";
    private DicyGame game;

    public StartGameMessage(DicyGame game, String gameId)
    {
        super(gameId);
        this.game = game;
        this.contentLength = MessageConverter.dicyGameLength;
    }

    public StartGameMessage(ByteBuffer buffer, int length)
    {
        super(buffer, length);
        game = MessageConverter.byteToDicyGame(buffer);
    }

    public String getName()
    {
        return Name;
    }

    @Override
    protected byte[] gameContentToByte()
    {
        ByteBuffer buffer = ByteBuffer.allocate(contentLength);
        buffer.put(MessageConverter.dicyGameToByte(game));
        return buffer.array();
    }

    public DicyGame getGame()
    {
        return game;
    }
}
