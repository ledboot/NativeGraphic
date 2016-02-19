package com.ledboot.nativegraphic

import android.graphics.*
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

    var canvas:Canvas ? = null
    var brush:Paint ?= null
    var matrix:Matrix ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        init()

        /*mHandler.postDelayed({
            val runner = GraphicManager(this@MainActivity.baseContext)
            val result = runnker.setBlur()
            mHandler.sendMessage(mHandler.obtainMessage(1, result))
        }, 3000)*/
        reset.setOnClickListener{draw_canvas.clear()}

    }

    private fun init(){
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inJustDecodeBounds = true

        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.oo)
        val drawable = BitmapDrawable(bitmap)
//        image.setBackgroundDrawable(drawable)


        val alterBitmap = Bitmap.createBitmap(bitmap.width,bitmap.height,bitmap.config);

        canvas = Canvas(alterBitmap)

        brush = Paint()
        brush?.color = Color.WHITE



    }

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                val bitmap = msg.obj as Bitmap
                val drawable = BitmapDrawable(bitmap)
//                image.setBackgroundDrawable(drawable)
            }
        }
    }

}
