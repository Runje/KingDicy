package com.example.thomas.dicyplayground.util;

import android.content.Intent;

import com.example.Communication.MessageConverter;
import com.example.Model.DicyGame;
import com.example.OldModel.Board;
import com.example.OldModel.Rules;

import java.nio.ByteBuffer;

/**
 * Created by Thomas on 09.04.2016.
 */
public class ActivityUtils
{

    public static final String Board = "Board";
    public static final String Rules = "Rules";
    public static final String DicyGame = "DicyGame";
    private static String GameId = "GameId";
    private static String IsOnline = "IsOnline";

    public static void putToIntent(Intent intent, Board board)
    {
        intent.putExtra(ActivityUtils.Board, MessageConverter.boardToByte(board));
    }

    public static void putGameIdToIntent(Intent intent, String gameId)
    {
        intent.putExtra(ActivityUtils.GameId, MessageConverter.stringToByte(gameId, MessageConverter.idLength));
    }

    public static void putIsOnlineToIntent(Intent intent, boolean isOnline)
    {
        intent.putExtra(IsOnline, isOnline);
    }

    public static void putToIntent(Intent intent, Rules rules)
    {
        intent.putExtra(ActivityUtils.Rules, MessageConverter.rulesToByte(rules));
    }

    public static Board getBoardFromIntent(Intent intent)
    {
        return MessageConverter.byteToBoard(ByteBuffer.wrap(intent.getByteArrayExtra(Board)));
    }

    public static Rules getRulesFromIntent(Intent intent)
    {
        return MessageConverter.byteToRules(ByteBuffer.wrap(intent.getByteArrayExtra(Rules)));
    }

    public static void putToIntent(Intent intent, DicyGame game)
    {
        intent.putExtra(ActivityUtils.DicyGame, MessageConverter.dicyGameToByte(game));
    }

    public static DicyGame getGameFromIntent(Intent intent)
    {
        return MessageConverter.byteToDicyGame(ByteBuffer.wrap(intent.getByteArrayExtra(DicyGame)));
    }

    public static String getGameIdFromIntent(Intent intent)
    {
        return MessageConverter.byteToString(ByteBuffer.wrap(intent.getByteArrayExtra(GameId)), MessageConverter.idLength);
    }

    public static boolean getIsOnlineFromIntent(Intent intent)
    {
        return intent.getBooleanExtra(IsOnline, false);
    }
}
