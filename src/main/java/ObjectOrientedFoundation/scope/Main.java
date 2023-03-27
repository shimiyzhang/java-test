package ObjectOrientedFoundation.scope;

public class Main {
    void foo() {
        // Hello是public，因此，可以被其他包的类访问
        // Main可以访问Hello
        Hello h = new Hello();
        // hi()是public,可以被其他类访问
        h.hi();

        // 只要在同一个包，就可以访问package权限的class、field和method：
        Package p = new Package();
        p.hi();
    }

    // 局部变量: 在方法内部定义的变量
    // 局部变量作用域从变量声明处开始到对应的块结束。
    // 方法参数也是局部变量。
    void hi(String name) { // ①
        String s = name.toLowerCase(); // ②
        int len = s.length(); // ③
        if (len < 10) { // ④
            int p = 10 - len; // ⑤
            for (int i=0; i<10; i++) { // ⑥
                System.out.println(); // ⑦
            } // ⑧
        } // ⑨
    } // ⑩
    // 方法参数name是局部变量，它的作用域是整个方法，即①～⑩；
    // 变量s的作用域是定义处到方法结束，即②～⑩；
    // 变量len的作用域是定义处到方法结束，即③～⑩；
    // 变量p的作用域是定义处到if块结束，即⑤～⑨；
    // 变量i的作用域是for循环，即⑥～⑧。
}
