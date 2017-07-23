package com.example.thomas.dicyplayground.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.OldModel.BoardElement;
import com.example.OldModel.Logger;
import com.example.thomas.dicyplayground.R;

/**
 * Created by Thomas on 25.03.2016.
 */
public class BitmapBoardElementView extends BoardElementView
{
    Bitmap bmp;

    public BitmapBoardElementView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

    }

    public static Bitmap createBitmap(Context context, int width, int height, BoardElement element)
    {
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), valueToImageResource(element.getValue()));
        return Bitmap.createScaledBitmap(bmp, width, height, false);
    }

    public static void draw(Canvas canvas, Bitmap bmp, int left, int top)
    {
        canvas.drawBitmap(bmp, left, top, new Paint(Paint.ANTI_ALIAS_FLAG));
    }

    public static int valueToImageResource(int value)
    {
        switch (value)
        {
            case 1:
                return R.drawable.one;

            case 2:
                return R.drawable.two;

            case 3:
                return R.drawable.three;

            case 4:
                return R.drawable.four;

            case 5:
                return R.drawable.five;

            case 6:
                return R.drawable.six;

            case 0:
                return R.drawable.dice3d;
            default:
                return 0;
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Logger.d("On Draw: Width: " + getMeasuredWidth() + ", height: " + getMeasuredHeight());
        int h = getMeasuredHeight();
        int w = getMeasuredWidth();
        if (bmp != null)
        {
            draw(canvas, bmp, 0, 0);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        if (element != null)
        {
            bmp = createBitmap(getContext(), w, h, element);
        }
    }
}
