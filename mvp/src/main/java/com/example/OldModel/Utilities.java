package com.example.OldModel;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Thomas on 05.10.2014.
 */
public class Utilities
{
    private static int id;

    /**
     * Checks if a number is a square
     *
     * @param n number to check.
     * @return Whether n is a square
     */
    public static boolean isPerfectSquare(long n)
    {
        if (n < 0)
            return false;

        long tst = (long) (Math.sqrt(n) + 0.5);
        return tst * tst == n;
    }

    public static int getPointsFrom(ArrayList<PointElement> pointElements)
    {
        int points = 0;
        for (PointElement e : pointElements)
        {
            points += e.getPoints();
        }

        return points;
    }

    public static boolean coordsInBoard(Coords position, Board board)
    {
        return (position.column >= 0) && (position.row >= 0) && (position.row < board.getNumberOfRows()) && (position.column < board.getNumberOfColumns());
    }

    public static int generateViewId()
    {
        id++;
        return id;
    }


    public static String doubleToString(double d, int afterSep)
    {
        return String.format(Locale.ENGLISH, "%." + afterSep + "f", d);
    }


}
