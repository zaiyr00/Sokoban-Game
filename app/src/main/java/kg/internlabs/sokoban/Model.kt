package kg.internlabs.sokoban

class Model {
    private val viewer: ViewerActivity
    private var x: Int
    private var y: Int

    public constructor(viewer: ViewerActivity) {
        this.viewer = viewer
        x = 0
        y = 0
    }

    fun move(direction: String?) {
        when (direction) {
            "Left" -> x -= 180
            "Up" -> y -= 180
            "Right" -> x += 180
            "Down" -> y += 180
            else -> return
        }
        viewer.update()
    }

    fun getX(): Int {
        return x
    }

    fun getY(): Int {
        return y
    }

    fun setX(x: Int) {
        this.x = x
    }

    fun setY(y: Int) {
        this.y = y
    }

}
