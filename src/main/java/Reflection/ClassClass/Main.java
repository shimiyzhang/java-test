package Reflection.ClassClass;

import ObjectOrientedFoundation.Package.ming.Person;
import org.apache.commons.logging.LogFactory;

// Class类
// 小结
//        JVM为每个加载的class及interface创建了对应的Class实例来保存class及interface的所有信息；
//
//        获取一个class对应的Class实例后，就可以获取该class的所有信息；
//
//        通过Class实例获取class信息的方法称为反射（Reflection）；
//
//        JVM总是动态加载class，可以在运行期根据条件来控制加载class。
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 如何获取一个class的Class
        // 方法一：直接通过一个class的静态变量class获取：
        Class cls = String.class;
        // 方法二：如果我们有一个实例变量，可以通过该实例变量提供的getClass()方法获取：
        String s = "hello";
        Class c = s.getClass();
        // 方法三：如果知道一个class的完整类名，可以通过静态方法Class.forName()获取：
        Class cs = Class.forName("java.lang.String");

        // 因为Class实例在JVM中是唯一的，所以，上述方法获取的Class实例是同一个实例。可以用==比较两个Class实例：
        boolean sameClass = cls == c;
        System.out.println(sameClass);

        // Class实例比较和instanceof的区别
        // 用instanceof不但匹配指定类型，还匹配指定类型的子类。
        // 而用==判断class实例可以精确地判断数据类型，但不能作子类型比较。
        Integer n = Integer.valueOf(123);
        boolean b1 = n instanceof Integer;
        boolean b2 = n instanceof Number;
        System.out.println(b1);
        System.out.println(b2);

        boolean b3 = n.getClass() == Integer.class;
//        boolean b4 = n.getClass() == Number.class;
        System.out.println(b3);
//        System.out.println(b4);

        // 从Class实例获取获取的基本信息
        printClassInfo("".getClass());
        printClassInfo(Runnable.class);
        printClassInfo(java.time.Month.class);
        printClassInfo(String[].class);
        printClassInfo(int.class);

        // 创建一个String实例
//        String s = (String) cls.newInstance();

        // 动态加载
        // JVM在执行Java程序的时候，并不是一次性把所有用到的class全部加载到内存，而是第一次需要用到class时才加载。

        // 利用JVM动态加载class的特性，我们才能在运行期根据条件加载不同的实现类。
        LogFactory factory = null;
        if (isClassPresent("org.apache.logging.log4j.Logger")) {
//            factory = createLog4j();
        } else {
//            factory = createJdkLog();
        }
    }

    static void printClassInfo(Class cls) {
        System.out.println("Class name: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        if(cls.getPackage() != null) {
            System.out.println("Package name: " + cls.getPackage().getName());
        }
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is array: " + cls.isArray());
        System.out.println("is primitive: " + cls.isPrimitive());
    }

    static boolean isClassPresent(String name) {
        try {
            Class.forName(name);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
