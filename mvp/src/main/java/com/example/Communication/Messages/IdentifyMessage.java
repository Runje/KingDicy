package com.example.Communication.Messages;

import com.example.Communication.DicyMessage;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 26.02.2015.
 */
public class IdentifyMessage extends DicyMessage
{
    public static final String Name = "IdentifyMessage";

    public IdentifyMessage()
    {
        this.contentLength = 0;
    }

    public IdentifyMessage(ByteBuffer buffer, int length)
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
