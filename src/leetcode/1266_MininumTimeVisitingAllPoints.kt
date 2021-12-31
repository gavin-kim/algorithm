package leetcode

class MinTimeToVisitAllPoints {
    fun minTimeToVisitAllPoints(points: Array<IntArray>): Int {
        var sum = 0

        points.reduce { pointA, pointB ->
            sum += getSeconds(pointA, pointB)
            pointB
        }

        return sum
    }

    fun getSeconds(pointA: IntArray, pointB: IntArray): Int {
        val x = Math.abs(pointA.first() - pointB.first())
        val y = Math.abs(pointA.last() - pointB.last())

        return maxOf(x, y)
    }
}
