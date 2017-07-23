package com.example.Communication.Messages;

import com.example.Model.DicyGame;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 26.05.2016.
 */
public class SyncGameMessage extends StartGameMessage
{
    public static final String Name = "SyncGameMessage";
    public SyncGameMessage(DicyGame game, String gameId)
    {
        super(game, gameId);
    }

    public SyncGameMessage(ByteBuffer buffer, int length)
    {
        super(buffer, length);
    }

    public String getName()
    {
        return Name;
    }
}
