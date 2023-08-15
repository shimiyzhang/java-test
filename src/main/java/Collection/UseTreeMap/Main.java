package Collection.UseTreeMap;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

// 使用TreeMap
// 小结
// SortedMap在遍历时严格按照Key的顺序遍历，最常用的实现类是TreeMap；
//
// 作为SortedMap的Key必须实现Comparable接口，或者传入Comparator；
//
// 要严格按照compare()规范实现比较逻辑，否则，TreeMap将不能正常工作。
public class Main {
    public static void main(String[] args) {
        // 我们已经知道，HashMap是一种以空间换时间的映射表，它的实现原理决定了内部的Key是无序的，
        // 即遍历HashMap的Key时，其顺序是不可预测的（但每个Key都会遍历一次且仅遍历一次）。

        // 还有一种Map，它在内部会对Key进行排序，这种Map就是SortedMap。
        // 注意到SortedMap是接口，它的实现类是TreeMap。
        //        ┌───┐
        //        │Map│
        //        └───┘
        //          ▲
        //     ┌────┴─────┐
        //     │          │
        // ┌───────┐ ┌─────────┐
        // │HashMap│ │SortedMap│
        // └───────┘ └─────────┘
        //                ▲
        //                │
        //           ┌─────────┐
        //           │ TreeMap │
        //           └─────────┘

        // SortedMap保证遍历时以Key的顺序来进行排序。
        // 例如，放入的Key是"apple"、"pear"、"orange"，遍历的顺序一定是"apple"、"orange"、"pear"，因为String默认按字母排序：
        Map<String, Integer> map = new TreeMap<>();
        map.put("orange", 1);
        map.put("apple", 2);
        map.put("pear", 3);
        for (String key : map.keySet()) {
            System.out.println(key);
        }
        // apple orange pear

        // 使用TreeMap时，放入的Key必须实现Comparable接口。
        // String、Integer这些类已经实现了Comparable接口，因此可以直接作为Key使用。
        // 作为Value的对象则没有任何要求。

        // 如果作为Key的class没有实现Comparable接口，那么，必须在创建TreeMap时同时指定一个自定义排序算法：
        Map<Person, Integer> map2 = new TreeMap<>(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.name.compareTo(p2.name);
            }
        });
        map2.put(new Person("Tom"), 1);
        map2.put(new Person("Bob"), 2);
        map2.put(new Person("Lily"), 3);
        for (Person key : map2.keySet()) {
            System.out.println(key);
        }
        // {Person: Bob}, {Person: Lily}, {Person: Tom}
        System.out.println(map2.get(new Person("Bob"))); // 2

        // 注意到Comparator接口要求实现一个比较方法，它负责比较传入的两个元素a和b，
        // 如果a<b，则返回负数，通常是-1，如果a==b，则返回0，如果a>b，则返回正数，通常是1。
        // TreeMap内部根据比较结果对Key进行排序。
        //
        // 从上述代码执行结果可知，打印的Key确实是按照Comparator定义的顺序排序的。
        // 如果要根据Key查找Value，我们可以传入一个new Person("Bob")作为Key，它会返回对应的Integer值2。
        //
        // 另外，注意到Person类并未覆写equals()和hashCode()，因为TreeMap不使用equals()和hashCode()。

        Map<Student, Integer> map3 = new TreeMap<>(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                 return Integer.compare(s2.score, s1.score);
            }
        });
        map3.put(new Student("Tom", 77), 1);
        map3.put(new Student("Bob", 66), 2);
        map3.put(new Student("Lily", 99), 3);
        for (Student key : map3.keySet()) {
            System.out.println(key);
        }
        // {Bob: score=66}, {Tom: score=77}, {Lily: score=99}
        System.out.println(map3.get(new Student("Bob", 66))); // 2

        // TreeMap在比较两个Key是否相等时，依赖Key的compareTo()方法或者Comparator.compare()方法。
        // 在两个Key相等时，必须返回0。
    }
}

class Person {
    public String name;
    Person(String name) {
        this.name = name;
    }
    public String toString() {
        return "{Person: " + name + "}";
    }
}

class Student {
    public String name;
    public int score;
    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
    public String toString() {
        return String.format("{%s: score=%d}", name, score);
    }
}