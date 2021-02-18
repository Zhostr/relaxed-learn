package algorithm.base;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @description: 最大堆
 * @author: Zhoust
 * @date: 2020/06/06 下午5:04
 * @version: V1.0
 */
@Slf4j
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    /**
     * O(n)
     * @param array
     */
    public MaxHeap(E[] array) {
        data = new Array<>(array);

        //heapfiy 过程，从最后一个非叶节点开始，依次执行 sift down 操作
//        int lastChildIndex = size() - 1;
//        if (lastChildIndex > 0) {
//            int parentIndex = parent(lastChildIndex);
//            while (parentIndex >= 0) {
//                siftDown(parentIndex);
//                parentIndex--;
//            }
//        }
        if (array.length != 1) {
            for (int i = parent(array.length - 1); i >= 0; i--) {
                siftDown(i);
            }
        }
    }

    public MaxHeap() {
        this(10);
    }

    /**
     * 返回堆中的元素个数
     * @return
     */
    public int size() {
        return data.getSize();
    }

    /**
     * 判断堆是否为空
     * @return
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回指定索引位置的父亲节点的索引
      * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException();
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    //---------- 添加元素
    public void add(E e) {
        this.data.addLast(e);
        //sift up 上浮过程（维护大顶堆的性质）
        siftUp(data.getSize() - 1);
    }

    public E findMax() {
        if (size() == 0) {
            throw new IllegalArgumentException("堆为空，取出操作非法！");
        }
        return this.data.get(0);
    }

    /**
     * 取出最大元素后，再放入新元素
     * @param newVal
     * @return
     */
    public E replace(E newVal) {
        E topVal = findMax();
        this.data.set(0, newVal);

        int parentIndex = 0;
        int leftChildIndex;
        while ((leftChildIndex = leftChild(parentIndex)) < size()) {
            E parentVal = this.data.get(parentIndex);
            //左右孩子中值较大的元素下标
            int largerChildIndex = leftChildIndex;
            if (leftChildIndex + 1 < size() &&
                    this.data.get(leftChildIndex + 1).compareTo(this.data.get(leftChildIndex)) > 0) {
                largerChildIndex = leftChildIndex + 1;
            }

            if (parentVal.compareTo(this.data.get(largerChildIndex)) < 0) {
                this.data.swap(parentIndex, largerChildIndex);
                parentIndex = largerChildIndex;
            } else {
                break;
            }
        }
        return topVal;
    }

    /**
     * 剔除堆顶元素
     * @return
     */
    public E extractMax() {
        E returnVal = findMax();
        //先把最后一个元素挪到堆顶，之后再执行 sift down 逻辑

        //不能先调用 removeLast，只剩下最后一个元素的时候就有问题
        //E lastVal = this.data.removeLast();
        //this.data.set(0, lastVal);
        data.swap(0, size() - 1);
        this.data.removeLast();
        siftDown(0);
        return returnVal;
    }

    /**
     * 下沉操作（用于移除堆顶元素）
     * @param parentIndex
     */
    private void siftDown(int parentIndex) {
        int leftChildIndex;
        while ((leftChildIndex = leftChild(parentIndex)) < size()) {
            //左右孩子中值比较大的元素的下标
            int largeChildIndex = leftChildIndex;
            if (leftChildIndex + 1 < size() &&
                    this.data.get(leftChildIndex + 1).compareTo(this.data.get(leftChildIndex)) > 0) {
                largeChildIndex = leftChildIndex + 1;
            }

            if (this.data.get(parentIndex).compareTo(this.data.get(largeChildIndex)) < 0) {
                this.data.swap(parentIndex, largeChildIndex);
                parentIndex = largeChildIndex;
            } else {
                break;
            }
        }
    }

    /**
     * 上浮操作（用于插入元素）
     * @param childIndex
     */
    private void siftUp(int childIndex) {
        while (childIndex > 0 &&
                data.get(parent(childIndex)).compareTo(data.get(childIndex)) < 0) {
            //父亲节点的值小于孩子节点，需要交换
            this.data.swap(parent(childIndex), childIndex);
            childIndex = parent(childIndex);
        }
    }

    static class Main {

        public static void main(String[] args) {
            //测试 add、extractMax 函数是否正确
            /* MaxHeap<Integer> maxHeap = new MaxHeap<>();
            Random random = new Random();
            int n = 1000000;
            for (int i = 0; i < n; i++) {
                maxHeap.add(random.nextInt(100));
            }

            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = maxHeap.extractMax();
            }

            for (int i = 1; i < n; i++) {
                if (array[i-1] < array[i]) {
                    throw new IllegalArgumentException("异常");
                }
            }*/

            //测试 heapify 效率
            Random random = new Random();
            int n = 10000000;
            Integer[] testData = new Integer[n];
            for (int i = 0; i < n; i++) {
                testData[i] = random.nextInt(Integer.MAX_VALUE);
            }

            double time1 = testHeap(testData, false);
            System.out.println("不使用 heapify 耗时：" + time1 + "s");
            double time2 = testHeap(testData, true);
            System.out.println("使用 heapify 耗时：" + time2 + "s");
        }

        public static double testHeap(Integer[] data, boolean isHeapify) {
            long startTime = System.nanoTime();
            MaxHeap<Integer> heap;
            if (isHeapify) {
                //O(n)
                heap = new MaxHeap<>(data);
            } else {
                //O(nlogn)
                heap = new MaxHeap<>(data.length);
                for (int i = 0; i < data.length; i++) {
                    heap.add(data[i]);
                }
            }

            int[] arr = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                arr[i] = heap.extractMax();
            }
            for (int i = 1; i < data.length; i++) {
                if (arr[i - 1] < arr[i]) {
                    throw new IllegalArgumentException("Error");
                }
            }
            System.out.println("完成");
            long endTime = System.nanoTime();
            return (endTime - startTime) / 1000000000.0;
        }

    }


}