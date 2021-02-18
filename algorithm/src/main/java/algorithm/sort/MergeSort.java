package algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: Zhoust
 * @date: 2020/05/19 上午10:29
 * @version: V1.0
 */
@Slf4j
public class MergeSort {

    public static void mergeSort(int[] array, int length) {
        merge_sort(array, 0, length - 1);
    }

    /**
     * 递推公式：merge_sort(startIndex, endIndex) = Sort(merge_sort(startIndex, midIndex), merge_sort(midIndex + 1, endIndex))
     * @param array
     * @param startIndex
     * @param endIndex
     */
    public static void merge_sort(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int midIndex = startIndex + (endIndex - startIndex) / 2;
        merge_sort(array, startIndex, midIndex);
        merge_sort(array, midIndex + 1, endIndex);
        //[startIndex, minIndex] 有序、[midIndex + 1, endIndex] 有序，得有这个额外的数组
        //额外数组的大小得是 [startIndex, endIndex] 中的元素个数
        int[] tempArray = new int[endIndex - startIndex + 1];
        int i = 0;
        int indexA = startIndex, indexB = midIndex + 1;
        while (indexA <= midIndex && indexB <= endIndex) {
            if (array[indexA] <= array[indexB]) {
                tempArray[i++] = array[indexA++];
            }
            else {
                tempArray[i++] = array[indexB++];
            }
        }
        while (indexA <= midIndex) {
            tempArray[i++] = array[indexA++];
        }
        while (indexB <= endIndex) {
            tempArray[i++] = array[indexB++];
        }

        i = 0;
        //复制 tempArray [0, length - 1] 对应 array 的 [startIndex, endIndex]
        while (i < tempArray.length) {
            array[startIndex++] = tempArray[i++];
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 2, 3, 1};
        mergeSort(array, array.length);
        log.info("array = {}", array);

    }

}