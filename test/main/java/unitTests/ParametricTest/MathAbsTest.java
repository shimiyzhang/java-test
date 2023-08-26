package unitTests.ParametricTest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathAbsTest {
    // 假设我们想对Math.abs()进行测试，先用一组正数进行测试：
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 100})
    void testAbs(int x) {
        assertEquals(x, Math.abs(x));
    }

    // 再用一组负数进行测试：
    @ParameterizedTest
    @ValueSource(ints = {-1, -5, -100})
    void testAbsNegative(int x) {
        assertEquals(-x, Math.abs(x));
    }
    // 注意到参数化测试的注解是@ParameterizedTest，而不是普通的@Test。
}
