package collection.useList;

import java.util.ArrayList;
import java.util.List;

// 练习
public class Test {
    public static void main(String[] args) {
        // 给定一组连续的整数，例如：10，11，12，……，20，但其中缺失一个数字，试找出缺失的数字：
        //
        // 构造从start到end的序列：
        final int start = 10;
        final int end = 20;
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }
        // 随机删除List中的一个元素:
        int removed = list.remove((int) (Math.random() * list.size()));
        int found = findMissingNumber(start, end, list);
        System.out.println(list.toString());
        System.out.println("missing number: " + found);
        System.out.println(removed == found ? "测试成功" : "测试失败");
    }

    static int findMissingNumber(int start, int end, List<Integer> list) {
        for(int i = 0; i < list.size(); i++) {
            int n = list.get(i);
            if (n != start + i) {
                return start + i;
            }
        }
        return end;
    }
}
