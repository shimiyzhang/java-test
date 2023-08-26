package unitTests.ParametricTest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {
    // 要用参数化测试的方法来测试，我们不但要给出输入，还要给出预期输出。
    // 因此，测试方法至少需要接收两个参数：
    // @ParameterizedTest
    // void testCapitalize(String input, String result) {
    //     assertEquals(result, StringUtils.capitalize(input));
    // }

    // 现在问题来了：参数如何传入？
    //
    // 最简单的方法是通过@MethodSource注解，它允许我们编写一个同名的静态方法来提供测试参数：
    @ParameterizedTest
    @MethodSource()
    void testCapitalize(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }

    static List<Arguments> testCapitalize() {
        return List.of(
                Arguments.of("abc", "Abc"),
                Arguments.of("APPLE", "Apple"),
                Arguments.of("gooD", "Good")
        );
    }
    // 上面的代码很容易理解：静态方法testCapitalize()返回了一组测试参数，每个参数都包含两个String，正好作为测试方法的两个参数传入。
    //
    //  如果静态方法和测试方法的名称不同，@MethodSource也允许指定方法名。但使用默认同名方法最方便。

    // 另一种传入测试参数的方法是使用@CsvSource，它的每一个字符串表示一行，一行包含的若干参数用,分隔，
    // 因此，上述测试又可以改写如下：
    @ParameterizedTest
    @CsvSource({ "abc, Abc", "APPLE, Apple", "gooD, Good" })
    void testCapitalizeUsingCxsSource(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }

    // 如果有成百上千的测试输入，那么，直接写@CsvSource就很不方便。
    // 这个时候，我们可以把测试数据提到一个独立的CSV文件中，然后标注上@CsvFileSource：
    @ParameterizedTest
    @CsvFileSource(resources = {"/test-capitalize.csv"})
    void testCapitalizeUsingCsvFile(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }
    // JUnit只在classpath中查找指定的CSV文件，因此，test-capitalize.csv这个文件要放到test目录下，内容如下：
    // apple, Apple
    // HELLO, Hello
    // JUnit, Junit
    // reSource, Resource
}
