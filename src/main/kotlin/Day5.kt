class Day5 {
    private val testInput =
        this::class.java.getResourceAsStream("/day5testinput")?.bufferedReader()?.readText()?.trim() ?: ""
    private val input = this::class.java.getResourceAsStream("/day5input")?.bufferedReader()?.readText()?.trim() ?: ""

    fun part1() {
        var ranges = mutableListOf<String>()
        var possibles = mutableListOf<Long>()
        var result = 0
        for (line in input.lines()) {
            if (line.contains("-")) {
                ranges.add(line)
            } else if (line.isEmpty()) {

            } else {
                val num = line.toLong()
                possibles.add(num)
            }
        }
        for (i in possibles) {
            for (range in ranges) {
                val min = range.split("-")[0].toLong()
                val max = range.split("-")[1].toLong()
                if (i in min..max) {
                    result += 1
                    break
                }
            }
        }
        println(result)
    }

    fun part2() {
        var ranges = mutableListOf<Range>()
        var result = 0L
        for (line in input.lines()) {
            if (line.contains("-")) {
                ranges.add(Range(line.split("-")[0].toLong(), line.split("-")[1].toLong()))
            }
        }

        ranges.sortBy { it.min }

        do {
            for (i in 0 until ranges.size) {
                var currentRange = ranges[i]
                var nextRange = if (i < ranges.size - 1) ranges[i + 1] else null
                if (nextRange == null) {
                    continue
                }
                if (currentRange.max >= nextRange.min) {
                    nextRange.min = minOf(currentRange.min, nextRange.min)
                    nextRange.max = maxOf(currentRange.max, nextRange.max)
                    currentRange.min = 0
                    currentRange.max = 0
                }
            }
            ranges.removeAll { it.min == 0L && it.max == 0L }
            ranges.sortBy { it.min }
        } while(ranges.contains(Range(0, 0)))

        for (range in ranges) {
            result += range.max - range.min + 1
        }
        println(result)
    }
}

data class Range(var min: Long, var max: Long)