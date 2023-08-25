package unitTests.useFixture;

// 使用Fixture
// 小结
// 编写Fixture是指针对每个@Test方法，编写@BeforeEach方法用于初始化测试资源，编写@AfterEach用于清理测试资源；
//
// 必要时，可以编写@BeforeAll和@AfterAll，使用静态变量来初始化耗时的资源，并且在所有@Test方法的运行前后仅执行一次。
public class Main {
    public static void main(String[] args) {
        // 在一个单元测试中，我们经常编写多个@Test方法，来分组、分类对目标代码进行测试。
        //
        // 在测试的时候，我们经常遇到一个对象需要初始化，测试完可能还需要清理的情况。
        // 如果每个@Test方法都写一遍这样的重复代码，显然比较麻烦。
        //
        // JUnit提供了编写测试前准备、测试后清理的固定代码，我们称之为Fixture。

        // 因此，我们总结出编写Fixture的套路如下：
        //
        // 对于实例变量，在@BeforeEach中初始化，在@AfterEach中清理，它们在各个@Test方法中互不影响，因为是不同的实例；
        //
        // 对于静态变量，在@BeforeAll中初始化，在@AfterAll中清理，它们在各个@Test方法中均是唯一实例，会影响各个@Test方法。
        //
        // 大多数情况下，使用@BeforeEach和@AfterEach就足够了。
        // 只有某些测试资源初始化耗费时间太长，以至于我们不得不尽量“复用”时才会用到@BeforeAll和@AfterAll。
        //
        // 最后，注意到每次运行一个@Test方法前，JUnit首先创建一个XxxTest实例，
        // 因此，每个@Test方法内部的成员变量都是独立的，不能也无法把成员变量的状态从一个@Test方法带到另一个@Test方法。
    }
}
