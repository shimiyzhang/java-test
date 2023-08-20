package collection.useList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// 使用List
// 小结
// List是按索引顺序访问的长度可变的有序表，优先使用ArrayList而不是LinkedList；
//
// 可以直接使用for each遍历List；
//
// List可以和Array相互转换。
public class Main {
    public static void main(String[] args) {
        // 在集合类中，List是最基础的一种集合：它是一种有序列表。

        // List的行为和数组几乎完全相同：
        // List内部按照放入元素的先后顺序存放，每个元素都可以通过索引确定自己的位置，List的索引和数组一样，从0开始。

        // 数组和List类似，也是有序结构，如果我们使用数组，在添加和删除元素的时候，会非常不方便。
        //
        // 例如，从一个已有的数组{'A', 'B', 'C', 'D', 'E'}中删除索引为2的元素：
        // ┌───┬───┬───┬───┬───┬───┐
        // │ A │ B │ C │ D │ E │   │
        // └───┴───┴───┴───┴───┴───┘
        //               │   │
        //           ┌───┘   │
        //           │   ┌───┘
        //           │   │
        //           ▼   ▼
        // ┌───┬───┬───┬───┬───┬───┐
        // │ A │ B │ D │ E │   │   │
        // └───┴───┴───┴───┴───┴───┘
        // 这个“删除”操作实际上是把'C'后面的元素依次往前挪一个位置，
        // 而“添加”操作实际上是把指定位置以后的元素都依次向后挪一个位置，腾出来的位置给新加的元素。
        // 这两种操作，用数组实现非常麻烦。
        //
        // 因此，在实际应用中，需要增删元素的有序列表，我们使用最多的是ArrayList。
        // 实际上，ArrayList在内部使用了数组来存储所有元素。
        //
        // 例如，一个ArrayList拥有5个元素，实际数组大小为6（即有一个空位）：
        // size=5
        // ┌───┬───┬───┬───┬───┬───┐
        // │ A │ B │ C │ D │ E │   │
        // └───┴───┴───┴───┴───┴───┘
        //
        // 当添加一个元素并指定索引到ArrayList时，ArrayList自动移动需要移动的元素：
        // size=5
        // ┌───┬───┬───┬───┬───┬───┐
        // │ A │ B │   │ C │ D │ E │
        // └───┴───┴───┴───┴───┴───┘
        //
        // 然后，往内部指定索引的数组位置添加一个元素，然后把size加1：
        // size=6
        // ┌───┬───┬───┬───┬───┬───┐
        // │ A │ B │ F │ C │ D │ E │
        // └───┴───┴───┴───┴───┴───┘
        //
        // 继续添加元素，但是数组已满，没有空闲位置的时候，ArrayList先创建一个更大的新数组，然后把旧数组的所有元素复制到新数组，紧接着用新数组取代旧数组：
        // size=6
        // ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
        // │ A │ B │ F │ C │ D │ E │   │   │   │   │   │   │
        // └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘
        //
        // 现在，新数组就有了空位，可以继续添加一个元素到数组末尾，同时size加1：
        // size=7
        // ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
        // │ A │ B │ F │ C │ D │ E │ G │   │   │   │   │   │
        // └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘
        //
        // 可见，ArrayList把添加和删除的操作封装起来，让我们操作List类似于操作数组，却不用关心内部元素如何移动。

        // 我们考察List<E>接口，可以看到几个主要的接口方法：
        //
        // 在末尾添加一个元素：boolean add(E e)
        // 在指定索引添加一个元素：boolean add(int index, E e)
        // 删除指定索引的元素：E remove(int index)
        // 删除某个元素：boolean remove(Object e)
        // 获取指定索引的元素：E get(int index)
        // 获取链表大小（包含元素的个数）：int size()
        //
        // 但是，实现List接口并非只能通过数组（即ArrayList的实现方式）来实现，另一种LinkedList通过“链表”也实现了List接口。

        // 在LinkedList中，它的内部每个元素都指向下一个元素：
        //         ┌───┬───┐   ┌───┬───┐   ┌───┬───┐   ┌───┬───┐
        // HEAD ──>│ A │ ●─┼──>│ B │ ●─┼──>│ C │ ●─┼──>│ D │   │
        //         └───┴───┘   └───┴───┘   └───┴───┘   └───┴───┘

        // 我们来比较一下ArrayList和LinkedList：
        //
        //                    ArrayList	      LinkedList
        // 获取指定元素	      速度很快       	  需要从头开始查找元素
        // 添加元素到末尾	      速度很快	      速度很快
        // 在指定位置添加/删除	  需要移动元素	  不需要移动元素
        // 内存占用	          少	          较大
        //
        // 通常情况下，我们总是优先使用ArrayList。

        // List的特点
        //
        // List接口允许我们添加重复的元素，即List内部的元素可以重复：
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("pear");
        list.add("apple");
        System.out.println(list.size()); // size=3
        //
        // List允许添加null：
        list.add(null);
        String last = list.get(3);
        System.out.println(last); // null

        // 创建List
        // 使用List接口提供的of()方法，根据给定元素快速创建List：
        List<Integer> intList = List.of(1, 2, 5);
        //
        // 但List.of()方法不接受null值，如果传入null，会抛出NullPointerException异常。

        // 遍历List
        // 和数组类型，我们要遍历一个List，完全可以用for循环根据索引配合get(int)方法遍历：
        List<String> stringList = List.of("apple", "pear", "banana");
        for (int i = 0; i < stringList.size(); i++) {
            String s = stringList.get(i);
            System.out.println(s);
        }
        // 但这种方式并不推荐，
        // 一是代码复杂，
        // 二是因为get(int)方法只有ArrayList的实现是高效的，换成LinkedList后，索引越大，访问速度越慢。
        //
        // 所以我们要始终坚持使用迭代器Iterator来访问List。
        // Iterator本身也是一个对象，但它是由List的实例调用iterator()方法的时候创建的。
        // Iterator对象知道如何遍历一个List，并且不同的List类型，返回的Iterator对象实现也是不同的，但总是具有最高的访问效率。

        // Iterator对象有两个方法：
        // boolean hasNext()判断是否有下一个元素，E next()返回下一个元素。
        //
        // 因此，使用Iterator遍历List代码如下：
        for (Iterator<String> it = stringList.iterator(); it.hasNext(); ) {
            String s = it.next();
            System.out.println(s);
        }
        //
        // 通过Iterator遍历List永远是最高效的方式。
        // 由于Iterator遍历是如此常用，所以，Java的for each循环本身就可以帮我们使用Iterator遍历。
        // 把上面的代码再改写如下：
        for (String s : stringList) {
            System.out.println(s);
        }
        //
        // 只要实现了Iterable接口的集合类都可以直接用for each循环来遍历，
        // Java编译器本身并不知道如何遍历集合对象，但它会自动把for each循环变成Iterator的调用，
        // 原因就在于Iterable接口定义了一个Iterator<E> iterator()方法，强迫集合类必须返回一个Iterator实例。

        // List和Array转换
        // 把List变为Array有三种方法，第一种是调用toArray()方法直接返回一个Object[]的数组：
        Object[] array = stringList.toArray();
        for (Object s : array) {
            System.out.println(s);
        }
        // 这种方法会丢失类型信息，所以实际应用很少。
        //
        // 第二种方式是给toArray(T[])传入一个类型相同的Array，List内部自动把元素复制到传入的Array中：
        Integer[] integers = intList.toArray(new Integer[3]);
        for (Integer n : integers) {
            System.out.println(n);
        }
        //
        // 注意到这个toArray(T[])方法的泛型参数<T>并不是List接口定义的泛型参数<E>，所以，我们实际上可以传入其他类型的数组，
        // 例如我们传入Number类型的数组，返回的仍然是Number类型：
        Number[] numbers = intList.toArray(new Number[3]);
        for (Number n : numbers) {
            System.out.println(n);
        }
        // 但是，如果我们传入类型不匹配的数组，
        // 例如，String[]类型的数组，由于List的元素是Integer，所以无法放入String数组，这个方法会抛出ArrayStoreException。


        // 如果我们传入的数组大小和List实际的元素个数不一致怎么办？
        // 如果传入的数组不够大，那么List内部会创建一个新的刚好够大的数组，填充后返回；
        // 如果传入的数组比List元素还要多，那么填充完元素后，剩下的数组元素一律填充null。
        //
        Integer[] integers1 = intList.toArray(new Integer[1]);
        for (Integer i: integers1) {
            System.out.println(i);
        }
        //
        Integer[] integers2 = intList.toArray(new Integer[9]);
        for (Integer i: integers2) {
            System.out.println(i);
        }
        //
        // 实际上，最常用的是传入一个“恰好”大小的数组：
        Integer[] integers3 = intList.toArray(new Integer[intList.size()]);
        //
        // 最后一种更简洁的写法是通过List接口定义的T[] toArray(IntFunction<T[]> generator)方法：
        Integer[] integers4 = intList.toArray(Integer[]::new);

        // 反过来，把Array变为List就简单多了，通过List.of(T...)方法最简单：
        Integer[] integers5 = {1, 2, 3};
        List<Integer> integerList5 = List.of(integers5);
        // 对于JDK 11之前的版本，可以使用Arrays.asList(T...)方法把数组转换成List。
        List<Integer> integerList6 = Arrays.asList(integers5);

        // 要注意的是，返回的List不一定就是ArrayList或者LinkedList，
        // 因为List只是一个接口，如果我们调用List.of()，它返回的是一个只读List：
        List<Integer> integerList7 = List.of(12,34,56);
        integerList7.add(78); // UnsupportedOperationException
        //
        // 对只读List调用add()、remove()方法会抛出UnsupportedOperationException。


    }
}
