package kg.internlabs.sokoban

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

    fun chooseLevel(index: Int): Array<Array<Int>> {
        var map: Array<Array<Int>> = Array( 12){Array(8) {0} }
        when(index) {
            1 -> map = getLevelOne()
            2 -> map = getLevelTwo()
        }
        return map
    }

    fun getLevelOne(): Array<Array<Int>> {
        val map: Array<Array<Int>> = Array(12) { Array(8) { 0 } }
        map[0] = arrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        map[1] = arrayOf(1, 1, 1, 1, 0, 0, 0, 1)
        map[2] = arrayOf(1, 1, 0, 0, 0, 0, 0, 1)
        map[3] = arrayOf(1, 1, 0, 1, 0, 0, 0, 1)
        map[4] = arrayOf(1, 0, 0, 3, 1, 1, 1, 1)
        map[5] = arrayOf(1, 0, 1, 2, 0, 0, 1, 1)
        map[6] = arrayOf(1, 0, 0, 0, 1, 0, 1, 1)
        map[7] = arrayOf(1, 0, 0, 0, 0, 0, 1, 1)
        map[8] = arrayOf(1, 0, 0, 4, 0, 0, 0, 1)
        map[9] = arrayOf(1, 0, 0, 0, 0, 0, 0, 1)
        map[10] = arrayOf(1, 0, 0, 0, 0, 0, 0, 1)
        map[11] = arrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        return map
    }

    fun getLevelTwo(): Array<Array<Int>> {
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
        map[9] = arrayOf(1, 1, 1, 0, 0, 0, 0, 1)
        map[10] = arrayOf(1, 3, 0, 0, 0, 0, 0, 1)
        map[11] = arrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        return map
    }
}