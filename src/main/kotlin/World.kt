class World(gridWidth: Int, gridHeight: Int) {
    private val width: Int = gridWidth
    private val height: Int = gridHeight
    private val cellSize: Int = 10
    private val widthCells: Int = width / cellSize
    private val heightCells: Int = height / cellSize
    private val cells = mutableListOf<Cell>()

    fun setup() {
        for (cellX in 0..widthCells) {
            for (cellY in 0..heightCells) {
                cells.add(Cell(cellX * cellSize, cellY * cellSize))
            }
        }
        activateRandomCells()
    }

    fun getCells(): List<Cell> {
        return cells
    }

    private fun activateRandomCells() {
        for (i in 0..10) {
            cells[(0..cells.size).random()].activateCell()
        }
    }
}