package exceptionHandling.CatchExceptions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

// 捕获异常
// 小结
//        使用try ... catch ... finally时：
//
//        多个catch语句的匹配顺序非常重要，子类必须放在前面；
//
//        finally语句保证了有无异常都会执行，它是可选的；
//
//        一个catch语句也可以匹配多个非继承关系的异常。
public class Main {
//    在Java中,凡是可能抛出异常的语句,都可以用try...catch捕获。
//    把可能发生异常的语句放在try { ... }中，然后使用catch捕获对应的Exception及其子类。

    //    多catch语句
//    JVM在捕获到异常后，会从上到下匹配catch语句
//    多个catch语句只有一个能被执行
    public static void main(String[] args) {
//    存在多个catch的时候，catch的顺序非常重要：子类必须写在前面。例如:
//    错误示例:
        try {
            process();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
//    正确示例:
        try {
            process();
        } catch (UnsupportedEncodingException e) {
            System.out.println("Bad encoding");
        } catch (IOException e) {
            System.out.println("IO error");
        }

//        finally语句
//        finally语句块保证有无错误都会执行。
//        1、finally语句不是必须的，可写可不写；
//        2、finally总是最后执行。
        try {
            process();
        } catch (UnsupportedEncodingException e) {
            System.out.println("Bad encoding");
        } catch (IOException e) {
            System.out.println("IO error");
        } finally {
            System.out.println("END");
        }

//        捕获多种异常

//        如果某些异常的处理逻辑相同，但是异常本身不存在继承关系，那么就得编写多条catch子句：
        try {
            process();
        } catch (IOException e) {
            System.out.println("Bad input");
        } catch (NumberFormatException e) {
            System.out.println("Bad input");
        } catch (Exception e) {
            System.out.println("Unknown error");
        }

//        因为处理IOException和NumberFormatException的代码是相同的，所以我们可以把它两用|合并到一起：
        try {
            process();
        } catch (IOException | NumberFormatException e) { // IOException或NumberFormatException
            System.out.println("Bad input");
        } catch (Exception e) {
            System.out.println("Unknown error");
        }
    }

    static void process() throws IOException {
        System.out.println("process");
    };

//    因为方法声明了可能抛出的异常，所以可以不写catch。
//    void process(String file) throws IOException {
//        try {
//        ...
//        } finally {
//            System.out.println("END");
//        }
//    }
}
