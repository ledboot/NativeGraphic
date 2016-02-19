package com.ledboot.nativegraphic;

import android.graphics.BitmapFactory;
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

    public static final String TAG = SubActivity.class.getSimpleName();

    TextView textView;
    Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canvas = new Canvas();

        final Path path = new Path();
        Paint brush = new Paint();
        brush.setAntiAlias(true);
        brush.setColor(Color.BLUE);
        brush.setStrokeWidth(10f);
        int [] arr = new int[]{};
        textView = new TextView(this);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = 0;
            }
        });

        BitmapFactory.Options bmFactoryOptions = new BitmapFactory.Options();

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

        try{

        }catch (Exception e){

        }
    }
}
