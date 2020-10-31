package kg.internlabs.sokoban

class MapFileManagement {
    fun getMap(message: String): Array<Array<Int>> {
        val message = message.replaceFirst("^\\s*".toRegex(), "")
        var row = 0
        for (element: Char in message) {
            if (element == '\n') {
                row += 1
            }
        }
        val array = Array(row) { Array(0) { 0 } }
        var columns = 0
        var index = 0
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