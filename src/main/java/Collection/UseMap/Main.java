package Collection.UseMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// 使用Map
// 小结
// Map是一种映射表，可以通过key快速查找value。
//
// 可以通过for each遍历keySet()，也可以通过for each遍历entrySet()，直接获取key-value。
//
// 最常用的一种Map实现是HashMap。
public class Main {
    public static void main(String[] args) {
        // 通过一个键去查询对应的值的需求非常常见。
        // 使用List来实现存在效率非常低的问题，因为平均需要扫描一半的元素才能确定，
        // 而Map这种键值（key-value）映射表的数据结构，作用就是能高效通过key快速查找value（元素）。

        // 用Map来实现根据name查询某个Student的代码如下：
        Student s = new Student("Xiao Ming", 100);
        Map<String, Student> map = new HashMap<>();
        map.put("Xiao Ming", s); // 将"Xiao Ming"和Student实例映射并关联
        Student target = map.get("Xiao Ming"); // 通过key查找并返回映射的Student实例
        System.out.println(target == s); // true
        Student other = map.get("Xiao Hong");
        System.out.println(other); // null

        // Map<K, V>是一种键-值映射表，当我们调用put(K key, V value)方法时，就把key和value做了映射并放入Map。
        // 当我们调用V get(K key)时，就可以通过key获取到对应的value。
        // 如果key不存在，则返回null。
        // 和List类似，Map也是一个接口，最常用的实现类是HashMap。

        // 如果只是想查询某个key是否存在，可以调用boolean containsKey(K key)方法。

        // 如果我们在存储Map映射关系的时候，对同一个key调用两次put()方法，分别放入不同的value，会有什么问题呢？
        // 例如：
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("apple", 123);
        map2.put("pear", 456);
        System.out.println(map2.get("apple")); // 123
        map2.put("apple", 789); // 再次放入apple作为key，但value变为789
        System.out.println(map2.get("apple")); // 789
        // 重复放入key-value并不会有任何问题，但是一个key只能关联一个value。
        // 实际上，put()方法的签名是V put(K key, V value)，如果放入的key已经存在，put()方法会返回被删除的旧的value，否则，返回null。

        //  始终牢记：Map中不存在重复的key，因为放入相同的key，只会把原有的key-value对应的value给替换掉。

        // 在一个Map中，虽然key不能重复，但value是可以重复的：
        Map<String, Integer> map3 = new HashMap<>();
        map3.put("apple", 123);
        map3.put("pear", 123); // ok

        // 遍历Map
        //
        // 对Map来说，要遍历key可以使用for each循环遍历Map实例的keySet()方法返回的Set集合，它包含不重复的key的集合：
        Map<String, Integer> map4 = new HashMap<>();
        map4.put("apple", 123);
        map4.put("pear", 456);
        map4.put("banana", 789);
        for (String key : map4.keySet()) {
            Integer value = map4.get(key);
            System.out.println(key + "=" + value);
        }
        //
        // 同时遍历key和value可以使用for each循环遍历Map对象的entrySet()集合，它包含每一个key-value映射：
        for(Map.Entry<String, Integer> entry : map4.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "=" + value);
        }

        // Map和List不同的是，Map存储的是key-value的映射关系，并且，它不保证顺序。
        // 在遍历的时候，遍历的顺序既不一定是put()时放入的key的顺序，也不一定是key的排序顺序。
        // 使用Map时，任何依赖顺序的逻辑都是不可靠的。
        // 以HashMap为例，假设我们放入"A"，"B"，"C"这3个key，遍历的时候，每个key会保证被遍历一次且仅遍历一次，
        // 但顺序完全没有保证，甚至对于不同的JDK版本，相同的代码遍历的输出顺序都是不同的！

        // 遍历Map时，不可假设输出的key是有序的！
    }
}

class Student {
    public String name;

    public int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
}