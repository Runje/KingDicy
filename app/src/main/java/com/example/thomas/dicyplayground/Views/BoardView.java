package com.example.thomas.dicyplayground.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.OldModel.BoardElement;
import com.example.OldModel.Coords;
import com.example.OldModel.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 15.03.2016.
 */
public abstract class BoardView extends View
{
    protected List<BoardElement> elements = new ArrayList<>();
    protected ArrayList<BoardElementContainer> elementsContainer;
    protected int rows;
    protected int columns;
    protected EventListener eventListener;
    protected GestureDetector.OnGestureListener gestureListener;
    protected GestureDetectorCompat detector;


    public EventListener getEventListener()
    {
        return eventListener;
    }

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public BoardView(Context context)
    {
        super(context);
        init();
    }

    public BoardView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public BoardView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init()
    {
        gestureListener = createGestureListener();
        detector = new GestureDetectorCompat(getContext(), gestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return detector.onTouchEvent(event);
    }

    private GestureDetector.OnGestureListener createGestureListener()
    {
        return new GestureDetector.SimpleOnGestureListener()
        {
            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e)
            {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
            {
                if (e1 == null || e2 == null)
                {
                    Logger.e("Motionevent is null");
                    return false;
                }
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                BoardElementContainer containerFromPixel = getContainerFromPixel(e1.getX(), e1.getY());
                if (containerFromPixel == null)
                {
                    return false;
                }

                BoardElement element = containerFromPixel.element;

                if (Math.abs(diffX) > Math.abs(diffY))
                {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD)
                    {
                        if (diffX > 0)
                        {
                            if (eventListener != null)
                            {
                                eventListener.onRight(element.getPosition());
                            }
                        }
                        else
                        {
                            if (eventListener != null)
                            {
                                eventListener.onLeft(element.getPosition());
                            }
                        }

                        return true;
                    }
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD)
                {
                    if (diffY > 0)
                    {
                        if (eventListener != null)
                        {
                            eventListener.onBottom(element.getPosition());
                        }
                    }
                    else
                    {
                        if (eventListener != null)
                        {
                            eventListener.onTop(element.getPosition());
                        }
                    }

                    return true;
                }

                return false;
            }
        };
    }

    private BoardElementContainer getContainerFromPixel(float x, float y)
    {
        for (BoardElementContainer container : elementsContainer)
        {
            if (container.rectF.contains(x, y))
            {
                return container;
            }
        }

        Logger.e("Can't find container for " + x + ", " + y);
        return null;
    }

    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public int getColumns()
    {
        return columns;
    }

    public void setColumns(int columns)
    {
        this.columns = columns;
    }

    public List<BoardElement> getElements()
    {
        return elements;
    }

    public void setElements(List<BoardElement> elements)
    {
        this.elements = elements;
    }

    public abstract void animateSwitchDices(Coords first, Coords second, int duration, Runnable runAfterAnimation);

    public abstract void reinit();


    public interface EventListener
    {
        void onBottom(Coords from);

        void onRight(Coords position);

        void onLeft(Coords position);

        void onTop(Coords position);
    }

    protected class BoardElementContainer
    {
        public BoardElement element;
        public RectF rectF;
        public Bitmap bmp;

        public BoardElementContainer(BoardElement element, RectF rectF, Bitmap bmp)
        {
            this.bmp = bmp;
            this.element = element;
            this.rectF = rectF;
        }
    }



}
