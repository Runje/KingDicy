package com.example.thomas.dicyplayground.Views;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.OldModel.BoardElement;
import com.example.OldModel.Coords;
import com.example.OldModel.Logger;

import java.util.ArrayList;

/**
 * Created by Thomas on 15.03.2016.
 */
public class SimpleBoardView extends BoardView
{
    private int size;
    private float margin;
    private float widthPerElement;
    private float heightPerElement;
    private Paint borderPaint;
    private boolean animationIsRunning;

    public SimpleBoardView(Context context)
    {
        super(context);
    }

    public SimpleBoardView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SimpleBoardView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void animateSwitchDices(final Coords first, Coords second, int duration, final Runnable runAfterAnimation)
    {
        if (animationIsRunning)
        {
            Logger.e("Animation is already running!");
            return;
        }

        animationIsRunning = true;
        BoardElementContainer containerFirst = getContainer(first);
        final RectF firstRec = containerFirst.rectF;
        BoardElementContainer containerSecond = getContainer(second);
        RectF secondRec = containerSecond.rectF;
        Runnable runnable = () -> {
            containerFirst.element.setPosition(second);
            containerSecond.element.setPosition(first);
            runAfterAnimation.run();
        };
        Animator animator = CreateSwitchValueAnimator(duration, runnable, firstRec, secondRec);
        animator.start();
    }

    private BoardElementContainer getContainer(Coords coords)
    {
        for(BoardElementContainer container : elementsContainer)
        {
            if (container.element.getPosition().equals(coords))
            {
                return container;
            }
        }

        Logger.e("Couldn't find element for coords: " + coords.toString());
        return null;
    }

    private Animator CreateSwitchValueAnimator(int duration, final Runnable runAfterAnimation, final RectF firstRec, final RectF secondRec)
    {
        float deltaX = secondRec.left - firstRec.left;
        float deltaY = secondRec.bottom - firstRec.bottom;
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(CreateMoveAnimator(firstRec, deltaX, deltaY, duration), CreateMoveAnimator(secondRec, -deltaX, -deltaY, duration));
        animatorSet.addListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animation)
            {

            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                animationIsRunning = false;
                runAfterAnimation.run();
            }

            @Override
            public void onAnimationCancel(Animator animation)
            {

            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {

            }
        });

        return animatorSet;
    }

    private Animator  CreateMoveAnimator(final RectF rec, final float deltaX, final float deltaY, int duration)
    {
        final String x = "X";
        final String y = "Y";
        final RectF initRec = new RectF(rec);
        ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder(new PropertyValuesHolder[]{
                PropertyValuesHolder.ofFloat(x, deltaX),
                PropertyValuesHolder.ofFloat(y, deltaY)
        });

        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                float f = animation.getAnimatedFraction();
                float ax = (float) animation.getAnimatedValue(x);
                float ay = (float) animation.getAnimatedValue(y);
                float dx = f * ax;
                float dy = f * ay;
                rec.left = initRec.left + dx;
                rec.right = initRec.right + dx;
                rec.top = initRec.top + dy;
                rec.bottom = initRec.bottom + dy;
                //Logger.d("f:" + f + ", ax: " + ax);
                invalidate();
            }
        });

        return animator;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        Logger.d("On Size changed: Width: " + w + ", height: " + h);
        size = Math.min(w, h);
        margin = size / 20f;

        widthPerElement = (size - 2 * margin) / rows;
        heightPerElement = widthPerElement;

        reinit();

        borderPaint = new Paint();
        borderPaint.setColor(Color.WHITE);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(size / 50f);
    }



    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        //Logger.d("On Draw: Width: " + getMeasuredWidth() + ", height: " + getMeasuredHeight());

        canvas.drawRect(canvas.getClipBounds(), borderPaint);
        if (elements.size() == 0 || columns == 0 || rows == 0 || columns * rows != elements.size())
        {
            return;
        }

        for (BoardElementContainer container : elementsContainer)
        {
            BitmapBoardElementView.draw(canvas, container.bmp, (int) container.rectF.left, (int) container.rectF.top);
        }
    }

    @Override
    public void reinit()
    {
        if (widthPerElement < 1 || heightPerElement < 1)
        {
            return;
        }

        elementsContainer = new ArrayList<>();
        for (BoardElement element : elements)
        {
            Coords position = element.getPosition();
            float x = margin + widthPerElement * position.column;
            float y = margin + heightPerElement * (position.row);
            RectF rectF = new RectF(x, y, x + widthPerElement, y + heightPerElement);
            elementsContainer.add(new BoardElementContainer(element, rectF, BitmapBoardElementView.createBitmap(getContext(), (int) widthPerElement, (int) heightPerElement, element)));
        }
    }

}
