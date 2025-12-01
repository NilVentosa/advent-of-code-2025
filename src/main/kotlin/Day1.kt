class Day1 {

    private val testInput = this::class.java.getResourceAsStream("/day1testinput")?.bufferedReader()?.readText()?.trim() ?: ""
    private val input = this::class.java.getResourceAsStream("/day1input")?.bufferedReader()?.readText()?.trim() ?: ""

    private var currentPosition = 50
    private var timesZeroPart1 = 0
    private var timesZeroPart2 = 0

    fun part1() {
        val parts = input.split("\n")
        for (part in parts) {
            val direction = part[0]
            val amount = part.substring(1).toInt()
            when(direction) {
                'L' -> moveLeft(amount)
                'R' -> moveRight(amount)
            }
            if (currentPosition == 0) {
                timesZeroPart1 = timesZeroPart1 + 1
            }
        }
        println(timesZeroPart1)
    }
    fun part2() {
        currentPosition = 50
        val parts = input.split("\n")
        for (part in parts) {
            val direction = part[0]
            val amount = part.substring(1).toInt()
            when(direction) {
                'L' -> for (i in 0 until amount) {
                    currentPosition = prev(currentPosition)
                    if (currentPosition == 0) {
                        timesZeroPart2 += 1
                    }
                }
                'R' -> for (i in 0 until amount) {
                    currentPosition = next(currentPosition)
                    if (currentPosition == 0) {
                        timesZeroPart2 += 1
                    }
                }
            }
        }
        println(timesZeroPart2)
    }

    fun moveRight(amount: Int) {
        currentPosition = (currentPosition + amount) % 100
    }

    fun moveLeft(amount: Int) {
        currentPosition = ((currentPosition - amount) % 100 + 100) % 100
    }

    fun next(number: Int): Int {
        if (number == 99) {
            return 0
        }
        return number + 1
    }

    fun prev(number: Int): Int {
        if (number == 0) {
            return 99
        }
        return number - 1
    }

}