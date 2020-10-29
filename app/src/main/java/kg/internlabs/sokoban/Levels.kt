package kg.internlabs.sokoban

import android.os.StrictMode
import java.io.BufferedReader
import java.io.FileReader
import java.util.*

public class Levels {

    private var nextLevel: Int
    private var currentLevel: Int

    constructor() {
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

    fun chooseLevel(numOfLevel: Int): Array<Array<Int>> {
        var map: Array<Array<Int>> = Array(12){Array(8) {0} }
        when(numOfLevel) {
            1 -> map = getLevelOne()
            2 -> map = getLevelTwo()
            3 -> map = getLevelThree()
            4 -> map = getLevelFour()
            7, 8 -> map = getServerLevel(numOfLevel)
        }
        return map
    }

    private fun getLevelOne(): Array<Array<Int>> {
//        val activity = Activity()
//        val f = activity.applicationContext.assets.open("levelFour.txt").bufferedReader().use {
//            it.readText()
//        }
//        println("-------------------------- $f")
//        var txt = ""
//        val inputStream: InputStream = activity.resources.openRawResource(R.raw.levelfour)
//        val buffer = byteArrayOf(inputStream.available().toByte())
//        while (inputStream.read(buffer) != -1) {
//            txt = buffer.toString()
//        }
//        println("----------------------------- $txt")

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
        val sc = Scanner(BufferedReader(FileReader("src/main/res/levelFour.txt")))
        val map: Array<Array<Int>> = Array(12) { Array(8) { 0 } }
        while (sc.hasNextLine()) {
            for (i in map.indices) {
                val line: List<String> = sc.nextLine().trim().split(" ")
                for (j in line.indices) {
                    map[i][j] = line[j].toInt()
                }
            }
        }
        return map
    }
    
    private fun getServerLevel(numOfLevel: Int): Array<Array<Int>> {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val connectToServer = ConnectToServer(numOfLevel)
        connectToServer.go()

        var response: String = connectToServer.getResponse()
        response = response.replace("[", "") // replacing all [ to ""
        response = response.substring(0, response.length - 2) // ignoring last two ]]
        val s1 = response.split("],".toRegex()).toTypedArray() // separating all by "],"
        val map= Array(12) { Array(8) { 0 } } // declaring two dimensional matrix for input

        for (i in s1.indices) {
            s1[i] = s1[i].trim { it <= ' ' } // ignoring all extra space if the string s1[i] has
            val single_int =
                s1[i].split(", ".toRegex()).toTypedArray() // separating integers by ", "
            for (j in single_int.indices) {
                map[i][j] = single_int[j].toInt() // adding single values
            }
        }
        return map
    }
}