package com.example.Communication.Messages;

import com.example.Communication.DicyGameMessage;
import com.example.Communication.MessageConverter;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 26.05.2016.
 */
public class GameCommandMessage extends DicyGameMessage
{
    public static final String Name = "GameCommandMessage";
    public static final String ReqSync = "ReqSync";

    public String getCommand()
    {
        return command;
    }

    public GameCommandMessage(ByteBuffer buffer, int length)
    {
        super(buffer, length);
        command = MessageConverter.byteToString(buffer, MessageConverter.commandLength);

    }

    public GameCommandMessage(String gameId, String command)
    {
        super(gameId);
        this.command = command;
        contentLength = MessageConverter.commandLength;
    }

    private String command;
    @Override
    protected byte[] gameContentToByte()
    {
        return MessageConverter.stringToByte(command, MessageConverter.commandLength);
    }

    @Override
    public String getName()
    {
        return Name;
    }

    public static GameCommandMessage reqSync(String gameId)
    {
        return new GameCommandMessage(gameId, ReqSync);
    }
}
