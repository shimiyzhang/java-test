package objectOrientedFoundation.innerClass;

import java.util.HashMap;

/**
 * 内部类
 * Inner Class的实例不能单独存在，必须依附于一个Outer Class的实例
 */
public class Main {
    public static void main(String[] args) {
        Outer outer = new Outer("Nested"); // 实例化一个Outer
        Outer.Inner inner = outer.new Inner(); // 实例化一个Inner
        inner.hello();
        outer.asyncHello();
        Outer.StaticNested sn = new Outer.StaticNested();
        sn.hello();

        // 匿名类继承自普通类
        HashMap<String, String> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<String, String>() {}; // 匿名类
        HashMap<String, String> map3 = new HashMap<String, String>() {
            {
                put("A", "1");
                put("B", "2");
            }
        };
        System.out.println(map3.get("A"));
    }
}
