package com.example.Communication.Messages;

import com.example.Communication.DicyMessage;
import com.example.Communication.MessageConverter;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 26.02.2015.
 */
public class ChallengePlayerReqMessage extends DicyMessage
{
    public static final String Name = "ChallengePlayerReqMessage";

    private String challengerId;

    public String getDefenderId()
    {
        return defenderId;
    }

    public void setDefenderId(String defenderId)
    {
        this.defenderId = defenderId;
    }

    private String defenderId;

    public String getChallengerId()
    {
        return challengerId;
    }

    public void setChallengerId(String challengerId)
    {
        this.challengerId = challengerId;
    }

    public ChallengePlayerReqMessage(String challengerId, String defenderId)
    {
        this.challengerId = challengerId;
        this.defenderId = defenderId;
        this.contentLength = 2 * MessageConverter.idLength;
    }

    public ChallengePlayerReqMessage(ByteBuffer buffer, int length)
    {
        this.contentLength = length - headerLength;
        this.challengerId = MessageConverter.byteToString(buffer, MessageConverter.idLength);
        this.defenderId = MessageConverter.byteToString(buffer, MessageConverter.idLength);
    }

    @Override
    public String getName()
    {
        return Name;
    }

    public byte[] contentToByte()
    {
        ByteBuffer buffer = ByteBuffer.allocate(contentLength);
        buffer.put(MessageConverter.stringToByte(challengerId, MessageConverter.idLength));
        buffer.put(MessageConverter.stringToByte(defenderId, MessageConverter.idLength));
        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        return bytes;
    }


}
