package collection.useList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProTest {
    public static void main(String[] args) {
        // 增强版：和上述题目一样，但整数不再有序，试找出缺失的数字：
        // 构造从start到end的序列：
        final int start = 10;
        final int end = 20;
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }
        // 洗牌算法shuffle可以随机交换List中的元素位置:
        Collections.shuffle(list);
        // 随机删除List中的一个元素:
        int removed = list.remove((int) (Math.random() * list.size()));
        int found = findMissingNumber(start, end, list);
        System.out.println(list.toString());
        System.out.println("missing number: " + found);
        System.out.println(removed == found ? "测试成功" : "测试失败");
    }

    static int findMissingNumber(int start, int end, List<Integer> list) {
        // 期望的连续整数总和
        int expectedSum = (start + end) * (end - start + 1) / 2;

        // 输入List的总和
        int actualSum = 0;
        for (int num : list) {
            actualSum += num;
        }

        return expectedSum - actualSum; // 缺失的数字即为差值
    }
}
