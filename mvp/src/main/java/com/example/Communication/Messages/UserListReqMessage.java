package com.example.Communication.Messages;

import com.example.Communication.DicyMessage;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 15.01.2016.
 */
public class UserListReqMessage extends DicyMessage
{
    public final static String Name = "UserListReqMessage";
    @Override
    public String getName()
    {
        return Name;
    }

    public UserListReqMessage()
    {
        this.contentLength = 0;
    }

    public UserListReqMessage(ByteBuffer buffer, int length)
    {
        this.contentLength = length - headerLength;
    }

    @Override
    protected byte[] contentToByte()
    {
        return new byte[0];
    }
}
