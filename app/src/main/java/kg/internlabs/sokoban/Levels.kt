package kg.internlabs.sokoban

import android.os.StrictMode
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

public class Levels {

    private val viewer: ViewerActivity
    private var nextLevel: Int
    private var currentLevel: Int

    constructor(viewer: ViewerActivity) {
        this.viewer = viewer
        nextLevel = 1
        currentLevel = 1
    }

    fun nextLevel() : Array<Array<Int>> {
        currentLevel = nextLevel
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
        var map: Array<Array<Int>> = Array(12){Array(8) {0} }
        when(numOfLevel) {
            1 -> map = getLevelOne()
            2 -> map = getLevelTwo()
            3 -> map = getLevelThree()
            4 -> map = getLevelFour()
            5 -> map = getLevelFive()
            6 -> map = getLevelSix()
            7, 8, 9 -> map = getServerLevel(numOfLevel)
            else -> map = startOver()
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

    private fun getLevelFour(): Array<Array<Int>> {
        val levelsParser: String = LevelsParser().getMessageFromFile(viewer, "levels/levelFour.txt")
        val map: Array<Array<Int>> = MapFileManagement().getMap(levelsParser)
        return map
    }

    private fun getLevelFive(): Array<Array<Int>> {
        val levelsParser: String = LevelsParser().getMessageFromFile(viewer, "levels/levelFive.txt")
        val map: Array<Array<Int>> = MapFileManagement().getMap(levelsParser)
        return map
    }

    private fun getLevelSix(): Array<Array<Int>> {
        val levelsParser: String = LevelsParser().getMessageFromFile(viewer, "levels/levelSix.txt")
        val map: Array<Array<Int>> = MapFileManagement().getMap(levelsParser)
        return map
    }
    
    private fun getServerLevel(numOfLevel: Int): Array<Array<Int>> {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val connectToServer = ConnectToServer(numOfLevel)
        connectToServer.go()
        var response: String = connectToServer.getResponse()
        val map: Array<Array<Int>> = MapFileManagement().getMap(response)
        return map
    }
}