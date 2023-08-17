package Collection.UseDeque;

import java.util.Deque;
import java.util.LinkedList;

// 使用Deque
// 小结
// Deque实现了一个双端队列（Double Ended Queue），它可以：
//
// 将元素添加到队尾或队首：addLast()/offerLast()/addFirst()/offerFirst()；
// 从队首／队尾获取元素并删除：removeFirst()/pollFirst()/removeLast()/pollLast()；
// 从队首／队尾获取元素但不删除：getFirst()/peekFirst()/getLast()/peekLast()；
// 总是调用xxxFirst()/xxxLast()以便与Queue的方法区分开；
// 避免把null添加到队列。
public class Main {
    public static void main(String[] args) {
        // Queue是队列，只能一头进，另一头出。
        // 允许两头都进，两头都出，这种队列叫双端队列（Double Ended Queue），学名Deque。

        // Java集合提供了接口Deque来实现一个双端队列，它的功能是：
        //
        // 既可以添加到队尾，也可以添加到队首；
        // 既可以从队首获取，又可以从队尾获取。

        // 比较一下Queue和Deque出队和入队的方法：
        //                       Queue	                        Deque
        // 添加元素到队尾	         add(E e) / offer(E e)	        addLast(E e) / offerLast(E e)
        // 取队首元素并删除	     E remove() / E poll()	        E removeFirst() / E pollFirst()
        // 取队首元素但不删除	     E element() / E peek()	        E getFirst() / E peekFirst()
        // 添加元素到队首	         无	                            addFirst(E e) / offerFirst(E e)
        // 取队尾元素并删除	     无	                            E removeLast() / E pollLast()
        // 取队尾元素但不删除	     无	                            E getLast() / E peekLast()
        //
        // 对于添加元素到队尾的操作，Queue提供了add()/offer()方法，而Deque提供了addLast()/offerLast()方法。
        // 添加元素到队首、取队尾元素的操作在Queue中不存在，在Deque中由addFirst()/removeLast()等方法提供。

        // Deque接口扩展自Queue，因此，Queue提供的add()/offer()方法在Deque中也可以使用，
        // 但是，使用Deque，最好不要调用offer()，而是调用offerLast()：
        Deque<String> deque = new LinkedList<>();
        deque.offerLast("A"); // A
        deque.offerLast("B"); // A <- B
        deque.offerFirst("C"); // C <- A <- B
        System.out.println(deque.pollFirst()); // C，剩下A <- B
        System.out.println(deque.pollLast()); // B，剩下A
        System.out.println(deque.pollFirst()); // A
        System.out.println(deque.pollFirst()); // null

        // 使用Deque，推荐总是明确调用offerLast()/offerFirst()或者pollFirst()/pollLast()方法。

        // Deque是一个接口，它的实现类有ArrayDeque和LinkedList。
        //
        // LinkedList即是List，又是Queue，还是Deque。
        // 我们在使用的时候，总是用特定的接口来引用它，这是因为持有接口说明代码的抽象层次更高，而且接口本身定义的方法代表了特定的用途。
        // 不推荐的写法：
        LinkedList<String> d1 = new LinkedList<>();
        // 推荐的写法：
        Deque<String> d2 = new LinkedList<>();

        // 面向抽象编程的一个原则就是：尽量持有接口，而不是具体的实现类。
    }
}
