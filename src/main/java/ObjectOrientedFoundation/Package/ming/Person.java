// 在Java虚拟机执行的时候，JVM只看完整类名，因此，只要包名不同，类就不同
package ObjectOrientedFoundation.Package.ming; // 申明包名

// 第二种写法是用import语句，导入小军的Arrays，然后写简单类名
import ObjectOrientedFoundation.Package.mr.jun.Arrays;

// 在写import的时候，可以使用*，表示把这个包下面的所有class都导入进来（但不包括子包的class）
// 我们一般不推荐这种写法，因为在导入了多个包后，很难看出Arrays类属于哪个包。
// import ObjectOrientedFoundation.Package.mr.jun.*;

// import static的语法，它可以导入可以导入一个类的静态字段和静态方法
// import static很少使用。
import static java.lang.System.*;

public class Person {
    // 包作用域:
    // 不用public、protected、private修饰的字段和方法就是包作用域
    // 位于同一个包的类，可以访问包作用域的字段和方法
    void hello() {
        System.out.println("hello!");

        // 相当于调用System.out.println(…)
        out.println("hello, world!");
    }

    public void run() {
        // 在一个class中，我们总会引用其他的class。
        // 第一种，直接写出完整类名
        ObjectOrientedFoundation.Package.mr.jun.Arrays arrays = new ObjectOrientedFoundation.Package.mr.jun.Arrays();
        Arrays arrays1 = new Arrays();
    }
}
