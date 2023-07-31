package JavaCoreClass.BigInteger;

import java.math.BigInteger;

// BigInteger
// java.math.BigInteger用来表示任意大小的整数
// BigInteger是不可变类，继承自Number类。
// 将BigInteger转换成基本类型时可使用longValueExact()等方法保证结果准确。
public class Main {
    public static void main(String[] args) {
//        BigInteger内部用一个Int[]数组来模拟一个非常大的整数
        BigInteger bi = new BigInteger("1234567890");
        System.out.println(bi.pow(5));
//        对BigInteger做运算，只能使用实例方法
        BigInteger  bi1 = new BigInteger("1234567890");
        BigInteger  bi2 = new BigInteger("1234567890");
        System.out.println(bi1.add(bi2));
//        BigInteger转long
        System.out.println(bi1.longValue());

        BigInteger n = new BigInteger("999999").pow(99);
        System.out.println(n.floatValue()); // Infinity
    }
}
