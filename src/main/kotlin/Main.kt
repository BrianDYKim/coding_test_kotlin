import jmBook.Boggle150

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val startVector = readLine().split(" ").map { token -> token.toInt() }
    val targetWord = readLine()
    var wordMap = arrayOf<Array<String>>()

    for (i in 0 until 5) {
        val strArray = readLine().split(" ").toTypedArray()

        wordMap = wordMap.plus(strArray)
    }

    val boggle150 = Boggle150(wordMap)

    println(boggle150.hasword(startVector[0], startVector[1], targetWord))
}