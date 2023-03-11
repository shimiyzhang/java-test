public class CharAndString {
    public static void main(String[] args) {
        char c1 = 'A';
        char c2 = '中';
        int n1 = 'A'; // 字母“A”的Unicode编码是65
        int n2 = '中'; // 汉字“中”的Unicode编码是20013
        // 注意是十六进制:
        char c3 = '\u0041'; // 'A'，因为十六进制0041 = 十进制65
        char c4 = '\u4e2d'; // '中'，因为十六进制4e2d = 十进制20013

        String s = ""; // 空字符串，包含0个字符
        String s1 = "A"; // 包含一个字符
        String s2 = "ABC"; // 包含3个字符
        String s3 = "中文 ABC"; // 包含6个字符，其中有一个空格
        String s4 = "abc\"xyz"; // 包含7个字符: a, b, c, ", x, y, z
        String s5 = "abc\\xyz"; // 包含7个字符: a, b, c, \, x, y, z
        String s6 = "ABC\n\u4e2d\u6587"; // 包含6个字符: A, B, C, 换行符, 中, 文

        // 字符串连接
        String s7 = "Hello";
        String s8 = "world";
        String s9 = s7 + " " + s8 + "!";
        System.out.println(s9);
        int age = 25;
        String s10 = "age is " + age;
        System.out.println(s10);

        // 多行字符串（Java 13开始支持）
        // String s = """
        //         SELECT * FROM
        //           users
        //         WHERE id > 100
        //         ORDER BY name DESC
        //         """;
        // System.out.println(s);

        // 不可变性
        String ss = "hello";
        System.out.println(ss); // 显示 hello
        ss = "world";
        System.out.println(ss); // 显示 world
        String sss = "hello";
        String ttt = sss;
        sss = "world";
        System.out.println(ttt); // hello

        // 空值null
        String ss1 = null; // ss1是null
        String ss2 = ss1; // ss2也是null
        String ss3 = ""; // ss3指向空字符串，不是null

        int a = 72;
        int b = 105;
        int c = 65281;
        String ssss ="" + (char)a + (char)b + (char)c;
        System.out.println(ssss);
    }
}
