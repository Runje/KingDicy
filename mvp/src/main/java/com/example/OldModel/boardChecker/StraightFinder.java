package com.example.OldModel.boardChecker;

import com.example.OldModel.Board;
import com.example.OldModel.BoardElement;
import com.example.OldModel.Coords;
import com.example.OldModel.Orientation;
import com.example.OldModel.PointElement;
import com.example.OldModel.PointType;
import com.example.OldModel.Rules;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Thomas on 08.10.2014.
 */
public class StraightFinder extends Finder
{
    public StraightFinder(Board board, Rules rules)
    {
        super(board, rules);
    }

    @Override
    protected Collection<? extends PointElement> getPointElementsFromLine(ArrayList<BoardElement> line, Orientation orientation, Coords firstPoint)
    {
        ArrayList<PointElement> elements = new ArrayList<PointElement>();
        if (line.size() < this.rules.getMinStraight())
        {
            return elements;
        }

        int oldValue = 0;
        int x = 1;
        int oldLength = 0;
        boolean descendingNow = false;
        boolean ascendingNow = false;
        boolean descendingLast = false;
        boolean straightEnded = false;
        for (int i = 0; i < line.size(); i++)
        {
            int newValue = line.get(i).getValue();
            if (oldValue != 0 && newValue == oldValue + 1)
            {
                // ascending
                if (ascendingNow)
                {
                    x++;
                }
                else
                {
                    oldLength = x;
                    x = 2;
                    descendingNow = false;
                    ascendingNow = true;
                    straightEnded = true;
                }
            }
            else if (oldValue != 0 && newValue == oldValue - 1)
            {
                // descending
                if (descendingNow)
                {
                    x++;
                }
                else
                {
                    oldLength = x;
                    x = 2;
                    descendingNow = true;
                    ascendingNow = false;
                    straightEnded = true;
                }
            }
            else
            {
                // reset variables
                descendingNow = false;
                ascendingNow = false;
                straightEnded = true;
                oldLength = x;
                x = 1;
            }

            // add element if it is a straight
            boolean isLast = (i == line.size() - 1);
            if (isLast || straightEnded)
            {
                // not last or straight ended
                int lastValue = oldValue;
                int length = oldLength;
                int endIndex = i - 1;

                if (isLast && !straightEnded)
                {
                    lastValue = newValue;
                    length = x;
                    endIndex = i;
                }

                if (length >= rules.getMinStraight())
                {
                    // ascending
                    int lowestValue = lastValue - length + 1;

                    // descending
                    if (descendingLast)
                    {
                        lowestValue = lastValue;
                    }

                    elements.add(this.createPointElement(length, endIndex, orientation, lowestValue, firstPoint, PointType.Straight));
                }
            }

            descendingLast = descendingNow;
            oldValue = newValue;
            straightEnded = false;
            oldLength = 0;
        }

        return elements;
    }
}
