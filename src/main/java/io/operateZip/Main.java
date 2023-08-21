package io.operateZip;

import java.io.*;
import java.util.zip.*;

// 操作Zip
// 小结
// ZipInputStream可以读取zip格式的流，ZipOutputStream可以把多份数据写入zip包；
//
// 配合FileInputStream和FileOutputStream就可以读写zip文件。
public class Main {
    public static void main(String[] args) {
        // ZipInputStream是一种FilterInputStream，它可以直接读取zip包的内容：
        // ┌───────────────────┐
        // │    InputStream    │
        // └───────────────────┘
        //           ▲
        //           │
        // ┌───────────────────┐
        // │ FilterInputStream │
        // └───────────────────┘
        //           ▲
        //           │
        // ┌───────────────────┐
        // │InflaterInputStream│
        // └───────────────────┘
        //           ▲
        //           │
        // ┌───────────────────┐
        // │  ZipInputStream   │
        // └───────────────────┘
        //           ▲
        //           │
        // ┌───────────────────┐
        // │  JarInputStream   │
        // └───────────────────┘
        //
        // 另一个JarInputStream是从ZipInputStream派生，
        // 它增加的主要功能是直接读取jar文件里面的MANIFEST.MF文件。
        // 因为本质上jar包就是zip包，只是额外附加了一些固定的描述文件。

        // 读取zip包
        // 我们要创建一个ZipInputStream，通常是传入一个FileInputStream作为数据源，
        // 然后，循环调用getNextEntry()，直到返回null，表示zip流结束。
        //
        // 一个ZipEntry表示一个压缩文件或目录，如果是压缩文件，我们就用read()方法不断读取，直到返回-1：
        try (
                FileInputStream input = new FileInputStream("test.zip");
                ZipInputStream zip = new ZipInputStream(input)
        ) {
            ZipEntry entry = null;
            while ((entry = zip.getNextEntry()) != null) {
                String name = entry.getName();
                if (!entry.isDirectory()) {
                    int n;
                    while ((n = zip.read()) != -1) {
                        System.out.print((char) n);
                    }
                    zip.closeEntry();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 写入zip包
        // ZipOutputStream是一种FilterOutputStream，它可以直接写入内容到zip包。
        // 我们要先创建一个ZipOutputStream，通常是包装一个FileOutputStream，
        // 然后，每写入一个文件前，先调用putNextEntry()，然后用write()写入byte[]数据，
        // 写入完毕后调用closeEntry()结束这个文件的打包。
        try (
                FileOutputStream input = new FileOutputStream("test.zip");
                ZipOutputStream zip = new ZipOutputStream(input)
        ) {
            // 创建一个新的 ZIP 条目（文件）
            ZipEntry entry = new ZipEntry("file.txt");
            zip.putNextEntry(entry);

            // 写入条目的内容
            byte[] data = "Hello, World!".getBytes();
            zip.write(data);

            // 关闭当前条目，准备添加下一个条目
            zip.closeEntry();

            // 添加另一个 ZIP 条目（文件夹）
            entry = new ZipEntry("folder/");
            zip.putNextEntry(entry);

            // 关闭当前条目，准备添加下一个条目
            zip.closeEntry();

            // ... 添加更多条目
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 上面的代码没有考虑文件的目录结构。
        // 如果要实现目录层次结构，new ZipEntry(name)传入的name要用相对路径。
    }
}
