package com.ledboot.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/2/19 0019.
 */
public class TestWidget extends View {
    public TestWidget(Context context) {
        this(context,null);
        init(context);
    }

    public TestWidget(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private int init(Context context){
        return 1;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                break;
            default:
                return false;
        }
        postInvalidate();
        return false;
    }
}
