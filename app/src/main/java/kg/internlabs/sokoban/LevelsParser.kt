package kg.internlabs.sokoban

import java.io.*

/*
* Sokoban Game
* Intern Labs 2.0
* @version 1.0
* @author Zaiyr Sharsheev <zaiyr.00@gmail.com>
* 01.11.2020
*/

class LevelsParser {
    fun getMessageFromFile(viewer: ViewerActivity, fileName: String): String {
        var lettersFromFile = ""
        try {
            val inputStream: InputStream = viewer.assets.open(fileName)
            val bufferedReader: BufferedReader = BufferedReader(InputStreamReader(inputStream))
            while (true) {
                val unicode: Int = bufferedReader.read()
                val symbol: Char = unicode.toChar()
                if (symbol in '0'..'9' || symbol == '\n') {
                    lettersFromFile += symbol
                }
                if (unicode == -1) {
                    break
                }
            }
        } catch (fne: FileNotFoundException) {
            println(fne)
        } catch (ioe: IOException) {
            println(ioe)
        }
        return lettersFromFile
    }
}