package kg.internlabs.sokoban

import android.os.StrictMode

public class LevelsServer {
    fun getServerLevel(numOfLevel: Int): Array<Array<Int>> {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val connectToServer = ConnectToServer(numOfLevel)
        connectToServer.go()
        var response: String = connectToServer.getResponse()
        val map: Array<Array<Int>> = MapFileManagement().getMap(response)
        return map
    }
}