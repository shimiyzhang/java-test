package ExceptionHandling.JavaException;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

// 异常处理
// Java的异常
// Java内置了一套异常处理机制，总是使用异常来表示错误。
// 小结
//        Java使用异常来表示错误，并通过try ... catch捕获异常；
//
//        Java的异常是class，并且从Throwable继承；
//
//        Error是无需捕获的严重错误，Exception是应该捕获的可处理的错误；
//
//        RuntimeException无需强制捕获，非RuntimeException（Checked Exception）需强制捕获，或者用throws声明；
//
//        不推荐捕获了异常但不进行任何处理。
public class Main {
//    Java的异常是一种class，它的继承关系如下：
//                                      Object
//                                         ↑
//                                     Throwable
//                                         ↑
//                          Error                     Exception
//                            ↑                           ↑
//            OutOfMemoryError           RuntimeException   IOException
//                                              ↑
//                         NullPointerException   IllegalArgumentException
//    Throwable继承自Object，它有两个体系，Error和Exception。

//    一、Error表示严重的错误，程序对此一般无能为力，例如：
//    OutOfMemoryError: 内存耗尽
//    NoClassDefFoundError: 无法加载某个Class
//    StackOverflowError: 栈溢出

//    二、而Exception则是运行时的错误，它可以被捕获并处理。
//    1、某些异常是应用程序逻辑处理的一部分，应该捕获并处理。例如：
//    NumberFormatException: 数值格式错误
//    FileNotFoundException: 未找到文件
//    SocketException: 读取网络失败
//    2、还有一些异常是程序逻辑编写不对造成的，应该修复程序本身。例如：
//    NullPointerException: 对某个null的对象调用方法或字段
//    IndexOutOfBoundsException: 数组索引越界

//    Exception分为两大类：
//    1、RuntimeException以及它的子类
//    2、非RuntimeException（包括IoException、ReflectiveOperationException等等）

//    Java规定：
//    必须捕获的异常，包括Exception及其子类，但不包括RuntimeException及其子类，这种类型的异常称为Checked Exception。
//    不需要捕获的异常，包括Error及其子类，RuntimeException及其子类。

//    注意：编译器对RuntimeException及其子类不做强制捕获要求，不是指应用程序本身不应该捕获并处理RuntimeException。
//    是否需要捕获，具体问题具体分析。

    //    捕获异常
//    捕获异常使用try...catch语句，把可能发生异常的代码放到try {...}中，然后使用catch捕获对应的Exception及其子类：
    public static void main(String[] args) {
        byte[] bs = toGBK("中文");
        System.out.println(Arrays.toString(bs));

        // 在toGBK2方法调用时捕获异常
        try {
            byte[] bs2 = toGBK2("中文");
            System.out.println(Arrays.toString(bs2));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); // 抛出异常
        }
    }

    static byte[] toGBK(String s) {
        try {
            // 用指定编码转换String为byte[]
            return s.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); // 抛出异常
            return s.getBytes(); // 尝试使用默认编码
        }
    }
//    如果我们不捕获UnsupportedEncodingException，会出现编译失败的问题：
//    因为String.getBytes()方法定义是
//    public byte[] getBytes(String charsetName) throws UnsupportedEncodingException {
//        ...
//    };
//    在方法定义时，使用throws Xxx表示该方法可能抛出的错误类型。
//    调用方在调用的时候，必须强制捕获这些异常，否则编译器会报错。

    //    在toGBK2方法定义时抛出对应异常
    static byte[] toGBK2(String s) throws UnsupportedEncodingException {
        return s.getBytes("GBK");
    }

//    可见，只要是方法声明的Checked Exception，不在调用层捕获，也必须在更高的调用层捕获。
//    所有未捕获的异常，最终也必须在main()方法中捕获，不会出现漏写try的情况。
//    这是由编译器保证的。main()方法也是最后捕获Exception的机会。

//    为了保证测试代码简单,直接把main()方法定义为throws Exception:
//    代价是一旦发生异常,程序会立刻退出!
//    public static void main(String[] args) throws Exception {
//        byte[] bs2 = toGBK2("中文");
//        System.out.println(Arrays.toString(bs2));
//    }
}
