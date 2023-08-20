package io.fileObject;

import java.io.File;
import java.io.IOException;

// 练习
// 请利用File对象列出指定目录下的所有子目录和文件，并按层次打印。
// 例如，输出：
//
// Documents/
//   word/
//     1.docx
//     2.docx
//     work/
//       abc.doc
//   ppt/
//   other/
// 如果不指定参数，则使用当前目录，如果指定参数，则使用指定目录。
public class Test {
    public static void main(String[] args) throws IOException {
        File currentDir;
        if (args.length > 0) {
            currentDir = new File(args[0]);
        } else {
            currentDir = new File(".");
        }
        listDir(currentDir.getCanonicalFile(), 0);
    }

    static void listDir(File dir, int depth) {
        // 打印当前目录名（带缩进）
        printIndent(depth);
        System.out.println(dir.getName() + "/");

        // 获取目录下的所有文件和目录
        File[] fs = dir.listFiles();
        if (fs != null) {
            for (File f : fs) {
                if (f.isDirectory()) {
                    // 递归处理子目录
                    listDir(f, depth + 1);
                } else {
                    // 打印文件名（带缩进）
                    printIndent(depth + 1);
                    System.out.println(f.getName());
                }
            }
        }
    }

    static void printIndent(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print(" ");
        }
    }
}
