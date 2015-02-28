package com.mentormateacademy.flashcardmobileclient.component.customComponents.canvasTutorial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CanvasTutorial extends View {


    public CanvasTutorial(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.argb(50, 255, 0, 36));

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(20, 20, 15, paint);
    }
}
