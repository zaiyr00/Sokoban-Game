package kg.internlabs.sokoban

import android.os.StrictMode
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

public class Levels {
    private val viewer: ViewerActivity
    private val levelsFile: LevelsFile
    private val levelsServer: LevelsServer
    private var nextLevel: Int
    private var currentLevel: Int

    constructor(viewer: ViewerActivity) {
        this.viewer = viewer
        nextLevel = 1
        currentLevel = 1
        levelsFile = LevelsFile(viewer)
        levelsServer = LevelsServer()
    }

    fun nextLevel() : Array<Array<Int>> {
        currentLevel = nextLevel
        return chooseLevel(nextLevel++)
    }

    fun getCurrentLevel(): Int {
        return currentLevel
    }

    fun levelItemClicked(level: Int) : Array<Array<Int>> {
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
        return getLevelOne()
    }

    private fun chooseLevel(numOfLevel: Int): Array<Array<Int>> {
        var map: Array<Array<Int>>
        map = when(numOfLevel) {
            1 -> getLevelOne()
            2 -> getLevelTwo()
            3 -> getLevelThree()
            4, 5, 6 -> levelsFile.getLevel(numOfLevel)
            7, 8, 9 -> levelsServer.getServerLevel(numOfLevel)
            else -> startOver()
        }
        return map
    }

    private fun getLevelOne(): Array<Array<Int>> {
        val map: Array<Array<Int>> = Array(12) { Array(8) { 0 } }
        map[0] = arrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        map[1] = arrayOf(1, 3, 0, 0, 0, 0, 3, 1)
        map[2] = arrayOf(1, 1, 1, 0, 0, 1, 1, 1)
        map[3] = arrayOf(1, 0, 0, 0, 0, 0, 0, 1)
        map[4] = arrayOf(1, 0, 0, 2, 2, 0, 0, 1)
        map[5] = arrayOf(1, 0, 0, 0, 0, 0, 0, 1)
        map[6] = arrayOf(1, 0, 0, 0, 0, 0, 0, 1)
        map[7] = arrayOf(1, 0, 0, 2, 2, 0, 0, 1)
        map[8] = arrayOf(1, 0, 0, 4, 0, 0, 0, 1)
        map[9] = arrayOf(1, 1, 1, 0, 0, 1, 1, 1)
        map[10] = arrayOf(1, 3, 0, 0, 0, 0, 3, 1)
        map[11] = arrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        return map
    }

    private fun getLevelTwo(): Array<Array<Int>> {
        val map: Array<Array<Int>> = Array(12) { Array(8) { 0 } }
        map[0] = arrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        map[1] = arrayOf(1, 1, 1, 1, 0, 0, 0, 1)
        map[2] = arrayOf(1, 1, 0, 2, 0, 0, 0, 1)
        map[3] = arrayOf(1, 1, 0, 1, 0, 0, 3, 1)
        map[4] = arrayOf(1, 0, 0, 3, 1, 1, 1, 1)
        map[5] = arrayOf(1, 0, 1, 2, 0, 0, 1, 1)
        map[6] = arrayOf(1, 4, 2, 0, 1, 0, 1, 1)
        map[7] = arrayOf(1, 0, 3, 0, 0, 0, 1, 1)
        map[8] = arrayOf(1, 0, 0, 0, 0, 2, 0, 1)
        map[9] = arrayOf(1, 1, 1, 0, 1, 1, 0, 1)
        map[10] = arrayOf(1, 3, 0, 0, 0, 0, 0, 1)
        map[11] = arrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        return map
    }

    private fun getLevelThree(): Array<Array<Int>> {
        val map: Array<Array<Int>> = Array(12) { Array(8) { 0 } }
        map[0] = arrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        map[1] = arrayOf(1, 3, 3, 0, 1, 0, 0, 1)
        map[2] = arrayOf(1, 3, 3, 0, 1, 0, 0, 1)
        map[3] = arrayOf(1, 3, 3, 0, 1, 2, 0, 1)
        map[4] = arrayOf(1, 0, 0, 0, 0, 0, 0, 1)
        map[5] = arrayOf(1, 0, 0, 0, 1, 0, 0, 1)
        map[6] = arrayOf(1, 1, 1, 1, 1, 2, 0, 1)
        map[7] = arrayOf(1, 0, 0, 0, 0, 0, 0, 1)
        map[8] = arrayOf(1, 0, 2, 0, 2, 0, 0, 1)
        map[9] = arrayOf(1, 0, 0, 2, 0, 2, 0, 1)
        map[10] = arrayOf(1, 0, 0, 0, 0, 0, 4, 1)
        map[11] = arrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        return map
    }
}