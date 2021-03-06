import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.dsl.*
import javafx.util.Duration.seconds
import javafx.util.Duration
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class GameOfLife : GameApplication() {

    private val gridWidth: Int = 400
    private val gridHeight: Int = 400
    private lateinit var world: World

    enum class Type {
        CELL
    }

    override fun initSettings(settings: GameSettings) {
        with(settings) {
            width = gridWidth
            height = gridHeight
            title = "Game of Life"
        }
        world = World(gridWidth, gridHeight)
        world.setup()
    }

    override fun initGame() {
        run({
            spawnCells()
            world.update()
        }, Duration.seconds(2.0))
    }

    private fun spawnCells() {
        val cells = world.getCells()
        for (cell in cells) {
            if (cell.isAlive()) {
                entityBuilder()
                    .type(Type.CELL)
                    .at(cell.getX().toDouble(), cell.getY().toDouble())
                    .viewWithBBox(Rectangle(cell.getCellSize().toDouble(), cell.getCellSize().toDouble(), Color.BLACK))
                    .buildAndAttach()
            } else {
                entityBuilder()
                    .type(Type.CELL)
                    .at(cell.getX().toDouble(), cell.getY().toDouble())
                    .viewWithBBox(Rectangle(cell.getCellSize().toDouble(), cell.getCellSize().toDouble(), Color.WHITE))
                    .buildAndAttach()
            }
        }
    }
}

fun main() {
    GameApplication.launch(GameOfLife::class.java, emptyArray())
}