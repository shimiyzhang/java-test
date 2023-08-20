package generice.extendsWildcard;

// extends通配符
// 小结
// 使用类似<? extends Number>通配符作为方法参数时表示：
//
// 方法内部可以调用获取Number引用的方法，例如：Number n = obj.getFirst();；
//
// 方法内部无法调用传入Number引用的方法（null除外），例如：obj.setFirst(Number n);。
//
// 即一句话总结：使用extends通配符表示可以读，不能写。
//
// 使用类似<T extends Number>定义泛型类时表示：
//
// 泛型类型限定为Number以及Number的子类。
public class Main {
    public static void main(String[] args) {
        // 由于泛型的继承关系：Class<Integer>不是Class<Number>的子类

        int sum = add(new Pair<>(1, 2));
        System.out.println(sum);
        // 注意：传入的类型是Pair<Number>，实际参数类型是(Integer, Integer)。

        // 试试传入Pair<Integer>：
        Pair<Integer> ip = new Pair<>(1, 2);
        // int n = add(ip);
        // 直接运行，会得到一个编译错误：
        //
        // incompatible types: Pair<Integer> cannot be converted to Pair<Number>
        // 原因很明显，因为Pair<Integer>不是Pair<Number>的子类，因此，add(Pair<Number>)不接受参数类型Pair<Integer>。

        // 但是从add()方法的代码可知，传入Pair<Integer>是完全符合内部代码的类型规范
        // 问题在于方法参数类型定死了只能传入Pair<Number>。
        // 解决办法：使用Pair<? extends Number>使得方法接收所有泛型类型为Number或Number子类的Pair类型。
        int n = editedAdd(ip);
        System.out.println(n);

        // 使用<? extends Number>的泛型定义称之为上界通配符（Upper Bounds Wildcards），
        // 即把泛型类型T的上界限定在Number了。
    }

    // 针对Pair<Number>类型写了静态方法，它的参数是Pair<Number>：
    static int add(Pair<Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();
        return first.intValue() + last.intValue();
    }

    // 改写参数为Pair<? extends Number>：
    static int editedAdd(Pair<? extends Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();

        // 但不能预测实际类型就是Integer：
        // Integer x = p.getFirst(); // Error!

        // <? extends Number>通配符的一个重要限制：
        // 方法参数签名setFirst(? extends Number)无法传递任何Number的子类型给setFirst(? extends Number)。

        // 因为如果传入的p是Pair<Double>，
        // 显然它满足参数定义Pair<? extends Number>，
        // 然而，Pair<Double>的setFirst()显然无法接受Integer类型。

        // p.setFirst(new Integer(first.intValue() + 1)); // Error!
        // p.setLast(new Integer(last.intValue() + 1)); // Error!

        // 唯一的例外是可以给方法参数传入null：
        p.setFirst(null); // ok, 但是后面会抛出NullPointerException
        p.getFirst().intValue(); // NullPointerException

        return first.intValue() + last.intValue();
    }

    // 现在，让我们定义一个方法来处理列表的每个元素：

    // 允许调用get()方法获取Integer的引用；
    // 不允许调用set(? extends Integer)方法并传入任何Integer的引用（null除外）。

    // 因此，方法参数类型List<? extends Integer>表明了该方法内部只会读取List的元素，
    // 不会修改List的元素（因为无法调用add(? extends Integer)、remove(? extends Integer)这些方法。
    // 换句话说，这是一个对参数List<? extends Integer>进行只读的方法（恶意调用set(null)除外）。
    static int sumOfList(List<? extends Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            Integer n = list.get(i);
            sum += n;
        }
        // list.add(new Integer(1)); // Error!;
        return sum;
    }
}

// 假设定义了Pair<T>：
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

// extends通配符的作用
// 如果我们考察Java标准库的java.util.List<T>接口，它实现的是一个类似“可变数组”的列表，主要功能包括：
interface List<T> {
    int size(); // 获取个数

    T get(int index); // 根据索引获取指定元素

    void add(T t); // 添加一个新元素

    void remove(T t); // 删除一个已有元素
}

// 使用extends限定T类型
class Pair2<T extends Number> {
}

// 现在，我们只能定义：
// Pair2<Number> p1 = null;
// Pair2<Integer> p2 = new Pair2<>(1, 2);
// Pair2<Double> p3 = null;

// 因为Number、Integer和Double都符合<T extends Number>。
//
// 非Number类型将无法通过编译：
//
// Pair<String> p1 = null; // compile error!
// Pair<Object> p2 = null; // compile error!
// 因为String、Object都不符合<T extends Number>，因为它们不是Number类型或Number的子类。