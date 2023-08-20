package objectOrientedFoundation.innerClass;

public class Outer {
    private static String NAME = "OUTER";
    private String name;

    Outer(String name) {
        this.name = name;
    }

    class Inner {
        // 定义了一个Inner Class
        void hello() {
            System.out.println("Hello, " + Outer.this.name);
        }
    }

    // 匿名类（Anonymous Class）
    void asyncHello() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, " + Outer.this.name);
            }
        };
        new Thread(r).start();
    }

    // 静态内部类（Static Nested Class）
    static class StaticNested {
        void hello() {
            System.out.println("Hello, " + Outer.NAME);
        }
    }
}
