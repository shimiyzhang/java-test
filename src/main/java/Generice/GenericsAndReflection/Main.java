package Generice.GenericsAndReflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;

// 泛型和反射
// 小结
// 部分反射API是泛型，例如：Class<T>，Constructor<T>；
//
// 可以声明带泛型的数组，但不能直接创建带泛型的数组，必须强制转型；
//
// 可以通过Array.newInstance(Class<T>, int)创建T[]数组，需要强制转型；
//
// 同时使用泛型和可变参数时需要特别小心。
public class Main {
    public static void main(String[] args) throws Exception {
        // Java的部分反射API也是泛型，比如Class<T>就是泛型：

        // compile warning：
        Class clazz = String.class;
        String str = (String) clazz.newInstance();

        // no warning：
        Class<String> clazz2 = String.class;
        String str2 = clazz2.newInstance();

        // 调用class的getSuperclass()方法返回的Class类型是Class<? super T>：
        Class<? super String> sup = String.class.getSuperclass();

        // 构造方法Constructor<T>也是泛型：
        Class<Integer> clazz3 = Integer.class;
        Constructor<Integer> cons = clazz3.getConstructor(int.class);

        // 可以声明带泛型的数组，但不能用new操作符创建带泛型的数组：
        Pair<String>[] ps = null; // ok
        // Pair<String>[] ps2 = new Pair<String>[2]; // compile error！

        // 必须通过强制转型实现带泛型的数组：
        @SuppressWarnings("unchecked")
        Pair<String>[] ps3 = (Pair<String>[]) new Pair[2];

        // 因为数组实际上在运行期没有泛型，编译器可以强制检查变量ps，因为它的类型是泛型数组。
        // 但是，编译器不会检查变量arr，因为它不是泛型数组。
        Pair[] arr = new Pair[2];
        Pair<String>[] ps4 = (Pair<String>[]) arr;

        // 要安全的使用泛型数组，必须扔掉arr的引用：
        @SuppressWarnings("unchecked")
        Pair<String>[] ps5 = (Pair<String>[]) new Pair[2];
        // 上面的代码中，由于拿不到原始数组的引用，就只能对泛型数组ps进行操作，这种操作就是安全的。

        ps4[0] = new Pair<String>("a", "b");
        arr[1] = new Pair<Integer>(1, 2);
        // ClassCastException:
        Pair<String> p = ps4[1];
        String s = p.getFirst();

        // 带泛型的数组实际上是编译器的类型擦除：
        System.out.println(ps4.getClass() == Pair[].class); // true
        String s1 = (String) arr[0].getFirst();
        String s2 = ps4[1].getFirst();

        String[] ss = ArrayHelper.asArray("a", "b", "c");
        Integer[] ns = ArrayHelper.asArray(1, 2, 3);

        // 谨慎使用泛型可变参数
        // 如果在方法内部创建了泛型数组，最好不要将它返回给外部使用。
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
        return this.first;
    }

    public T getLast() {
        return last;
    }
}

// 所以我们不能直接创建泛型数组T[]，因为擦拭后代码变为Object[]：
// compile error:
@SuppressWarnings("unchecked")
class Abc<T> {
    // T[] createArray() {
    //     return new T[5];
    // }

    // 必须借助Class<T>来创建泛型数组：
    T[] createArray(Class<T> cls) throws Exception {
        return (T[]) Array.newInstance(cls, 5);
    }
}

// 我们还可以利用可变参数创建泛型数组T[]：
class ArrayHelper {
    @SafeVarargs
    static <T> T[] asArray(T ...objs) {
        return objs;
    }
}

