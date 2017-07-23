package com.example.Communication.Messages;

import com.example.Communication.DicyMessage;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 15.01.2016.
 */
public class GameListReqMessage extends DicyMessage
{
    public final static String Name = "GameListReqMessage";
    @Override
    public String getName()
    {
        return Name;
    }

    public GameListReqMessage()
    {
        this.contentLength = 0;
    }

    public GameListReqMessage(ByteBuffer buffer, int length)
    {
        this.contentLength = length - headerLength;
    }

    @Override
    protected byte[] contentToByte()
    {
        return new byte[0];
    }
}
