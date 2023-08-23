package io.useFiles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

// 使用Files
// 小结
// 对于简单的小文件读写操作，可以使用Files工具类简化代码。
public class Main {
    public static void main(String[] args) throws IOException {
        // 从Java 7开始，提供了Files这个工具类，能极大地方便我们读写文件。

        // 虽然Files是java.nio包里面的类，但他俩封装了很多读写文件的简单方法，
        // 例如，我们要把一个文件的全部内容读取为一个byte[]，可以这么写：
        Path path = Path.of("src/readme.txt");
        byte[] data = Files.readAllBytes(path);

        // 如果是文本文件，可以把一个文件的全部内容读取为String：
        // 默认使用UTF-8编码读取：
        String content = Files.readString(path);
        // 可指定编码:
        String content2 = Files.readString(Path.of("src", "readme.txt"), StandardCharsets.UTF_8);
        // 按行读取并返回每行内容:
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        // 写入文件也非常方便：
        // 写入二进制文件:
        byte[] data2 = {1, 2, 3};
        Files.write(path, data2);
        // 写入文本并指定编码:
        Files.writeString(path, "文本内容...", StandardCharsets.UTF_8);
        // 按行写入文本:
        List<String> lines2 = List.of("line1", "line2");
        Files.write(path, lines2);

        // 此外，Files工具类还有copy()、delete()、exists()、move()等快捷方法操作文件和目录。

        // 最后需要特别注意的是，Files提供的读写方法，受内存限制，只能读写小文件，例如配置文件等，不可一次读入几个G的大文件。
        // 读写大型文件仍然要使用文件流，每次只读写一部分文件内容。
    }
}
