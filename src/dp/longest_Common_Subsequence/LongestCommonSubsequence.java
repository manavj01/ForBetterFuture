package dp.longest_Common_Subsequence;

import java.util.Arrays;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String text1 = "pmjghexybyrgzczy"; //"abcde"; //
        String text2 = "hafcdqbgncrcbihkd"; //"ace"; //

        System.out.println(longestCommonSubsequence(text1, text2));
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        /*
        For Tabulation
         */
        int[][] dp = new int[n + 1][m + 1];


        return solveByTabulation(text1, text2, n, m, dp);


       /* For Memoization

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < dp.length; i++) { // initialization for memoization dp
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = -1;
                }
            }
        }

        return solveByMemoization(text1, text2, n, m, dp);

        */

        /* For Recursion
       return solveByRecursion(text1, text2, n, m);
        */

    }

    private static int solveByTabulation(String text1, String text2, int n, int m, int[][] dp) {

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {


                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {

                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
        }


        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }

        return dp[n][m];

    }

    private static int solveByMemoization(String text1, String text2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return 0;
        }

        if (dp[n][m] != -1) {
            return dp[n][m];
        }

        if (text1.charAt(n - 1) == text2.charAt(m - 1)) {
            dp[n][m] = 1 + solveByMemoization(text1, text2, n - 1, m - 1, dp);
        } else {
            dp[n][m] = Math.max(solveByMemoization(text1, text2, n - 1, m, dp),
                    solveByMemoization(text1, text2, n, m - 1, dp));
        }

        return dp[n][m];

    }

    public static int solveByRecursion(String text1, String text2, int n, int m) {
        if (n == 0 || m == 0) {
            return 0;
        }

        if (text1.charAt(n - 1) == text2.charAt(m - 1)) {
            return 1 + solveByRecursion(text1, text2, n - 1, m - 1);
        } else {
            return Math.max(solveByRecursion(text1, text2, n - 1, m),
                    solveByRecursion(text1, text2, n, m - 1));
        }
    }

}

