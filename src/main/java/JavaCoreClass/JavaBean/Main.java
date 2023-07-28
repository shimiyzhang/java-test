package JavaCoreClass.JavaBean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class Main {
//     在Java中，有很多class的定义都符合这样的规范：
//     若干private实例字段；
//     通过public方法来实例字段。

//     读写方法符合以下这种命名规范的class被称为JavaBean:
//     读方法:
//     public Type getXyz()
//     写方法:
//     public void setXyz(Type value)

//     boolean字段比较特殊，isXyz()
//     public boolean isChild()
//     public void setChild(boolean value)

//    JavaBean的作用
//    JavaBean主要用来传递数据，即把一组数据组合成一个JavaBean便于传输。

//    使用Java核心库提供的Introspector枚举JavaBean属性
public static void main(String[] args) throws Exception {
//    class属性是从Object继承的getClass()方法带来的
    BeanInfo info = Introspector.getBeanInfo(Person.class);
    for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
        System.out.println(pd.getName());
        System.out.println(pd.getWriteMethod());
        System.out.println(pd.getReadMethod());
    }

//    小结
//    JavaBean是一种符合命名规范的class，它通过getter和setter来定义属性；
//
//    属性是一种通用的叫法，并非Java语法规定；
//
//    可以利用IDE快速生成getter和setter；
//
//    使用Introspector.getBeanInfo()可以获取属性列表。
}
}

class Person {
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}