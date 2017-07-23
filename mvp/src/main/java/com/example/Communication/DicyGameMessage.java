package com.example.Communication;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 14.04.2016.
 */
public abstract class DicyGameMessage extends DicyMessage
{
    protected String gameId;

    public DicyGameMessage(ByteBuffer buffer, int length)
    {
        this.contentLength = length - headerLength;
        gameId = MessageConverter.byteToString(buffer, MessageConverter.idLength);
    }

    public DicyGameMessage(String gameId)
    {
        this.gameId = gameId;
    }

    public String getGameId()
    {
        return gameId;
    }

    public void setGameId(String gameId)
    {
        this.gameId = gameId;
    }

    @Override
    public int getTotalLength()
    {
        return super.getTotalLength() + MessageConverter.idLength;
    }

    @Override
    protected final byte[] contentToByte()
    {
        ByteBuffer buffer = ByteBuffer.allocate(MessageConverter.idLength + contentLength);
        buffer.put(MessageConverter.stringToByte(gameId, MessageConverter.idLength));
        buffer.put(gameContentToByte());
        return buffer.array();
    }

    protected abstract byte[] gameContentToByte();
}
