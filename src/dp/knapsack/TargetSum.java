package dp.knapsack;

public class TargetSum {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(new TargetSum().findTargetSumWays(nums, target));
    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        int n = nums.length;

        if ((sum + target) % 2 != 0) return 0;


        if (sum < target) return 0;

        int nTarget = (target + sum) / 2;

        int[][] dp = new int[n + 1][nTarget + 1];

        return subsetSum(nums, nTarget, dp, n);

    }

    public int subsetSum(int[] nums, int nTarget, int[][] dp, int n) {

        dp[0][0] = 1;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {

                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][nTarget];
    }
}
