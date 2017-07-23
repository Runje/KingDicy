package com.example.Communication;

import com.example.Communication.Messages.AskForusernameMessage;
import com.example.Communication.Messages.ChallengePlayerReqMessage;
import com.example.Communication.Messages.ChallengePlayerResMessage;
import com.example.Communication.Messages.ChatMessage;
import com.example.Communication.Messages.CommandMessage;
import com.example.Communication.Messages.GameCommandMessage;
import com.example.Communication.Messages.GameListReqMessage;
import com.example.Communication.Messages.GameListResMessage;
import com.example.Communication.Messages.IdentifyMessage;
import com.example.Communication.Messages.NextMessage;
import com.example.Communication.Messages.ResumeGameMessage;
import com.example.Communication.Messages.SendUsernameMessage;
import com.example.Communication.Messages.StartGameMessage;
import com.example.Communication.Messages.SwitchMessage;
import com.example.Communication.Messages.SyncGameMessage;
import com.example.Communication.Messages.UserListReqMessage;
import com.example.Communication.Messages.UserListResMessage;
import com.example.OldModel.Logger;

import java.nio.ByteBuffer;


/**
 * Created by Thomas on 14.02.2015.
 */
public class MessageParser
{
    public static DicyMessage parse(ByteBuffer buffer, int length)
    {
        String name = MessageConverter.byteToString(buffer, MessageConverter.usernameLength);
        String fromId = MessageConverter.byteToString(buffer, MessageConverter.idLength);
        String toId = MessageConverter.byteToString(buffer, MessageConverter.idLength);

        // TODO: parse header in constructor of DicyMessage!
        Logger.i("Parsing Message " + name + ", from " + fromId + " to " + toId);

        DicyMessage msg = null;
        switch (name)
        {
            case IdentifyMessage.Name:
                msg = new IdentifyMessage(buffer, length);
                break;
            case AskForusernameMessage.Name:
                msg = new AskForusernameMessage(buffer, length);
                break;
            case SendUsernameMessage.Name:
                msg = new SendUsernameMessage(buffer, length);
                break;
            case UserListResMessage.Name:
                msg = new UserListResMessage(buffer, length);
                break;
            case UserListReqMessage.Name:
                msg = new UserListReqMessage(buffer, length);
                break;
            case ChatMessage.Name:
                msg = new ChatMessage(buffer, length);
                break;
            case ChallengePlayerReqMessage.Name:
                msg = new ChallengePlayerReqMessage(buffer, length);
                break;
            case ChallengePlayerResMessage.Name:
                msg = new ChallengePlayerResMessage(buffer, length);
                break;
            case StartGameMessage.Name:
                msg = new StartGameMessage(buffer, length);
                break;
            case SwitchMessage.Name:
                msg = new SwitchMessage(buffer, length);
                break;
            case GameListReqMessage.Name:
                msg = new GameListReqMessage(buffer, length);
                break;
            case GameListResMessage.Name:
                msg = new GameListResMessage(buffer, length);
                break;
            case ResumeGameMessage.Name:
                msg = new ResumeGameMessage(buffer, length);
                break;
            case SyncGameMessage.Name:
                msg = new SyncGameMessage(buffer, length);
                break;
            case NextMessage.Name:
                msg = new NextMessage(buffer, length);
                break;
            case GameCommandMessage.Name:
                msg = new GameCommandMessage(buffer, length);
                break;
            case CommandMessage.Name:
                msg = new CommandMessage(buffer, length);
                break;
            default:
                Logger.e("Unknown message: " + msg.getName());
                break;
        }

        msg.setToId(toId);
        msg.setFromId(fromId);
        return msg;
    }
}
