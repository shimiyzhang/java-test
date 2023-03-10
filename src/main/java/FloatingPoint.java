// 浮点运算
public class FloatingPoint {
    public static void main(String[] args) {
        // 浮点数运算会产生误差
        double x = 1.0 / 10;
        double y = 1 - 9.0 / 10;
        // 观察x和y是否相等:
        System.out.println(x);
        System.out.println(y);

        // 比较x和y是否相等，先计算其差的绝对值:
        double r = Math.abs(x - y);
        // 再判断绝对值是否足够小:
        if (r < 0.00001) {
            // 可以认为相等
            System.out.println("equal");
        } else {
            // 不相等
            System.out.println("no equal");
        }

        // 类型提升
        int n = 5;
        double d = 1.2 + 24.0 / n; // 6.0
        System.out.println(d);
        // 两个整数的运算不会出现自动提升的情况
        double e = 1.2 + 24 / n; // 5.2
        System.out.println(e);

        // 溢出
        double d1 = 0.0 / 0; // NaN
        double d2 = 1.0 / 0; // Infinity
        double d3 = -1.0 / 0; // -Infinity
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);

        // 强制转型
        int n1 = (int) 12.3; // 12
        int n2 = (int) 12.7; // 12
        int n3 = (int) -12.7; // -12
        int n4 = (int) (12.7 + 0.5); // 13
        int n5 = (int) 1.2e20; // 2147483647

        // 四舍五入(加上0.5再强制转型)
        double d4 = 2.6;
        int n6 = (int) (d + 0.5);
        System.out.println(n6);
    }
}
