package objectOrientedFoundation;

// 方法重载
public class MethodOverload {
    public static void main(String[] args) {
        Hello sayHello = new Hello();
        sayHello.hello();
        sayHello.hello("Xiao Ming");
        sayHello.hello("Xiao Wang", 16);

        // String类提供了多个重载方法indexOf()，可以查找子串
        String s = "Test string";
        int n1 = s.indexOf('t');
        int n2 = s.indexOf("st");
        int n3 = s.indexOf("st", 4);
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);

        PersonWithOverload ming = new PersonWithOverload();
        PersonWithOverload hong = new PersonWithOverload();
        ming.setName("Xiao Ming");
        hong.setName("Xiao", "Hong");
        System.out.println(ming.getName());
        System.out.println(hong.getName());
    }
}

class Hello {
    // 方法名相同，但各自的参数不同
    // 重载方法返回值类型应该相同
    public void hello() {
        System.out.println("hello, world!");
    }

    public void hello(String name) {
        System.out.println("hello, " + name + "!");
    }

    public void hello(String name, int age) {
        if (age < 18) {
            System.out.println("hi, " + name + "!");
        } else {
            System.out.println("hello, " + name + "!");
        }
    }
}

class PersonWithOverload {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName(String first, String last) {
        this.name = first + " " + last;
    }
}
