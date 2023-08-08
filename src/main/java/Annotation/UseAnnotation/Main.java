package Annotation.UseAnnotation;

// 使用注解
// 小结
//        注解（Annotation）是Java语言用于工具处理的标注：
//
//        注解可以配置参数，没有指定配置的参数使用默认值；
//
//        如果参数名称是value，且只有一个参数，那么可以省略参数名称。
public class Main {
    public static void main(String[] args) {
        // 注解是放在Java源码的类、方法、字段、参数前的一种特殊“注释”：
        // this is a component:
        // @Resource("hello")
        // public class Hello {
        //     @Inject
        //    int n;

        //    @PostConstruct
        //    public void hello(@Param String name) {
        //        System.out.println(name);
        //    }

        //    @Override
        //    public String toString() {
        //        return "Hello";
        //    }
        // }
        // 注释会被编译器直接忽略，注解则可以被编译器打包进入class文件，因此，注解是一种用作标注的“元数据”。

        // 注解的作用
        // 从JVM的角度看，注解本身对代码逻辑没有任何影响，如何使用注解完全由工具决定。

        // Java的注解可以分为三类：
        // 第一类是由编辑器使用的注解，例如：
        //      @Override：让编译器检查该方法是否正确地实现了覆写；
        //      @SuppressWarnings：告诉编译器忽略此处代码产生的警告。
        // 不会被编译进入.class文件

        // 第二类是由工具处理.class文件使用的注解
        // 会被编译进入.class文件，但加载结束后并不会存在于内存中。

        // 第三类是在程序运行期能够读取的注解，它们在加载后一直存在于JVM中，这也是最常用的注解。

        // 定义一个注解时，还可以定义配置参数。配置参数可以包括：
        //
        // 所有基本类型；
        // String；
        // 枚举类型；
        // 基本类型、String、Class以及枚举的数组。

        // 配置参数必须是常量

        // 注解的配置参数可以有默认值

        // 此外，大部分注解会有一个名为value的配置参数，对此参数赋值，可以只写常量，相当于省略了value参数。

    }
}

class Hello {
    @Check(min=0, max=100, value=55)
    public int n;

    @Check(value=99)
    public int p;

    @Check(99) // @Check(value=99)
    public int x;

    @Check
    public int y;
}

@interface Check {
    int min() default 0;
    int max() default 100;
    int value() default 0;
}

// @Check就是一个注解。
// 第一个@Check(min=0, max=100, value=55)明确定义了三个参数，
// 第二个@Check(value=99)只定义了一个value参数，它实际上和@Check(99)是完全一样的。
// 最后一个@Check表示所有参数都使用默认值。
