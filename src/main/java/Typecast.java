public class Typecast {
    public static void main(String[] args) {
        short s = 12345;
        int i = 123456;
        int x = s + i; // s自动转型为int
        // short y = s + i; // 编译错误！
        short z = (short) (s + i); // 强制转型

        // 要注意，超出范围的强制转型会得到错误的结果，原因是转型时，int的两个高位字节直接被扔掉，仅保留了低位的两个字节
        int i1 = 1234567;
        short s1 = (short) i1; // -10617
        System.out.println(s1);
        int i2 = 12345678;
        short s2 = (short) i2; // 24910
        System.out.println(s2);
    }
}
