package algorithm.sort;

/**
 * @description:
 * @author: Zhoust
 * @date: 2020/06/17 上午9:27
 * @version: V1.0
 */
public class QuickSort {

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = quick_sort(array, low, high);
            quickSort(array, low, mid);
            quickSort(array, mid + 1, high);
        }
    }

    public static int quick_sort(int[] array, int low, int high) {
        while (low < high) {
            int temp = array[low];
            //从右往左找到第一个小于 temp 的下标
            while (low < high && array[high] < temp) {
                high--;
            }
            array[low] = array[high];
            //从左往右找，找到第一个大于等于 temp 的下标
            while (low < high && array[low] >= temp) {
                low++;
            }
            array[high] = array[low];
        }
        return low;
    }

}