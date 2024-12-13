package dp.longest_Common_Subsequence;

import java.util.Arrays;

public class LongestCommonSubstring {
    public static void main(String[] args) {
        String s1 = "abcdjhfjafagfjafgkasgjftfgasvsfjashfgasfjcbamsfhasfsafh";
        String s2 = "dahgfhagfkaufygsakfhjahfyuagfysaukhfasfaskgfsahfahf";


        LongestCommonSubstring obj = new LongestCommonSubstring();
//        int ans = obj.findLengthByTabulationDP(s1, s2);
        int ans = obj.Memoization(s1, s2);
        System.out.println(ans);
    }

    private int Recursion(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();
        return solveRecursion(s1, s2, n, m, 0);
    }

    public int solveRecursion(String s1, String s2, int n, int m, int ans) {
        if (n == 0 || m == 0) {
            return ans;
        }
        int take = 0;
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            take = solveRecursion(s1, s2, n - 1, m - 1, ans + 1);
        }
        int excl1 = solveRecursion(s1, s2, n - 1, m, 0);
        int excl2 = solveRecursion(s1, s2, n, m - 1, 0);


        return Math.max(Math.max(ans, take), Math.max(excl1, excl2));
    }


    private int Memoization(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();
        int[][][] dp = new int[n + 1][m + 1][Math.min(n, m)];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return solveMemoization(s1, s2, n, m, 0, dp);
    }

    public int solveMemoization(String s1, String s2, int n, int m, int ans, int[][][] dp) {
        if (n == 0 || m == 0) {
            return ans;
        }

        if (dp[n][m][ans] != -1) {
            return dp[n][m][ans];
        }


        int take = 0;
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            take = solveMemoization(s1, s2, n - 1, m - 1, ans + 1, dp);
        }
        int excl1 = solveMemoization(s1, s2, n - 1, m, 0, dp);
        int excl2 = solveMemoization(s1, s2, n, m - 1, 0, dp);


        return dp[n][m][ans] = Math.max(Math.max(ans, take), Math.max(excl1, excl2));
    }


    public int findLengthByTabulationDP(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];


        return solveByTabulation(s1, s2, n, m, dp);
    }

    public int solveByTabulation(String s1, String s2, int n, int m, int[][] dp) {

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 0;
                }
            }

        }

        int max = Integer.MIN_VALUE;

        for (int[] arr : dp) {
            for (int num : arr) {
                max = Math.max(max, num);
            }
        }

        return max;

    }

}
