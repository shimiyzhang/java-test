package objectOrientedFoundation;

// 继承
public class Extend {
    public static void main(String[] args) {
        People p = new People("Xiao Ming", 18);
        p.setName("Xiao Ming");
        p.setAge(18);
        System.out.println(p.getName());
        System.out.println(p.getAge());

        Student s = new Student("Xiao Wang", 20, 100);
        s.setName("Xiao Wang");
        s.setAge(20);
        s.setScore(100);
        System.out.println(s.getName());
        System.out.println(s.getAge());
        System.out.println(s.getScore());

        // 向上转型(把一个子类类型安全地变为父类类型的赋值)
        Student s1 = new Student("Xiao Wang", 20, 100);
        People p1 = s1; // ok
        Object o1 = p1; // ok
        Object o2 = s1; // ok

        // 向下转型
        People p2 = new Student("Xiao Wang", 20, 100); // ok
        People p3 = new People("Xiao Ming", 18);
        Student s2 = (Student) p2; // ok
        // 不能把父类变为子类，因为子类功能比父类多
        // Student s3 = (Student) p3; // 运行错误 ClassCastException!

        // instanceof(用来判断一个实例究竟是不是某种类型)
        System.out.println(p instanceof People); // true
        System.out.println(p instanceof Student); // false
        System.out.println(s instanceof People); // true
        System.out.println(s instanceof Student); // true
        Student n = null;
        System.out.println(n instanceof Student); // false
        // 利用instanceof，在向下转型前可以先判断
        People ppp = new Student("Xiao Wang", 20, 100);
        if (ppp instanceof Student) {
            // 只有判断成功才会向下转型:
            Student sss = (Student) ppp; // 一定会成功
        }

        // 从Java 14开始，判断instanceof后，可以直接转型为指定变量，避免再次强制转型。
        Object obj = "hello";
        if (obj instanceof String) {
            String ssss = (String) obj;
            System.out.println(ssss.toUpperCase());
        }
        // 可以改写如下：
        // if (obj instanceof String s) {
        //     // 可以直接使用变量s:
        //     System.out.println(s.toUpperCase());
        // }

        PrimaryStudent ps = new PrimaryStudent("Xiao Hong", 20, 100, 3);
        System.out.println(ps.getGrade());
    }
}

class People {
    protected String name;
    protected int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

// Student从Person继承
class Student extends People {
    // 不要重复name和age字段/方法
    // 只需要定义新增score字段/方法
    private int score;
    // Student持有一个Book实例
    protected Book book;

    // 编译错误:在Student的构造方法中，无法调用Person的构造方法
    public Student(String name, int age, int score) {
        // 第一行语句必须是调用父类的构造方法, 如果没有明确地调用父类的构造方法，编译器会帮我们自动加一句super();
        // 但是，Person类并没有无参数的构造方法，因此，编译失败。
        // 错误：
        // super();
        // 正确：
        super(name, age);
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String hello() {
        // 子类无法访问父类的private字段或者private方法
        // 用protected修饰的字段可以被子类访问
        return "hello, " + name;
    }
}

// 区分继承和组合
// 不能让Student继承自Book
// 因为Student是Person的一种，它们是is关系，而Student并不是Book。实际上Student和Book的关系是has关系
// 具有has关系不应该使用继承，而是使用组合
class Book {
    protected String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class PrimaryStudent extends Student {
    protected int grade;

    public PrimaryStudent(String name, int age, int score, int grade) {
        super(name, age, score);
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}