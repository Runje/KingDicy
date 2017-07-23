package com.example.Communication.Messages;

import com.example.Communication.DicyGameMessage;
import com.example.Communication.DicyMessage;
import com.example.Communication.MessageConverter;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 26.05.2016.
 */
public class CommandMessage extends DicyMessage
{
    public static final String Name = "CommandMessage";
    public static final String ReqGameId = "ReqGameId";

    public String getCommand()
    {
        return command;
    }

    public CommandMessage(ByteBuffer buffer, int length)
    {
        command = MessageConverter.byteToString(buffer, MessageConverter.commandLength);
    }

    public CommandMessage(String command)
    {
        super();
        this.command = command;
        contentLength = MessageConverter.commandLength;
    }

    private String command;

    @Override
    public String getName()
    {
        return Name;
    }

    @Override
    protected byte[] contentToByte()
    {
        return MessageConverter.stringToByte(command, MessageConverter.commandLength);
    }

    public static CommandMessage reqGameId()
    {
        return new CommandMessage(ReqGameId);
    }
}
