package kg.internlabs.sokoban

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val nav_restart_btn = findViewById<View>(R.id.nav_restart)
        nav_restart_btn.setOnClickListener(controller)
        return super.onOptionsItemSelected(item)
    }

    fun update() {
        canvas.update()
    }

}