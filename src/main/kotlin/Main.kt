import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings

class GameOfLife : GameApplication() {

    override fun initSettings(settings: GameSettings) {
        with(settings) {
            width = 1600
            height = 1300
            title = "Kotlin Game - Game of Life"
        }
    }
}

fun main() {
    GameApplication.launch(GameOfLife::class.java, emptyArray())
}