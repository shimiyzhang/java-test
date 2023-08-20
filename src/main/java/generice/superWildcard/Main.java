package generice.superWildcard;

import java.util.List;

// super通配符
public class Main {
    public static void main(String[] args) {
        // 传入Pair<Integer>是允许的，但是传入Pair<Number>是不允许的。
        Pair<Integer> p = new Pair<>(1, 2);
        set(p, 10, 20);
        Pair<Number> p2 = new Pair<>(1, 2);
        // set(p2, 10, 20); // Error!

        // 我们希望接受Pair<Integer>类型，以及Pair<Number>、Pair<Object>
        editedSet(p2, 10, 20); // 被正常编译

        // 考察Pair<? super Integer>的setFirst()方法，它的方法签名实际上是：
        // void setFirst(? super Integer);
        // 因此，可以安全地传入Integer类型。

        // 对比extends和super通配符
        // 作为方法参数，<? extends T>类型和<? super T>类型的区别在于：
        //
        // <? extends T>允许调用读方法T get()获取T的引用，但不允许调用写方法set(T)传入T的引用（传入null除外）；
        //
        // <? super T>允许调用写方法set(T)传入T的引用，但不允许调用读方法T get()获取T的引用（获取Object除外）。
        //
        // 一个是允许读不允许写，另一个是允许写不允许读。

        List<Number> numbers = List.of(1, 2, 3);
        List<Integer> integers = List.of(1, 2, 3);

        Collections.copy(numbers, integers);

        // ERROR: cannot copy List<Number> to List<Integer>:
        // Collections.copy(integers, numbers);

        // 个人理解的报错原因：
        // 报错是在编译时出现的
        // 因为定义的参数类型是List<? super T> dest, List<? extends T> src
        // 当调用时传递copy(integers, numbers)，实际类型为List<Integer>, List<Number>
        // 不存在一个类型T，使得T即是Integer的子类（满足Integer = ? super T），而且是Number的父类（满足Number = ? extends T）
        // 如果满足Integer = ? super T），则T只能是Integer，因此不满足Number = ? extends Integer
        // 如果满足Number = ? extends T），则T可能是Number、Object，因此不满足Integer = ? super Number或Integer = ? super Object
    }

    static void set(Pair<Integer> p, Integer first, Integer last) {
        p.setFirst(first);
        p.setLast(last);
    }

    // 我们使用super通配符来改写这个方法：
    // 注意到Pair<? super Integer>表示，方法参数接受所有泛型类型为Integer或Integer父类的Pair类型。
    static void editedSet(Pair<? super Integer> p, Integer first, Integer last) {
        p.setFirst(first);
        p.setLast(last);

        // 这里注意到我们无法使用Integer类型来接收getFirst()的返回值，即下面的语句将无法通过编译：
        // Integer x = p.getFirst(); // Error!
        // 因为如果传入的实际类型是Pair<Number>，编译器无法将Number类型转型为Integer。

        // 唯一可以接收getFirst()方法返回值的是Object类型：
        Object x = p.getFirst();

        // 因此，使用<? super Integer>通配符表示：
        //
        // 允许调用set(? super Integer)方法传入Integer的引用；
        //
        // 不允许调用get()方法获得Integer的引用。
        //
        // 唯一例外是可以获取Object的引用：Object o = p.getFirst()。
        //
        // 换句话说，使用<? super Integer>通配符作为方法参数，表示方法内部代码对于参数只能写，不能读。
    }
}

class Pair<T> {
    private T first;
    private T last;

    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getLast() {
        return last;
    }

    public void setLast(T last) {
        this.last = last;
    }
}

// 我们来看Java标准库的Collections类定义的copy()方法：
class Collections {
    // 把src的每个元素复制到dest中:
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            T t = src.get(i);
            dest.add(t);
        }
        // T t = dest.get(0); // compile error!
        // src.add(t); // compile error!
    }
    // 这个copy()方法的定义就完美地展示了extends和super的意图：
    //
    // copy()方法内部不会读取dest，因为不能调用dest.get()来获取T的引用；
    //
    // copy()方法内部也不会修改src，因为不能调用src.add(T)。
}