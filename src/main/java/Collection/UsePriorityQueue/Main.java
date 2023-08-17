package Collection.UsePriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// 使用PriorityQueue（优先队列）
// 小结
// PriorityQueue实现了一个优先队列：从队首获取元素时，总是获取优先级最高的元素。
//
// PriorityQueue默认按元素比较的顺序排序（必须实现Comparable接口），也可以通过Comparator自定义排序算法（元素就不必实现Comparable接口）。
public class Main {
    public static void main(String[] args) {
        // PriorityQueue的出队顺序与元素的优先级有关，
        // 对PriorityQueue调用remove()或poll()方法,返回的总是优先级最高的元素。
        Queue<String> queue = new PriorityQueue<>();
        queue.offer("Apple");
        queue.offer("pear");
        queue.offer("Banana");
        System.out.println(queue.poll()); // Apple
        System.out.println(queue.poll()); // Banana
        System.out.println(queue.poll()); // pear
        System.out.println(queue.poll()); // null
        // 放入PriorityQueue的元素，必须实现Comparable接口，PriorityQueue会根据元素的排序顺序决定出队的优先级。

        // 如果我们要放入的元素并没有实现Comparable接口怎么办？
        // PriorityQueue允许我们提供一个Comparator对象来判断两个元素的顺序。
        // 我们以银行排队业务为例，实现一个PriorityQueue：
        Queue<User> q = new PriorityQueue<>(new UserComparator());
        // 添加3个元素到队列:
        q.offer(new User("Bob", "A1"));
        q.offer(new User("Tom", "A10"));
        q.offer(new User("Alice", "A2"));
        q.offer(new User("Boss", "V1"));
        System.out.println(q.poll()); // Boss/V1
        System.out.println(q.poll()); // Bob/A1
        System.out.println(q.poll()); // Alice/A2
        System.out.println(q.poll()); // null,因为队列为空
    }
}

class UserComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        if (o1.number.charAt(0) == o2.number.charAt(0)) {
            // 如果都是A或V开头，比较号的大小：
            return Integer.parseInt(o1.number.substring(1)) - Integer.parseInt(o2.number.substring(1));
            // return o1.number.compareTo(o2.number);
        }
        if (o1.number.charAt(0) == 'V') {
            // o1的号码是V，优先级高
            return -1;
        } else {
            return 1;
        }
    }
}

class User {
    public final String name;
    public final String number;

    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String toString() {
        return name + "/" + number;
    }
}