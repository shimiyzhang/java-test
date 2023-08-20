package javaCoreClass.packagingType;

public class Main {
    public static void main(String[] args) {
//        String s = null;
////        int i = null;
//        Integer n = null;
//        Integer n2 = new Integer(99);
//        int n3 = n2.intValue();

//        int i = 100;
////        Integer n1 = new Integer(i); // (不推荐使用,会有编译警告):
//        Integer n2 = Integer.valueOf(i);
//        Integer n3 = Integer.valueOf("100");
//        System.out.println(n3.intValue());

//        Auto Boxing
//        int i = 100;
//        Integer n = Integer.valueOf(i);
//        int x = n.intValue();

//        Integer n = 100; // 编译器自动使用Integer.valueOf(int)
//        int x = n; // 编译器自动使用Integer.intValue()

//        Integer n = null;
//        int i = n;

//        所有的包装类型都是不变类
//        Integer是引用类型，必须使用equals比较
        Integer x = 127;
        Integer y = 127;
        Integer m = 99999;
        Integer n = 99999;
//        为了节省内存，Integer.valueOf()对于较小的数（-128~127），始终返回相同的实例
        System.out.println("x == y:" + (x == y)); // true
        System.out.println("m == n: " + (m == n)); // false
        System.out.println("x.equals(y): " + x.equals(y)); // true
        System.out.println("m.equals(n): " + m.equals(n)); // true

//        进制转换
//        静态方法parseInt()可以把字符串解析成一个整数
        int x1 = Integer.parseInt("100"); // 100
        int x2 = Integer.parseInt("100", 16); // 256，按照16进制解析
        System.out.println(x1);
        System.out.println(x2);

//        把整数格式化为指定进制的字符串
        System.out.println(Integer.toString(100)); // "100",表示为10进制
        System.out.println(Integer.toString(100, 36)); // "2s",表示为36进制
        System.out.println(Integer.toHexString(100)); // "64",表示为16进制
        System.out.println(Integer.toOctalString(100)); // "144",表示为8进制
        System.out.println(Integer.toBinaryString(100)); // "1100100",表示为2进制

//        程序设计的一个重要原则：数据的存储和显示要分离

//        Java的包装类型还定义了一些有用的静态变量
//        boolean只有两个值true/false，其包装类型只需要引用Boolean提供的静态字段:
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
//        int可表示的最大/最小值:
        int max = Integer.MAX_VALUE; // 2147483647
        int min = Integer.MIN_VALUE; // -2147483648
//        long类型占用的bit和byte数量:
        int sizeOfLong = Long.SIZE; // 64 (bits)
        int bytesOfLong = Long.BYTES; // 8 (bytes)

        Number num = new Integer(999);
        byte b = num.byteValue();
        int nn = num.intValue();
        long lon = num.longValue();
        System.out.println(b);
        System.out.println(nn);
        System.out.println(lon);

//        处理无符号整型
//        在Java中，并没有无符号整型（Unsigned）的基本数据类型。
//        byte、short、int和long都是带符号整型，最高位是符号位。
        byte xx = -1;
        byte yy = 127;
        System.out.println(Byte.toUnsignedInt(xx)); // 255
        System.out.println(Byte.toUnsignedInt(yy)); // 127
//        因为byte的-1的二进制表示是11111111，以无符号整型转换后的int就是255。
//        类似的，可以把一个short按unsigned转换为int，把一个int按unsigned转换为long。

//        小结
//        Java核心库提供的包装类型可以把基本类型包装为class；
//
//        自动装箱和自动拆箱都是在编译期完成的（JDK>=1.5）；
//
//        装箱和拆箱会影响执行效率，且拆箱时可能发生NullPointerException；
//
//        包装类型的比较必须使用equals()；
//
//        整数和浮点数的包装类型都继承自Number；
//
//        包装类型提供了大量实用方法。

//        原码：第一位为符号位，正数为0负数为1
//        反码：正数为原码本身，负数为原码除符号位取反
//        补码：正数为原码本身，负数为原码除符号位取反，然后+1（即反码+1）
//         真值     原码         反码          补码
//         1 -> 0000 0001 -> 0000 0001 -> 0000 0001
//        -1 -> 1000 0001 -> 1111 1110 -> 1111 1111
    }
}

//// int类型的包装类
//class Integer {
//    private int value;
//
//    public Integer(int value) {
//        this.value = value;
//    }
//
//    public int intValue() {
//        return this.value;
//    }
//}