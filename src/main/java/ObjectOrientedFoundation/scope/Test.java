package ObjectOrientedFoundation.scope;

/**
 * 作用域
 */
public class Test extends Hello {
    void foo() {
        // 可以访问protected方法:
        hi(); // 等价于 => super.hi();
    }
}

// package
// 包作用域是指一个类允许访问同一个package的没有public、private修饰的class，以及没有public、protected、private修饰的字段和方法。
// package权限的类:
class Package {
    // package权限的方法:
    void hi() {
    }
}

// final修饰符

// 用final修饰class可以阻止被继承：
// 无法被继承:
final class Final {
    // 用final修饰field可以阻止被重新赋值：
    private final int FINAL = 0;

    // 用final修饰method可以阻止被子类覆写：
    public final void hi() {
        // FINAL = 1; // error!
    }

    // 用final修饰局部变量可以阻止被重新赋值：
    public void hello(final int t) {
        // t = 1; // error!
    }
}