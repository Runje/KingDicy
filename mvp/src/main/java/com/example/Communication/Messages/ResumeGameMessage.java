package com.example.Communication.Messages;

import com.example.Communication.DicyMessage;
import com.example.Communication.MessageConverter;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 26.02.2015.
 */
public class ResumeGameMessage extends DicyMessage
{
    public static final String Name = "ResumeMessage";

    private String gameId;

    public String getGameId()
    {
        return gameId;
    }

    public void setGameId(String gameId)
    {
        this.gameId = gameId;
    }

    public ResumeGameMessage(String gameId)
    {
        this.gameId = gameId;
        this.contentLength = MessageConverter.idLength;
    }

    public ResumeGameMessage(ByteBuffer buffer, int length)
    {
        this.contentLength = length - headerLength;
        this.gameId = MessageConverter.byteToString(buffer, MessageConverter.idLength);
    }

    @Override
    public String getName()
    {
        return Name;
    }

    public byte[] contentToByte()
    {
        ByteBuffer buffer = ByteBuffer.allocate(contentLength);
        buffer.put(MessageConverter.stringToByte(gameId, MessageConverter.idLength));
        return buffer.array();
    }
}
