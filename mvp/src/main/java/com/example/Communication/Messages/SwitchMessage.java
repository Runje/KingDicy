package com.example.Communication.Messages;

import com.example.Communication.DicyGameMessage;
import com.example.Communication.DicyMessage;
import com.example.Communication.MessageConverter;
import com.example.Model.SwitchType;
import com.example.OldModel.Coords;
import com.example.OldModel.Logger;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 17.02.2015.
 */
public class SwitchMessage extends DicyGameMessage
{
    public static final String Name = "SwitchMessage";
    private SwitchType switchType;
    private Coords second;
    private Coords first;
    private String requesterId;

    public Coords getSecond()
    {
        return second;
    }

    public Coords getFirst()
    {
        return first;
    }

    public SwitchMessage(Coords first, Coords second, String gameId, SwitchType switchType, String requesterId)
    {
        super(gameId);
        this.first = first;
        this.second = second;
        this.switchType = switchType;
        this.requesterId = requesterId;
        this.contentLength = 2 * MessageConverter.coordsLength + MessageConverter.switchTypeLength + MessageConverter.idLength;
    }

    public SwitchMessage(ByteBuffer buffer, int length)
    {
        super(buffer, length);
        first = MessageConverter.byteToCoords(buffer);
        second = MessageConverter.byteToCoords(buffer);
        switchType = MessageConverter.byteToSwitchType(buffer);
        requesterId = MessageConverter.byteToId(buffer);
    }

    @Override
    public String getName()
    {
        return Name;
    }

    public SwitchType getSwitchType()
    {
        return switchType;
    }

    @Override
    protected byte[] gameContentToByte()
    {
        ByteBuffer buffer = ByteBuffer.allocate(contentLength);
        buffer.put(MessageConverter.coordsToByte(first));
        buffer.put(MessageConverter.coordsToByte(second));
        buffer.put(MessageConverter.switchTypeToByte(switchType));
        buffer.put(MessageConverter.idToByte(requesterId));
        return buffer.array();
    }

    public String getRequesterId()
    {
        return requesterId;
    }


}
