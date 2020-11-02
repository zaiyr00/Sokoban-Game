package kg.internlabs.sokoban

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.dialog_view.view.*

/*
* Sokoban Game 
* Intern Labs 2.0
* @version 1.0
* @author Zaiyr Sharsheev <zaiyr.00@gmail.com>
* 01.11.2020
*/

public class ViewerActivity : AppCompatActivity {
    private val controller: Controller
    private var canvas: CanvasSokoban?
    private lateinit var dialog: AlertDialog

    public constructor() {
        controller = Controller(this)
        canvas = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Sokoban Game | Level №1"
        canvas = CanvasSokoban(this, controller.getModel())
        canvas?.setOnTouchListener(controller)
        setContentView(canvas)
        controller.runGame()
    }

    // AlertDialog Window after winning the level
    fun openDialog() {
        val view: View = View.inflate(this, R.layout.dialog_view, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        view.btn_next.setOnClickListener(controller)
    }

    // AlertDialog Window after winning the level
    fun closeDialog() {
        dialog.dismiss()
    }

    // Options Menu in the Toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    // Items in the Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        controller.onItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    fun updateToolbarTitle(currentLevel: Int) {
        supportActionBar?.title = "Sokoban Game | Level №$currentLevel"
    }

    fun update() {
        canvas?.update()
    }
}