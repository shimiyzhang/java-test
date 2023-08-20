package generice;

import java.util.ArrayList;
import java.util.List;

// 泛型
// 泛型的本质是参数化类型，也就是说所操作的数据类型被指定为一个参数。
// 小结
//        泛型就是编写模板代码来适应任意类型；
//
//        泛型的好处是使用时不必对类型进行强制转换，它通过编译器对类型进行检查；
//
//        注意泛型的继承关系：
//        可以把ArrayList<Integer>向上转型为List<Integer>（T不能变！），
//        但不能把ArrayList<Integer>向上转型为ArrayList<Number>（T不能变成父类）。
public class Main {
    public static void main(String[] args) {
        // 泛型就是定义一种模板
        // 例如ArrayList<T>，然后在代码中为用到的类创建对应的ArrayList<类型>：
        ArrayList<String> list = new ArrayList<String>();
        // 由编译器针对类型作检查：
        list.add("hello"); // ok
        String s = list.get(0); // ok
        // list.add(123); // compile error!
        // Integer i = list.get(0); // compile error!
        // 既实现了编写一次，万能匹配，又通过编译器保证了类型安全：这就是泛型。

        // 向上转型
        List<String> list2 = new ArrayList<String>();
        // 即类型ArrayList<T>可以向上转型为List<T>。

        // 要特别注意：不能把ArrayList<Integer>向上转型为ArrayList<Number>或List<Number>。
        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(123);
        // ArrayList<Number> numberList = integerList; // Error!
        //  ArrayList<Integer>和ArrayList<Number>两者完全没有继承关系。
    }
}
