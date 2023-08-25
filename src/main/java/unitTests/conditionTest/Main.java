package unitTests.conditionTest;

// 条件测试
// 小结
// 条件测试是根据某些注解在运行期让JUnit自动忽略某些测试。
public class Main {
    public static void main(String[] args) {
        // 在运行测试的时候，有些时候，我们需要排出某些@Test方法，不要让它运行，这时，我们就可以给它标记一个@Disabled：
        //
        // @Disabled
        // @Test
        // void testBug101() {
        //     // 这个测试不会运行
        // }

        // 为什么我们不直接注释掉@Test，而是要加一个@Disabled？
        //
        // 这是因为注释掉@Test，JUnit就不知道这是个测试方法，
        // 而加上@Disabled，JUnit仍然识别出这是个测试方法，只是暂时不运行。
        // 它会在测试结果中显示：
        //
        // Tests run: 68, Failures: 2, Errors: 0, Skipped: 5

        // 类似@Disabled这种注解就称为条件测试，JUnit根据不同的条件注解，决定是否运行当前的@Test方法。
    }
}
