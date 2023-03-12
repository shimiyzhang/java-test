// 循环
public class Circulation {
    public static void main(String[] args) {
        // // while循环
        // int sum = 0; // 累加的和，初始化为0
        // int n = 1;
        // while (n <= 100) { // 循环条件是n <= 100
        //     sum = sum + n; // 把n累加到sum中
        //     n ++; // n自身加1
        // }
        // System.out.println(sum); // 5050

        // // 使用while计算从m到n的和
        // int sum = 0;
        // int m = 20;
        // int n = 100;
        // // 使用while计算M+...+N:
        // while (m <= n) {
        //     sum += m;
        //     m++;
        // }
        // System.out.println(sum);

        // // do while循环
        // int sum = 0;
        // int n = 1;
        // do {
        //     sum = sum + n;
        //     n ++;
        // } while (n <= 100);
        // System.out.println(sum);

        // // 使用do while循环计算从m到n的和
        // int sum = 0;
        // int m = 20;
        // int n = 100;
        // do {
        //     sum += m;
        //     m++;
        // } while (m <= n);
        // System.out.println(sum);

        // // for循环
        // int sum = 0;
        // for (int i = 1; i <= 100; i++) {
        //     sum = sum + i;
        // }
        // System.out.println(sum);
        // int[] ns = {1, 4, 9, 16, 25};
        // int sum = 0;
        // for (int i = 0; i < ns.length; i++) {
        //     System.out.println("i = " + i + ", ns[i] = " + ns[i]);
        //     sum = sum + ns[i];
        // }
        // System.out.println("sum = " + sum);

        // // for each循环
        // int[] ns = { 1, 4, 9, 16, 25 };
        // for (int n : ns) {
        //     System.out.println(n);
        // }

        // // 给定一个数组，请用for循环倒序输出每一个元素：
        // int[] ns = {1, 4, 9, 16, 25};
        // for (int i = ns.length - 1; i >= 0; i--) {
        //     System.out.println(ns[i]);
        // }

        // // 利用for each循环对数组每个元素求和：
        // int[] ns = {1, 4, 9, 16, 25};
        // int sum = 0;
        // for (int i : ns) {
        //     sum += i;
        // }
        // System.out.println(sum); // 55

        // 圆周率π可以使用公式计算：
        // PI/4 = 1 - 1/3 + 1/5 - 1/7 + 1/9-...
        // // 请利用for循环计算π
        // double pi = 0;
        // double x = 4.0;
        // for (int i = 1; i < 99999999; i = i + 2) {
        //     pi += x * 1 / i;
        //     x = -x;
        // }
        // System.out.println(pi);

        // // break和continue
        // int sum = 0;
        // for (int i=1; ; i++) {
        //     sum = sum + i;
        //     if (i == 100) {
        //         break;
        //     }
        // }
        // System.out.println(sum);

        // break语句总是跳出自己所在的那一层循环
        for (int i=1; i<=10; i++) {
            System.out.println("i = " + i);
            for (int j=1; j<=10; j++) {
                System.out.println("j = " + j);
                if (j >= i) {
                    break;
                }
            }
            // break跳到这里
            System.out.println("breaked");
        }

        // continue则是提前结束本次循环，直接继续执行下次循环
        int sum = 0;
        for (int i=1; i<=10; i++) {
            System.out.println("begin i = " + i);
            if (i % 2 == 0) {
                continue; // continue语句会结束本次循环
            }
            sum = sum + i;
            System.out.println("end i = " + i);
        }
        System.out.println(sum); // 25
    }
}
