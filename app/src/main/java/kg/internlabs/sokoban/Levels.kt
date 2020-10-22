package kg.internlabs.sokoban

public class Levels {

    fun getLevelOne(): Array<Array<Int>> {
        val level: Array<Array<Int>> = Array(9, {Array(8, {0}) })
        level[0] = arrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        level[1] = arrayOf(1, 1, 1, 1, 0, 0, 0, 1)
        level[2] = arrayOf(1, 1, 0, 2, 0, 0, 0, 1)
        level[3] = arrayOf(1, 1, 0, 1, 0, 0, 3, 1)
        level[4] = arrayOf(1, 0, 0, 3, 1, 1, 1, 1)
        level[5] = arrayOf(1, 0, 1, 2, 0, 0, 1, 1)
        level[6] = arrayOf(1, 4, 2, 0, 1, 0, 1, 1)
        level[7] = arrayOf(1, 0, 3, 0, 0, 0, 1, 1)
        level[8] = arrayOf(1, 1, 1, 1, 1, 1, 1, 1)
        return level
//        for(row in level) {
//            for(cell in row) {
//                print("$cell \t")
//            }
//            println()
//        }
    }
}