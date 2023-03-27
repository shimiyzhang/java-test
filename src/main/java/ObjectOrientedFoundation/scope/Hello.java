package ObjectOrientedFoundation.scope;

// public
// 定义为public的class、interface可以被其他任何类访问：
public class Hello {
    public static void main(String[] args) {
        Inner i = new Inner();
        i.hi();
    }

    // 定义为public的field、method可以被其他类访问，前提是首先有访问class的权限
    public void hi() {
        hello();
    }

    // 定义为private的field、method无法被其他类访问：
    // 不能被其他类调用:
    private static void hello() {
        System.out.println("private hello!");
    }

    // 静态内部类:
    static class Inner {
        public void hi() {
            // 嵌套类拥有访问private的权限
            Hello.hello();
        }
    }

    // protected作用于继承关系。定义为protected的字段和方法可以被子类访问，以及子类的子类
    // protected方法:
    protected void eat() {}
}
