package com.anwesh.uiprojects.bianglebouncylineview

/**
 * Created by anweshmishra on 28/12/19.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.content.Context
import android.app.Activity

val nodes : Int = 5
val lines : Int = 2
val scGap : Float = 0.02f / lines
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#3F51B5")
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 30
val rot : Float = 45f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawAngleBouncyLine(i : Int, scale : Float, size : Float, paint : Paint) {
    val sf : Float = scale.sinify().divideScale(i, lines)
    val si : Float = 1f - 2 * i
    save()
    rotate(-rot * sf * sf)
    drawLine(0f, 0f, size * sf, 0f, paint)
    restore()
}

fun Canvas.drawBiAngleBouncyLine(scale : Float, size : Float, paint : Paint) {
    for (j in 0..(lines - 1)) {
        drawAngleBouncyLine(j, scale, size, paint)
    }
}

fun Canvas.drawBABLNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = w / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.color = foreColor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    save()
    translate(gap * (i + 1), h / 2)
    drawBiAngleBouncyLine(scale, size, paint)
    restore()
}

class BiAngleBouncyLineView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}