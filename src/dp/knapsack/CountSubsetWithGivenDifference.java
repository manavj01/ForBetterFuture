package dp.knapsack;

import java.io.*;
import java.util.*;

public class CountSubsetWithGivenDifference {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());

        while (tc-- > 0) {

            String[] str = br.readLine().trim().split(" ");
            int[] a = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                a[i] = Integer.parseInt(str[i]);
            }
            String[] nk = br.readLine().trim().split(" ");
            int k = Integer.parseInt(nk[0]);

            int ans = countPartitions(a, k);

            System.out.println(ans);
            System.out.println("~");
        }
    }

    static int countPartitions(int[] arr, int d) {
        int sum = 0;

        for (int num : arr) sum += num;

        int s1 = (sum + d) / 2;

        if ((sum + d) % 2 != 0) return 0;

        int n = arr.length;

        /** for Memoization
         *
         */

//        int[][] dp = new int[n + 1][s1 + 1];
//
//        for (int[] a : dp) {
//            Arrays.fill(a, -1);
//        }

//        int ans = subsetSum(arr, s1, n, dp);
//        return dp[n][s1];


        /**
         * For DP Tabulation
         */

        int[][] dp = new int[n + 1][s1 + 1];

        return subsetSumTabulation(arr, s1, n, dp);
    }

    static int subsetSum(int[] arr, int sum, int n, int[][] dp) {
        if (sum == 0 && n == 0) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }

        if (dp[n][sum] != -1) {
            return dp[n][sum];
        }

        if (arr[n - 1] <= sum) {
            dp[n][sum] = subsetSum(arr, sum - arr[n - 1], n - 1, dp) + subsetSum(arr, sum, n - 1, dp);
        } else {
            dp[n][sum] = subsetSum(arr, sum, n - 1, dp);
        }

        return dp[n][sum];
    }

    static int subsetSumTabulation(int[] arr, int sum, int n, int[][] dp) {

        dp[0][0] = 1;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {

                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        for (int[] d : dp){
            System.out.println(Arrays.toString(d));
        }

        return dp[n][sum];
    }
}

