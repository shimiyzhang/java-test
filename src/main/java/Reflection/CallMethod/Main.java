package Reflection.CallMethod;

import java.lang.reflect.Method;

// 调用方法
// 小结
//        Java的反射API提供的Method对象封装了方法的所有信息：
//
//        通过Class实例的方法可以获取Method实例：getMethod()，getMethods()，getDeclaredMethod()，getDeclaredMethods()；
//
//        通过Method实例可以获取方法信息：getName()，getReturnType()，getParameterTypes()，getModifiers()；
//
//        通过Method实例可以调用某个对象的方法：Object invoke(Object instance, Object... parameters)；
//
//        通过设置setAccessible(true)来访问非public方法；
//
//        通过反射调用方法时，仍然遵循多态原则。
public class Main {
    public static void main(String[] args) throws Exception {
        // 通过Class实例获取所有Method信息。
        // Class类提供了以下几个方法来获取Method：
        // Method getMethod(name, Class...)：获取某个public的Method（包括父类）
        // Method getDeclaredMethod(name, Class...)：获取当前类的某个Method（不包括父类）
        // Method[] getMethods()：获取所有public的Method（包括父类）
        // Method[] getDeclaredMethods()：获取当前类的所有Method（不包括父类）
        Class stdClass = Student.class;
        // 获取public方法getScore，参数为String:
        System.out.println(stdClass.getMethod("getScore", String.class));
        // 获取继承的public方法getName，无参数:
        System.out.println(stdClass.getMethod("getName"));
        // 获取private方法getGrade，参数为int:
        System.out.println(stdClass.getDeclaredMethod("getGrade", int.class));

        // 一个Method对象包含一个方法的所有信息：
        // getName()：返回方法名称，例如："getScore"；
        // getReturnType()：返回方法返回值类型，也是一个Class实例，例如：String.class；
        // getParameterTypes()：返回方法的参数类型，是一个Class数组，例如：{String.class, int.class}；
        // getModifiers()：返回方法的修饰符，它是一个int，不同的bit表示不同的含义。

        // 调用方法
        String s = "Hello World";
        System.out.println(s.substring(6));; // "World"
        // 用反射来调用substring方法，需要以下代码：
        //获取String substring(int)方法
        Method m = String.class.getMethod("substring", int.class);
        String r = (String) m.invoke(s, 6);
        System.out.println(r);
        //获取String substring(int, int)方法
        Method m2 = String.class.getMethod("substring", int.class, int.class);
        String r2 = (String) m2.invoke(s, 6, 10);
        System.out.println(r2);

        // 调用静态方法
        // 如果获取到的Method表示一个静态方法，调用静态方法时，由于无需指定实例对象，所以invoke方法传入的第一个参数永远为null。
        // 获取Integer.parseInt(String)方法，参数为String:
        Method m3 = Integer.class.getMethod("parseInt", String.class);
        // 调用该静态方法并获取结果:
        Integer r3 = (Integer) m3.invoke(null, "12345");
        System.out.println(r3);

        // 调用非public方法
        Person p = new Person();
        Method m4 = p.getClass().getDeclaredMethod("setName", String.class);
        m4.setAccessible(true);
        m4.invoke(p, "Tom");
        System.out.println(p.name);

        // 此外，setAccessible(true)可能会失败。
        // 如果JVM运行期存在SecurityManager，那么它会根据规则进行检查，有可能阻止setAccessible(true)。
        // 例如，某个SecurityManager可能不允许对java和javax开头的package的类调用setAccessible(true)，这样可以保证JVM核心库的安全。

        // 多态
        // 获取Person的hello方法:
        Method h = Person.class.getMethod("hello");
        // 对Student实例调用hello方法:
        h.invoke(new Student()); // Student:Hello

        // 使用反射调用方法时，仍然遵循多态原则：即总是调用实际类型的覆写方法（如果存在）。
        // 实际上相当于：
        Person pp = new Student();
        pp.hello();
    }
}

class Student extends Person {
    public int getScore(String type) {
        return 99;
    }
    private int getGrade(int year) {
        return 1;
    }

    public void hello() {
        System.out.println("Student:Hello");
    }
}

class Person {
    String name;

    public String getName() {
        return "Person";
    }

    private void setName(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println("Person:Hello");
    }
}
