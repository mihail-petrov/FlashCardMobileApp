package com.mentormateacademy.flashcardmobileclient.component.customComponent.canvasView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class CanvasView extends View {

    public CanvasView(Context context) {
        super(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        // measure canvas
        // ==================================
        int canvasWidth  = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        int planeCenterX =  (canvasWidth / 2);
        int planeCenterY = (canvasHeight / 2);

        int objectPadding = 100;

        int left   = planeCenterX - objectPadding;
        int top    = planeCenterY - objectPadding;
        int right  = planeCenterX + objectPadding;
        int bottom = planeCenterY + objectPadding;

        // Base Arc Circle
        // ==================================
        Paint rectPaintNew = new Paint();
        rectPaintNew.setColor(Color.parseColor("#e7e8e9"));
        rectPaintNew.setAntiAlias(true);
        rectPaintNew.setStyle(Paint.Style.STROKE);
        rectPaintNew.setStrokeWidth(12);
        rectPaintNew.setStrokeCap(Paint.Cap.ROUND);

        final RectF baseOval = new RectF();
        baseOval.set(left, top, right, bottom);

        // draw base arc for contrast
        canvas.drawArc(baseOval, 0, 360, false, rectPaintNew);


        // Pointer Arc Circle
        // ==================================
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ff9000"));
        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);
        paint.setStrokeCap(Paint.Cap.ROUND);

        final RectF oval = new RectF();
        oval.set(left, top, right, bottom);

        // draw pointer canvas
        canvas.drawArc(oval, 0, 240, false, paint);


        // Canvas Text
        // ==================================

        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        //canvas.drawPaint(textPaint);

        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30);

        String text = "4";

        float textSize = textPaint.getTextSize();
        float textLengthSize = (text.length() / 2f);

        //float textX = (planeCenterX - (textPaint.getTextSize() * Math.abs(text.length() / 2)));
        float textX = (planeCenterX - (textSize * textLengthSize) / 2);

        float textY = (planeCenterY - ((textPaint.descent() + textPaint.ascent()) / 2));

        canvas.drawText(text, textX, textY, textPaint);

    }
}
