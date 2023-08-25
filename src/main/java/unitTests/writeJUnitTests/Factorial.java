package unitTests.writeJUnitTests;

// 假定我们编写了一个计算阶乘的类，它只有一个静态方法来计算阶乘：
// n! = 1×2×3×...×n
// 代码如下：
public class Factorial {
    public static long fact(long n) {
        long r = 1;
        for (long i = 1; i <= n; i++) {
            r *= i;
        }
        return r;
    }
}
