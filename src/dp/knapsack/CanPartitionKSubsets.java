package dp.knapsack;

import java.util.Arrays;

public class CanPartitionKSubsets {
    public static void main(String[] args) {
//        int[] nums = {4,3,2,3,5,2,1};
        int[] nums = {6,6,6,7,7,7,10,10,10};
        int k= 3;
        System.out.println(canPartitionKSubsets(nums,k));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;

        for (int num : nums)
            sum += num;

        if(sum % k != 0 ) return false;

        int target = sum/k;

        return (solve(nums,n-1,target,new int[k]));

    }
    public static boolean solve(int[] nums,int index, int target, int[] sums){
        if(index < 0 ) return true;

        for (int i=0; i< sums.length; i++){
            if(nums[index] + sums[i] >target) continue;

            sums[i] += nums[index];
            if (solve(nums,index-1,target,sums)) return true;
            sums[i] -= nums[index];

            if (sums[i] == 0) break;
        }

        return false;

    }
}
