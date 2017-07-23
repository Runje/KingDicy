package com.example.Communication;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 14.02.2015.
 */
public abstract class DicyMessage implements com.example.Message
{
    public static final String ServerId = "DICY_SERVER_ID";
    protected final int headerLength = MessageConverter.sizeLength + MessageConverter.usernameLength + MessageConverter.idLength + MessageConverter.idLength;
    protected int contentLength;
    protected String fromId;
    protected String toId;

    public abstract String getName();

    public int getTotalLength()
    {
        return headerLength + contentLength;
    }

    protected byte[] lengthAndNameToByte()
    {
        int size = MessageConverter.sizeLength + MessageConverter.usernameLength;
        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.putInt(getTotalLength());
        buffer.put(MessageConverter.stringToByte(getName(), MessageConverter.usernameLength));
        return buffer.array();
    }

    protected abstract byte[] contentToByte();

    public byte[] toByte()
    {
        ByteBuffer buffer = ByteBuffer.allocate(getTotalLength());
        buffer.put(lengthAndNameToByte());
        buffer.put(MessageConverter.stringToByte(fromId, MessageConverter.idLength));
        buffer.put(MessageConverter.stringToByte(toId, MessageConverter.idLength));
        buffer.put(contentToByte());
        return buffer.array();
    }

    public String getFromId()
    {
        return fromId;
    }

    public void setFromId(String fromId)
    {
        this.fromId = fromId;
    }

    public String getToId()
    {
        return toId;
    }

    public void setToId(String toId)
    {
        this.toId = toId;
    }

    public ByteBuffer getBuffer()
    {
        System.out.println("Get Buffer");
        ByteBuffer buffer = ByteBuffer.allocate(getTotalLength());
        buffer.put(lengthAndNameToByte());
        buffer.put(MessageConverter.stringToByte(fromId, MessageConverter.idLength));
        buffer.put(MessageConverter.stringToByte(toId, MessageConverter.idLength));
        buffer.put(contentToByte());
        buffer.flip();
        return buffer;
    }
}
