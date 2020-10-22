package kg.internlabs.sokoban

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity

class ViewerActivity : AppCompatActivity {
    private val controller: Controller
    private lateinit var canvas: CanvasSokoban

    public constructor() {
        controller = Controller(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        canvas = CanvasSokoban(this, controller.getModel())
        canvas.setOnTouchListener(controller)
        setContentView(canvas)
    }

    fun update() {
        canvas.update()
    }

}