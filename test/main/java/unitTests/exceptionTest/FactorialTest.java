package unitTests.exceptionTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class FactorialTest {
    // 在JUnit测试中，我们可以编写一个@Test方法专门测试异常：
    //
    // JUnit提供assertThrows()来期望捕获一个指定的异常。
    // 第二个参数Executable封装了我们要执行的会产生异常的代码。
    // 当我们执行Factorial.fact(-1)时，必定抛出IllegalArgumentException。
    // assertThrows()在捕获到指定异常时表示通过测试，未捕获到异常，或者捕获到的异常类型不对，均表示测试失败。
    @Test
    void testNegative() {
        // assertThrows(IllegalArgumentException.class, new Executable() {
        //     @Override
        //     public void execute() throws Throwable {
        //         Factorial.fact(-1);
        //     }
        // });

        // Java 8开始引入了函数式编程，所有单方法接口都可以简写如下：
        assertThrows(IllegalArgumentException.class, () -> {
            Factorial.fact(-1);
        });
    }

    @Test
    void testOverRange() {
        assertThrows(ArithmeticException.class, () -> {
            Factorial.fact(21);
        });
    }
}
