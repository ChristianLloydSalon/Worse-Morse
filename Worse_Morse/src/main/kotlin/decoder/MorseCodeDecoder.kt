package decoder

import kotlin.Exception

class MorseCodeDecoder {
    private val morseCode = mapOf(
        ".-"   to  "A",
        "-..." to  "B",
        "-.-." to  "C",
        "-.."  to  "D",
        "."    to  "E",
        "..-." to  "F",
        "--."  to  "G",
        "...." to  "H",
        ".."   to  "I",
        ".---" to  "J",
        "-.-"  to  "K",
        ".-.." to  "L",
        "--"   to  "M",
        "-."   to  "N",
        "---"  to  "O",
        ".--." to  "P",
        "--.-" to  "Q",
        ".-."  to  "R",
        "..."  to  "S",
        "-"    to  "T",
        "..-"  to  "U",
        "...-" to  "V",
        ".--"  to  "W",
        "-..-" to  "X",
        "-.--" to  "Y",
        "--.." to  "Z",
    )

    // a function that decodes a morse code
    fun decode(code: String?) {
        try {
            if (code == null) throw Exception("Input String is Empty.")

            if (!isMorseCode(code)) throw Exception("Invalid Morse Code.")

            // populate list with n 1s
            // where n = length of the morse code
            val list = List(code.length) { 1 }

            // set the pointer to the current element to 0
            val pointer = 0

            // decode each character of the morse code
            for (element in code) {
                val decoded = morseCode[element.toString()]
                print(decoded)
            }
            println()

            // finds all possible ways to decode the morse code
            findWays(pointer = pointer, list = list, code = code)
        }
        catch (e: Exception) {
            println(e.message)
        }
    }

    // a function that checks if a string is valid morse code
    private fun isMorseCode(morseCode: String) : Boolean {
        val regex = Regex(pattern = """[-.]+""")
        return regex.matches(input = morseCode)
    }

    // a function that finds all possible ways to decode the morse code
    private fun findWays(pointer: Int, list: List<Int>, code: String) {
        if(pointer + 1 > list.count() - 1)
            return

        val newList = mutableListOf<Int>()

        var decoded:String? = String()

        var i = 0
        var j = 0
        while (i < list.size) {
            val num: Int = (if (i != pointer) (list[i]) else (list[i] + list[i + 1]))
            newList.add(num)

            // get substring from j to j + num
            val str = code.substring(startIndex = j, endIndex = j + num)

            // check if substring is a valid morse code
            if (morseCode.containsKey(str))
                decoded += morseCode[str]
            else {
                decoded = null
                break
            }

            // move to next index if (i) is equal to pointer
            i = (if(i == pointer) (i + 2) else (i + 1))

            j += num
        }

        if(decoded != null) {
            println(decoded)
        }

        findWays(pointer = pointer, list = newList, code = code)

        // checks if the list contains elements greater than 4
        if (newList.any{ element -> element > 4 }) findWays(pointer = (pointer + 1), list = newList, code = code)

        findWays(pointer = (pointer + 1), list = list, code = code)
    }
}