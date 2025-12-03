class Day3 {
    private val testInput = this::class.java.getResourceAsStream("/day3testinput")?.bufferedReader()?.readText()?.trim() ?: ""
    private val input = this::class.java.getResourceAsStream("/day3input")?.bufferedReader()?.readText()?.trim() ?: ""

    fun part1() {
        var total = 0L

        for (line in input.lines()) {
            total += findLargest(line, 2)
        }
        println(total)
    }

    fun part2() {
        var total = 0L

        for (line in input.lines()) {
            total += findLargest(line, 12)
        }
        println(total)
    }

    fun findLargest(number: String, size: Int): Long {
        var numberLeft = number
        var result = ""

        var toDiscard = size - 1
        repeat(size) {
            var largest = numberLeft[0].digitToIntOrNull()!!
            var index = 0

            numberLeft.substring(0, numberLeft.length - toDiscard).forEachIndexed { i, char ->
                if (char.digitToIntOrNull()!! > largest) {
                    largest = char.digitToIntOrNull()!!
                    index = i
                }
            }
            toDiscard -= 1
            result += largest.toString()
            numberLeft = numberLeft.substring(index + 1)
        }
        return result.toLong()
    }
}