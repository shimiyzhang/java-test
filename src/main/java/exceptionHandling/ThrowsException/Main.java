package exceptionHandling.ThrowsException;

// 抛出异常
// 小结
//        调用printStackTrace()可以打印异常的传播栈，对于调试非常有用；
//
//        捕获异常并再次抛出新的异常时，应该持有原始异常信息；
//
//        通常不要在finally中抛出异常。如果在finally中抛出异常，应该原始异常加入到原有异常中。
//        调用方可通过Throwable.getSuppressed()获取所有添加的Suppressed Exception。
public class Main {
    public static void main(String[] args) throws Exception {
//        异常的传播
//    当某个方法抛出了异常时，如果当前方法没有捕获异常，异常就会被抛到上层调用方法，直到遇到某个try ... catch被捕获为止：
//        try {
//            process1();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        通过printStackTrace()可以打印出方法的调用栈，类似：
//        java.lang.NumberFormatException: null
//          at java.base/java.lang.Integer.parseInt(Integer.java:614)
//          at java.base/java.lang.Integer.parseInt(Integer.java:770)
//          at Main.process2(Main.java:16)
//          at Main.process1(Main.java:12)
//          at Main.main(Main.java:5)

//        printStackTrace()对于调试错误非常有用
//        并且，每层调用均给出了源代码的行号，可直接定位。

//        在main()中捕获IllegalArgumentException
//        try {
//            process4(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        因此，在catch中抛出异常，不会影响finally的执行。
//        JVM会先执行finally，然后抛出异常。
//        try {
//            Integer.parseInt("abc");
//        } catch (Exception e) {
//            System.out.println("catched");
//            throw new RuntimeException(e);
//        } finally {
//            System.out.println("finally");
//        }

//        异常屏蔽
//        这说明finally抛出异常后，原来在catch中准备抛出的异常就“消失”了，因为只能抛出一个异常。
//        没有被抛出的异常称为“被屏蔽”的异常（Suppressed Exception）。
//        try {
//            Integer.parseInt("abc");
//        } catch (Exception e) {
//            System.out.println("catched");
//            throw new RuntimeException(e);
//        } finally {
//            System.out.println("finally");
//            throw new IllegalArgumentException();
//        }
//        获知所有的异常
//        方法是先用origin变量保存原始异常，然后调用Throwable.addSuppressed()，把原始异常添加进来，最后在finally抛出：
//        通过Throwable.getSuppressed()可以获取所有的Suppressed Exception。
        Exception origin = null;
        try {
            System.out.println(Integer.parseInt("abc"));
        } catch (Exception e) {
            origin = e;
            throw e;
        } finally {
            Exception e = new IllegalArgumentException();
            if (origin != null) {
                e.addSuppressed(origin);
            }
            throw e;
        }

//        提问时贴出异常
    }

    static void process1() {
        process2();
    }

    static void process2() {
        Integer.parseInt(null); // 会抛出NumberFormatException
    }

//    抛出异常
//    1.创建某个Exception的实例
//    2.用throw语句抛出
    static void process3(String s) {
        if (s == null) {
//            NullPointerException e = new NullPointerException();
//            throw e;
//            合并一行
             throw new NullPointerException();
        }
    }

    static void process4(String s) {
        try {
            process3(null);
        } catch (NullPointerException e) {
//            在构造异常的时候，把原始的Exception实例传进去
            throw new IllegalArgumentException(e);
        };
    }
//    捕获到异常并再次抛出时，一定要留住原始异常，否则很难定位第一案发现场！
}
