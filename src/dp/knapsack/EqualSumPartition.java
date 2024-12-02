package dp.knapsack;

import java.util.Arrays;

public class EqualSumPartition {

    public static void main(String[] args) {
        int[] nums = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};//{1,2,3,5};//{1,5,11,5};

        System.out.println(canPartition(nums));
    }

    public static boolean canPartition(int[] nums) {
        int sum = 0;

        for(int num : nums){
            sum += num;
        }

        if(sum % 2 != 0){
            return false;
        }else {
            int[][] dp = new int[nums.length + 1][(sum / 2) + 1];
            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }
            return subsetSum(nums, sum / 2, nums.length, dp);
        }
    }

    public static boolean subsetSum(int[] nums, int sum, int n, int[][] dp) {
        if (sum == 0) {
            return true;
        }
        if (n == 0) {
            return false;
        }

        if(dp[n][sum] != -1){
            return dp[n][sum] == 1; // condition to check if 0 or 1 exists
        }

        if (nums[n - 1] <= sum) {
            dp[n][sum] = (
                    subsetSum(nums, sum - nums[n - 1], n - 1,dp) ||
                            subsetSum(nums, sum, n - 1,dp)
            ) ? 1 : 0;
        } else {
            dp[n][sum] =  subsetSum(nums, sum, n - 1, dp) ? 1 :0;
        }

        return dp[n][sum] == 1;
    }
}
