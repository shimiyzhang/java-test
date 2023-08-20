package objectOrientedFoundation;

// 面向对象基础
public class Foundation {
    public static void main(String[] args) {
        // 创建实例
        Person ming = new Person();
        // 操作实例
        ming.name = "Xiao Ming";
        ming.age = 15;
        System.out.println("my name is " + ming.name + ", i am " + ming.age + "years old." );

        City bj = new City();
        bj.name = "Beijing";
        bj.latitude = 39.903;
        bj.longitude = 116.401;
        System.out.println(bj.name);
        System.out.println("location: " + bj.latitude + ", " + bj.longitude);
    }
}

// 定义class
class Person {
    // public是用来修饰字段的，它表示这个字段可以被外部访问。
    public String name;
    public int age;
}

class City {
    public String name;
    public double latitude;
    public double longitude;
}
