package leetcode

class DestinationCity {
    fun destCity(paths: List<List<String>>): String {
        val pathMap = paths.map { it.first() to it.last() }.toMap()

        var dest = paths.first().first()

        while (pathMap[dest] != null) {
            dest = pathMap[dest]!!
        }

        return dest
    }
}

