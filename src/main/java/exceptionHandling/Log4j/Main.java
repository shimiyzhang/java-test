package exceptionHandling.Log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// 使用Log4j
// Commons Logging，可以作为“日志接口”来使用。
// 而真正的“日志实现”可以使用Log4j。

//Log4j是一个组件化设计的日志系统，它的架构大致如下：
//
//        log.info("User signed in.");
//        │
//        │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
//        ├──>│ Appender │───>│  Filter  │───>│  Layout  │───>│ Console  │
//        │   └──────────┘    └──────────┘    └──────────┘    └──────────┘
//        │
//        │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
//        ├──>│ Appender │───>│  Filter  │───>│  Layout  │───>│   File   │
//        │   └──────────┘    └──────────┘    └──────────┘    └──────────┘
//        │
//        │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
//        └──>│ Appender │───>│  Filter  │───>│  Layout  │───>│  Socket  │
//        │   └──────────┘    └──────────┘    └──────────┘    └──────────┘

//当我们使用Log4j输出一条日志时，Log4j自动通过不同的Appender把同一条日志输出到不同的目的地。例如：
//
//        console：输出到屏幕；
//        file：输出到文件；
//        socket：通过网络输出到远程计算机；
//        jdbc：输出到数据库
//
//        在输出日志的过程中，通过Filter来过滤哪些log需要被输出，哪些log不需要被输出。例如，仅输出ERROR级别的日志。
//        最后，通过Layout来格式化日志信息，例如，自动添加日期、时间、方法名称等信息。

// 最佳实践
//        在开发阶段，始终使用Commons Logging接口来写入日志，并且开发阶段无需引入Log4j。
//        如果需要把日志写入文件， 只需要把正确的配置文件和Log4j相关的jar包放入classpath，就可以自动把日志切换成使用Log4j写入，无需修改任何代码。

// 小结
//        通过Commons Logging实现日志，不需要修改代码即可使用Log4j；
//
//        使用Log4j只需要把log4j2.xml和相关jar放入classpath；
//
//        如果要更换Log4j，只需要移除log4j2.xml和相关jar；
//
//        只有扩展Log4j时，才需要引用Log4j的接口（例如，将日志加密写入数据库的功能，需要自己开发）。
public class Main {
    public static void main(String[] args) {
        Log log = LogFactory.getLog(Main.class);
        log.info("log4j info");
        log.error("log4j error");
    }
}
