package kg.internlabs.sokoban.view

import android.app.AlertDialog
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kg.internlabs.sokoban.canvas.CanvasSokoban
import kg.internlabs.sokoban.controller.Controller
import kg.internlabs.sokoban.R
import kg.internlabs.sokoban.utils.SokobanProperties.Companion.SOUND_ID
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
    private var soundOfStep: SoundPool? = null
    private var soundOfPushing: SoundPool? = null
    private var soundOfTarget: SoundPool? = null
    private var soundOfVictory: SoundPool? = null

    public constructor() {
        controller = Controller(this)
        canvas = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        canvas = CanvasSokoban(this, controller.getModel())
        canvas?.setOnTouchListener(controller)
        setContentView(canvas)
        controller.runGame()
        soundOfStep = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundOfStep!!.load(baseContext, R.raw.step, 1)
        soundOfPushing = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundOfPushing!!.load(baseContext, R.raw.pushing, 1)
        soundOfTarget = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundOfTarget!!.load(baseContext, R.raw.target, 1)
        soundOfVictory = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundOfVictory!!.load(baseContext, R.raw.victory, 1)
    }

    fun stepSound() {
        soundOfStep?.play(SOUND_ID, 1F, 1F, 0, 0, 1F)
    }

    fun pushingSound() {
        soundOfPushing?.play(SOUND_ID, 1F, 1F, 0, 0, 1F)
    }

    fun targetSound() {
        soundOfTarget?.play(SOUND_ID, 1F, 1F, 0, 0, 1F)
    }

    fun victorySound() {
        soundOfVictory?.play(SOUND_ID, 1F, 1F, 0, 0, 1F)
    }

    fun stopSounds() {
        soundOfStep?.stop(SOUND_ID)
        soundOfPushing?.stop(SOUND_ID)
        soundOfTarget?.stop(SOUND_ID)
        soundOfVictory?.stop(SOUND_ID)
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
        victorySound()
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