package kg.internlabs.sokoban

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
        for (row: Int in map.indices) {
            for (cell in map[row].indices) {
                if (map[row][cell] == SokobanProperties.PLAYER) {
                    xPosition = row
                    yPosition = cell
                } else if (map[row][cell] == SokobanProperties.TARGET) {
                    val target: IntArray = intArrayOf(row, cell)
                    listOfTargets.add(target)
                }
            }
        }
        return listOfTargets.toTypedArray()
    }

    fun doAction(action: String) {
        when (action) {
            "Restart" -> {
                map = levels.resetLevel()
                targetsPositions = initialize()
                isCurrentOnBox = false
                viewer.update()
            }
            "Next" -> {
                map = levels.nextLevel()
                targetsPositions = initialize()
                isCurrentOnBox = false
                viewer.closeDialog()
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
            viewer.openDialog()
        }
        isTargetChanged()
        viewer.update()
    }

    private fun moveRight() {
        if (map[xPosition][yPosition + 1] == SokobanProperties.BOX && map[xPosition][yPosition + 2] == SokobanProperties.FLOOR) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition][yPosition + 1] = SokobanProperties.PLAYER
            map[xPosition][yPosition + 2] = SokobanProperties.BOX
            yPosition += 1
        } else if (map[xPosition][yPosition + 1] == SokobanProperties.BOX && map[xPosition][yPosition + 2] == SokobanProperties.TARGET) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition][yPosition + 1] = SokobanProperties.PLAYER
            map[xPosition][yPosition + 2] = SokobanProperties.BOX
            yPosition += 1
        } else if (map[xPosition][yPosition + 1] == SokobanProperties.TARGET) {
            map[xPosition][yPosition + 1] = SokobanProperties.PLAYER
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            yPosition += 1
            isCurrentOnBox = true
        } else if (map[xPosition][yPosition + 1] == SokobanProperties.FLOOR) {
            if(isCurrentOnBox) {
                map[xPosition][yPosition] = SokobanProperties.TARGET
                isCurrentOnBox = false
            } else {
                map[xPosition][yPosition] = SokobanProperties.FLOOR
            }
            map[xPosition][yPosition + 1] = SokobanProperties.PLAYER
            yPosition += 1
        }
    }

    private fun moveLeft() {
        if (map[xPosition][yPosition - 1] == SokobanProperties.BOX && map[xPosition][yPosition - 2] == SokobanProperties.FLOOR) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition][yPosition - 1] = SokobanProperties.PLAYER
            map[xPosition][yPosition - 2] = SokobanProperties.BOX
            yPosition -= 1
        } else if (map[xPosition][yPosition - 1] == SokobanProperties.BOX && map[xPosition][yPosition - 2] == SokobanProperties.TARGET) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition][yPosition - 1] = SokobanProperties.PLAYER
            map[xPosition][yPosition - 2] = SokobanProperties.BOX
            yPosition -= 1
        } else if (map[xPosition][yPosition - 1] == SokobanProperties.TARGET) {
            map[xPosition][yPosition - 1] = SokobanProperties.PLAYER
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            yPosition -= 1
            isCurrentOnBox = true
        } else if (map[xPosition][yPosition - 1] == SokobanProperties.FLOOR) {
            if(isCurrentOnBox) {
                map[xPosition][yPosition] = SokobanProperties.TARGET
                isCurrentOnBox = false
            } else {
                map[xPosition][yPosition] = SokobanProperties.FLOOR
            }
            map[xPosition][yPosition - 1] = SokobanProperties.PLAYER
            yPosition -= 1
        }
    }

    private fun moveUp() {
        if (map[xPosition - 1][yPosition] == SokobanProperties.BOX && map[xPosition - 2][yPosition] == SokobanProperties.FLOOR) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition - 1][yPosition] = SokobanProperties.PLAYER
            map[xPosition - 2][yPosition] = SokobanProperties.BOX
            xPosition -= 1
        } else if (map[xPosition - 1][yPosition] == SokobanProperties.BOX && map[xPosition - 2][yPosition] == SokobanProperties.TARGET) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition - 1][yPosition] = SokobanProperties.PLAYER
            map[xPosition - 2][yPosition] = SokobanProperties.BOX
            xPosition -= 1
        } else if (map[xPosition - 1][yPosition] == SokobanProperties.TARGET) {
            map[xPosition - 1][yPosition] = SokobanProperties.PLAYER
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            xPosition -= 1
            isCurrentOnBox = true
        } else if (map[xPosition - 1][yPosition] == SokobanProperties.FLOOR) {
            if(isCurrentOnBox) {
                map[xPosition][yPosition] = SokobanProperties.TARGET
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
        if (map[xPosition + 1][yPosition] == SokobanProperties.BOX && map[xPosition + 2][yPosition] == SokobanProperties.FLOOR) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition + 1][yPosition] = SokobanProperties.PLAYER
            map[xPosition + 2][yPosition] = SokobanProperties.BOX
            xPosition += 1
        } else if (map[xPosition + 1][yPosition] == SokobanProperties.BOX && map[xPosition + 2][yPosition] == SokobanProperties.TARGET) {
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            map[xPosition + 1][yPosition] = SokobanProperties.PLAYER
            map[xPosition + 2][yPosition] = SokobanProperties.BOX
            xPosition += 1
        } else if (map[xPosition + 1][yPosition] == SokobanProperties.TARGET) {
            map[xPosition + 1][yPosition] = SokobanProperties.PLAYER
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            xPosition += 1
            isCurrentOnBox = true
        } else if (map[xPosition + 1][yPosition] == SokobanProperties.FLOOR) {
            if(isCurrentOnBox) {
                map[xPosition][yPosition] = SokobanProperties.TARGET
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

   private fun isPlayerWon() : Boolean {
       var counter = 0
       for(target in targetsPositions) {
           if (map[target[0]][target[1]] == SokobanProperties.BOX) counter++
       }
       return counter == targetsPositions.size
   }

    private fun isTargetChanged() {
        for(target in targetsPositions) {
            if (map[target[0]][target[1]] == SokobanProperties.FLOOR) map[target[0]][target[1]] = SokobanProperties.TARGET
        }
    }

}
