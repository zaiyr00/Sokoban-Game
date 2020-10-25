package kg.internlabs.sokoban

import android.app.AlertDialog
import android.view.View
import kotlinx.android.synthetic.main.dialog_view.view.*

class Model {
    private val viewer: ViewerActivity
    private var xPosition: Int
    private var yPosition: Int
    private var map: Array<Array<Int>>
    private var levels: Levels
    private var isCurrentOnBox: Boolean
    private var targetsPositions: Array<IntArray>

    public constructor(viewer: ViewerActivity) {
        this.viewer = viewer
        xPosition = 0
        yPosition = 0
        levels = Levels()
        map = levels.nextLevel()
        targetsPositions = initialize()
        isCurrentOnBox = false
    }

    private fun initialize() : Array<IntArray> {
        val listOfTargets = ArrayList<IntArray>()
        for (x in map.indices) {
            for (y in map[x].indices) {
                if (map[x][y] == SokobanProperties.PLAYER) {
                    xPosition = x
                    yPosition = y
                } else if (map[x][y] == SokobanProperties.BOX) {
                    val target = intArrayOf(x, y)
                    listOfTargets.add(target)
                }
            }
        }
        return listOfTargets.toTypedArray()
    }

    fun openDialog() {
        val view: View = View.inflate(viewer, R.layout.dialog_view, null)
        val builder = AlertDialog.Builder(viewer)
        builder.setView(view)
        val dialog: AlertDialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        view.btn_next.setOnClickListener {
            map = levels.nextLevel()
            targetsPositions = initialize()
            isCurrentOnBox = false
            viewer.update()
            dialog.dismiss()
        }
    }

    fun doAction(action: String) {
        when (action) {
            "Restart" -> {
                map = levels.resetLevel()
                targetsPositions = initialize()
                isCurrentOnBox = false
                viewer.update()
            }
            else -> return
        }
    }

    fun move(direction: String?) {
        when (direction) {
            "Left" ->{
                moveLeft()
            }
            "Up" -> {
                moveUp()
            }
            "Right" -> {
                moveRight()
            }
            "Down" -> {
                moveDown()
            }
            else -> return
        }
        if(isPlayerWon()){
            openDialog()
        }
        isTargetChanged()
        viewer.update()

    }

    private fun moveRight() {
        if (map[xPosition][yPosition + 1] == SokobanProperties.DIAMOND && map[xPosition][yPosition + 2] == SokobanProperties.FLOOR) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition][yPosition + 1] = SokobanProperties.PLAYER
            map[xPosition][yPosition + 2] = SokobanProperties.DIAMOND
            yPosition += 1
        } else if (map[xPosition][yPosition + 1] == SokobanProperties.DIAMOND && map[xPosition][yPosition + 2] == SokobanProperties.BOX) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition][yPosition + 1] = SokobanProperties.PLAYER
            map[xPosition][yPosition + 2] = SokobanProperties.DIAMOND
            yPosition += 1
        } else if (map[xPosition][yPosition + 1] == SokobanProperties.BOX) {
            map[xPosition][yPosition + 1] = SokobanProperties.PLAYER
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            yPosition += 1
            isCurrentOnBox = true
        } else if (map[xPosition][yPosition + 1] == SokobanProperties.FLOOR) {
            if(isCurrentOnBox) {
                map[xPosition][yPosition] = SokobanProperties.BOX
                isCurrentOnBox = false
            } else {
                map[xPosition][yPosition] = SokobanProperties.FLOOR
            }
            map[xPosition][yPosition + 1] = SokobanProperties.PLAYER
            yPosition += 1
        }
    }

    private fun moveLeft() {
        if (map[xPosition][yPosition - 1] == SokobanProperties.DIAMOND && map[xPosition][yPosition - 2] == SokobanProperties.FLOOR) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition][yPosition - 1] = SokobanProperties.PLAYER
            map[xPosition][yPosition - 2] = SokobanProperties.DIAMOND
            yPosition -= 1
        } else if (map[xPosition][yPosition - 1] == SokobanProperties.DIAMOND && map[xPosition][yPosition - 2] == SokobanProperties.BOX) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition][yPosition - 1] = SokobanProperties.PLAYER
            map[xPosition][yPosition - 2] = SokobanProperties.DIAMOND
            yPosition -= 1
        } else if (map[xPosition][yPosition - 1] == SokobanProperties.BOX) {
            map[xPosition][yPosition - 1] = SokobanProperties.PLAYER
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            yPosition -= 1
            isCurrentOnBox = true
        } else if (map[xPosition][yPosition - 1] == SokobanProperties.FLOOR) {
            if(isCurrentOnBox) {
                map[xPosition][yPosition] = SokobanProperties.BOX
                isCurrentOnBox = false
            } else {
                map[xPosition][yPosition] = SokobanProperties.FLOOR
            }
            map[xPosition][yPosition - 1] = SokobanProperties.PLAYER
            yPosition -= 1
        }
    }

    private fun moveUp() {
        if (map[xPosition - 1][yPosition] == SokobanProperties.DIAMOND && map[xPosition - 2][yPosition] == SokobanProperties.FLOOR) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition - 1][yPosition] = SokobanProperties.PLAYER
            map[xPosition - 2][yPosition] = SokobanProperties.DIAMOND
            xPosition -= 1
        } else if (map[xPosition - 1][yPosition] == SokobanProperties.DIAMOND && map[xPosition - 2][yPosition] == SokobanProperties.BOX) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition - 1][yPosition] = SokobanProperties.PLAYER
            map[xPosition - 2][yPosition] = SokobanProperties.DIAMOND
            xPosition -= 1
        } else if (map[xPosition - 1][yPosition] == SokobanProperties.BOX) {
            map[xPosition - 1][yPosition] = SokobanProperties.PLAYER
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            xPosition -= 1
            isCurrentOnBox = true
        } else if (map[xPosition - 1][yPosition] == SokobanProperties.FLOOR) {
            if(isCurrentOnBox) {
                map[xPosition][yPosition] = SokobanProperties.BOX
                isCurrentOnBox = false
            } else {
                map[xPosition][yPosition] = SokobanProperties.FLOOR
            }
            map[xPosition - 1][yPosition] = SokobanProperties.PLAYER
            xPosition -= 1
            isCurrentOnBox = false
        }
    }

    private fun moveDown() {
        if (map[xPosition + 1][yPosition] == SokobanProperties.DIAMOND && map[xPosition + 2][yPosition] == SokobanProperties.FLOOR) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition + 1][yPosition] = SokobanProperties.PLAYER
            map[xPosition + 2][yPosition] = SokobanProperties.DIAMOND
            xPosition += 1
        } else if (map[xPosition + 1][yPosition] == SokobanProperties.DIAMOND && map[xPosition + 2][yPosition] == SokobanProperties.BOX) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition + 1][yPosition] = SokobanProperties.PLAYER
            map[xPosition + 2][yPosition] = SokobanProperties.DIAMOND
            xPosition += 1
        } else if (map[xPosition + 1][yPosition] == SokobanProperties.BOX) {
            map[xPosition + 1][yPosition] = SokobanProperties.PLAYER
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            xPosition += 1
            isCurrentOnBox = true
        } else if (map[xPosition + 1][yPosition] == SokobanProperties.FLOOR) {
            if(isCurrentOnBox) {
                map[xPosition][yPosition] = SokobanProperties.BOX
                isCurrentOnBox = false
            } else {
                map[xPosition][yPosition] = SokobanProperties.FLOOR
            }
            map[xPosition + 1][yPosition] = SokobanProperties.PLAYER
            xPosition += 1
        }
    }

   fun updateMap(): Array<Array<Int>> {
        return map
   }

   fun isPlayerWon() : Boolean {
       var counter = 0
       for(target in targetsPositions) {
           if (map[target[0]][target[1]] == SokobanProperties.DIAMOND) counter++
       }
       return counter == targetsPositions.size
   }

    private fun isTargetChanged() {
        for(target in targetsPositions) {
            if (map[target[0]][target[1]] == SokobanProperties.FLOOR) map[target[0]][target[1]] = SokobanProperties.BOX
        }
    }

    fun getTargetsPositions():  Array<IntArray> {
        return targetsPositions
    }

}
