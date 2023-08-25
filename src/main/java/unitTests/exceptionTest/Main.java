package unitTests.exceptionTest;

// 异常测试
// 小结
// 测试异常可以使用assertThrows()，期待捕获到指定类型的异常；
//
// 对可能发生的每种类型的异常都必须进行测试。
public class Main {
    public static void main(String[] args) {
        // 在Java程序中，异常处理是非常重要的。
        //
        // 我们自己编写的方法，也经常抛出各种异常。对于可能抛出的异常进行测试，本身就是测试的重要环节。
        //
        // 因此，在编写JUnit测试的时候，除了正常的输入输出，我们还要特别针对可能导致异常的情况进行测试。

        // 我们仍然用Factorial举例：
        // 在方法入口，我们增加了对参数n的检查，如果为负数，则直接抛出IllegalArgumentException。

        // 现在，我们希望对异常进行测试。

        // 练习
        // 观察Factorial.fact()方法，注意到由于long型整数有范围限制，
        //
        // 当我们传入参数21时，得到的结果是-4249290049419214848，而不是期望的51090942171709440000，
        //
        // 因此，当传入参数大于20时，Factorial.fact()方法应当抛出ArithmeticException。
        //
        // 请编写测试并修改实现代码，确保测试通过。
    }
}
