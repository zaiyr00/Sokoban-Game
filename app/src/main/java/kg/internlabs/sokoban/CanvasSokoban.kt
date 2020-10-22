package kg.internlabs.sokoban

import android.graphics.*
import android.view.SurfaceHolder
import android.view.View

class CanvasSokoban: View {
    private val model: Model
    private val bitmapGamerIcon: Bitmap
    private val bitmapBoxIcon: Bitmap
    private val bitmapDiamondIcon: Bitmap
    private val bitmapFloorIcon: Bitmap
    private val bitmapWallIcon: Bitmap
    private var isStarted: Boolean

    private var level: Array<Array<Int>>

    public constructor(viewer: ViewerActivity, model: Model) : super(viewer) {
        this.model = model
        setBackgroundResource(R.drawable.background)

        bitmapGamerIcon = BitmapFactory.decodeResource(resources, R.drawable.mario)
        bitmapBoxIcon = BitmapFactory.decodeResource(resources, R.drawable.box)
        bitmapDiamondIcon = BitmapFactory.decodeResource(resources, R.drawable.diamond)
        bitmapFloorIcon = BitmapFactory.decodeResource(resources, R.drawable.floor)
        bitmapWallIcon = BitmapFactory.decodeResource(resources, R.drawable.brick_wall)
        val levels = Levels()
        level = levels.getLevelOne()
        isStarted = false

    }

    override fun onDraw(canvas: Canvas) {
        var left = 0
        var top = 0
        var right = 180
        var bottom = 180

        for(row in level) {
            for(cell in row) {
                when (cell) {
                    0 -> {
                        canvas.drawBitmap(bitmapFloorIcon, null, Rect(left, top, right, bottom), null)
                    }
                    1 -> {
                        canvas.drawBitmap(bitmapWallIcon, null, Rect(left, top, right, bottom), null)
                    }
                    2 -> {
                        canvas.drawBitmap(bitmapFloorIcon, null, Rect(left, top, right, bottom), null)
                        canvas.drawBitmap(bitmapDiamondIcon, null, Rect(left, top, right, bottom), null)
                    }
                    3 -> {
                        canvas.drawBitmap(bitmapFloorIcon, null, Rect(left, top, right, bottom), null)
                        canvas.drawBitmap(bitmapBoxIcon, null, Rect(left, top, right, bottom), null)
                    }
                    4 -> {
                        if(!isStarted) {
                            model.setX(left)
                            model.setY(top)
                            isStarted = true
                        }
                        canvas.drawBitmap(bitmapFloorIcon, null, Rect(left, top, right, bottom), null)
                        canvas.drawBitmap(bitmapGamerIcon, null, Rect(model.getX(), model.getY(), model.getX() + 180, model.getY() + 180), null)
                    }
                }
                left += 180
                right += 180
            }
            left -= 1440
            right -= 1440
            top += 180
            bottom += 180
        }
    }

    fun update() {
        invalidate()
    }
}
