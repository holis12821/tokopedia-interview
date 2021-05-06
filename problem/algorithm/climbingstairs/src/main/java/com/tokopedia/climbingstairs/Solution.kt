package com.tokopedia.climbingstairs

object Solution {
    fun climbStairs(n: Int): Long {
        // TODO, return in how many distinct ways can you climb to the top. Each time you can either climb 1 or 2 steps.
        // 1 <= n < 90
        return when {
            n <= 1 -> 1
            n in 1..89 -> {
                climbStairs(n - 1) + climbStairs(n - 2)
            }
            else -> -1
        }
    }
}
