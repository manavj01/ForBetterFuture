package dp.knapsack;

import java.io.*;
import java.util.*;
import java.util.HashMap;


public class MinimumSumPartition {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {

            String line = br.readLine();
            String[] tokens = line.split(" ");

            // Create an ArrayList to store the integers
            ArrayList<Integer> array = new ArrayList<>();

            // Parse the tokens into integers and add to the array
            for (String token : tokens) {
                array.add(Integer.parseInt(token));
            }

            int[] arr = new int[array.size()];
            int idx = 0;
            for (int i : array) arr[idx++] = i;

            // int k = Integer.parseInt(br.readLine());
            // Create Solution object and find closest sum

            int ans = minDifference(arr);

            System.out.print(ans);

            System.out.println(); // New line after printing the results
        }
    }

    public static int minDifference(int[] arr) {

        int n = arr.length;
        int sum = 0;

        for (int val : arr) sum += val;

        boolean[][] dp = new boolean[n + 1][sum + 1];

        subsetSum(arr, dp, n, sum);

        int max = Integer.MIN_VALUE;
        for (int j = 0; j <=dp[0].length / 2; j++) {
            if (dp[n][j] == true){
                max = Math.max(max,j);
            }
        }

        System.out.println(max);
        for (boolean[] a : dp){
            System.out.println(Arrays.toString(a));
        }
        return sum - (2 * max);

    }

    public static void subsetSum(int[] arr, boolean[][] dp, int n, int sum) {

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < dp.length; i++) { // tabulation
            for (int j = 1; j < dp[0].length; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
    }
}

