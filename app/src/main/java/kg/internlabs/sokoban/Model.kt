package kg.internlabs.sokoban

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/*
* Sokoban Game
* Intern Labs 2.0
* @version 1.0
* @author Zaiyr Sharsheev <zaiyr.00@gmail.com>
* 01.11.2020
*/

public class Model {
    private val viewer: ViewerActivity
    private var xPosition: Int
    private var yPosition: Int
    private lateinit var map: Array<Array<Int>>
    private var levels: Levels
    private lateinit var targetsPositions: Array<IntArray>

    public constructor(viewer: ViewerActivity) {
        this.viewer = viewer
        xPosition = 0
        yPosition = 0
        levels = Levels(viewer)
    }

    fun createMap() {
        map = levels.nextLevel()
        targetsPositions = initialize()
    }

    fun initialize() : Array<IntArray> {
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
            "RestartLevel" -> {
                map = emptyArray()
                map = levels.resetLevel()
            }
            "Next" -> {
                map = emptyArray()
                map = levels.nextLevel()
                viewer.closeDialog()
            }
            "LevelOne" -> map = levels.levelItemClicked(1)
            "LevelTwo" -> map = levels.levelItemClicked(2)
            "LevelThree" -> map = levels.levelItemClicked(3)
            "LevelFour" -> map = levels.levelItemClicked(4)
            "LevelFive" -> map = levels.levelItemClicked(5)
            "LevelSix" -> map = levels.levelItemClicked(6)
            "LevelSeven" -> map = levels.levelItemClicked(7)
            "LevelEight" -> map = levels.levelItemClicked(8)
            "LevelNine" -> map = levels.levelItemClicked(9)
            else -> return
        }
        targetsPositions = initialize()
        viewer.updateToolbarTitle(levels.getCurrentLevel())
        viewer.update()
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
        } else if (map[xPosition][yPosition + 1] == SokobanProperties.TARGET || map[xPosition][yPosition + 1] == SokobanProperties.FLOOR) {
            map[xPosition][yPosition + 1] = SokobanProperties.PLAYER
            map[xPosition][yPosition] = SokobanProperties.FLOOR
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
        } else if (map[xPosition][yPosition - 1] == SokobanProperties.TARGET || map[xPosition][yPosition - 1] == SokobanProperties.FLOOR) {
            map[xPosition][yPosition - 1] = SokobanProperties.PLAYER
            map[xPosition][yPosition] = SokobanProperties.FLOOR
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
        } else if (map[xPosition - 1][yPosition] == SokobanProperties.TARGET || map[xPosition - 1][yPosition] == SokobanProperties.FLOOR) {
            map[xPosition - 1][yPosition] = SokobanProperties.PLAYER
            map[xPosition][yPosition] = SokobanProperties.FLOOR
            xPosition -= 1
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
        } else if (map[xPosition + 1][yPosition] == SokobanProperties.TARGET || map[xPosition + 1][yPosition] == SokobanProperties.FLOOR) {
            map[xPosition + 1][yPosition] = SokobanProperties.PLAYER
            map[xPosition][yPosition] = SokobanProperties.FLOOR
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
