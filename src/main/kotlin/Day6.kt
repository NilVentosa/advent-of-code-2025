class Day6 {
    private val testInput = this::class.java.getResourceAsStream("/day6testinput")?.bufferedReader()?.readText()?.trim() ?: ""
    private val input = this::class.java.getResourceAsStream("/day6input")?.bufferedReader()?.readText()?.trim() ?: ""

    fun part1() {
        var result = 0L
        val parts = input.lines().map { line -> line.split(" ").filter { it.isNotEmpty() } }
        for (i in 0 until parts[0].size) {
            var thing = mutableListOf<String>()
            for (line in parts) {
                thing.add(line[i])
            }
            result += calculatePart1(thing)
        }
        println(result)
    }

    fun calculatePart1(operation: MutableList<String>): Long {
        var result = 0L
        result += when (operation.last()) {
            "+" -> operation.subList(0, operation.size - 1).sumOf { it.toLong() }
            "*" -> operation.subList(0, operation.size - 1).map { it.toLong() }.reduce { acc, n -> acc * n }
            else -> throw IllegalArgumentException("Invalid operation: ${operation.last()}")
        }
        return result
    }

    fun part2() {
        val lines = input.lines().map { line -> line.split("") }
        val largest = lines.maxOf { it.size }
        var currentBlock = Block(Operation.SUM, mutableListOf())
        val blocks = mutableListOf<Block>()
        for (i in 0 until largest) {
            var current = ""
            for (line in lines) {
                if (i < line.size) {
                    current += line[i]
                }
            }
            if (current.trim().isEmpty()) {
                blocks.add(currentBlock)
                currentBlock = Block(Operation.SUM, mutableListOf())
            } else if (isNumeric(current)) {
                currentBlock.numbers.add(current.trim().toLong())
            } else if (current.endsWith("*")) {
                currentBlock.operation = Operation.MULTIPLY
                currentBlock.numbers.add(current.removeSuffix("*").trim().toLong())
            } else if (current.endsWith("+")) {
                currentBlock.numbers.add(current.removeSuffix("+").trim().toLong())
            }
        }
        var result = 0L
        for (block in blocks) {
            if (block.operation == Operation.SUM) {
                result += block.numbers.sum()
            } else {
                val multiplied = block.numbers.fold(1L) { accumulator, number ->
                    accumulator * number
                }
                result += multiplied
            }
        }

        println(result)
    }

    data class Block(var operation: Operation, val numbers: MutableList<Long>) { }

    enum class Operation {
        SUM,
        MULTIPLY
    }

    fun isNumeric(input: String): Boolean {
        return input.trim().toDoubleOrNull() != null
    }

}