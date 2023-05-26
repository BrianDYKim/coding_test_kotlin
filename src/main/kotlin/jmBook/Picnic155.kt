package jmBook

import java.util.Arrays

/**
 * @author Doyeop Kim
 * @since 2023/05/26
 */
class Picnic155 {
    // 테스트 케이스를 여러번 실행하는 메소드
    fun execute(): Unit = with(System.`in`.bufferedReader()) {
        val testCases = readLine().toInt()

        for (i in 0 until testCases) {
            solution()
        }
    }

    fun solution(): Unit = with(System.`in`.bufferedReader()) {
        // 학생의 수와, 짝의 수를 구한다
        val tempList = readLine().split(" ").map { it -> it.toInt() }
        val studentNumber: Int = tempList[0]
        val pairNumber: Int = tempList[1]

        // 짝의 정보를 행렬의 형태로 저장한다
        val pairInfo = Array(studentNumber) { BooleanArray(studentNumber) }

        val pairInputList = readLine().split(" ").map { it -> it.toInt() }

        for (i in pairInputList.indices step 2) {
            val firstFriend = pairInputList[i]
            val secondFriend = pairInputList[i + 1]

            pairInfo[firstFriend][secondFriend] = true
            pairInfo[secondFriend][firstFriend] = true
        }

        val taken = BooleanArray(studentNumber) { false }

        val result = calculatePossibilities(studentNumber, pairInfo, taken)

        println(result)
    }

    // 짝을 지을 수 있는 방법의 수를 계산하는 메소드
    private fun calculatePossibilities(studentNumber: Int, pairInfo: Array<BooleanArray>, taken: BooleanArray): Int {
        // 기저 조건 명시 -> 짝이 지어지지않은 가장 빠른 학생부터 찾아내서 그 학생부터 짝을 지어준다
        var firstFreeStudent = -1

        for (i in taken.indices) {
            if (!taken[i]) {
                firstFreeStudent = i
                break
            }
        }

        // 만약에 모든 학생이 짝을 지은 경우 바로 종료한다
        if (firstFreeStudent == -1) return 1

        var answer = 0

        // 서로 친구인 경우를 매칭해서 answer를 증가시킨다 -> firstFree부터 먼저 탐색한다
        for (pairWith in firstFreeStudent + 1 until studentNumber) {
            // 아직 짝이 지어지지는 않았지만 서로 친구인 경우
            if (!taken[pairWith] && pairInfo[firstFreeStudent][pairWith]) {
                taken[firstFreeStudent] = true
                taken[pairWith] = true

                answer += calculatePossibilities(studentNumber, pairInfo, taken)

                taken[firstFreeStudent] = false
                taken[pairWith] = false
            }
        }

        return answer
    }
}