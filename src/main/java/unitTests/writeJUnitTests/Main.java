package unitTests.writeJUnitTests;

// 编写JUnit测试
// 小结
// JUnit是一个单元测试框架，专门用于运行我们编写的单元测试：
//
// 一个JUnit测试包含若干@Test方法，并使用Assertions进行断言，注意浮点数assertEquals()要指定delta。
public class Main {
    public static void main(String[] args) {
        // 什么是单元测试呢？单元测试就是针对最小的功能单元编写测试代码。
        // Java程序最小的功能单元是方法，因此，对Java程序进行单元测试就是针对单个Java方法的测试。
        //
        // 单元测试有什么好处呢？在学习单元测试前，我们可以先了解一下测试驱动开发。

        // 所谓测试驱动开发，是指先编写接口，紧接着编写测试。
        // 编写完测试后，我们才开始真正编写实现代码。
        // 在编写实现代码的过程中，一边写，一边测，什么时候测试全部通过了，那就表示编写的实现完成了：
        //     编写接口
        //      │
        //      ▼
        //     编写测试
        //      │
        //      ▼
        // ┌─> 编写实现
        // │    │
        // │ N  ▼
        // └── 运行测试
        //      │ Y
        //      ▼
        //     任务完成
        // 这就是传说中的TDD(Test-driven development)
        // 当然，这是一种理想情况。大部分情况是我们已经编写了实现代码，需要对已有的代码进行测试。

        // 我们先通过一个示例来看如何编写测试。
        // 要测试这个方法，一个很自然的想法是编写一个main()方法，然后运行一些测试代码：
        if (Factorial.fact(10) == 3628800) {
            System.out.println("pass");
        } else {
            System.out.println("fail");
        }
        // 这样我们就可以通过运行main()方法来运行测试代码。
        //
        // 不过，使用main()方法测试有很多缺点：
        //
        // 一是只能有一个main()方法，不能把测试代码分离，
        // 二是没有打印出测试结果和期望结果，例如，expected: 3628800, but actual: 123456，
        // 三是很难编写一组通用的测试代码。
        //
        // 因此，我们需要一种测试框架，帮助我们编写测试。

        // JUnit
        // JUnit是一个开源的Java语言的单元测试框架，专门针对Java设计，使用最广泛。
        // JUnit是事实上的单元测试的标准框架，任何Java开发者都应当学习并使用JUnit编写单元测试。

        // 核心测试方法testFact()加上了@Test注解，这是JUnit要求的，它会把带有@Test的方法识别为测试方法。
        // 在测试方法内部，我们用assertEquals(1, Factorial.fact(1))表示，期望Factorial.fact(1)返回1。
        // assertEquals(expected, actual)是最常用的测试方法，它在Assertion类中定义。
        // Assertion还定义了其他断言方法，例如：
        //
        // assertTrue(): 期待结果为true
        // assertFalse(): 期待结果为false
        // assertNotNull(): 期待结果为非null
        // assertArrayEquals(): 期待结果为数组并与期望数组每个元素的值均相等
        // ...

        // 如果测试结果与预期不符，assertEquals()会抛出异常，我们就会得到一个测试失败的结果：
        // org.opentest4j.AssertionFailedError:
        // Expected :362880000
        // Actual   :3628800
        // <Click to see difference>
        // 	at org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
        // 	at org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
        // 	at org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
        // 	at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:166)
        // 	at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:161)
        // 	at org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:628)
        // 	at unitTests.writeJUnitTests.FactorialTest.testFact(FactorialTest.java:13)
        // 	at java.base/java.lang.reflect.Method.invoke(Method.java:578)
        // 	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        // 	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

        // 单元测试的好处
        // 单元测试可以确保单个方法按照正确预期运行，如果修改了某个方法的代码，只需确保其对应的单元测试通过，即可认为改动正确。
        // 此外，测试代码本身就可以作为示例代码，用来演示如何调用该方法。
        //
        // 使用JUnit进行单元测试，我们可以使用断言（Assertion）来测试期望结果，可以方便地组织和运行测试，并方便地查看测试结果。
        // 此外，JUnit既可以直接在IDE中运行，也可以方便地集成到Maven这些自动化工具中运行。
        //
        // 在编写单元测试的时候，我们要遵循一定的规范：
        //
        // 一是单元测试代码本身必须非常简单，能一下看明白，决不能再为测试代码编写测试；
        // 二是每个单元测试应当互相独立，不依赖运行的顺序；
        // 三是测试时不但要覆盖常用测试用例，还要特别注意测试边界条件，例如输入为0，null，空字符串""等情况。
    }
}
