import java.util.Arrays;

// 数组操作
public class ArrayOperation {
    public static void main(String[] args) {
        // // 遍历数组
        // int[] ns = {1,4,9,16,25};
        // System.out.println(ns);
        // for (int i = 0; i < ns.length; i++) {
        //     int n = ns[i];
        //     System.out.println(n);
        // }
        // for (int n : ns) {
        //     System.out.println(n);
        // }
        // // Java标准库提供了Arrays.toString()，可以快速打印数组内容
        // System.out.println(Arrays.toString(ns));
        // // 请按倒序遍历数组并打印每个元素
        // for (int i = ns.length - 1; i >=0; i--) {
        //     System.out.println(ns[i]);
        // }

        // 数组排序
        // 冒泡排序
        // int[] ns = { 28, 12, 89, 73, 65, 18, 96, 50, 8, 36, 33 };
        // // 排序前:
        // System.out.println(Arrays.toString(ns));
        // for (int i = 0; i < ns.length - 1; i++) {
        //     for (int j = 0; j < ns.length - i - 1; j++) {
        //         if (ns[j] > ns[j + 1]) {
        //             int temp = ns[j];
        //             ns[j] = ns[j + 1];
        //             ns[j + 1] = temp;
        //         }
        //     }
        // }
        // // 排序后:
        // System.out.println(Arrays.toString(ns));
        // // 使用Arrays.sort()排序
        // Arrays.sort(ns);
        // System.out.println(Arrays.toString(ns));
        // 实现对数组进行降序排序
        // 排序前:
        // System.out.println(Arrays.toString(ns));
        // // 方法一
        // for (int i = 0; i < ns.length - 1; i++) {
        //     for (int j = 0; j < ns.length - i - 1; j++) {
        //         if (ns[j] < ns[j + 1]) {
        //             int temp = ns[j];
        //             ns[j] = ns[j + 1];
        //             ns[j + 1] = temp;
        //         }
        //     }
        // }
        // // 方法二
        // Arrays.sort(ns);
        // int length = ns.length;
        // for (int i = 0; i < length / 2; i ++) {
        //     int temp = ns[i];
        //     ns[i] = ns[length - i - 1];
        //     ns[length - i - 1] = temp;
        // }
        // // 排序后:
        // System.out.println(Arrays.toString(ns));

        // // 多维数组
        // // 二维数组
        // int [][] ns = {
        //         {1,2,3,4},
        //         {5,6,7,8},
        //         {9,10,11,12}
        // };
        // System.out.println(ns.length); // 3
        // int[] arr0 = ns[1];
        // System.out.println(arr0.length); // 4
        // System.out.println(ns[1][2]); // 7
        // // 打印一个二维数组，可以使用两层嵌套的for循环
        // for (int[] arr : ns) {
        //     for (int n : arr) {
        //         System.out.print(n);
        //         System.out.print(',');
        //     }
        //     System.out.println();
        // }
        // // Arrays.deepToString()
        // System.out.println(Arrays.deepToString(ns));
        // // 使用二维数组可以表示一组学生的各科成绩，请计算所有学生的平均分
        // // 用二维数组表示的学生成绩:
        // int[][] scores = {
        //         { 82, 90, 91 },
        //         { 68, 72, 64 },
        //         { 95, 91, 89 },
        //         { 67, 52, 60 },
        //         { 79, 81, 85 },
        // };
        // double sum = 0;
        // int length = 0;
        // for (int[] score : scores) {
        //     length += score.length;
        //     for (int s : score) {
        //         sum += s;
        //     }
        // }
        // double average = sum / length;
        // System.out.println(average);

        // 命令行参数
        for (String arg : args) {
            System.out.println(arg);
            if ("-version".equals(arg)) {
                System.out.println("v1.0");
                break;
            }
        }
    }
}
