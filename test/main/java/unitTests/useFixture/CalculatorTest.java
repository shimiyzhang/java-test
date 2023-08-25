package unitTests.useFixture;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class CalculatorTest {
    // 这个类的功能很简单，但是测试的时候，我们要先初始化对象，
    // 我们不必在每个测试方法中都写上初始化代码，
    // 而是通过@BeforeEach来初始化，通过@AfterEach来清理资源：
    Calculator calculator;

    @BeforeEach
    public void setUp() {
        this.calculator = new Calculator();
    }

    @AfterEach
    public void tearDown() {
        this.calculator = null;
    }

    @Test
    void testAdd() {
        assertEquals(100, this.calculator.add(100));
        assertEquals(150, this.calculator.add(50));
        assertEquals(130, this.calculator.add(-20));
    }

    @Test
    void testSub() {
        assertEquals(-100, this.calculator.sub(100));
        assertEquals(-150, this.calculator.sub(50));
        assertEquals(-130, this.calculator.sub(-20));
    }
    // 在CalculatorTest测试中，有两个标记为@BeforeEach和@AfterEach的方法，它们会在运行每个@Test方法前后自动运行。

    // 上面的测试代码在JUnit中运行顺序如下：
    //
    // for (Method testMethod : findTestMethods(CalculatorTest.class)) {
    //     var test = new CalculatorTest(); // 创建Test实例
    //     invokeBeforeEach(test);
    //         invokeTestMethod(test, testMethod);
    //     invokeAfterEach(test);
    // }
    // 可见，@BeforeEach和@AfterEach会“环绕”在每个@Test方法前后。

    // 还有一些资源初始化和清理可能更加繁琐，而且会耗费较长的时间，例如初始化数据库。
    // JUnit还提供了@BeforeAll和@AfterAll，它们在运行所有@Test前后运行，顺序如下：
    //
    // invokeBeforeAll(CalculatorTest.class);
    // for (Method testMethod : findTestMethods(CalculatorTest.class)) {
    //     var test = new CalculatorTest(); // 创建Test实例
    //     invokeBeforeEach(test);
    //         invokeTestMethod(test, testMethod);
    //     invokeAfterEach(test);
    // }
    // invokeAfterAll(CalculatorTest.class);

    // 因为@BeforeAll和@AfterAll在所有@Test方法运行前后仅运行一次，因此，它们只能初始化静态变量，例如：
    // public class DatabaseTest {
    //     static Database db;
    //
    //     @BeforeAll
    //     public static void initDatabase() {
    //         db = createDb(...);
    //     }
    //
    //     @AfterAll
    //     public static void dropDatabase() {
    //         ...
    //     }
    // }
    // 事实上，@BeforeAll和@AfterAll也只能标注在静态方法上。
    // 因为它们在测试类被加载时执行，而测试类还未被实例化。
}
