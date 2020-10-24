package kg.internlabs.sokoban

public class Levels {

    fun chooseLevel(index: Int): Array<Array<Int>> {
        var desktop: Array<Array<Int>> = Array( 11){Array(8) {0} }
        when(index) {
            1 -> desktop = getLevelOne()
        }
        return desktop
    }

    fun getLevelOne(): Array<Array<Int>> {
        val desktop: Array<Array<Int>> = Array(12) { Array(8) { 0 } }
        desktop[0] = arrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        desktop[1] = arrayOf(1, 1, 1, 1, 0, 0, 0, 1)
        desktop[2] = arrayOf(1, 1, 0, 2, 0, 0, 0, 1)
        desktop[3] = arrayOf(1, 1, 0, 1, 0, 0, 3, 1)
        desktop[4] = arrayOf(1, 0, 0, 3, 1, 1, 1, 1)
        desktop[5] = arrayOf(1, 0, 1, 2, 0, 0, 1, 1)
        desktop[6] = arrayOf(1, 4, 2, 0, 1, 0, 1, 1)
        desktop[7] = arrayOf(1, 0, 3, 0, 0, 0, 1, 1)
        desktop[8] = arrayOf(1, 0, 0, 0, 0, 0, 0, 1)
        desktop[9] = arrayOf(1, 0, 0, 0, 0, 0, 0, 1)
        desktop[10] = arrayOf(1, 0, 0, 0, 0, 0, 0, 1)
        desktop[11] = arrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        return desktop
    }
}