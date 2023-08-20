package reflection.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 动态代理
// 小结
//        Java标准库提供了动态代理功能，允许在运行期动态创建一个接口的实例；
//
//        动态代理是通过Proxy创建代理对象，然后将接口方法“代理”给InvocationHandler完成的。
public class Main {
    public static void main(String[] args) {
        // Java的class和interface的区别：
        // 可以实例化class（非abstract）
        // 不能实例化interface
        CharSequence cs = new StringBuilder();

        // Java标准库提供了一种动态代理（Dynamic Proxy）的机制：
        // 可以在运行期动态创建某个interface的实例。

        // 所谓动态代理，是和静态相对应的。我们来看静态代码怎么写：
        // 创建实例，转型为接口并调用：
        Hello hello = new HelloWorld();
        hello.morning("Tom");

        // 还有一种方式是动态代码，定义了接口Hello，但是不去编写实现类，而是直接通过JDK提供的一个Proxy.newProxyInstance()创建了一个Hello接口对象。
        // 这种没有实现类但是在运行期动态创建了一个接口对象的方式，我们称为动态代码。
        // JDK提供的动态创建接口对象的方式，就叫动态代理。
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning, " + args[0]);
                }
                return null;
            }
        };

        Hello helloProxy = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(),
                new Class[]{Hello.class},
                handler);
        helloProxy.morning("Bob");

        // 在运行期动态创建一个interface实例的方法如下：

        // 1、定义一个InvocationHandler实例，它负责实现接口的方法调用；
        // 2、通过Proxy.newProxyInstance()创建interface实例，它需要3个参数：
        //    1、使用的ClassLoader，通常就是接口类的ClassLoader；
        //    2、需要实现的接口数组，至少需要传入一个接口进去；
        //    3、用来处理接口方法调用的InvocationHandler实例。
        // 3、将返回的Object强制转型为接口。

        // 动态代理实际上是JVM在运行期动态创建class字节码并加载的过程

    }
}

// 定义接口：
interface Hello {
    void morning(String name);
}

// 编写实现类：
class HelloWorld implements Hello {
    @Override
    public void morning(String name) {
        System.out.println("Good morning, " + name);
    }
}

// 把上面的动态代理改写为静态实现类大概长这样：
class HelloDynamicProxy implements Hello {
    InvocationHandler handler;

    public HelloDynamicProxy(InvocationHandler handler) {
        this.handler = handler;
    }

    public void morning(String name) {
        try {
            handler.invoke(
                    this,
                    Hello.class.getMethod("morning", String.class),
                    new Object[]{name});
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
// 其实就是JVM帮我们自动编写了一个上述类（不需要源码，可以直接生成字节码），并不存在可以直接实例化接口的黑魔法。
