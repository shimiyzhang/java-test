package javaCoreClass.recordClass;

// 记录类
// String Integer等类型都是不变类,具有以下特点:
// 1.定义class时使用final,无法派生子类
// 2.每个字段使用final,保证创建实例后无法修改任何字段

// record
// 从Java 14开始，引入了新的Record类。我们定义Record类时，使用关键字record。
// 除了用final修饰class以及每个字段外，编译器还自动为我们创建了构造方法，和字段名同名的方法，
// 以及覆写toString()、equals()和hashCode()方法。
// 使用record可以一行写出一个不变类
// 和enum类似，我们自己不能直接从Record派生，只能通过record关键字由编译器实现继承。

//小结
//        从Java 14开始，提供新的record关键字，可以非常方便地定义Data Class：
//
//        使用record定义的是不变类；
//
//        可以编写Compact Constructor对参数进行验证；
//
//        可以定义静态方法。
public class Main {
    public static void main(String[] args) {
        Point2 p = new Point2(123,456);
        System.out.println(p.x());
        System.out.println(p.y());
        System.out.println(p);
        Point2 p2 = Point2.of(123,456);
        System.out.println(p2);
    }
}

// Point为不变类，同时有x、y两个变量
final class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }
}

record Point2(int x, int y) {
//    构造方法
    public Point2 {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException();
        };
    }

//    添加静态方法
//    of方法，用来创建Point:
    public static Point2 of() {
        return new Point2(0, 0);
    }
    public static Point2 of(int x, int y) {
        return new Point2(x, y);
    }
}

// 方法public Point {...}被称为Compact Constructor，它的目的是让我们编写检查逻辑
// 编译器最终生成的构造方法
//public final class Point extends Record {
//    public Point(int x, int y) {
//        // 这是我们编写的Compact Constructor:
//        if (x < 0 || y < 0) {
//            throw new IllegalArgumentException();
//        }
//        // 这是编译器继续生成的赋值代码:
//        this.x = x;
//        this.y = y;
//    }
//    ...
//}