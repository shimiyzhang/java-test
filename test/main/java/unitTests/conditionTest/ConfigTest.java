package unitTests.conditionTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigTest {
    Config config;

    @BeforeEach
    public void setUp() {
        this.config = new Config();
    }

    // 我们想要测试getConfigFile()这个方法，但是在Windows上跑，和在Linux上跑的代码路径不同，
    // 因此，针对两个系统的测试方法，其中一个只能在Windows上跑，另一个只能在Mac/Linux上跑：

    // 因此，我们给上述两个测试方法分别加上条件如下：
    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testWindows() {
        assertEquals("C:\\test.ini", config.getConfigFile("test.ini"));
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC})
    void testLinuxAndMac() {
        assertEquals("/user/local/test.cfg", config.getConfigFile("test.cfg"));
    }

    // @EnableOnOs就是一个条件测试判断。

    // 我们来看一些常用的条件测试：
    // 不在Windows平台执行的测试，可以加上@DisabledOnOs(OS.WINDOWS)：
    @Test
    @DisabledOnOs(OS.WINDOWS)
    void testOnNonWindowsOs() {
        // TODO: this test is disabled on windows
    }

    // 只能在Java 9或更高版本执行的测试，可以加上@DisabledOnJre(JRE.JAVA_8)：
    @Test
    @DisabledOnJre(JRE.JAVA_8)
    void testOnJava9OrAbove() {
        // TODO: this test is disabled on java 8
    }

    // 只能在64位操作系统上执行的测试，可以用@EnabledIfSystemProperty判断：
    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void testOnlyOn64bitSystem() {
        // TODO: this test is only run on 64 bit system
    }

    // 需要传入环境变量DEBUG=true才能执行的测试，可以用@EnabledIfEnvironmentVariable：
    @Test
    @EnabledIfEnvironmentVariable(named = "DEBUG", matches = "true")
    void testOnlyOnDebugMode() {
        // TODO: this test is only run on DEBUG=true
    }

    // 当我们在JUnit中运行所有测试的时候，JUnit会给出执行的结果。
}
