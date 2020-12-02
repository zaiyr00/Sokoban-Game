package kg.internlabs.sokoban.level

import kg.internlabs.sokoban.view.ViewerActivity

/*
* Sokoban Game
* Intern Labs 2.0
* @version 1.0
* @author Zaiyr Sharsheev <zaiyr.00@gmail.com>
* 01.11.2020
*/

public class LevelsFile {
    private val viewer: ViewerActivity

    constructor(viewer: ViewerActivity) {
        this.viewer = viewer
    }

    fun getLevel(currentLevel: Int): Array<Array<Int>> {
        return when(currentLevel) {
            1 -> getLevelOne()
            2 -> getLevelTwo()
            3 -> getLevelThree()
            4 -> getLevelFour()
            5 -> getLevelFive()
            6 -> getLevelSix()
            7 -> getLevelSeven()
            8 -> getLevelEight()
            9 -> getLevelNine()
            else -> arrayOf(emptyArray())
        }
    }

    private fun getLevelOne(): Array<Array<Int>> {
        val levelsParser: String = LevelsParser().getMessageFromFile(viewer, "levels/levelOne.txt")
        val map: Array<Array<Int>> = MapFileManagement().getMap(levelsParser)
        return map
    }

    private fun getLevelTwo(): Array<Array<Int>> {
        val levelsParser: String = LevelsParser().getMessageFromFile(viewer, "levels/levelTwo.txt")
        val map: Array<Array<Int>> = MapFileManagement().getMap(levelsParser)
        return map
    }

    private fun getLevelThree(): Array<Array<Int>> {
        val levelsParser: String = LevelsParser().getMessageFromFile(viewer, "levels/levelFour.txt")
        val map: Array<Array<Int>> = MapFileManagement().getMap(levelsParser)
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

    private fun getLevelSeven(): Array<Array<Int>> {
        val levelsParser: String = LevelsParser().getMessageFromFile(viewer, "levels/levelSeven.txt")
        val map: Array<Array<Int>> = MapFileManagement().getMap(levelsParser)
        return map
    }

    private fun getLevelEight(): Array<Array<Int>> {
        val levelsParser: String = LevelsParser().getMessageFromFile(viewer, "levels/levelEight.txt")
        val map: Array<Array<Int>> = MapFileManagement().getMap(levelsParser)
        return map
    }

    private fun getLevelNine(): Array<Array<Int>> {
        val levelsParser: String = LevelsParser().getMessageFromFile(viewer, "levels/levelNine.txt")
        val map: Array<Array<Int>> = MapFileManagement().getMap(levelsParser)
        return map
    }
}