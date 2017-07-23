package com.example.Communication.Messages;

import com.example.Communication.DicyMessage;
import com.example.Communication.MessageConverter;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 26.02.2015.
 */
public class SendUsernameMessage extends DicyMessage
{
    public static final String Name = "SendUsernameMessage";

    private String username;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public SendUsernameMessage(String username)
    {
        this.username = username;
        this.contentLength = MessageConverter.usernameLength;
    }

    public SendUsernameMessage(ByteBuffer buffer, int length)
    {
        this.contentLength = length - headerLength;
        this.username = MessageConverter.byteToUsername(buffer);
    }

    @Override
    public String getName()
    {
        return Name;
    }

    public byte[] contentToByte()
    {
        return MessageConverter.usernameToByte(username);
    }
}
