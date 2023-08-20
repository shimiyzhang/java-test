package io.outputStream;

import java.io.*;

// 练习
// 请利用InputStream和OutputStream，编写一个复制文件的程序，它可以带参数运行：
// java CopyFile.java source.txt copy.txt
public class Test {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java Test.java <source> <target>");
            System.exit(1);
        }
        copy(args[0], args[1]);
    }

    static void copy(String source, String target) throws IOException {
        // 友情提示：测试时请使用无关紧要的文件
        try (InputStream input = new FileInputStream(source);
             OutputStream output = new FileOutputStream(target)
        ) {
            input.transferTo(output);
        }
    }
}
