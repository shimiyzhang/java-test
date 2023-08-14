package Collection.EqualsMethod;

import java.util.List;
import java.util.Objects;

// 编写equals方法
// 小结
// 在List中查找元素时，List的实现类通过元素的equals()方法比较两个元素是否相等，
// 因此，放入的元素必须正确覆写equals()方法，Java标准库提供的String、Integer等已经覆写了equals()方法；
//
// 编写equals()方法可借助Objects.equals()判断。
//
// 如果不在List中查找元素，就不必覆写equals()方法。
public class Main {
    public static void main(String[] args) {
        // List是一种有序链表：List内部按照放入元素的先后顺序存放，并且每个元素都可以通过索引确定自己的位置。

        // List还提供了boolean contains(Object o)方法来判断List是否包含某个指定元素。
        // 此外，int indexOf(Object o)方法可以返回某个元素的索引，如果元素不存在，就返回-1。
        //
        List<String> list = List.of("a", "b", "c");
        System.out.println(list.contains("c")); // true
        System.out.println(list.contains("d")); // false
        System.out.println(list.indexOf("c")); // 2
        System.out.println(list.indexOf("d")); // -1
        // 传入不同的实例：
        System.out.println(list.contains(new String("c"))); // true
        System.out.println(list.indexOf(new String("c"))); // 2

        // List内部不是使用==判断两个元素是否相等，而是使用equal()方法判断两个元素是否相等
        //
        // 因此，要正确使用List的contains()、indexOf()这些方法，放入的实例必须正确覆写equals()方法，否则，放进去的实例，查找不到。
        // 我们之所以能正常放入String、Integer这些对象，是因为Java标准库定义的这些类已经正确实现了equals()方法。

        // 我们以Person对象为例，测试一下：
        List<Person> list1 = List.of(
                new Person("Xiao Ming"),
                new Person("Xiao Hong"),
                new Person("Bob")
        );
        System.out.println(list.contains(new Person("Bob"))); // false
        // 不出意外，虽然放入了new Person("Bob")，但是用另一个new Person("Bob")查询不到，原因就是Person类没有覆写equals()方法。

        // 编写equals
        // equals()方法要求我们必须满足以下条件：
        //
        // 自反性（Reflexive）：对于非null的x来说，x.equals(x)必须返回true；
        // 对称性（Symmetric）：对于非null的x和y来说，如果x.equals(y)为true，则y.equals(x)也必须为true；
        // 传递性（Transitive）：对于非null的x、y和z来说，如果x.equals(y)为true，y.equals(z)也为true，那么x.equals(z)也必须为true；
        // 一致性（Consistent）：对于非null的x和y来说，只要x和y状态不变，则x.equals(y)总是一致地返回true或者false；
        // 对null的比较：即x.equals(null)永远返回false。

        // 总结一下equals()方法的正确编写方法：
        //
        // 1、先确定实例“相等”的逻辑，即哪些字段相等，就认为实例相等；
        // 2、用instanceof判断传入的待比较的Object是不是当前类型，如果是，继续比较，否则，返回false；
        // 3、对引用类型用Objects.equals()比较，对基本类型直接用==比较。

        System.out.println(list.indexOf(new Person("Bob")));
    }
}

// 例如contains()方法可以实现如下：
class ArrayList {
    Object[] elementData;

    public boolean contains(Object o) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i].equals(o)) {
                return true;
            }
        }
        return false;
    }
}

class Person {
    public String name;

    public int age;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // public boolean equals(Object obj) {
    //     if (obj instanceof Person p) {
    //         boolean nameEquals = false;
    //         if (this.name == null && p.name == null) {
    //             nameEquals = true;
    //         }
    //         if (this.name != null) {
    //             nameEquals = this.name.equals(p.name);
    //         }
    //         return nameEquals && this.age == p.age;
    //     }
    //     return false;
    // }

    // 简化引用类型的比较，我们使用Objects.equals()静态方法：
    public boolean equals(Object obj) {
        if (obj instanceof Person p) {
            return Objects.equals(this.name, p.name) && this.age == p.age;
        }
        return false;
    }
}
