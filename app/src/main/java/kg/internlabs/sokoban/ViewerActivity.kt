package kg.internlabs.sokoban

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.dialog_view.view.*

class ViewerActivity : AppCompatActivity {
    private val controller: Controller
    private var canvas: CanvasSokoban?
    // I don't need to initialize dialog in constructor during creating ViewerActivity
    private lateinit var dialog: AlertDialog

    public constructor() {
        controller = Controller(this)
        canvas = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        canvas = CanvasSokoban(this, controller.getModel())
        canvas?.setOnTouchListener(controller)
        setContentView(canvas)
    }

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

    fun closeDialog() {
        dialog.dismiss()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        controller.onItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    fun update() {
        canvas?.update()
    }
}