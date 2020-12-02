package kg.internlabs.sokoban.level

/*
* Sokoban Game
* Intern Labs 2.0
* @version 1.0
* @author Zaiyr Sharsheev <zaiyr.00@gmail.com>
* 01.11.2020
*/

public class MapFileManagement {
    fun getMap(message: String): Array<Array<Int>> {
        val message = message.replaceFirst("^\\s*".toRegex(), "")
        var row = 0
        // counting rows
        for (element: Char in message) {
            if (element == '\n') {
                row += 1
            }
        }
        val array = Array(row) { Array(0) { 0 } }
        var columns = 0
        var index = 0
        // counting columns
        for (element: Char in message) {
            if (element == '\n') {
                array[index] = Array(columns){0}
                index += 1
                columns = 0
            } else {
                columns += 1
            }
        }
        var count = 0
        // fill in symbols in array
        for (i in array.indices) {
            for (j in 0..array[i].size) {
                val symbol: Char = message[count]
                if (symbol != '\n') {
                    val value = Character.getNumericValue(symbol)
                    array[i][j] = value
                }
                count++
            }
        }
        return array
    }
}