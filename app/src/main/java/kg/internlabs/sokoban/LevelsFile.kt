package kg.internlabs.sokoban

public class LevelsFile {
    private val viewer: ViewerActivity

    constructor(viewer: ViewerActivity) {
        this.viewer = viewer
    }

    fun getLevel(currentLevel: Int): Array<Array<Int>> {
        var map: Array<Array<Int>>
        map = when(currentLevel) {
            4 -> getLevelFour()
            5 -> getLevelFive()
            6 -> getLevelSix()
            else -> arrayOf(emptyArray<Int>())
        }
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
}