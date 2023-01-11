import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ArrayTest {
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static int[] reverse(int[] list) {
        int[] result = new int[list.length];

        for (int i = 0, j = result.length - 1; i < list.length; i++, j--) {
            result[j] = list[i];
        }
        return result;
    }

    public static void main(String[] args) {
        printArray(new int[]{3, 1, 2, 6, 4, 2});

        int[] result = reverse(new int[]{1,2,3,4,5,6});
        for (int i = 0; i < result.length; i++) {
            System.out.println("reverse" + result[i]);
        }

        double[] myList = {1.9,2.9,3.4,3.5};

        // 打印所有数组元素
        for (int i = 0; i < myList.length; i++) {
            System.out.println(myList[i] + " ");
        }
        for (double element: myList) {
            System.out.println(element);
        }
        // 计算所有元素的总和
        double total = 0;
        for (int i = 0; i < myList.length; i++) {
            total += myList[i];
        }
        System.out.println("Total is " + total);
        // 查找最大元素
        double max = myList[0];
        for (int i = 0; i < myList.length; i++) {
            if (myList[i] > max) max = myList[i];
        }
        System.out.println("Max is " + max);

        // 多维数组
        String[][] s = new String[2][];
        s[0] = new String[2];
        s[1] = new String[3];
        s[0][0] = "Good";
        s[0][1] = "Luck";
        s[1][0] = "to";
        s[1][1] = "you";
        s[1][2] = "!";
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                System.out.print(s[i][j] + " ");
            }
        }

        // 数组和字符串的转换处理
        String str = "helloworld";
        char[] data = str.toCharArray();// 将字符串转为数组
        for (int x = 0; x < data.length; x++) {
            System.out.print(data[x] + "  ");
            data[x] -= 32;
            System.out.print(data[x] + "  ");
        }
        System.out.println(new String(data));
    }
}