package javaCoreClass.commonToolClasses;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.Random;

// 常用工具类
//小结
//        Java提供的常用工具类有：
//
//        Math：数学计算
//
//        Random：生成伪随机数
//
//        SecureRandom：生成安全的随机数
public class Main {
    public static void main(String[] args) {
//        Math 数学计算
//        求绝对值
        Math.abs(-100);
        Math.abs(-7.8);
//        求最大或最小值
        Math.max(1, 2);
        Math.min(1, 2);
//        计算x的y次方
        Math.pow(2, 3); // 8
//        计算√x
        Math.sqrt(9); // 3
//        计算ex次方
        Math.exp(2); // 7.389...
//        计算以e为底的对数
        Math.log(4); // 1.386...
//        计算以10为底的对数
        Math.log10(100); // 2
//        三角函数
        Math.sin(3.14); // 0.00159...
        Math.cos(3.14); // -0.9999...
        Math.tan(3.14); // -0.0015...
        Math.asin(1.0); // 1.57079...
        Math.acos(1.0); // 0.0
//        数学常量
        double pi = Math.PI; // 3.14159...
        double e = Math.E; // 2.7182818...
        double sin = Math.sin(Math.PI / 6); // sin(π/6) = 0.5
        System.out.println(pi);
        System.out.println(e);
        System.out.println(sin);

//        生成一个随机数x 0 <= x < 1
        System.out.println(Math.random());
//        生成一个区间在[MIN, MAX)的随机数
        double x = Math.random();
        double min = 10;
        double max = 50;
        double y = min + x * (max - min);
        long n = (long) y;
        System.out.println(n);

//        HexFormat
//        Java 17引入的一个新类，用于方便地处理十六进制格式的数据。
        HexFormat hexFormat = HexFormat.of();
        byte[] bytes = "Hello".getBytes();
        String hex = hexFormat.formatHex(bytes);
        System.out.println(hex); // 48656c6c6f
//        定制转换格式
//        分隔符为空格，添加0x前缀，大写字母
        HexFormat hx = HexFormat.ofDelimiter(" ").withPrefix("0x").withUpperCase();
        System.out.println(hx.formatHex("Hello".getBytes())); // 0x48 0x65 0x6C 0x6C 0x6F
//        从16进制字符串到byte[]数组转换
        byte[] bs = HexFormat.of().parseHex("48656c6c6f");
        System.out.println(new String(bs));

//        Random用来创建伪随机数。
        Random random = new Random();
        System.out.println("random");
        System.out.println(random.nextInt());
        System.out.println(random.nextInt(10)); // [0, 10)
        System.out.println(random.nextLong());
        System.out.println(random.nextFloat());
        System.out.println(random.nextDouble());
        System.out.println(random.nextBoolean());
//        在创建Random实例时，如果指定一个种子，就会得到完全确定的随机数序列
        Random r = new Random(12345);
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextInt(100));
        }
//        Math.random()内部调用了Random类，也是伪随机数，只是我们无法指定种子

//        SecureRandom 安全随机数
//        需要使用安全随机数的时候，必须使用SecureRandom，绝不能使用Random！
        SecureRandom sr = new SecureRandom();
        System.out.println(sr.nextInt(100));

        SecureRandom ssr = null;
        try {
            ssr = SecureRandom.getInstanceStrong(); // 获取高强度安全随机数生成器
        } catch (NoSuchAlgorithmException error) {
            ssr = new SecureRandom(); // 获取普通的安全随机数生成器
        }
        byte[] buffer = new byte[16];
        ssr.nextBytes(buffer); // 用安全随机数填充buffer
        System.out.println(Arrays.toString(buffer));
    }
}
