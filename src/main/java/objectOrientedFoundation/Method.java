package objectOrientedFoundation;

// 方法
public class Method {
    public static void main(String[] args) {
        PrivateFieldPerson ming = new PrivateFieldPerson();
        ming.setName("Xiao Ming");
        ming.setAge(18);
        System.out.println(ming.getName() + ", " + ming.getAge());
        ming.setBirth(1999);
        System.out.println(ming.getAgeByBirth());

        // 可变参数
        Group g = new Group();
        g.setNames("Xiao Ming", "Xiao Hong", "Xiao Jun"); // 传入3个String
        g.setNames("Xiao Ming", "Xiao Hong"); // 传入2个String
        g.setNames("Xiao Ming"); // 传入1个String
        g.setNames(); // 传入0个String
        g.setNamesByArray(new String[] {"Xiao Ming", "Xiao Hong", "Xiao Jun"}); // 传入1个String[]

        // 参数绑定
        PrivateFieldPerson p = new PrivateFieldPerson();
        int n = 15; // n的值为15
        p.setAge(n); // 传入n的值
        System.out.println(p.getAge()); // 15
        n = 20; // n的值改为20
        System.out.println(p.getAge()); // 15

        PersonByArray pa = new PersonByArray();
        String[] fullName = new String[] {"Tom", "Jerry"};
        pa.setName(fullName);
        System.out.println(pa.getName()); // Tom Jerry
        fullName[0] = "Spike";
        System.out.println(pa.getName()); // Spike Jerry

        PersonByString pp = new PersonByString();
        String bob = "Bob";
        pp.setName(bob); // 传入bob变量
        System.out.println(pp.getName()); // "Bob"
        bob = "Alice"; // bob改名为Alice
        // 因为字符串具有不可变性，所以JVM创建了字符串“Alice”，然后将变量bob指向“Alice”，因此name指向的还是原来的“Bob”
        System.out.println(pp.getName()); // "Bob"
    }
}

// 为了避免外部代码直接去访问field，我们可以用private修饰field，拒绝外部访问
class PrivateFieldPerson {
    private String name;
    private int age;
    private int birth;

    // 使用方法（method）来让外部代码可以间接修改field
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("invalid name");
        }
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("invalid age value");
        }
        this.age = age;
    }

    public int getAgeByBirth() {
        return calAge(2023); // 调用private方法
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    // private方法:
    private int calAge(int currentYear) {
        return currentYear - this.birth;
    }
}

class Group {
    private String[] names;

    // 可变参数
    public void setNames(String... names) {
        this.names = names;
    }

    public void setNamesByArray(String[] names) {
        this.names = names;
    }
}

class PersonByArray {
    private String[] name;

    public String getName() {
        return this.name[0] + " " + this.name[1];
    }

    public void setName(String[] name) {
        this.name = name;
    }
}

class PersonByString {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}