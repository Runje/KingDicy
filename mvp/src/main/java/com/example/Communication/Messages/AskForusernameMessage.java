package com.example.Communication.Messages;

import com.example.Communication.DicyMessage;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 26.02.2015.
 */
public class AskForusernameMessage extends DicyMessage
{
    public static final String Name = "AskForusername";

    public AskForusernameMessage()
    {
        this.contentLength = 0;
    }

    public AskForusernameMessage(ByteBuffer buffer, int length)
    {
        this.contentLength = length - headerLength;
    }

    @Override
    public String getName()
    {
        return Name;
    }

    public byte[] contentToByte()
    {
        return new byte[0];
    }
}
