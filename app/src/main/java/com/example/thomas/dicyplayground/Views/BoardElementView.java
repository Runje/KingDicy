package com.example.thomas.dicyplayground.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.OldModel.BoardElement;

/**
 * Created by Thomas on 16.03.2016.
 */
public abstract class BoardElementView extends View
{
    protected BoardElement element;

    public BoardElement getElement()
    {
        return element;
    }

    public void setElement(BoardElement element)
    {
        this.element = element;
    }

    public BoardElementView(Context context)
    {
        super(context);
    }

    public BoardElementView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public BoardElementView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

}
