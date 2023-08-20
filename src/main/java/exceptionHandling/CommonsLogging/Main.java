package exceptionHandling.CommonsLogging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// 使用Commons Logging
// Commons Logging是一个第三方日志库，它是由Apache创建的日志模块。
// 它可以挂接不同的日志系统，并通过配置文件指定挂接的日志系统。
// 默认情况下，Commons Logging自动搜索并使用Log4j（Log4j是另一个流行的日志系统），如果没有找到Log4j，再使用JDK Logging。
// 小结
//        Commons Logging是使用最广泛的日志模块；
//
//        Commons Logging的API非常简单；
//
//        Commons Logging可以自动检测并使用其他日志模块。
public class Main {
    public static void main(String[] args) {
//        Commons Logging定义了6个日志级别：
//        FATAL
//        ERROR
//        WARNING
//        INFO
//        DEBUG
//        TRACE
//        默认级别是INFO。
        Log log = LogFactory.getLog(Main.class);
        log.info("start...");
        log.warn("end.");

//        使用重载方法：info(String, Throwable)记录异常
        try {
            System.out.println(Integer.parseInt(null));
        } catch (Exception e) {
            log.error("got exception", e);
        }
    }
}

//    在实例方法中引用Log，通常定义一个实例变量：
class Person {
    protected final Log log = LogFactory.getLog(getClass());
//    实例变量log的获取方式是LogFactory.getLog(getClass())，
//    子类可以直接使用该log实例。
//    也可以使用LogFactory.getLog(Person.class)，

    void foo() {
        log.info("foo");
    }
}

// 在子类中使用父类实例化的log:
class Student extends Person {
    void bar() {
        log.info("bar");
    }
}
