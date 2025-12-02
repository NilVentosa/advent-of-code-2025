class Day2 {
    private val input = this::class.java.getResourceAsStream("/day2input")?.bufferedReader()?.readText()?.trim() ?: ""

    fun part1and2() {
        var result1 = 0L
        var result2 = 0L
        val ranges = input.split(",")
        ranges.forEach { range ->
            val low = range.split("-")[0].toLong()
            val high = range.split("-")[1].toLong()
            for (i in low..high) {
                if (isInvalidPart1(i.toString())) {
                    result1 += i
                }
                if (isInvalidPart2(i.toString())) {
                    result2 += i
                }
            }
        }
        println(result1)
        println(result2)
    }

    fun isInvalidPart1(string: String): Boolean {
        if (string.length % 2 == 0) {
            return isInvalid(string, string.length / 2)
        }
        return false
    }

    fun isInvalidPart2(string: String): Boolean {
        for (chunkSize in 1..string.length/2) {
            if (string.length % chunkSize == 0) {
                if (isInvalid(string, chunkSize)) {
                    return true
                }
            }
        }
        return false
    }

    fun isInvalid(string: String, chunkSize: Int): Boolean {
        val strings = string.chunked(chunkSize)
        return strings.distinct().size == 1
    }
}