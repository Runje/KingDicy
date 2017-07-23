package com.example.thomas.dicyplayground.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.OldModel.BoardElement;
import com.example.OldModel.Coords;
import com.example.OldModel.Logger;

/**
 * Created by Thomas on 16.03.2016.
 */
public class SimpleBoardElementView extends BoardElementView
{
    public SimpleBoardElementView(Context context)
    {
        super(context);
    }

    public SimpleBoardElementView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SimpleBoardElementView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public static void draw(Canvas canvas, RectF rectF, BoardElement element)
    {
        Logger.d("Draw: Rec: " + rectF.toShortString());
        Paint borderPaint = new Paint();
        borderPaint.setColor(Color.RED);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(5f);
        canvas.drawRect(rectF, borderPaint);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.GREEN);
        textPaint.setTextSize(rectF.height() / 2);
        String text = Integer.toString(element.getValue());
        Rect bounds = new Rect();
        borderPaint.getTextBounds(text, 0, text.length(), bounds);
        canvas.drawText(text, rectF.centerX() - bounds.width(), rectF.centerY() - bounds.height(), textPaint);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Logger.d("On Draw: Width: " + getMeasuredWidth() + ", height: " + getMeasuredHeight());
        int h = getMeasuredHeight();
        int w = getMeasuredWidth();
        draw(canvas, new RectF(0,0,w, h), element);
    }
}
