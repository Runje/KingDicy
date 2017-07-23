package com.example.Communication.Messages;

import com.example.Communication.MessageConverter;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 09.04.2016.
 */
public class ChallengePlayerResMessage extends ChallengePlayerReqMessage
{
    public static final String Name = "ChallengePlayerResMessage";

    private boolean accepted;

    public boolean isAccepted()
    {
        return accepted;
    }

    public void setAccepted(boolean accepted)
    {
        this.accepted = accepted;
    }

    public ChallengePlayerResMessage(String challenger, String defender, boolean accepted)
    {
        super(challenger, defender);
        this.accepted = accepted;
        this.contentLength = 2 * MessageConverter.idLength + 1;
    }

    public ChallengePlayerResMessage(ChallengePlayerReqMessage msg, boolean accepted)
    {
        this(msg.getChallengerId(), msg.getDefenderId(), accepted);
    }

    public ChallengePlayerResMessage(ByteBuffer buffer, int length)
    {
        super(buffer, length);
        accepted = MessageConverter.byteToBoolean(buffer.get());
    }

    @Override
    public byte[] contentToByte()
    {
        ByteBuffer byteBuffer = ByteBuffer.allocate(contentLength);
        byteBuffer.put(super.contentToByte());
        byteBuffer.put(MessageConverter.booleanToByte(accepted));
        return byteBuffer.array();
    }

    @Override
    public String getName()
    {
        return Name;
    }
}
