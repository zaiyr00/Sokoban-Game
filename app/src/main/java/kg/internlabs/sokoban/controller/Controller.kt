package kg.internlabs.sokoban.controller

import android.view.*
import android.view.GestureDetector.SimpleOnGestureListener
import kg.internlabs.sokoban.R
import kg.internlabs.sokoban.utils.SokobanProperties
import kg.internlabs.sokoban.model.Model
import kg.internlabs.sokoban.view.ViewerActivity
import kotlinx.android.synthetic.main.dialog_view.view.*

/*
* Sokoban Game
* Intern Labs 2.0
* @version 1.0
* @author Zaiyr Sharsheev <zaiyr.00@gmail.com>
* 01.11.2020
*/

public class Controller : View.OnTouchListener, SimpleOnGestureListener, View.OnClickListener {
    private val model: Model
    private val gestureDetector: GestureDetector

    public constructor(viewer: ViewerActivity){
        model = Model(viewer)
        // Detect various gestures and events using the supplied MotionEvent
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

    fun runGame() {
        model.createMap()
    }

    override fun onFling( e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float ) : Boolean {
        var result = false
        try {
            val diffY = e2.y - e1.y
            val diffX = e2.x - e1.x
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SokobanProperties.SWIPE_THRESHOLD && Math.abs(velocityX) > SokobanProperties.SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight()
                    } else {
                        onSwipeLeft()
                    }
                    result = true
                }
            } else if (Math.abs(diffY) > SokobanProperties.SWIPE_THRESHOLD && Math.abs(velocityY) > SokobanProperties.SWIPE_VELOCITY_THRESHOLD) {
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

    fun onItemSelected(item: MenuItem): Boolean {
        var action: String = ""
        when(item.itemId) {
            R.id.nav_restartLevel -> action = "RestartLevel"
            R.id.nav_levelOne -> action = "LevelOne"
            R.id.nav_levelTwo -> action = "LevelTwo"
            R.id.nav_levelThree -> action = "LevelThree"
            R.id.nav_levelFour -> action = "LevelFour"
            R.id.nav_levelFive -> action = "LevelFive"
            R.id.nav_levelSix -> action = "LevelSix"
            R.id.nav_levelSeven -> action = "LevelSeven"
            R.id.nav_levelEight -> action = "LevelEight"
            R.id.nav_levelNine -> action = "LevelNine"
        }
        model.doAction(action)
        return true
    }

    override fun onClick(view: View) {
        var action: String = ""
        when (view) {
            view.btn_next -> {
                action = "Next"
            }
        }
        model.doAction(action)
    }
}
