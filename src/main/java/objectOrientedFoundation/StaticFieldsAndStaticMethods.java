package objectOrientedFoundation;

/**
 * 静态字段和静态方法
 * 用static修饰的字段，称为静态字段：static field
 * 静态字段只有一个共享“空间”，所有实例都会共享该字段
 */
public class StaticFieldsAndStaticMethods {
    public static void main(String[] args) {
        PersonWithStatic ming = new PersonWithStatic("Xiao Ming", 18);
        PersonWithStatic hong = new PersonWithStatic("Xiao Hong", 20);
        // 对于静态字段，修改某个实例的静态字段，所有实例的静态字段都被修改了，原因是静态字段并不属于实例
        ming.number = 88; // 不推荐用实例变量.静态字段去访问静态字段
        System.out.println(hong.number);
        hong.number = 99; // 不推荐用实例变量.静态字段去访问静态字段
        System.out.println(ming.number);
        PersonWithStatic.number = 88; // 推荐用类名来访问静态字段
        PersonWithStatic.setNumber(99);
        System.out.println(PersonWithStatic.number);
        System.out.println(PersonWithStatic.getCount()); // 统计实例创建的个数
    }
}

class PersonWithStatic {
    public String name;
    public int age;
    // 定义静态字段number:
    public static int number;
    // 增加一个静态字段count和静态方法getCount()，统计实例创建的个数。
    public static int count = 0;

    public static int getCount() {
        return count;
    }

    public PersonWithStatic(String name, int age) {
        this.name = name;
        this.age = age;
        // 创建实例需要调用构造方法，因此在此计数
        count++;
    }

    // 定义静态方法setNumber:
    public static void setNumber(int value) {
        // 静态方法内部，无法访问this变量
        number = value;
    }
}

interface PeopleWithStatic {
    // interface可以有静态字段，并且必须为final类型
    public static final int MALE = 1;
    public static final int FEMALE = 2;

    // 编译器会自动加上public static final:
    int X = 1;
    int Y = 2;
}
