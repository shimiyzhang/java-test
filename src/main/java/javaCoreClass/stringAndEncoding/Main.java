package javaCoreClass.stringAndEncoding;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

// 字符串和编码
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // String
        String s1 = "Hello!"; // "Hello!" 等价于 new String("Hello!")；
        System.out.println(s1);
        // 实际上字符串在String内部是通过一个char[]数组表示的
        String s2 = new String(new char[]{'H', 'e', 'l', 'l', 'o', '!'});
        System.out.println(s2);
        // 字符串不可变
        String s = "Hello";
        System.out.println(s);
        s = s.toUpperCase(); // 变的不是字符串，而是变量s的指向
        System.out.println(s);
        // 字符串比较
        String s3 = "Hello!";
        System.out.println(s1 == s3); // true: Java编译器在编译期，会自动把所有相同的字符串当作一个对象放入常量池
        System.out.println(s1.equals(s3)); // true
        String s4 = "hello";
        String s5 = "HELLO".toLowerCase();
        System.out.println(s4 == s5);// false
        System.out.println(s4.equals(s5));// true
        String s6 = "HELLO";
        System.out.println(s4.equalsIgnoreCase(s6)); // true: 忽略大小写比较
        String s7 = "Hello";
        // 是否包含子串
        System.out.println(s7.contains("ll")); // true
        // 搜索子串
        System.out.println(s7.indexOf("l")); // 2
        System.out.println(s7.startsWith("He")); // ture
        System.out.println(s7.endsWith("lo")); // true
        // 提取子串
        System.out.println(s7.substring(2));
        System.out.println(s7.substring(2, 4));
        // 去除首尾空白字符
        // 使用trim()方法可以移除字符串首尾空白字符(空格，\t，\r，\n)
        System.out.println(" \tHello\r\n".trim()); // "Hello"
        System.out.println(" Hello ".stripLeading()); // "Hello "
        System.out.println(" Hello ".stripTrailing()); // " Hello"
        // 判断字符串是否为空和空白字符串
        System.out.println("".isEmpty());
        System.out.println(" ".isEmpty());
        System.out.println(" \n".isBlank());
        // 替换子串
        System.out.println("Hello".replace('l', 'w')); // Hewwo
        System.out.println("Hello".replace("ll", "^^")); // He^^o
        // 正则表达式替换
        String s8 = "A,,B;C ,D";
        System.out.println(s8.replaceAll("[\\,\\;\\s]+", ",")); // "A,B,C,D"
        // 分割字符串
        String s9 = "A,B,C,D";
        String[] ss9 = s9.split("\\,");
        System.out.println(Arrays.toString(ss9)); // [A, B, C, D]
        // 拼接字符串
        System.out.println(String.join("***", ss9)); // A***B***C***D
        // 格式化字符串
        String s10 = "Hi %s, your score is %d!";
        System.out.println(s10.formatted("Alice", 80));
        System.out.println(String.format("Hi %s, your score is %.2f!", "Bob", 59.5));
        // 类型转换
        System.out.println(String.valueOf(123)); // "123"
        System.out.println(String.valueOf(45.67)); // "45.67"
        System.out.println(String.valueOf(true)); // "true"
        System.out.println(String.valueOf(new Object())); // 类似java.lang.Object@636be97c
        // 把字符串转换为int类型
        System.out.println(Integer.parseInt("123")); // 123
        System.out.println(Integer.parseInt("ff", 16)); // 按十六进制转换，255
        // 把字符串转换为boolean类型
        System.out.println(Boolean.parseBoolean("true")); // true
        System.out.println(Boolean.parseBoolean("FALSE")); // false
        // 把该字符串对应的系统变量转换为Integer
        System.out.println(Integer.getInteger("java.version"));
        // 转换为char[]
        char[] cs = "Hello".toCharArray(); // String -> char[]
        String s11 = new String(cs); // char[] -> String
        System.out.println(Arrays.toString(cs)); // [H, e, l, l, o]
        System.out.println(s11); // Hello
        // 如果修改了char[]数组，String并不会改变
        cs[0] = 'X';
        System.out.println(s11);
        // 从String的不变性设计可以看出，如果传入的对象有可能改变，我们需要复制而不是直接引用。
        // 例如，下面的代码设计了一个Score类保存一组学生的成绩：
        int[] scores = new int[] { 88, 77, 51, 66 };
        Score score = new Score(scores);
        score.printScores();
        scores[2] = 99;
        score.printScores(); // 对int[]数组的修改，影响到Score类的字段
        // 字符编码
        // Java的 String 和 char 在内存中总是以Unicode编码表示
        byte[] b1 = "Hello".getBytes(); // 按系统默认编码转换，不推荐
        byte[] b2 = "Hello".getBytes("UTF-8"); // 按UTF-8编码转换
        byte[] b3 = "Hello".getBytes("GBK"); // 按GBK编码转换
        byte[] b4 = "Hello".getBytes(StandardCharsets.UTF_8); // 按UTF-8编码转换
        String sb1 = new String(b1); // 按系统默认编码转换，不推荐
        String sb2 = new String(b2, "UTF-8"); // 按UTF-8编码转换
        String sb3 = new String(b3, "GBK"); // 按GBK转换
        String sb4 = new String(b4, StandardCharsets.UTF_8); // 按UTF-8转换
        System.out.println(sb1);
        System.out.println(sb2);
        System.out.println(sb3);
        System.out.println(sb4);
    }
}

class Score {
    private int[] scores;

    public Score(int[] scores) {
        // 修改前
        this.scores = scores;
        // 修改后
        this.scores = Arrays.copyOf(scores, scores.length);
    }

    public void printScores() {
        System.out.println(Arrays.toString(scores));
    }
}
