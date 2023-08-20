package reflection.getInheritance;

// 获取继承关系
// 小结
//        通过Class对象可以获取继承关系：
//        Class getSuperclass()：获取父类类型；
//        Class[] getInterfaces()：获取当前类实现的所有接口。
//        通过Class对象的isAssignableFrom()方法可以判断一个向上转型是否可以实现。
public class Main {
    public static void main(String[] args) throws Exception {
        // 当我们获取到某个Class对象时，实际上就获取到了一个类的类型：
        Class cls = String.class; // 获取到String的Class

        // 还可以用实例的getClass()方法获取：
        String s = "";
        Class cls1 = s.getClass(); // s是String，因此获取到String的Class

        // 最后一种获取Class的方法是通过Class.forName("")，传入Class的完整类名获取：
        Class cls2 = Class.forName("java.lang.String");

        // 这三种方式获取的Class实例都是同一个实例，因为JVM对每个加载的Class只创建一个Class实例来表示它的类型。

        // 获取父类的Class
        Class i = Integer.class;
        Class n = i.getSuperclass();
        System.out.println(n); // Number
        // 获取父类的父类
        Class n2 = n.getSuperclass();
        System.out.println(n2); // Object
        // 获取父类的父类的父类
        System.out.println(n2.getSuperclass()); // null

        // 获取interface
        Class cli = Integer.class;
        Class[] interfaces = cli.getInterfaces();
        for (Class c : interfaces) {
            System.out.println(c);
        }
        // 运行上述代码可知，Integer实现的接口有：
        // java.lang.Comparable
        // java.lang.constant.Constable
        // java.lang.constant.ConstantDesc

        // getInterfaces()只返回当前类直接实现的接口类型，并不包括其父类实现的接口类型：
        Class ss = Integer.class.getSuperclass();
        Class[] interfaces1 = ss.getInterfaces();
        for (Class c : interfaces1) {
            System.out.println(c);
        }

        // Integer的父类是Number，Number实现的接口是java.io.Serializable。
        // 此外，对所有interface的Class调用getSuperclass()返回的是null，
        System.out.println(java.io.DataInputStream.class.getSuperclass()); // java.io.FilterInputStream
        System.out.println(java.io.Closeable.class.getSuperclass()); // null

        // 获取接口的父接口要用getInterfaces()
        Class[] ints = java.io.Closeable.class.getInterfaces();
        for (Class c : ints) {
            System.out.println(c); // interface java.lang.AutoCloseable
        }
        // 如果一个类没有实现任何interface，那么getInterfaces()返回空数组。

        // 继承关系
        // 判断一个实例是否是某个类型时，正常情况下，使用instanceof操作符：
        Object nn = Integer.valueOf(123);
        boolean isDouble = nn instanceof Double; // false
        boolean isInteger = nn instanceof Integer; // true
        boolean isNumber = nn instanceof Number; // true
        boolean isSerializable = nn instanceof java.io.Serializable; // true

        // 两个Class实例，要判断一个向上转型是否成立，可以调用isAssignableFrom()：
        // Integer i = ?
        Integer.class.isAssignableFrom(Integer.class); // true，因为Integer可以赋值给Integer
        // Number n = ?
        Number.class.isAssignableFrom(Integer.class); // true，因为Integer可以赋值给Number
        // Object o = ?
        Object.class.isAssignableFrom(Integer.class); // true，因为Integer可以赋值给Object
        // Integer i = ?
        Integer.class.isAssignableFrom(Number.class); // false，因为Number不能赋值给Integer
    }
}
