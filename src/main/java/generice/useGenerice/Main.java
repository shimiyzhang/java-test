package generice.useGenerice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 使用泛型
// 小结
//        使用泛型时，把泛型参数<T>替换为需要的class类型，例如：ArrayList<String>，ArrayList<Number>等；
//
//        可以省略编译器能自动推断出的类型，例如：List<String> list = new ArrayList<>();；
//
//        不指定泛型参数类型时，编译器会给出警告，且只能将<T>视为Object类型；
//
//        可以在接口中定义泛型类型，实现此接口的类必须实现正确的泛型类型。
public class Main {
    public static void main(String[] args) {
        // 如果不定义泛型类型时，泛型类型实际上就是Object
        // 编辑器警告：
        List list = new ArrayList();
        list.add("hello");
        list.add("world");
        String first = (String) list.get(0);
        String second = (String) list.get(1);
        System.out.println(first + " " + second);
        // 此时，只能把<T>当作Object使用，没有发挥泛型的优势。

        // 当我们定义泛型类型<String>后，List<T>的泛型接口变为强类型List<String>：
        // 无编辑器警告：
        List<String> list1 = new ArrayList<String>();
        list1.add("hello");
        list1.add("world");
        // 无强制转型:
        String first1 = list1.get(0);
        String second1 = list1.get(1);
        System.out.println(first1 + " " + second1);

        // List<Number> list2 = new ArrayList<Number>();
        // 代码简写为：
        List<Number> list2 = new ArrayList<>();
        list2.add(123);
        list2.add(12.34);
        Number n1 = list2.get(0);
        Number n2 = list2.get(1);
        System.out.println(n1 + " " + n2);

        // 泛型接口
        // 除了ArrayList<T>使用了泛型，还可以在接口中使用泛型。
        // 例如，Arrays.sort(Object[])可以对任意数组进行排序，但待排序的元素必须实现Comparable<T>这个泛型接口：

        // 直接对String数组进行排序：
        String[] strArray = new String[]{"Orange", "Apple", "Pear" };
        Arrays.sort(strArray);
        System.out.println(Arrays.toString(strArray)); // Apple Orange Pear
        // 这是因为String本身已经实现了Comparable<String>接口。

        // 自定义的Person类型：
        Person[] ps = new Person[]{
                new Person("Bob", 90),
                new Person("Alice", 100),
                new Person("Candy", 95)
        };
        Arrays.sort(ps); // ClassCastException，即无法将Person转型为Comparable
        System.out.println(Arrays.toString(ps));
    }
}

// 修改代码，让Person实现Comparable<T>接口：
class Person implements Comparable<Person> {
    String name;
    int score;

    public Person(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // 实现Comparable<T>接口的compareTo()方法：
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
        // return this.score - other.score;
    }

    public String toString() {
        return this.name + " " + this.score;
    }
}