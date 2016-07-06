package com.ledboot.nativegraphic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/2/18.
 */
public class SubActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Canvas canvas = new Canvas();

        final Path path = new Path();
        Paint brush = new Paint();
        brush.setAntiAlias(true);
        brush.setColor(Color.BLUE);
        brush.setStrokeWidth(10f);

        textView = new TextView(this);

        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float pointX = event.getX();
                float pointY = event.getY();

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        path.moveTo(pointX,pointY);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        path.lineTo(pointX,pointY);
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });
    }
}
