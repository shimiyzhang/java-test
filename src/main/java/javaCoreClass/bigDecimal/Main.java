package javaCoreClass.bigDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

// BigDecimal
// BigDecimal表示一个任意大小且精度完全准确的浮点数
//小结
//        BigDecimal用于表示精确的小数，常用于财务计算；
//
//        比较BigDecimal的值是否相等，必须使用compareTo()而不能使用equals()。
public class Main {
    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal("123.4567");
        System.out.println(bd.multiply(bd)); // 15241.55677489

//        scale()表示小数位数
        BigDecimal d1 = new BigDecimal("123.45");
        BigDecimal d2 = new BigDecimal("123.4500");
        BigDecimal d3 = new BigDecimal("1234500");
        System.out.println(d1.scale()); // 2,两位小数
        System.out.println(d2.scale()); // 4
        System.out.println(d3.scale()); // 0

//        stripTrailingZeros()方法，可以将一个BigDecimal格式化为一个相等的，但去掉了末尾0的BigDecimal
        BigDecimal d4 = new BigDecimal("123.4500");
        System.out.println(d4.stripTrailingZeros()); // 123.45
        System.out.println(d4.scale()); // 4;
        System.out.println(d4.stripTrailingZeros().scale()); // 2

        BigDecimal d5 = new BigDecimal("1234500");
        System.out.println(d5.stripTrailingZeros()); // 1234500
        System.out.println(d5.scale()); // 0
        System.out.println(d5.stripTrailingZeros().scale()); // -2 表示这个数是整数，并且末尾有2个0

//        设置BigDecimal的scale
        BigDecimal d6 = new BigDecimal("123.456789");
        BigDecimal d7 = d6.setScale(4, RoundingMode.HALF_UP); // 四舍五入 123.4568
        BigDecimal d8 = d6.setScale(4, RoundingMode.DOWN); // 直接截断 123.4567
        System.out.println(d7);
        System.out.println(d8);

//        对BigDecimal做加、减、乘时，精度不会丢失，
//        但是做除法时，存在无法除尽的情况，必须指定精度以及如何进行截断
        BigDecimal d9 = new BigDecimal("123.456");
        BigDecimal d10 = new BigDecimal("23.456789");
        BigDecimal d11 = d9.divide(d10, 10, RoundingMode.HALF_UP); // 保留10位小数并四舍五入
//        BigDecimal d12 = d9.divide(d10); // 报错：ArithmeticException，因为除不尽
        System.out.println(d11);
//        System.out.println(d12);

        // 做除法的同时求余数
        BigDecimal n = new BigDecimal("12.345");
        BigDecimal m = new BigDecimal("0.12");
        BigDecimal[] dr = n.divideAndRemainder(m);
        System.out.println(dr[0]); // 102.0
        System.out.println(dr[1]); // 0.105

//        判断两个BigDecimal是否整数倍数
        if (dr[1].signum() == 0) {
            System.out.println("n是m的整数倍");
        }

//        比较BigDecimal
//        使用equals()方法不但要求两个BigDecimal的值相等，还要求它们的scale()相等
        BigDecimal mm = new BigDecimal("123.456");
        BigDecimal nn = new BigDecimal("123.45600");
        System.out.println(mm.equals(nn)); // false,因为scale不同
        System.out.println(mm.equals(nn.stripTrailingZeros())); // true
        System.out.println(mm.compareTo(nn)); // 0 负数、正数和0，分别表示小于、大于和等于
//        总是使用compareTo()比较两个BigDecimal的值，不要使用equals()！
    }
}
