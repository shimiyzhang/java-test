package Collection.EqualsMethod;

import java.util.List;
import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        List<Person1> list = List.of(
                new Person1("Xiao", "Ming", 18),
                new Person1("Xiao", "Hong", 25),
                new Person1("Bob", "Smith", 20)
        );
        boolean exist = list.contains(new Person1("Bob", "Smith", 20));
        System.out.println(exist ? "测试成功!" : "测试失败!");
    }
}

class Person1 {
    String firstName;
    String lastName;
    int age;

    public Person1(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person1 p) {
            return Objects.equals(this.firstName, p.firstName) && Objects.equals(this.lastName, p.lastName) && this.age == p.age;
        }
        return false;
    }
}
