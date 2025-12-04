class Day4 {
    private val testInput = this::class.java.getResourceAsStream("/day4testinput")?.bufferedReader()?.readText()?.trim() ?: ""
    private val input = this::class.java.getResourceAsStream("/day4input")?.bufferedReader()?.readText()?.trim() ?: ""
    val grid: MutableList<MutableList<String>> = input.lines().map { line -> line.split("").toMutableList() }.toMutableList()

    fun part1() {
        var result = 0

        grid.forEachIndexed { y, line -> line.forEachIndexed { x, item -> if (isAccessibleRoll(grid, Position(x, y))) result += 1 } }
        println(result)
    }

    fun part2() {
        var positions = mutableListOf<Position>()

        var lastPositions = -1

        while (lastPositions != positions.size) {
            lastPositions = positions.size
            grid.forEachIndexed { y, line -> line.forEachIndexed { x, item -> if (isAccessibleRoll(grid, Position(x, y))) positions.add(Position(x, y)) } }
            positions.forEach { grid[it.y][it.x] = "X" }
        }

        println(positions.size)
    }

    fun isAccessibleRoll(grid: List<List<String>>, pos: Position): Boolean {
        var rollsAround = 0
        val accessibleRolls: MutableList<Position> = mutableListOf()

        if (grid[pos.y][pos.x] != "@") {
            return false
        }
        val possiblePositions = listOf(
            Position(pos.x + 1, pos.y + 1),
            Position(pos.x + 1, pos.y),
            Position(pos.x + 1, pos.y - 1),
            Position(pos.x, pos.y + 1),
            Position(pos.x, pos.y - 1),
            Position(pos.x - 1, pos.y + 1),
            Position(pos.x - 1, pos.y - 1),
            Position(pos.x - 1, pos.y),
        )
        for (position in possiblePositions) {
            try {
                if (grid[position.y][position.x] == "@") {
                    accessibleRolls.add(position)
                    rollsAround += 1
                }
            } catch (e: IndexOutOfBoundsException) { }
        }
        return rollsAround < 4
    }


}

data class Position(val x: Int, val y: Int)
