import nju.zt.designpattern.SingletonHungry;
import nju.zt.designpattern.SingletonLazy;
import nju.zt.sort.Sort;

import javax.net.ssl.SNIHostName;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = {-1, 0, 1, 2, -1, -4};
    }

    public int threeSumClosest(int[] nums, int target) {
        int ans = Integer.MAX_VALUE;
        Arrays.sort(nums);
        //枚举第一个数
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; //确保第一个数不重复
            }
            int right = nums.length - 1;
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int a = nums[i] + nums[j] + nums[k];
                if (a == target) {
                    return target;
                }
                if (Math.abs(target - a) < Math.abs(target - ans)) {
                    ans = a;
                }
                if (a > target) {
                    int k0 = k - 1;
                    while (j < k0 && nums[k0] == nums[k]) {
                        k0--;
                    }
                    k = k0;
                } else {
                    int j0 = j - 1;
                    while (j0 < k && nums[j0] == nums[j]) {
                        j0++;
                    }
                    j = j0;
                }
            }
        }
        return ans;

    }
}