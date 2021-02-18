package algorithm.base;

/**
 * @description:
 * @author: Zhoust
 * @date: 2020/06/07 下午4:46
 * @version: V1.0
 */
public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    /**
     * 入队
     * @param e
     */
    void enqueue(E e);

    /**
     * 出队
     * @return
     */
    E dequeue();

    /**
     * 查看堆首元素
     * @return
     */
    E peek();
}