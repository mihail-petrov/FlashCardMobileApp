package com.mentormateacademy.flashcardmobileclient.component.customComponents.canvasView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class CanvasView extends View {

    private int canvasCenterX;
    private int canvasCenterY;

    private int objectPadding;

    // number elements
    private int inputNumber;
    private int maxNumber;

    //
    private boolean includeMax;

    //
    private String paintColor;

    public CanvasView(Context context, int inputNumber, int maxNumber, boolean includeMax) {
        super(context);

        this.inputNumber = inputNumber;
        this.maxNumber   = maxNumber;

        this.includeMax = includeMax;
    }

    public void setColor(String colorString){
        this.paintColor = colorString;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // measure canvas screen
        initCanvasScreen(canvas);

        // calculate bar size
        int arcSize = (360 / this.maxNumber);
        int maxSize = (this.inputNumber * arcSize);

        // Base Arc Circle
        drawArc(canvas, "#e7e8e9", 360);

        // Pointer Arc Circle
        if(this.paintColor != null) {
            drawArc(canvas, this.paintColor, maxSize);
        }
        else {
            drawArc(canvas, "#ff9000", maxSize);
        }


        String result = (this.includeMax) ?
                (this.inputNumber + "/" + this.maxNumber)  : String.valueOf(this.inputNumber);

        // Canvas Text
        drawText(canvas, result);
    }

    /**
     *
     * @param canvas
     */
    public void initCanvasScreen(Canvas canvas){

        canvasCenterX = (canvas.getWidth() / 2);
        canvasCenterY = (canvas.getHeight() / 2);

        objectPadding = 100;
    }

    /**
     *
     * @param canvas
     * @param color
     * @param endAngle
     */
    public void drawArc(Canvas canvas, String color, int endAngle){

        int left   = (canvasCenterX - objectPadding);
        int top    = (canvasCenterY - objectPadding);
        int right  = (canvasCenterX + objectPadding);
        int bottom = (canvasCenterY + objectPadding);

        // Paint object
        Paint rectPaintNew = new Paint();
        rectPaintNew.setColor(Color.parseColor(color));
        rectPaintNew.setAntiAlias(true);
        rectPaintNew.setStyle(Paint.Style.STROKE);
        rectPaintNew.setStrokeWidth(16);
        rectPaintNew.setStrokeCap(Paint.Cap.ROUND);

        // Rectangle point object
        final RectF baseOval = new RectF();
        baseOval.set(left, top, right, bottom);

        // draw base arc for contrast
        canvas.drawArc(baseOval, 90, endAngle, false, rectPaintNew);
    }

    /**
     *
     * @param canvas
     * @param textValue
     */
    public void drawText(Canvas canvas, String textValue){

        Paint textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);

        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30);

        //String text = "4";

        float textSize = textPaint.getTextSize();
        float textLengthSize = (textValue.length() / 2f);


        float textX = (canvasCenterX - (textSize * textLengthSize) / 2);
        float textY = (canvasCenterY - ((textPaint.descent() + textPaint.ascent()) / 2));

        canvas.drawText(textValue, textX, textY, textPaint);
    }
}
