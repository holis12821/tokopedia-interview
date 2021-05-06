package com.tokopedia.minimumpathsum

import java.util.ArrayList

object Solution {
    fun minimumPathSum(matrix: Array<IntArray>): Int {
        // TODO, find a path from top left to bottom right which minimizes the sum of all numbers along its path, and return the sum
        // below is stub
        if (matrix.isEmpty()) {
            return 0
        }
        return sumPath(0,0, matrix)
    }

    private fun sumPath(
            si: Int,
            sj: Int,
            matrix: Array<IntArray>
    ) : Int {
        val ei = matrix.size-1 //get last index
        val ej = matrix[0].size-1

        if (si == ei && sj == ej)
            return matrix[si][sj]

        if (si > ei || sj > ej)
            return Int.MAX_VALUE

        val op1 = sumPath(si+1, sj, matrix)
        val op2 = sumPath(si, sj+1, matrix)

        return matrix[si][sj] + Math.min(op1, op2)
    }
}
