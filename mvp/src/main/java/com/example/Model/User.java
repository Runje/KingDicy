package com.example.Model;

/**
 * Created by Thomas on 01.04.2016.
 */
public class User
{
    protected String name;
    protected String id;

    public User(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return name == null || name.isEmpty() ? id : name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!name.equals(user.name)) return false;
        return id.equals(user.id);

    }

    @Override
    public int hashCode()
    {
        int result = name.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
