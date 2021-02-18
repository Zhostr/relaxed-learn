package algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Zhoust
 * @date: 2020/05/15 上午8:30
 * @version: V1.0
 */
@Slf4j
public class SelectionSort {
    
    private SelectionSort(){}

    public static void sort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            //寻找 [i, n) 区间内最小值的下标
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}