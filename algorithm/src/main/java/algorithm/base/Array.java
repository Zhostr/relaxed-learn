package algorithm.base;

/**
 * @description:
 * @author: Zhoust
 * @date: 2020/06/06 上午8:40
 * @version: V1.0
 */
public class Array<E> {

    private E[] data;

    /** 数组中的有效元素个数（size 指向被添加元素将要放置的下标） **/
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public Array() {
        this(10);
    }

    public Array(E[] array) {
        this(array.length);
        for (int i = 0; i < array.length; i++) {
            data[i] = array[i];
        }
        size = array.length;
    }

    /** 获取数组中的元素个数 **/
    public int getSize() {
        return size;
    }

    /** 获取数组的容量 **/
    public int getCapacity() {
        return data.length;
    }

    /** 数组是否为空 **/
    public boolean isEmpty() {
        return size == 0;
    }

    //---------- 添加元素 ----------
    public void addLast(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    //---------- 查询 ----------
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get failed. Index is illegal");
        }
        return data[index];
    }

    //---------- 修改 ----------
    public void set(int index, E newE) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Set failed. Index is illegal");
        }
        data[index] = newE;
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal");
        }
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /** 查询数组中是否包含元素 e **/
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(i)) {
                return true;
            }
        }
        return false;
    }

    /** 查找元素 e 首次出现的下标，找不到就返回 -1 **/
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(i)) {
                return i;
            }
        }
        return -1;
    }

    private void resize(int newCapacity) {
        E[] tempArray = (E[])new Object[newCapacity];
        //System.arraycopy(data, 0, tempArray, 0, size);
        for (int i = 0; i < size; i++) {
            tempArray[i] = data[i];
        }
        data = tempArray;
    }

    /**
     * 在 index 位置插入元素 e
     * [0, 1, 2 ... size - 1]
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Required index >= 0 and index <= size.");
        }

        //扩容
        if (size == data.length) {
            resize(data.length * 2);
        }
        //将 [index, size - 1] 的元素向后移动
        int i = size - 1;
        while (i >= index) {
            data[i+1]= data[i];
            i--;
        }
        data[index] = e;
        size++;
    }

    //---------- 删除元素 ----------
    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    //如果数组中存在 e 这个元素，只删除第一个
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    /** 删除索引 index 位置的元素 **/
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index illegal");
        }
        E delVal = data[index];
        //将 [index + 1, size - 1] 位置的元素向前移动一个位置
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        if (size == data.length / 2) {
            resize(data.length / 2);
        }
        return delVal;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Array: size = %s, capacity = %s\n", size, data.length));
        stringBuilder.append("data = [");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i != size - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}