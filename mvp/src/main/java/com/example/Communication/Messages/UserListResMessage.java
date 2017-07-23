package com.example.Communication.Messages;

import com.example.Communication.DicyMessage;
import com.example.Communication.MessageConverter;
import com.example.Model.User;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by Thomas on 15.01.2016.
 */
public class UserListResMessage extends DicyMessage
{
    public final static String Name = "UserListResMessage";
    private List<User> users;

    @Override
    public String getName()
    {
        return Name;
    }

    public UserListResMessage(List<User> users)
    {
        this.contentLength = users.size() * MessageConverter.userLength;
        this.users = users;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public UserListResMessage(ByteBuffer buffer, int length)
    {
        this.contentLength = length - headerLength;
        this.users = new ArrayList<>();
        while(buffer.position() < length)
        {
            users.add(MessageConverter.byteToUser(buffer));
        }
    }
    
    @Override
    protected byte[] contentToByte()
    {
        ByteBuffer buffer = ByteBuffer.allocate(contentLength);
        for(User player: users)
        {
            buffer.put(MessageConverter.userToByte(player));
        }
        return buffer.array();
    }
}
