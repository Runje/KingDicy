package com.example.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas on 03.04.2016.
 */
public class OnlineLobby
{
    private Map<String, User> users;

    private List<String> chat = new ArrayList<>();
    public OnlineLobby()
    {
        users = new HashMap<>();
    }

    public Map<String, User> getUsers()
    {
        return users;
    }

    public OnlineLobby(Map<String, User> users)
    {
        this.users = users;
    }

    public void setUsers(Map<String, User> users)
    {
        this.users = users;
    }

    public void setUsers(List<User> userList)
    {
        users.clear();
        for(User user : userList)
        {
            users.put(user.getId(), user);
        }
    }

    public List<User> getUsersAsList()
    {
        return new ArrayList<>(users.values());
    }

    public List<String> getChat()
    {
        return chat;
    }

    public void setChat(List<String> chat)
    {
        this.chat = chat;
    }

    public void addChat(String chat)
    {
        this.chat.add(chat);
    }

    public User getUser(String id)
    {
        return users.get(id);
    }
}
