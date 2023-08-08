package Annotation.ProcessAnnotation;

import java.lang.annotation.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// 处理注解
// 小结
//        可以在运行期通过反射读取RUNTIME类型的注解，
//        注意千万不要漏写@Retention(RetentionPolicy.RUNTIME)，
//        否则运行期无法读取到该注解。
//
//        可以通过程序处理注解来实现相应的功能：
//
//        对JavaBean的属性值按规则进行检查；
//        JUnit会自动运行@Test标记的测试方法。
public class Main {
    public static void main(String[] args) throws Exception {
        // Java的注解本身对代码逻辑没有任何影响。根据@Retention的配置：
        //
        // SOURCE类型的注解在编译期就被丢掉了；
        // CLASS类型的注解仅保存在class文件中，它们不会被加载进JVM；
        // RUNTIME类型的注解会被加载进JVM，并且在运行期可以被程序读取。

        // 如何使用注解完全由工具决定。
        // SOURCE类型的注解主要由编译器使用，因此我们一般只使用，不编写。
        // CLASS类型的注解主要由底层工具库使用，涉及到class的加载，一般我们很少用到。
        // 只有RUNTIME类型的注解不但要使用，还经常需要编写。

        // 如何读取RUNTIME类型的注解。
        // 注解定义后也是一种class，所有的注解都继承自java.lang.annotation.Annotation，
        // 因此，读取注解，需要使用反射API。

        // Java提供的使用反射API读取Annotation的方法包括：
        //
        // 判断某个注解是否存在于Class、Field、Method或Constructor：
        //
        // Class.isAnnotationPresent(Class)
        // Field.isAnnotationPresent(Class)
        // Method.isAnnotationPresent(Class)
        // Constructor.isAnnotationPresent(Class)
        boolean classHas = Test.class.isAnnotationPresent(MyAnnotation.class);
        System.out.println(classHas); // false
        Constructor cons = Test.class.getConstructor(String.class);
        boolean consHas = cons.isAnnotationPresent(MyAnnotation.class);
        System.out.println(consHas); // true
        Method method = Test.class.getMethod("test", int.class);
        boolean methodHas = method.isAnnotationPresent(MyAnnotation.class);
        System.out.println(methodHas); // true

        // 使用反射API读取Annotation：
        //
        // Class.getAnnotation(Class)
        // Field.getAnnotation(Class)
        // Method.getAnnotation(Class)
        // Constructor.getAnnotation(Class)

        MyAnnotation classAnnotation = Test.class.getAnnotation(MyAnnotation.class);
        System.out.println(classAnnotation); // null

        MyAnnotation consAnnotation = (MyAnnotation) cons.getAnnotation(MyAnnotation.class);
        System.out.println(consAnnotation); // @MyAnnotation(type=1, name=test, value=test)
        int type = consAnnotation.type();
        String name = consAnnotation.name();
        String value = consAnnotation.value();
        System.out.println(type + " " + name + " " + value);

        // 使用反射API读取Annotation有两种方法。
        // 方法一是先判断Annotation是否存在，如果存在，就直接读取：
        if (Test.class.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation myAnnotation = Test.class.getAnnotation(MyAnnotation.class);
            System.out.println(myAnnotation);
        }
        // 第二种方法是直接读取Annotation，如果Annotation不存在，将返回null：
        MyAnnotation myAnnotation = Test.class.getAnnotation(MyAnnotation.class);
        if (myAnnotation != null) {
            System.out.println(myAnnotation);
        }

        // 读取方法、字段和构造方法的Annotation和Class类似。
        //
        // 但要读取方法参数的Annotation就比较麻烦一点，
        // 因为方法参数本身可以看成一个数组，而每个参数又可以定义多个注解，
        // 所以，一次获取方法参数的所有注解就必须用一个二维数组来表示。
        // 获取Method实例：
        Method m = Test.class.getMethod("test", int.class);
        Annotation[][] parameterAnnotations = m.getParameterAnnotations();
        for (Annotation[] annotations : parameterAnnotations) {
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }
        }

        // 使用注解
        // 但是，定义了注解，本身对程序逻辑没有任何影响。我们必须自己编写代码来使用注解。
        // 这里，我们编写一个RangeTest实例的检查方法，它可以检查RangeTest实例的String字段长度是否满足@Range的定义：
        RangeTest rangeTest = new RangeTest("test", "test");
        check(rangeTest);
        RangeTest rangeTest2 = new RangeTest("test", "test test test test");
        check(rangeTest2);
        RangeTest rangeTest3 = new RangeTest("test", "test", 520);
        check(rangeTest3);
        // 这样一来，我们通过@Range注解，配合check()方法，就可以完成Person实例的检查。
        // 注意检查逻辑完全是我们自己编写的，JVM不会自动给注解添加任何额外的逻辑。
    }

    static void check(RangeTest rangeTest) throws IllegalAccessException {
        // 获取所有Field：
        for (Field field : rangeTest.getClass().getFields()) {
            // 获取Field定义的@Range:
            Range range = field.getAnnotation(Range.class);
            // 如果@Range存在:
            if (range != null) {
                // 获取Field的值
                Object value = field.get(rangeTest);
                if (value instanceof String s) {
                    // 判断值是否满足@Range的min/max:
                    if (s.length() < range.min() || s.length() > range.max()) {
                        throw new IllegalAccessException("Invalid field: " + field.getName());
                    }
                }
                if (value instanceof Integer i) {
                    if (i < range.min() || i > range.max()) {
                        throw new IllegalAccessException("Invalid field: " + field.getName());
                    }
                }
            }
        }
    }
}

class Test {
    public String name;

    @MyAnnotation(type = 1, name = "test", value = "test")
    public Test(String name) {
        this.name = name;
    }

    @MyAnnotation
    public void test(@MyAnnotation @MyAnnotation2 int age) {
        System.out.println(this.name + " " + age);
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    int type() default 0;

    String name() default "";

    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2 {
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Range {
    int min() default 0;

    int max() default 255;
}

class RangeTest {
    @Range(min = 1, max = 20)
    public String name;

    @Range(max = 10)
    public String city;

    @Range()
    public int age;

    public RangeTest(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public RangeTest(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }
}
