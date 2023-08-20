package collection.useStack;

import java.util.Deque;
import java.util.LinkedList;

// 练习
public class Test {
    public static void main(String[] args) {
        // 请利用Stack把一个给定的整数转换为十六进制：
        String hex = toHex(12500);
        if (hex.equalsIgnoreCase("30D4")) {
            System.out.println("测试通过");
        } else {
            System.out.println("测试失败");
        }
    }

    static String toHex(int n) {
        if (n == 0) {
            return "0";
        }

        // 使用Deque来保存十六进制位
        Deque<Character> stack = new LinkedList<>();
        char[] hexDigits = "0123456789ABCDEF".toCharArray();

        while (n != 0) {
            int remainder = n % 16;
            stack.push(hexDigits[remainder]);
            n /= 16;
        }
        
        StringBuilder hexSting = new StringBuilder();
        while (!stack.isEmpty()) {
            hexSting.append(stack.pop());
        }

        return hexSting.toString();
    }
}