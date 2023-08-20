package reflection.accessFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

// 访问字段
// 小结
//        Java的反射API提供的Field类封装了字段的所有信息：
//
//        通过Class实例的方法可以获取Field实例：getField()，getFields()，getDeclaredField()，getDeclaredFields()；
//
//        通过Field实例可以获取字段信息：getName()，getType()，getModifiers()；
//
//        通过Field实例可以读取或设置某个对象的字段，如果存在访问限制，要首先调用setAccessible(true)来访问非public字段。
//
//        通过反射读写字段是一种非常规方法，它会破坏对象的封装。
public class Main {
    public static void main(String[] args) throws Exception {
        // 通过Class实例获取字段信息。
        // Class类提供了以下几个方法来获取字段：
        // Field getField(name)：根据字段名获取某个public的field（包括父类）
        // Field getDeclaredField(name)：根据字段名获取当前类的某个field（不包括父类）
        // Field[] getFields()：获取所有public的field（包括父类）
        // Field[] getDeclaredFields()：获取当前类的所有field（不包括父类）
        Class stdClass = Student.class;
        // 获取public字段score:
        System.out.println(stdClass.getField("score"));
        // 获取继承的public字段name:
        System.out.println(stdClass.getField("name"));
        // 获取private字段grade
        System.out.println(stdClass.getDeclaredField("grade"));
        // 获取所有public的field（包括父类）
        Field[] fields = stdClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        // 获取当前类的所有field（不包括父类）
        Field[] declaredFields = stdClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field);
        }

        // 一个Field对象包含了一个字段的所有信息：
        // getName()：返回字段名称，例如，"name"；
        // getType()：返回字段类型，也是一个Class实例，例如，String.class；
        // getModifiers()：返回字段的修饰符，它是一个int，不同的bit表示不同的含义。

        // 以String类的value字段为例，它的定义是：
        // public final class String {
        //     private final byte[] value;
        // }
        Field f = String.class.getDeclaredField("value");
        System.out.println(f.getName()); // "value"
        System.out.println(f.getType()); // class [B 表示byte[]类型
        int m = f.getModifiers();
        System.out.println(m); // 18
        boolean isFinal = Modifier.isFinal(m);
        boolean isPublic = Modifier.isPublic(m);
        boolean isProtected =   Modifier.isProtected(m);
        boolean isPrivate = Modifier.isPrivate(m);
        boolean isStatic = Modifier.isStatic(m);
        System.out.println(isFinal); // true
        System.out.println(isPublic); // false
        System.out.println(isProtected); // false
        System.out.println(isPrivate); // true
        System.out.println(isStatic); // false

        // 获取字段值
        Person p = new Person("Xiao Ming");
        Class c = p.getClass(); // 先获取Class实例
        Field nameField = c.getDeclaredField("name"); // 再获取Field实例
        nameField.setAccessible(true); // 别管这个字段是不是public，一律允许访问。
        Object value = nameField.get(p); // 用Field.get(Object)获取指定实例的指定字段的值。
        System.out.println(value); // "Xiao Ming";

        // 设置字段值
        System.out.println(p.getName());
        nameField.setAccessible(true);
        nameField.set(p, "Xiao Hong");
        System.out.println(p.getName()); // "Xiao Hong";
    }
}

class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Student extends Person {
    public int score;
    private int grade;

    public Student(String name) {
        super(name);
    }
}
