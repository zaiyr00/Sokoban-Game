package kg.internlabs.sokoban

/*
* Sokoban Game
* Intern Labs 2.0
* @version 1.0
* @author Zaiyr Sharsheev <zaiyr.00@gmail.com>
* 01.11.2020
*/

public class Levels {
    private val viewer: ViewerActivity
    private val levelsFile: LevelsFile
    private var nextLevel: Int
    private var currentLevel: Int
    private var previousLevel: Int

    constructor(viewer: ViewerActivity) {
        this.viewer = viewer
        nextLevel = 1
        currentLevel = 1
        previousLevel = 1
        levelsFile = LevelsFile(viewer)
    }

    fun nextLevel() : Array<Array<Int>> {
        previousLevel = currentLevel
        currentLevel = nextLevel
        return chooseLevel(nextLevel++)
    }

    fun getCurrentLevel(): Int {
        return currentLevel
    }

    fun levelItemClicked(level: Int) : Array<Array<Int>> {
        previousLevel = currentLevel
        currentLevel = level
        nextLevel = level
        return chooseLevel(nextLevel++)
    }

    fun resetLevel() : Array<Array<Int>> {
        return chooseLevel(currentLevel)
    }

    private fun startOver() : Array<Array<Int>> {
        nextLevel = 2
        currentLevel = 1
        return levelsFile.getLevel(currentLevel)
    }

    private fun chooseLevel(numOfLevel: Int): Array<Array<Int>> {
        return when(numOfLevel) {
            1, 2, 3, 4, 5, 6, 7, 8, 9 -> levelsFile.getLevel(numOfLevel)
            else -> startOver()
        }
    }
}