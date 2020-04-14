class World(gridWidth: Int, gridHeight: Int) {
    private val width: Int = gridWidth
    private val height: Int = gridHeight
    private val cellSize: Int = 10
    private val widthCells: Int = width / cellSize
    private val heightCells: Int = height / cellSize
    private val cells = mutableListOf<Cell>()

    fun getCells(): List<Cell> {
        return cells
    }

    private fun getCellAtPos(x: Int, y: Int): Cell {
        for (cell in cells) {
            if (cell.getX() == x && cell.getY() == y) {
                return cell
            }
        }
        return Cell(-1, -1)
    }

    private fun getNeighborCells(cell: Cell): MutableList<Cell> {
        val neighborCells = mutableListOf<Cell>()
        val cellX = cell.getX()
        val cellY = cell.getY()

        val topLeftCell = getCellAtPos(cellX - cellSize, cellY - cellSize)
        val topCell = getCellAtPos(cellX, cellY - cellSize)
        val topRightCell = getCellAtPos(cellX + cellSize, cellY - cellSize)
        val leftCell = getCellAtPos(cellX - cellSize, cellY)
        val rightCell = getCellAtPos(cellX + cellSize, cellY)
        val bottomLeftCell = getCellAtPos(cellX - cellSize, cellY + cellSize)
        val bottomCell = getCellAtPos(cellX, cellY + cellSize)
        val bottomRightCell = getCellAtPos(cellX + cellSize, cellY + cellSize)

        if (topLeftCell.getX() != -1 && topLeftCell.getY() != -1) {
            neighborCells.add(topLeftCell)
        }
        if (topCell.getX() != -1 && topCell.getY() != -1) {
            neighborCells.add(topCell)
        }
        if (topRightCell.getX() != -1 && topRightCell.getY() != -1) {
            neighborCells.add(topRightCell)
        }
        if (leftCell.getX() != -1 && leftCell.getY() != -1) {
            neighborCells.add(leftCell)
        }
        if (rightCell.getX() != -1 && rightCell.getY() != -1) {
            neighborCells.add(rightCell)
        }
        if (bottomLeftCell.getX() != -1 && bottomLeftCell.getY() != -1) {
            neighborCells.add(bottomLeftCell)
        }
        if (bottomCell.getX() != -1 && bottomCell.getY() != -1) {
            neighborCells.add(bottomCell)
        }
        if (bottomRightCell.getX() != -1 && bottomRightCell.getY() != -1) {
            neighborCells.add(bottomRightCell)
        }

        return neighborCells
    }

    private fun calculateNeighborAliveCount() {
        for (cell in cells) {
            cell.resetAliveNeighborCount()
            val neighborCells = getNeighborCells(cell)

            for (neighborCell in neighborCells) {
                if (neighborCell.isAlive()) {
                    cell.incrementAliveNeighborCount()
                }
            }
        }
    }

    private fun activateCells() {
        for (cell in cells) {
            val cellAliveNeighborCount = cell.getAliveNeighborCount()
            if (cell.isAlive()) {
                if (cellAliveNeighborCount < 2) {
                    cell.kill()
                } else if (cellAliveNeighborCount == 2 || cellAliveNeighborCount == 3) {
                    cell.activateCell()
                } else if (cellAliveNeighborCount > 3) {
                    cell.kill()
                }
            } else {
                if (cellAliveNeighborCount == 3) {
                    cell.activateCell()
                }
            }
        }
    }

    private fun activateRandomCells() {
        for (i in 0..20) {
            cells[(0..cells.size).random()].activateCell()
        }
    }

    private fun activateCertainCells() {
        // A Cell Block
        getCellAtPos(3 * cellSize, 3 * cellSize).activateCell()
        getCellAtPos(4 * cellSize, 3 * cellSize).activateCell()
        getCellAtPos(3 * cellSize, 4 * cellSize).activateCell()
        getCellAtPos(4 * cellSize, 4 * cellSize).activateCell()

        // A Blinker
        getCellAtPos(8 * cellSize, 8 * cellSize).activateCell()
        getCellAtPos(9 * cellSize, 8 * cellSize).activateCell()
        getCellAtPos(10 * cellSize, 8 * cellSize).activateCell()

        // A Glider
        getCellAtPos(20 * cellSize, 20 * cellSize).activateCell()
        getCellAtPos(21 * cellSize, 20 * cellSize).activateCell()
        getCellAtPos(22 * cellSize, 20 * cellSize).activateCell()
        getCellAtPos(22 * cellSize, 19 * cellSize).activateCell()
        getCellAtPos(21 * cellSize, 18 * cellSize).activateCell()

        // Random cells
        activateRandomCells()
    }

    fun setup() {
        for (cellX in 0..widthCells) {
            for (cellY in 0..heightCells) {
                cells.add(Cell(cellX * cellSize, cellY * cellSize))
            }
        }
        activateCertainCells()
    }

    fun update() {
        calculateNeighborAliveCount()
        activateCells()
    }
}