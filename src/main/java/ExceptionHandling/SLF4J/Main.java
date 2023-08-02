package ExceptionHandling.SLF4J;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// SLF4J和Logback
// SLF4J类似于Commons Logging，也是一个日志接口，而Logback类似于Log4j，是一个日志的实现。
// 与Log4j的使用方法类似

// 小结
//        SLF4J和Logback可以取代Commons Logging和Log4j；
//
//        始终使用SLF4J的接口写入日志，使用Logback只需要配置，不需要修改代码。
public class Main {
    public static void main(String[] args) {
        int score = 99;

        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("score={}", score); // 传入一个带占位符的字符串，用后面的变量自动替换占位符
        logger.error("score={}", score);
    }
}
