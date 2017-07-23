package com.example.Model;

/**
 * Created by Thomas on 13.04.2016.
 */
public class Player extends User
{
    private boolean playingOnThisDevice;

    public Player(User user, boolean playingOnThisDevice)
    {
        this(user.getId(), user.getName(), playingOnThisDevice);
    }

    public boolean isPlayingOnThisDevice()
    {
        return playingOnThisDevice;
    }

    public Player(String id, String name, boolean playingOnThisDevice)
    {
        super(id, name);
        this.playingOnThisDevice = playingOnThisDevice;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Player player = (Player) o;

        return isPlayingOnThisDevice() == player.isPlayingOnThisDevice();

    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (isPlayingOnThisDevice() ? 1 : 0);
        return result;
    }
}
