package algorithm.base;

import algorithm.base.Array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: Zhoust
 * @date: 2020/05/15 上午6:24
 * @version: V1.0
 */
public class ForTest {

    public static void main(String[] args) {
        search(new int[]{5, 1, 2, 3,4}, 4);
    }


    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[low] < nums[mid]) {
                //把 mid 分配到了左边
                //在区间 [low, mid] 递增，[mid + 1, high] 包含反转部分
                if (target >= nums[low] && target <= nums[mid]) {
                    //target 在区间 [low, mid] 内，排除 [mid+1, high]
                    high = mid;
                }
                else {
                    low = mid + 1;
                }
            }
            else {
                //这样会把 mid 分配到右边，为了跟上面的保持一样，虽然已经确定在 [mid, high] 区间递增，但认为 [mid+1, high] 递增
                //在区间 [mid, high] 递增，[low, mid - 1] 包含反转部分
                if (nums[mid+1] >= target && nums[high] <= target) {
                    //target 在区间 [mid + 1, high] 内，排除 [low, mid]
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
        }
        if (nums[low] == target) {
            return low;
        }
        return -1;
    }


}