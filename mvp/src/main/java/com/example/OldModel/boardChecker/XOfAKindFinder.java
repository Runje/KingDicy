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
public class XOfAKindFinder extends Finder
{
    public XOfAKindFinder(Board board, Rules rules)
    {
        super(board, rules);
    }

    /**
     * Looks for PointElements in a line.
     *
     * @param line        to look in
     * @param orientation of the line
     * @param firstPoint  first Point of the line in the board
     * @return PointElements in this line.
     */
    protected Collection<? extends PointElement> getPointElementsFromLine(ArrayList<BoardElement> line, Orientation orientation, Coords firstPoint)
    {
        ArrayList<PointElement> elements = new ArrayList<PointElement>();
        if (line.size() < rules.getMinXOfAKind())
        {
            return elements;
        }

        int value = 0;
        int x = 1;
        for (int i = 0; i < line.size(); i++)
        {
            int newValue = line.get(i).getValue();
            if (newValue != -1 && newValue == value)
            {
                x++;

                // if it is the last one
                if (i == line.size() - 1)
                {
                    if (x >= rules.getMinXOfAKind())
                    {
                        elements.add(this.createPointElement(x, i, orientation, value, firstPoint, PointType.XOfAKind));
                    }
                }
            }
            else
            {
                if (x >= rules.getMinXOfAKind())
                {
                    elements.add(this.createPointElement(x, i - 1, orientation, value, firstPoint, PointType.XOfAKind));
                }

                value = newValue;
                x = 1;
            }
        }

        return elements;
    }

}
