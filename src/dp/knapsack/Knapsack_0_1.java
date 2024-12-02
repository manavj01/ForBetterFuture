package dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Knapsack_0_1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());

        while (testCases-- > 0) {
            // Read capacity
            int capacity = Integer.parseInt(br.readLine());

            // Read values
            String[] valInput = br.readLine().split(" ");
            int[] val = new int[valInput.length];
            for (int i = 0; i < valInput.length; i++) {
                val[i] = Integer.parseInt(valInput[i]);
            }

            // Read weights
            String[] wtInput = br.readLine().split(" ");
            int[] wt = new int[wtInput.length];
            for (int i = 0; i < wtInput.length; i++) {
                wt[i] = Integer.parseInt(wtInput[i]);
            }

            // Call the knapSack function and print the result
            System.out.println(Solution.knapSack(capacity, val, wt));
            System.out.println("~");
        }
    }
}

// } Driver Code Ends


class Solution {
    // Function to return max value that can be put in knapsack of capacity.

    static int knapSack(int capacity, int[] val, int[] wt) {

        int n = wt.length - 1;
        int[][] dp = new int[capacity + 1][n + 1];
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[0].length; j++) {
//                dp[i][j] = -1;
//            }
//        }

        int ans = solveDP(capacity, val, wt, n, dp); //solveRecursion(capacity, val, wt, n);

        return ans;
    }

    static int solveRecursion(int capacity, int[] val, int[] wt, int n) {
        if (capacity < 0 || n < 0) {
            return 0;
        }

        if (wt[n] <= capacity) {
            return Math.max((val[n] + solveRecursion(capacity - wt[n], val, wt, n - 1)),
                    (solveRecursion(capacity, val, wt, n - 1)));
        } else if (wt[n] > 0) {
            return solveRecursion(capacity, val, wt, n - 1);
        }

        return 0;
    }

//    static int solveDP(int capacity, int[] val, int[] wt, int n, int[][] dp) {
//        if (capacity < 0 || n < 0) {
//            return 0;
//        }
//
//        if (dp[capacity][n] != -1) {
//            return dp[capacity][n];
//        }
//
//        if (wt[n] <= capacity) {
//            return dp[capacity][n] = Math.max((val[n] + solveDP(capacity - wt[n], val, wt, n - 1, dp)),
//                    (solveDP(capacity, val, wt, n - 1, dp)));
//        } else if (wt[n] > capacity) {
//            return dp[capacity][n] = solveDP(capacity, val, wt, n - 1, dp);
//        }
//        return 0;
//    }

    static int solveDP(int capacity, int[] val, int[] wt, int n, int[][] dp) {
        if (capacity < 0 || n < 0) {
            return 0;
        }

        if (dp[capacity][n] != -1) {
            return dp[capacity][n];
        }

        if (wt[n] <= capacity) {
            return dp[capacity][n] = Math.max((val[n] + solveDP(capacity - wt[n], val, wt, n - 1, dp)),
                    (solveDP(capacity, val, wt, n - 1, dp)));
        } else if (wt[n] > capacity) {
            return dp[capacity][n] = solveDP(capacity, val, wt, n - 1, dp);
        }
        return 0;
    }

}



