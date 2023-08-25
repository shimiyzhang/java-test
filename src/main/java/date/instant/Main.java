package date.instant;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

// Instant
// 小结
// Instant表示高精度时间戳，它可以和ZonedDateTime以及long互相转换。
public class Main {
    public static void main(String[] args) {
        // 我们已经讲过，计算机存储的当前时间，本质上只是一个不断递增的整数。
        // Java提供的System.currentTimeMillis()返回的就是以毫秒表示的当前时间戳。

        // 这个当前时间戳在java.time中以Instant类型表示，我们用Instant.now()获取当前时间戳，效果和System.currentTimeMillis()类似：
        Instant now = Instant.now();
        System.out.println(now.getEpochSecond()); // 秒
        System.out.println(now.toEpochMilli()); // 毫秒
        // 打印的结果类似：
        //
        // 1692924923
        // 1692924923424

        // 实际上，Instant内部只有两个核心字段：
        //
        // public final class Instant implements ... {
        //     private final long seconds;
        //     private final int nanos;
        // }
        // 一个是以秒为单位的时间戳，一个是更精确的纳秒精度。
        // 它和System.currentTimeMillis()返回的long相比，只是多了更高精度的纳秒。

        // 既然Instant就是时间戳，那么，给它附加上一个时区，就可以创建出ZonedDateTime：
        Instant ins = Instant.ofEpochSecond(1568568760);
        ZonedDateTime zdt = ins.atZone(ZoneId.systemDefault());
        System.out.println(zdt); // 2019-09-16T01:32:40+08:00[Asia/Shanghai]

        // 可见，对于某一个时间戳，给它关联上指定的ZoneId，就得到了ZonedDateTime，继而可以获得了对应时区的LocalDateTime。
        // 所以，LocalDateTime，ZoneId，Instant，ZonedDateTime和long都可以互相转换：
        //
        // ┌─────────────┐
        // │LocalDateTime│────┐
        // └─────────────┘    │    ┌─────────────┐
        //                    ├───>│ZonedDateTime│
        // ┌─────────────┐    │    └─────────────┘
        // │   ZoneId    │────┘           ▲
        // └─────────────┘      ┌─────────┴─────────┐
        //                      │                   │
        //                      ▼                   ▼
        //               ┌─────────────┐     ┌─────────────┐
        //               │   Instant   │<───>│    long     │
        //               └─────────────┘     └─────────────┘
        // 转换的时候，只需要留意long类型以毫秒还是秒为单位即可。

        // Instant 和 long 之间的转换：
        Instant instant = Instant.now(); // 当前时间
        long millis = instant.toEpochMilli(); // 转换为毫秒级的long时间戳
        Instant convertedInstant = Instant.ofEpochMilli(millis); // 将毫秒级 long 时间戳转换为 Instant
        System.out.println(convertedInstant);

        // Instant 和 ZonedDateTime 之间的转换：
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("America/New_York")); // 将 Instant 转换为特定时区的 ZonedDateTime

        ZonedDateTime convertedZonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo")); // 获取特定时区的当前时间
        Instant convertedIns = convertedZonedDateTime.toInstant(); // 将 ZonedDateTime 转换为 Instant
        System.out.println(convertedIns);
        // 需要注意的是，Instant 是不包含时区信息的，而 ZonedDateTime 包含了时区信息。在进行转换时，请确保考虑时区的影响。
    }
}
