package Annotation.DefineAnnotation;

import java.lang.annotation.*;

// 定义注解
// 小结
//        Java使用@interface定义注解：
//
//        可定义多个参数和默认值，核心参数使用value名称；
//
//        必须设置@Target来指定Annotation可以应用的范围；
//
//        应当设置@Retention(RetentionPolicy.RUNTIME)便于运行期读取该Annotation。
public class Main {
    public static void main(String[] args) {
        // 元注解：可以修饰其他注解的注解
        // Java标准库已经定义了一些元注解，我们只需要使用元注解

        // @Target()
        // 最常用的元注解是@Target。使用@Target可以定义Annotation能够被应用于源码的哪些位置：
        //
        // 类或接口：ElementType.TYPE；
        // 字段：ElementType.FIELD；
        // 方法：ElementType.METHOD；
        // 构造方法：ElementType.CONSTRUCTOR；
        // 方法参数：ElementType.PARAMETER。

        // @Retention
        // 元注解@Retention定义了Annotation的生命周期:
        //
        // 仅编译期：RetentionPolicy.SOURCE；
        // 仅class文件：RetentionPolicy.CLASS；
        // 运行期：RetentionPolicy.RUNTIME。
        //
        // 如果@Retention不存在，则该Annotation默认为CLASS。
        // 通常我们自定义的Annotation都是RUNTIME

        // @Repeatable
        // 使用@Repeatable这个元注解可以定义Annotation是否可重复。
        // 这个注解应用不是特别广泛。

        // @Inherited
        // 使用@Inherited定义子类是否可继承父类定义的Annotation。
        // @Inherited仅针对@Target(ElementType.TYPE)类型的annotation有效，
        // 并且仅针对class的继承，对interface的继承无效：
    }
}

// Java使用@interface定义注解（Annotation）:
// 注解的参数类似于无参数方法，可以用default设置一个默认值。
// 最常用的参数应当命名value。

@Inherited
@Repeatable(Reports.class)
@Target(ElementType.TYPE) // 定义注解@Report可用在方法上
//@Target({ElementType.FIELD, ElementType.METHOD}) // 定义注解@Report可用在方法或字段上
@Retention(RetentionPolicy.RUNTIME) // 运行期
@interface Report {
    int type() default 0;
    String level() default "info";
    String value() default "";
}

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Reports {
    Report[] value();
}

// 经过@Repeatable修饰后，在某个类型声明处，就可以添加多个@Report注解：
@Report(type = 1, level = "debug")
@Report(type = 2, level = "warning")
class Test {
}

// 在使用的时候，如果一个类用到了@Report：
@Report(type=1)
class Person {
}

// 则它的子类默认也定义了该注解：
class Student extends Person {
}

// 如何定义Annotation
// 第一步，用@interface定义注解：
@interface TestAnnotation {
}

// 第二步，添加参数、默认值：
@interface TestAnnotation2 {
    int type() default 0;
    String level() default "info";
    String value() default "";
}
// 把最常用的参数定义为value()，推荐所有参数都尽量设置默认值。

// 第三步，用元注解配置注解：
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation3 {
    int type() default 0;
    String level() default "info";
    String value() default "";
}

// 其中，必须设置@Target和@Retention，@Retention一般设置为RUNTIME，因为我们自定义的注解通常要求在运行期读取。
// 一般情况下，不必写@Inherited和@Repeatable。