package kg.internlabs.sokoban

import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View

class Controller : View.OnTouchListener, View.OnClickListener, SimpleOnGestureListener {

    private val model: Model
    private val gestureDetector: GestureDetector

    public constructor(viewer: ViewerActivity){
        model = Model(viewer)
        gestureDetector = GestureDetector(this)
    }

    fun getModel(): Model {
        return model
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent): Boolean {
        return true
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        var result = false
        try {
            val diffY = e2.y - e1.y
            val diffX = e2.x - e1.x
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight()
                    } else {
                        onSwipeLeft()
                    }
                    result = true
                }
            } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    onSwipeBottom()
                } else {
                    onSwipeTop()
                }
                result = true
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return result
    }

    companion object {
        private const val SWIPE_THRESHOLD = 100
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }

    fun onSwipeRight() {
        model.move("Right")
    }

    fun onSwipeLeft() {
        model.move("Left")
    }

    fun onSwipeTop() {
        model.move("Up")
    }

    fun onSwipeBottom() {
        model.move("Down")
    }

    override fun onClick(view: View) {
        var action: String = ""
        when(view.id) {
            R.id.nav_restart -> action = "Restart"
        }
        model.doAction(action)
    }
}
