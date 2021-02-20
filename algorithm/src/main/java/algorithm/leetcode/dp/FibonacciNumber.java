package algorithm.leetcode.dp;

/**
 * @description: https://leetcode-cn.com/problems/fibonacci-number/
 * @author: Zest
 * @date: 2021/02/20 9:03 上午
 * @version: V1.0
 */
public class FibonacciNumber {

    /**
     * 最基础的递归
     * 缺点：存在被重复计算的节点
     *               4
     *            /   ｜
     *           3     2
     *          /｜   / |
     *         2  1  1  0
     *        /\
     *       1 0
     * @param n
     * @return
     */
    public int fib1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib1(n-1) + fib1(n-2);
    }


    public int fib2()

}