package dp.unbounded_Knapsack;

public class CoinChange2_NumberOfWays {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;

        System.out.println(coinChange(coins, amount));
    }

    public static int coinChange(int[] coins, int amount) {
        int n = coins.length;

        int[][] dp = new int[n + 1][amount + 1];

//        return solveByDPTabulation(dp, coins, n, amount);
//        return solveByMemoization(coins,n,amount,dp); // fill the dp array with -1 first.
        return solveByRecursion(coins, n, amount);
    }

    public static int solveByDPTabulation(int[][] dp, int[] coins, int n, int amount) {

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (coins[i - 1] <= j) {
                    dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }


        return dp[n][amount];
    }

    public static int solveByRecursion(int[] coins, int n, int amount) {
        if (amount == 0) return 1;
        if (n == 0) return 0;

        int pick = 0, unpick = 0;
        if (coins[n - 1] <= amount) {
            pick = solveByRecursion(coins, n, amount - coins[n - 1]) + solveByRecursion(coins, n - 1, amount);
        } else {
            unpick = solveByRecursion(coins, n - 1, amount);
        }

        return pick + unpick;

    }


    public static int solveByMemoization(int[] coins, int n, int amount, int[][] dp) {
        if (amount == 0) return 1;
        if (n == 0) return 0;

        if (dp[n][amount] != -1) {
            return dp[n][amount];
        }

        int pick = 0, unpick = 0;
        if (coins[n - 1] <= amount) {
            pick = solveByMemoization(coins, n, amount - coins[n - 1], dp) + solveByMemoization(coins, n - 1, amount, dp);
        } else {
            unpick = solveByMemoization(coins, n - 1, amount, dp);
        }

        return dp[n][amount] = pick + unpick;

    }
}
