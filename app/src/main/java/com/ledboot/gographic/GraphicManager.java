package com.ledboot.gographic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.ledboot.nativegraphic.R;

/**
 * Created by Administrator on 2016/1/23.
 */
public class GraphicManager {

    static {
        System.loadLibrary("gographic");
    }

    private Context mContext;

    public GraphicManager(Context context) {
        mContext = context;
    }

    public Bitmap setGray(){
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.login_128);
        int num = nativeGray(bitmap);
        Log.d("GraphicManager","-------------------有数字"+num);
        return  bitmap;
    }

    public Bitmap setBlur(){
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.oo);
        nativeBlur(bitmap,50);
        return  bitmap;
    }

    private native int nativeGray(Bitmap bitmap);

    private native int nativeBlur(Bitmap bitmap,int r);
}
