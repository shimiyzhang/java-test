package ExceptionHandling.JDKLogging;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

// 使用JDK Logging
// 小结
//        日志是为了替代System.out.println()，可以定义格式，重定向到文件等；
//
//        日志可以存档，便于追踪问题；
//
//        日志记录可以按级别分类，便于打开或关闭某些级别；
//
//        可以根据配置文件调整日志，无需修改代码；
//
//        Java标准库提供了java.util.logging来实现日志功能。
public class Main {
    public static void main(String[] args) {
//        如何使用日志？
//        因为Java标准库内置了日志包java.util.logging，我们可以直接用。先看一个简单的例子：
        Logger logger = Logger.getGlobal();
        logger.info("start process...");
        logger.warning("memory is running out");
        logger.fine("ignored.");
        logger.severe("process will be terminated...");

        Logger l = Logger.getLogger(Main.class.getName());
        l.info("start process...");
        try {
            "".getBytes("invalidCharsetName");
        } catch (UnsupportedEncodingException e) {
            l.severe("unsupported charset");
        }
        l.info("end process...");
    }
}
