package Collection.UseQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 使用Queue
// 小结
// 队列Queue实现了一个先进先出（FIFO）的数据结构：
//
// 通过add()/offer()方法将元素添加到队尾；
// 通过remove()/poll()从队首获取元素并删除；
// 通过element()/peek()从队首获取元素但不删除。
//
// 要避免把null添加到队列。
public class Main {
    public static void main(String[] args) {
        // 队列（Queue）是一种经常使用的集合。
        // Queue实际上是实现了一个先进先出（FIFO：First In First Out）的有序表。
        // 它和List的区别在于，List可以在任意位置添加和删除元素，而Queue只有两个操作：
        //
        // 把元素添加到队列末尾；
        // 从队列头部取出元素。

        // 在Java的标准库中，队列接口Queue定义了以下几个方法：
        //
        // int size()：获取队列长度；
        // boolean add(E)/boolean offer(E)：添加元素到队尾；
        // E remove()/E poll()：获取队首元素并从队列中删除；
        // E element()/E peek()：获取队首元素但并不从队列中删除。

        // 对于具体的实现类，有的Queue有最大队列长度限制，有的Queue没有。
        // 注意到添加、删除和获取队列元素总是有两个方法，这是因为在添加或获取元素失败时，这两个方法的行为是不同的。
        //
        // 我们用一个表格总结如下：
        //
        //                        throw Exception	      返回false或null
        // 添加元素到队尾	          add(E e)	              boolean offer(E e)
        // 取队首元素并删除	      E remove()	          E poll()
        // 取队首元素但不删除	      E element()	          E peek()

        Queue<String> queue = new LinkedList<>();
        queue.add("Apple"); // 当添加失败时，抛出异常
        queue.offer("Banana"); // 当添加失败时，返回false
        queue.offer("Orange");
        queue.offer("Grape");
        String s = queue.remove(); // 当取出队首元素时，如果当前Queue是一个空队列，调用remove()方法，抛出异常
        String s1 = queue.poll(); // 当获取失败时，返回null
        String s2 = queue.element(); // 当获取失败时，抛出异常
        String s3 = queue.peek(); // 当获取失败时，返回null

        // 因此，两套方法可以根据需要来选择使用。
        // 注意：不要把null添加到队列中，否则poll()方法返回null时，很难确定是取到了null元素还是队列为空。

        // 接下来我们以poll()和peek()为例来说说“获取并删除”与“获取但不删除”的区别。
        // 对于Queue来说，每次调用poll()，都会获取队首元素，并且获取到的元素已经从队列中被删除了：
        Queue<String> queue1 = new LinkedList<>();
        queue1.offer("Apple");
        queue1.offer("Banana");
        queue1.offer("Orange");
        System.out.println(queue1.poll()); // Apple
        System.out.println(queue1.poll()); // Banana
        System.out.println(queue1.poll()); // Orange
        System.out.println(queue1.poll()); // null
        //
        // 如果用peek()，因为获取队首元素时，并不会从队列中删除这个元素，所以可以反复获取：
        Queue<String> queue2 = new LinkedList<>();
        queue2.offer("Apple");
        queue2.offer("Banana");
        queue2.offer("Orange");
        // 队首永远都是apple，因为peek()不会删除它:
        System.out.println(queue2.peek()); // Apple
        System.out.println(queue2.peek()); // Apple
        System.out.println(queue2.peek()); // Apple

        // 从上面的代码中，我们还可以发现，LinkedList即实现了List接口，又实现了Queue接口，
        // 但是，在使用的时候，如果我们把它当作List，就获取List的引用，如果我们把它当作Queue，就获取Queue的引用：
        List<String> list = new LinkedList<>(); // 这是一个List
        Queue<String> queue3 = new LinkedList<>(); // 这是一个Queue
        // 始终按照面向抽象编程的原则编写代码，可以大大提高代码的质量。
    }
}
