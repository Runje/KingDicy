package com.example.Communication.Messages;

import com.example.Communication.DicyGameMessage;

import java.nio.ByteBuffer;

/**
 * Created by thomas on 22.02.15.
 */
public class NextMessage extends DicyGameMessage
{
    public static final String Name = "NextMessage";

    public NextMessage(String gameId)
    {
        super(gameId);
        contentLength = 0;
    }

    public NextMessage(ByteBuffer buffer, int length)
    {
        super(buffer, length);
        this.contentLength = length - headerLength;
    }

    @Override
    public String getName()
    {
        return Name;
    }

    @Override
    protected byte[] gameContentToByte()
    {
        return new byte[0];
    }
}
