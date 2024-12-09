package dp.unbounded_Knapsack;

import java.util.Arrays;

public class CointChange1_MinimumCoinsNeeded {
    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int amount = 5;

        System.out.println(coinChange(coins, amount));
    }

    public static int coinChange(int[] coins, int amount) {
        int n = coins.length;

        int[][] dp = new int[n + 1][amount + 1];

//        return solveByDPTabulation(dp, coins, n, amount);
        return solveByRecursion(coins, n, amount);
//        return solveByMemoization(coins, n, amount,dp); // fill dp array with -1
    }

    public static int solveByDPTabulation(int[][] dp, int[] coins, int n, int amount) {

        Arrays.fill(dp[0], Integer.MAX_VALUE - 1); // it is impossible to create an amount with no coins. so mathematically we write : Positive infinity (as we are finding the minimum so we take max integer value)
        // also we reduce 1 from infinity as if it is ever used/added 1 to it, it will make the +ve infi to -ve infi, so we reduce one number.

        for (int i = 1, j = 1; j < dp[0].length; j++) { // we do this for the first coin only
            if (j % coins[0] != 0) {
                dp[i][j] = Integer.MAX_VALUE - 1;
            } else {
                dp[i][j] = j / coins[0];
            }
        }

        for (int i = 2; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (coins[i - 1] <= j) {
                    dp[i][j] = Math.min(dp[i][j - coins[i - 1]] + 1, dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }


        return dp[n][amount] == Integer.MAX_VALUE - 1 ? -1 : dp[n][amount];
    }

    public static int solveByRecursion(int[] coins, int n, int amount) {
        if (n == 0 && amount == 0) return 0;

        if (n == 0) {
            return Integer.MAX_VALUE - 1;
        }


        int pick = Integer.MAX_VALUE, unpick = Integer.MAX_VALUE;
        if (coins[n - 1] <= amount) {
            pick = Math.min(solveByRecursion(coins, n, amount - coins[n - 1]) + 1, solveByRecursion(coins, n - 1, amount));
        } else {
            unpick = solveByRecursion(coins, n - 1, amount);
        }

        return Math.min(pick, unpick);

    }

    public static int solveByMemoization(int[] coins, int n, int amount, int[][] dp) {
        if (n == 0 && amount == 0) return 0;

        if (n == 0) {
            return Integer.MAX_VALUE - 1;
        }

        if (dp[n][amount] != -1) return dp[n][amount];

        int pick = Integer.MAX_VALUE - 1, unpick = Integer.MAX_VALUE - 1;
        if (coins[n - 1] <= amount) {
            pick = Math.min(solveByMemoization(coins, n, amount - coins[n - 1], dp) + 1, solveByMemoization(coins, n - 1, amount, dp));
        } else {
            unpick = solveByMemoization(coins, n - 1, amount, dp);
        }

        return dp[n][amount] = Math.min(pick, unpick);

    }
}
