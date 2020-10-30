package kg.internlabs.sokoban

import java.io.*

class LevelsParser {
    fun getMessageFromFile(viewer: ViewerActivity, fileName: String): String {
        var lettersFromFile = ""
        try {
            val inputStream: InputStream = viewer.assets.open("levels/levelFour.txt")
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