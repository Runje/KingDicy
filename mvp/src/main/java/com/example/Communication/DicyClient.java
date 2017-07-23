package com.example.Communication;

import com.example.Communication.Messages.IdentifyMessage;
import com.example.Message;
import com.example.OldModel.Logger;
import com.example.OnConnectionChangedListener;
import com.example.OnReceiveBytesListener;
import com.example.SocketChannelTCPClient;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 26.03.2016.
 */
public class DicyClient extends SocketChannelTCPClient
{
    public static final int SERVER_PORT = 4400;
    //public static final String ipaddress = "runje.ddns.net";
    public static final String ipaddress = "192.168.0.4";
    private final String id;
    private DicyMessageReceivedListener dicyMessageReceivedListener;
    private int connectionUsers = 0;

    public DicyMessageReceivedListener getDicyMessageReceivedListener()
    {
        return dicyMessageReceivedListener;
    }

    public void setDicyMessageReceivedListener(DicyMessageReceivedListener dicyMessageReceivedListener)
    {
        this.dicyMessageReceivedListener = dicyMessageReceivedListener;

    }


    public void stop()
    {
        connectionUsers--;
        Logger.i("ConnectionUsers: " + connectionUsers);

        if (connectionUsers == 0)
        {
            Logger.i("Disconnecting");
            super.disconnect();
        }
    }


    public void start()
    {
        connectionUsers++;
        Logger.i("ConnectionUsers: " + connectionUsers);
        if (!isConnected())
        {
            Logger.i("Connecting");
            super.connect();
        }
    }

    public DicyClient(int port, String ip, String id)
    {
        super(port, ip);
        this.id = id;
        Logger.i("Add ConnectionListener from DicyClient");
        addOnConnectionChangedListener(new OnConnectionChangedListener()
        {
            @Override
            public void onConnectionChanged(boolean b)
            {
                if (b)
                {
                    // identify to server
                    sendDicyMessage(new IdentifyMessage());
                    Logger.i("Sent identifying message");
                }
                else
                {
                    Logger.i("Disconnected");
                }
            }
        });

        addOnReceiveBytesListener(new OnReceiveBytesListener()
        {
            @Override
            public void onReceiveBytes(byte[] bytes)
            {
                Logger.i("Received bytes");
                DicyMessage msg = MessageParser.parse(ByteBuffer.wrap(bytes), bytes.length);
                if (msg != null)
                {
                    if (dicyMessageReceivedListener != null)
                    {
                        dicyMessageReceivedListener.onReceivedDicyMessage(msg);
                    }
                } else
                {
                    Logger.e("Parsed message is null");
                }
            }
        });
    }

    public DicyClient(String id)
    {
        this(SERVER_PORT, ipaddress, id);
    }

    public void sendDicyMessage(DicyMessage message)
    {
        message.setFromId(id);
        message.setToId(DicyMessage.ServerId);
        sendMessage(message);
    }

    @Override
    public void sendMessage(Message msg)
    {
        System.out.println("Send Normal message");
        super.sendMessage(msg);
    }
}
