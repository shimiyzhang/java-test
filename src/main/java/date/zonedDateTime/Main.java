package date.zonedDateTime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

// ZonedDateTime
// 小结
// ZonedDateTime是带时区的日期和时间，可用于时区转换；
//
// ZonedDateTime和LocalDateTime可以相互转换。
public class Main {
    public static void main(String[] args) {
        // LocalDateTime总是表示本地日期和时间。
        // ZonedDateTime表示一个带时区的日期和时间。

        // 可以简单地把ZonedDateTime理解成LocalDateTime加ZoneId。
        // ZoneId是java.time引入的新的时区类，注意和旧的java.util.TimeZone区别。

        // 要创建一个ZonedDateTime对象，有以下几种方法，一种是通过now()方法返回当前时间：
        ZonedDateTime zdt = ZonedDateTime.now(); // 默认时区
        ZonedDateTime zdt2 = ZonedDateTime.now(ZoneId.of("America/New_York")); // 用指定时区获取当前时间
        System.out.println(zdt);
        System.out.println(zdt2);
        // 观察打印的两个ZonedDateTime，发现它们时区不同，但表示的时间都是同一时刻（毫秒数不同是执行语句时的时间差）：
        //
        // 2023-08-24T13:57:48.191967900+08:00[Asia/Shanghai]
        // 2023-08-24T01:57:48.194114500-04:00[America/New_York]

        // 另一种方式是通过给一个LocalDateTime附加一个ZoneId，就可以变成ZonedDateTime：
        LocalDateTime ldt = LocalDateTime.of(2023, 8, 24, 13, 57, 48);
        ZonedDateTime zbj = ldt.atZone(ZoneId.systemDefault());
        ZonedDateTime zny = ldt.atZone(ZoneId.of("America/New_York"));
        System.out.println(zbj);
        System.out.println(zny);
        // 以这种方式创建的ZonedDateTime，它的日期和时间与LocalDateTime相同，但附加的时区不同，因此是两个不同的时刻：
        //
        // 2023-08-24T13:57:48+08:00[Asia/Shanghai]
        // 2023-08-24T13:57:48-04:00[America/New_York]

        // 时区转换
        // 要转换时区，首先我们需要有一个ZonedDateTime对象，
        // 然后，通过withZoneSameInstant()将关联时区转换到另一个时区，转换后日期和时间都会相应调整。

        // 下面的代码演示了如何将北京时间转换为纽约时间：
        // 以中国时区获取当前时间:
        ZonedDateTime zbj2 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        // 转换为纽约时间:
        ZonedDateTime zny2 = zbj2.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(zbj2);
        System.out.println(zny2);

        // 要特别注意，时区转换的时候，由于夏令时的存在，不同的日期转换的结果很可能是不同的。
        // 涉及到时区时，千万不要自己计算时差，否则难以正确处理夏令时。

        // 有了ZonedDateTime，将其转换为本地时间就非常简单：
        ZonedDateTime zdt3 = ZonedDateTime.now(ZoneId.of("America/New_York"));
        LocalDateTime ldt3 = zdt3.toLocalDateTime();
        System.out.println(ldt3);
        // 转换为LocalDateTime时，直接丢弃了时区信息。
    }
}
