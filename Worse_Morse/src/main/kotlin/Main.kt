package decoder

fun main() {
    val decoder = MorseCodeDecoder()
    print("Enter morse code: ")
    val morseCode = readLine()
    decoder.decode(code = morseCode)
}