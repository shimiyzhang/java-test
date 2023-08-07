package Reflection.CallConstructor;

import java.lang.reflect.Constructor;

// 调用构造方法
// 小结
//        Constructor对象封装了构造方法的所有信息；
//
//        通过Class实例的方法可以获取Constructor实例：getConstructor()，getConstructors()，getDeclaredConstructor()，getDeclaredConstructors()；
//
//        通过Constructor实例可以创建一个实例对象：newInstance(Object... parameters)； 通过设置setAccessible(true)来访问非public构造方法。
public class Main {
    public static void main(String[] args) throws Exception {
        // 使用new操作符创建新的实例:
        Person p = new Person();
        // 通过反射来创建新的实例，调用Class提供的newInstance方法:
        Person p2 = Person.class.newInstance();
        // 调用Class.newInstance()的局限是，只能调用该类的public无参数构造方法。

        // 为了调用任意的构造方法，Java的反射API提供了Constructor对象，它包含一个构造方法的所有信息，可以创建一个实例。
        // Constructor是一个构造方法，并且总是返回实例:
        // 获取构造方法Integer(int):
        Constructor cons1 = Integer.class.getConstructor(int.class);
        // 调用构造方法:
        Integer n1 = (Integer) cons1.newInstance(123);
        System.out.println(n1);

        // 获取构造方法Integer(String)
        Constructor cons2 = Integer.class.getConstructor(String.class);
        Integer n2 = (Integer) cons2.newInstance("456");
        System.out.println(n2);

        // 通过Class实例获取Constructor的方法如下：
        // getConstructor(Class...)：获取某个public的Constructor；
        // getDeclaredConstructor(Class...)：获取某个Constructor；
        // getConstructors()：获取所有public的Constructor；
        // getDeclaredConstructors()：获取所有Constructor。

        // 注意Constructor总是当前类定义的构造方法，和父类无关，因此不存在多态的问题。

        // 调用非public的Constructor时，必须首先通过setAccessible(true)设置允许访问。
        // setAccessible(true)可能会失败。
    }
}

class Person {
    private String name;
}
