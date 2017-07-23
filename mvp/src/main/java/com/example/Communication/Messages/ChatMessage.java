package com.example.Communication.Messages;

import com.example.Communication.DicyMessage;
import com.example.Communication.MessageConverter;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 26.02.2015.
 */
public class ChatMessage extends DicyMessage
{
    public static final String Name = "ChatMessage";

    private String chat;

    private String authorId;

    public String getAuthorId()
    {
        return authorId;
    }

    public void setAuthorId(String authorId)
    {
        this.authorId = authorId;
    }

    public String getChat()
    {
        return chat;
    }

    public void setChat(String chat)
    {
        this.chat = chat;
    }

    public ChatMessage(String chat, String authorId)
    {
        this.authorId = authorId;
        this.chat = chat;
        this.contentLength = MessageConverter.chatLength + MessageConverter.idLength;
    }

    public ChatMessage(ByteBuffer buffer, int length)
    {
        this.contentLength = length - headerLength;
        this.chat = MessageConverter.byteToString(buffer, MessageConverter.chatLength);
        this.authorId = MessageConverter.byteToString(buffer, MessageConverter.idLength);
    }

    @Override
    public String getName()
    {
        return Name;
    }

    public byte[] contentToByte()
    {
        ByteBuffer buffer = ByteBuffer.allocate(contentLength);
        buffer.put(MessageConverter.stringToByte(chat, MessageConverter.chatLength));
        buffer.put(MessageConverter.stringToByte(authorId, MessageConverter.idLength));
        return buffer.array();
    }
}
