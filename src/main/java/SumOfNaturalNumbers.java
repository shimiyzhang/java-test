import java.util.Scanner;

// 计算前N个自然数的和
// sum(1...N) = (1+N)*N/2
public class SumOfNaturalNumbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            int n = scan.nextInt();
            if (n == 0) {
                System.out.println("EXIT");
                break;
            }
            int sum = (1 + n) * n / 2;
            System.out.println("前" + n + "个自然数的和是" + sum);
        }
    }
}
