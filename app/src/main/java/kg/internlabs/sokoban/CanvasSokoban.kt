package kg.internlabs.sokoban

import android.graphics.*
import android.view.View

class CanvasSokoban: View {
  private val model: Model
  private val bitmapPlayerIcon: Bitmap
  private val bitmapTargetIcon: Bitmap
  private val bitmapBoxIcon: Bitmap
  private val bitmapFloorIcon: Bitmap
  private val bitmapWallIcon: Bitmap
  private var mHeight: Int
  private var mWidth: Int
  private var blockVerticalSize: Int
  private var blockHorizontalSize: Int

  public constructor(viewer: ViewerActivity, model: Model) : super(viewer) {
    this.model = model
    setBackgroundResource(R.drawable.background)
    bitmapPlayerIcon = BitmapFactory.decodeResource(resources, R.drawable.mario)
    bitmapTargetIcon = BitmapFactory.decodeResource(resources, R.drawable.target)
    bitmapBoxIcon = BitmapFactory.decodeResource(resources, R.drawable.box)
    bitmapFloorIcon = BitmapFactory.decodeResource(resources, R.drawable.floor)
    bitmapWallIcon = BitmapFactory.decodeResource(resources, R.drawable.brick_wall)
    mHeight = 0
    mWidth = 0
    blockVerticalSize = 0
    blockHorizontalSize = 0
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    mHeight = MeasureSpec.getSize(heightMeasureSpec)
    mWidth = MeasureSpec.getSize(widthMeasureSpec)
    blockVerticalSize = mWidth / SokobanProperties.MAP_ROW
    blockHorizontalSize = mHeight / SokobanProperties.MAP_COLUMN
  }

  override fun onDraw(canvas: Canvas) {
    var left = 0
    var top = 0
    var right = blockVerticalSize
    var bottom = blockHorizontalSize

    for(row in model.updateMap()) {
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
            canvas.drawBitmap(bitmapBoxIcon, null, Rect(left, top, right, bottom), null)
          }
          3 -> {
            canvas.drawBitmap(bitmapFloorIcon, null, Rect(left, top, right, bottom), null)
            canvas.drawBitmap(bitmapTargetIcon, null, Rect(left, top, right, bottom), null)
          }
          4 -> {
            canvas.drawBitmap(bitmapFloorIcon, null, Rect(left, top, right, bottom), null)
            canvas.drawBitmap(bitmapPlayerIcon, null, Rect(left, top, right, bottom), null)
          }
        }
        left += blockVerticalSize
        right += blockVerticalSize
      }
      left -= blockVerticalSize * 8
      right -= blockVerticalSize * 8
      top += blockHorizontalSize
      bottom += blockHorizontalSize
    }
  }

  fun update() {
    invalidate()
  }
}
