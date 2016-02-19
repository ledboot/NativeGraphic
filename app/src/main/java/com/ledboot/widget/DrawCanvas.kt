package com.ledboot.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.ledboot.nativegraphic.R
import com.ledboot.util.Debuger

/**
 * Created by Administrator on 2016/2/19 0019.
 */


class DrawCanvas : View{

    companion object{
        var TAG = DrawCanvas::class.java.simpleName
    }

    var brush:Paint = Paint()
    var path: Path = Path()
    var originBitmap :Bitmap ?= null
    var newBitmap:Bitmap ? = null

    constructor(context: Context?) : this(context,null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(context)
    }

    private fun init(context: Context?){
        brush.isAntiAlias = true
        brush.color = Color.BLUE
        brush.style = Paint.Style.STROKE
        brush.strokeWidth = 10f
        originBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.oo).copy(Bitmap.Config.ARGB_8888,true)
        newBitmap = Bitmap.createBitmap(originBitmap)
    }

    public fun clear(){
        path.reset()
        newBitmap = Bitmap.createBitmap(originBitmap)
        postInvalidate()
    }

    override fun draw(canvas: Canvas) {
        handleDraw(newBitmap)
        canvas.drawBitmap(newBitmap,0f,0f,null)
    }

    private fun handleDraw(drawBitmap:Bitmap?){
        var canvas:Canvas = Canvas(drawBitmap)
        var matrix:Matrix = Matrix()
        path.transform(matrix)
        var arr = floatArrayOf()
        matrix.mapPoints(arr)
        Debuger.logD(TAG,"arr size="+arr.size)
        var i:Int =0;
        while(i<arr.size){
            Debuger.logD(TAG,"point="+arr[i++])
        }
        canvas.drawPath(path,brush)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when(event.action){
            MotionEvent.ACTION_DOWN ->{
                path.moveTo(pointX,pointY)
                return true
            }
            MotionEvent.ACTION_MOVE ->{
                path.lineTo(pointX,pointY)
            }
            else ->{
                return false
            }
        }
        postInvalidate()
        return false
    }
}