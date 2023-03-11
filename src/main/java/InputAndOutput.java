import java.util.Scanner;

// 输入输出
public class InputAndOutput {
    public static void main(String[] args) {
        System.out.print("A,");
        System.out.print("B,");
        System.out.print("C.");
        System.out.println();
        System.out.println("END");

        // 格式化输出
        double d = 12900000;
        System.out.println(d); // 1.29E7
        double e = 3.1415926;
        System.out.printf("%.2f\n", e); // 显示两位小数3.14
        System.out.printf("%.4f\n", e); // 显示4位小数3.1416
        // 把一个整数格式化成十六进制，并用0补足8位
        int n = 12345000;
        System.out.printf("n=%d, hex=%08x", n, n); // 注意，两个%占位符必须传入两个数

        // 输入
        Scanner scanner = new Scanner(System.in); // 创建Scanner对象
        System.out.print("Input your name: "); // 打印提示
        String name = scanner.nextLine(); // 读取一行输入并获取字符串
        System.out.print("Input your age: "); // 打印提示
        int age = scanner.nextInt(); // 读取一行输入并获取整数
        System.out.printf("Hi, %s, you are %d\n", name, age); // 格式化输出

        // 请帮小明同学设计一个程序，输入上次考试成绩（int）和本次考试成绩（int），然后输出成绩提高的百分比，保留两位小数位（例如，21.75%）。
        System.out.print("请输入上次考试成绩: ");
        int lastTime = scanner.nextInt();
        System.out.print("请输入这次考试成绩: ");
        int thisTime = scanner.nextInt();
        double percentage = 100.0 * (thisTime - lastTime) / lastTime;
        System.out.printf("成绩提高: %.2f%%", percentage);
    }
}
