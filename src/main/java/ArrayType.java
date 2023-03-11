// 数组类型
public class ArrayType {
    public static void main(String[] args) {
        int[] ns = new int[5];
        ns[0] = 68;
        ns[1] = 79;
        ns[2] = 91;
        ns[3] = 85;
        ns[4] = 62;
        System.out.println(ns.length); // 5
        // System.out.println(ns[5]); // 索引n不能超出范围

        int[] ns1 = new int[]{68, 79, 91, 85, 62};
        System.out.println(ns1.length);

        int[] ns2 = {68, 79, 91, 85, 62};

        // 数组是引用类型，并且数组大小不可变
        ns = new int[]{1, 2, 3};
        System.out.println(ns.length); // 3

        // 字符串数组
        String[] names = {"ABC", "XYZ", "zoo"};
        String s = names[1];
        names[1] = "cat";
        System.out.println(s); // s是"XYZ"还是"cat"?
    }
}
