package com.ledboot.nativegraphic

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ledboot.gographic.GraphicManager
import com.ledboot.util.Debuger
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    companion object{
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.oo)
        val drawable = BitmapDrawable(bitmap)
        image.setBackgroundDrawable(drawable)



        mHandler.postDelayed({
            val runner = GraphicManager(this@MainActivity.baseContext)
            val result = runner.setBlur()
            mHandler.sendMessage(mHandler.obtainMessage(1, result))
        }, 3000)


        image!!.setOnTouchListener { view, motionEvent ->
            val x = motionEvent.x
            val y = motionEvent.y
            Debuger.logD(TAG,"x="+x)
            false
        }
    }

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                val bitmap = msg.obj as Bitmap
                val drawable = BitmapDrawable(bitmap)
                image.setBackgroundDrawable(drawable)
            }
        }
    }

}
