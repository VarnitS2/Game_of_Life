class Cell(xPos: Int, yPos: Int) {
    private val side: Int = 10
    private val x: Int = xPos
    private val y: Int = yPos
    private var alive: Boolean = false
    private var aliveNeighborCount: Int = 0

    fun getX(): Int {
        return x
    }

    fun getY(): Int {
        return y
    }

    fun getCellSize(): Int {
        return side
    }

    fun isAlive(): Boolean {
        return alive
    }

    fun kill() {
        alive = false
    }

    fun activateCell() {
        alive = true
    }

    fun incrementAliveNeighborCount() {
        aliveNeighborCount++
    }

    fun resetAliveNeighborCount() {
        aliveNeighborCount = 0
    }

    fun getAliveNeighborCount(): Int {
        return aliveNeighborCount
    }
}