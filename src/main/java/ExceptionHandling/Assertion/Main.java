package ExceptionHandling.Assertion;

// 使用断言

// 断言（Assertion）是一种调试程序的方式。

// 在Java中，使用assert关键字来实现断言。

// Java断言的特点是：断言失败时会抛出AssertionError，导致程序结束退出。
// 因此，断言不能用于可恢复的程序错误，只应该用于开发和测试阶段。

// JVM默认关闭断言指令，即遇到assert语句就自动忽略了，不执行。

// 小结
//        断言是一种调试方式，断言失败会抛出AssertionError，只能在开发和测试阶段启用断言；
//
//        对可恢复的错误不能使用断言，而应该抛出异常；
//
//        断言很少被使用，更好的方法是编写单元测试。
public class Main {
    public static void main(String[] args) {
        double x = Math.abs(-123.45);
        assert x >= 0;
        assert x < 0 : "x < 0";
        System.out.println(x);
    }
}
