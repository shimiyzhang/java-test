package objectOrientedFoundation;

// 构造方法
public class ConstructionMethod {
    public static void main(String[] args) {
        PersonWithCM p1 = new PersonWithCM("Xiao Ming", 18);
        System.out.println(p1.getName());
        System.out.println(p1.getAge());
        System.out.println(p1.getCity());
        System.out.println(p1.getBirth());
        // 如果我们自定义了一个构造方法，那么，编译器就不再自动创建默认构造方法
        PersonWithCM p2 = new PersonWithCM("Xiao Wang", 20, "ShangHai", 2003);
        System.out.println(p2.getCity());
        System.out.println(p2.getBirth());
    }
}

class PersonWithCM {
    private String name; // 默认初始化为null
    private int age; // 默认初始化为0
    // 对字段直接进行初始化
    private String city = "BeiJing";
    private int birth = 1999;

    public PersonWithCM() {
    }

    public PersonWithCM(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public PersonWithCM(String name, int age, String city, int birth) {
        // this.name = name;
        // this.age = age;
        // 一个构造方法可以调用其他构造方法
        this(name, age);
        this.city = city;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public int getBirth() {
        return birth;
    }
}