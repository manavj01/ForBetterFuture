package dp.knapsack;

import java.io.*;
import java.util.*;

public class SubsetSumProblem {
//{ Driver Code Starts

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {

            String[] input_line = read.readLine().trim().split("\\s+");
            int N = input_line.length;
            int arr[] = new int[N];
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(input_line[i]);
            int sum = Integer.parseInt(read.readLine());

            if (isSubsetSumRecursion(arr, sum))
                System.out.println("true");
            else
                System.out.println("false");

            System.out.println("~");
        }
    }


    static Boolean isSubsetSum(int[] arr, int sum) {
        boolean[][] dp = new boolean[arr.length + 1][sum + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
//                if (i == 0) {   // by default whole array is false. so dont need to add false after doing true
//                    dp[i][j] = false;
//                }
                if (j == 0) {
                    dp[i][j] = true;
                }
            }
        }

        for (boolean[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (arr[i - 1] <= j) {
                    dp[i][j] = (dp[i - 1][j - arr[i - 1]]) || (dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }

        }

        return dp[arr.length][sum];

    } // tabulation dp

    static Boolean isSubsetSumRecursion(int[] arr, int sum) {
        int n = arr.length;
        return solveByRecursion(arr, sum, n);
    } // recursion

    static Boolean solveByRecursion(int[] arr, int sum, int n) {
        if (sum == 0) return true;

        if (n == 0) return false;

        if (arr[n - 1] <= sum) {
            return solveByRecursion(arr, sum - arr[n - 1], n - 1) ||
                    solveByRecursion(arr, sum, n - 1);
        } else
            return solveByRecursion(arr, sum, n - 1);
    }


    static Boolean isSubsetSumMemoization(int[] arr, int sum) {
        int n = arr.length;
        int[][] dp = new int[n + 1][sum + 1];

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        return solveByMemoization(arr, sum, n, dp);
    }

    static Boolean solveByMemoization(int[] arr, int sum, int n, int[][] dp) {
        if (sum == 0) return true;

        if (n == 0) return false;

        if (dp[n][sum] != -1) {
            return dp[n][sum] == 1 ? true : false;
        }

        if (arr[n - 1] <= sum) {
            dp[n][sum] = (
                    solveByMemoization(arr, sum - arr[n - 1], n - 1, dp) ||
                    solveByMemoization(arr, sum, n - 1, dp)
            ) ? 1 : 0;
        } else {
            dp[n][sum] = solveByMemoization(arr, sum, n - 1, dp) ? 1 : 0;
        }

        return dp[n][sum] == 1;
    }
}



