package dp.unbounded_Knapsack;

import java.io.*;
import java.util.*;

public class RodCuttingProblem {

    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(in.readLine().trim());
        while (t-- > 0) {
            String[] s = in.readLine().trim().split(" ");
            int[] arr = new int[s.length];
            for (int i = 0; i < s.length; i++) arr[i] = Integer.parseInt(s[i]);

            RodCuttingProblem ob = new RodCuttingProblem();
            out.println(ob.cutRod(arr));

            out.println("~");
        }
        out.close();
    }


    public int cutRod(int[] price) {
        int n = price.length;

        int[][] dp = new int[n + 1][n + 1];

        return solve(price, dp, n);

    }

    public int solve(int[] price, int[][] dp, int n) {


        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (i <= j) {
                    dp[i][j] = Math.max(price[i - 1] + dp[i][j - i], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        for (int[] a : dp) {
            System.out.println(Arrays.toString(a));
        }

        return dp[n][n];
    }

}


