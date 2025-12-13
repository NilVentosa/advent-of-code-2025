class Day6 {
    private val testInput = this::class.java.getResourceAsStream("/day6testinput")?.bufferedReader()?.readText()?.trim() ?: ""
    private val input = this::class.java.getResourceAsStream("/day6input")?.bufferedReader()?.readText()?.trim() ?: ""

    fun part1() {
        var result = 0L
        var parts = input.lines().map { line -> line.split(" ").filter { it.isNotEmpty() } }
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
        var result = 0L
        var parts = testInput.lines().map { line -> line.split("") }
        var currentNumber = ""
        var currentOperation = "*"
        for (i in 0 until parts[0].size) {
            for (line in parts) {
                if (line[i] == "+" || line[i] == "*") {
                    currentOperation = line[i]
                    currentNumber = ""
                    break
                } else if (line[i] != "" && line[i] != " ") {
                    currentNumber += line[i]
                }
            }
        }
        println(result)
    }

    fun calculatePart2(operation: MutableList<String>): Long {
        var result = 0L
        result += when (operation.last()) {
            "+" -> sumPart2(operation. subList(0, operation.size - 1))
            "*" -> multiplyPart2(operation.subList(0, operation.size - 1))
            else -> throw IllegalArgumentException("Invalid operation: ${operation.last()}")
        }
        return result
    }

    fun sumPart2(operation: MutableList<String>): Long {
        var result = 0L
        var paddedNumbers = operation.map { it.padStart(10, '0')}
        for (i in 0 until paddedNumbers[0].length - 1) {
            var partial = ""
            for (number in paddedNumbers) {
                partial += number[i]
            }
            result += partial.toLong()
        }
        println(result)
        return result
    }

    fun multiplyPart2(operation: MutableList<String>): Long {
        var result = 1L
        var paddedNumbers = operation.map { it.padStart(10, '0')}
        for (i in 0 until paddedNumbers[0].length - 1) {
            var partial = ""
            for (number in paddedNumbers) {
                partial += number[i]
            }
            if (partial.toLong() > 0) {
                result *= partial.toLong()
            }
        }
        println(result)
        return result
    }

}