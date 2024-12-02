package dp.knapsack;


//{ Driver Code Starts
import java.io.*;
import java.util.*;


public class SubsetsWithSumEqualToTarget {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine().trim()); // Read number of test cases

        // Loop for each test case
        while (t-- > 0) {
            String line = read.readLine().trim(); // Read the array input
            String[] numsStr = line.split(" ");   // Split the input string by spaces
            int[] nums =
                    new int[numsStr.length]; // Convert string array to integer array
            for (int i = 0; i < numsStr.length; i++) {
                nums[i] = Integer.parseInt(numsStr[i]);
            }

            int target = Integer.parseInt(read.readLine().trim()); // Read target sum


            System.out.println(
                    perfectSum(nums, target)); // Call perfectSum and print the result
            System.out.println("~");          // Call perfectSum and print the result
        }
    }

    // Function to calculate the number of subsets with a given sum
    public static int perfectSum(int[] nums, int target) {
        // code here
        int n = nums.length;
        int[][] dp = new int[n+1][target+1];

        for(int[] d : dp){
            Arrays.fill(d,-1);
        }

        return solve(nums, target, dp, n);
    }

    public static int solve(int[] nums, int target, int[][] dp, int n){

        if(target == 0 && n ==0){ // considers multiple 0 in the input array.
            return 1;
        }

        if(n == 0){
            return 0;
        }

        if(dp[n][target] != -1){
            return dp[n][target];
        }

        if(nums[n-1] <= target){
            dp[n][target] = solve(nums,target-nums[n-1], dp, n-1) +
                    solve(nums, target,dp , n-1);
        }else{
            dp[n][target] = solve(nums, target,dp , n-1);
        }

        return dp[n][target];
    }


}

// } Driver Code Ends

