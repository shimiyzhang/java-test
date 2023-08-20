package generice.typeErasure;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

// 擦拭法
// 小结
//        Java的泛型是采用擦拭法实现的；
//
//        擦拭法决定了泛型<T>：
//
//        不能是基本类型，例如：int；
//        不能获取带泛型类型的Class，例如：Pair<String>.class；
//        不能判断带泛型类型的类型，例如：x instanceof Pair<String>；
//        不能实例化T类型，例如：new T()。
//        泛型方法要防止重复定义方法，例如：public boolean equals(T obj)；
//
//        子类可以获取父类的泛型类型<T>。
public class Main {
    public static void main(String[] args) {
        // Java语言的泛型实现方式是擦拭法（Type Erasure）。
        // 所谓擦拭法是指，虚拟机对泛型其实一无所知，所有的工作都是编译器做的。

        // 因此，Java使用擦拭法实现泛型，导致了：
        //
        // 编译器把类型<T>视为Object；
        // 编译器根据<T>实现安全的强制转型。

        // 使用泛型的时候，我们编写的代码也是编译器看到的代码：
        Pair<String> p = new Pair<String>("Hello", "World");
        String s1 = p.getFirst();
        String s2 = p.getLast();

        // 而虚拟机执行的代码并没有泛型：
        Pair p2 = new Pair("Hello", "World");
        String s3 = (String) p2.getFirst();
        String s4 = (String) p2.getLast();

        // 所以，Java的泛型是由编译器在编译时实行的，编译器内部永远把所有类型T视为Object处理，
        // 但是，在需要转型的时候，编译器会根据T的类型自动为我们实行安全地强制转型。

        // Java泛型的局限：

        // 局限一：<T>不能是基本类型，例如int，因为实际类型是Object，Object类型无法持有基本类型：
        // Pair<int> p3 = new Pair<int>(1, 2); // compile error

        // 局限二：无法取得带泛型的Class。观察以下代码：
        Pair<String> p5 = new Pair<String>("Hello", "World");
        Pair<Integer> p6 = new Pair<Integer>(1, 2);
        Class c1 = p5.getClass();
        Class c2 = p6.getClass();
        System.out.println(c1 == c2); // true
        System.out.println(c1 == Pair.class); // true
        // 所有泛型实例，无论T的类型是什么，getClass()返回同一个Class实例，
        // 因为编译后它们全部都是Pair<Object>。

        // 局限三：无法判断带泛型的类型：
        Pair<String> p7 = new Pair<String>("Hello", "World");
        // Compile error:
        // if (p7 instanceof Pair<Integer>) {
            // System.out.println("Yes");
        // }
        // 原因和前面一样，并不存在Pair<String>.class，而是只有唯一的Pair.class。

        // 借助Class<T>参数并通过反射来实例化T类型，使用的时候，也必须传入Class<T>
        Pair<String> p8 = new Pair<>(String.class);

        // 泛型继承
        // 使用的时候，因为子类IntPair并没有泛型类型，所以，正常使用即可：
        IntPair ip = new IntPair(1, 2);

        // 在父类是泛型类型的情况下，编译器就必须把类型T（对IntPair来说，也就是Integer类型）保存到子类的class文件中，
        // 不然编译器就不知道IntPair只能存取Integer这种类型。

        // 在继承了泛型类型的情况下，子类可以获取父类的泛型类型。
        Class<IntPair> clazz = IntPair.class;
        Type t = clazz.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) t;
            Type[] types = pt.getActualTypeArguments(); // 可能有多个泛型类型
            Type firstType = types[0];
            Class<?> typeClass = (Class<?>) firstType;
            System.out.println(typeClass); // Integer
        }

        // 因为Java引入了泛型，所以，只用Class来标识类型已经不够了。
        // 实际上，Java的类型系统结构如下：
        //                       ┌────┐
        //                       │Type│
        //                       └────┘
        //                          ▲
        //                          │
        //    ┌────────────┬────────┴─────────┬───────────────┐
        //    │            │                  │               │
        // ┌─────┐┌─────────────────┐┌────────────────┐┌────────────┐
        // │Class││ParameterizedType││GenericArrayType││WildcardType│
        // └─────┘└─────────────────┘└────────────────┘└────────────┘
    }
}

// 例如，我们编写了一个泛型类Pair<T>，这是编译器看到的代码：
class Pair<T> {
    private T first;
    private T last;

    // 局限四：不能实例化T类型：
    public Pair() {
        // Compile error:
        // first = new T();
        // last = new T();

        // 擦拭后实际上变成了：
        // first = new Object();
        // last = new Object();
        // 这样一来，创建new Pair<String>()和创建new Pair<Integer>()就全部成了Object，
        // 显然编译器要阻止这种类型不对的代码。
    }

    public Pair(Class<T> clazz) {
        try {
            this.first = clazz.newInstance();
            this.last = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }

    public T getLast() {
        return last;
    }

    // 不恰当的覆写方法
    // 这是因为，定义的equals(T t)方法实际上会被擦拭成equals(Object t)，
    // 而这个方法是继承自Object的，编译器会阻止一个实际上会变成覆写的泛型方法定义。
    // public boolean equals(T t) {
        // return this == t;
    // }

    // 换个方法名，避开与Object.equals(Object)的冲突就可以成功编译：
    public boolean same(T o) {
        return this == o;
    }
}

// 而虚拟机根本不知道泛型。这是虚拟机执行的代码：
//class Pair {
//    private Object first;
//    private Object last;
//
//    public Pair(Object first, Object last) {
//        this.first = first;
//        this.last = last;
//    }
//
//    public Object getFirst() {
//        return first;
//    }
//
//    public Object getLast() {
//        return last;
//    }
//}

// 泛型继承
// 一个类可以继承自一个泛型类。
class IntPair extends Pair<Integer> {
    public IntPair(Integer first, Integer last) {
        super(first, last);
    }
}