package jmBook

import java.util.Arrays

// 종만북 150 페이지 boggle 예제
class Boggle150(private val wordMap: Array<Array<String>>) {
    // 방향 정의
    private lateinit var directionVectors: Array<IntArray>;

    // 맵 사이즈 가져와서 초기화
    private val mapSize = wordMap.size

    init {
        val directionX = arrayOf<Int>(1, 0, -1, 0, 1, -1, -1, 1)
        val directionY = arrayOf<Int>(0, 1, 0, -1, 1, 1, -1, -1)

        directionVectors = directionX.mapIndexed { index, _ ->
            val componentX = directionX[index]
            val componentY = directionY[index]

            intArrayOf(componentX, componentY)
        }.toTypedArray()
    }

    fun hasword(x: Int, y: Int, word: String): Boolean {
        // 기저 조건 명시
        // 1. 범위 밖을 읽으려고하면 실패
        if (!isPossible(x, y)) return false
        // 2. 첫 글자가 일치하지 않으면 실패
        if (word[0].toString() != wordMap[x][y]) return false
        // 3. 단어의 길이가 1이면 성공
        if (word.length == 1) return true

        // 점화관계 -> (x,y)가 정당함은 이미 보증이 되어있는 상태
        directionVectors.forEach { vector ->
            val nextX = x + vector[0]
            val nextY = y + vector[1]

            // 한 글자씩 제외시키면서 판단하면 된다
            if (hasword(nextX, nextY, word.substring(1))) return true
        }

        return false
    }

    // 해당 좌표가 가능한 좌표인지 검사하는 메소드
    private fun isPossible(x: Int, y: Int): Boolean {
        val xPossible = x >= 0 && x < this.mapSize
        val yPossible = y >= 0 && y < this.mapSize

        return xPossible && yPossible
    }
}