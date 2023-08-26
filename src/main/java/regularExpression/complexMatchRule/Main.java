package regularExpression.complexMatchRule;

// 复杂匹配规则
// 小结
// 复杂匹配规则主要有：
//
// 正则表达式	        规则	                可以匹配
// ^	            开头	                字符串开头
// $	            结尾	                字符串结束
// [ABC]	        […]内任意字符	        A，B，C
// [A-F0-9xy]	    指定范围的字符	        A，……，F，0，……，9，x，y
// [^A-F]	        指定范围外的任意字符	非A~F
// AB|CD|EF	        AB或CD或EF	        AB，CD，EF
public class Main {
    public static void main(String[] args) {
        // 匹配开头和结尾
        // 用正则表达式进行多行匹配时，我们用^表示开头，$表示结尾。例如，^A\d{3}$，可以匹配"A001"、"A380"。

        // 匹配指定范围
        // 如果我们规定一个7~8位数字的电话号码不能以0开头，应该怎么写匹配规则呢？
        // \d{7,8}是不行的，因为第一个\d可以匹配到0。
        //
        // 使用[...]可以匹配范围内的字符，
        // 例如，[123456789]可以匹配1~9，这样就可以写出上述电话号码的规则：[123456789]\d{6,7}。
        String regex = "[123456789]\\d{6,7}";
        System.out.println("01234567".matches(regex)); // false
        System.out.println("12345678".matches(regex)); // true
        //
        // 把所有字符全列出来太麻烦，[...]还有一种写法，直接写[1-9]就可以。
        String regex1 = "[1-9]\\d{6,7}";
        System.out.println("01234567".matches(regex1)); // false
        System.out.println("12345678".matches(regex1)); // true
        //
        // 要匹配大小写不限的十六进制数，比如1A2b3c，我们可以这样写：[0-9a-fA-F]，它表示一共可以匹配以下任意范围的字符：
        String regex2 = "[0-9a-fA-F]";
        //
        // 0-9：字符0~9；
        // a-f：字符a~f；
        // A-F：字符A~F。
        // 如果要匹配6位十六进制数，前面讲过的{n}仍然可以继续配合使用：[0-9a-fA-F]{6}。
        String regex3 = "[0-9a-fA-F]{6}";
        //
        // [...]还有一种排除法，即不包含指定范围的字符。假设我们要匹配任意字符，但不包括数字，可以写[^1-9]{3}：
        String regex4 = "[^1-9]{3}";
        //
        // 可以匹配"ABC"，因为不包含字符1~9；
        // 可以匹配"A00"，因为不包含字符1~9；
        // 不能匹配"A01"，因为包含字符1；
        // 不能匹配"A05"，因为包含字符5。
        System.out.println("ABC".matches(regex4)); // true
        System.out.println("A00".matches(regex4)); // true
        System.out.println("A01".matches(regex4)); // false
        System.out.println("A05".matches(regex4)); // false

        // 或规则匹配
        // 用|连接的两个正则规则是或规则，例如，AB|CD表示可以匹配AB或CD。
        //
        // 我们来看这个正则表达式java|php：
        String regex5 = "java|php";
        System.out.println("java".matches(regex5)); // true
        System.out.println("php".matches(regex5)); // true
        System.out.println("go".matches(regex5)); // false
        // 它可以匹配"java"或"php"，但无法匹配"go"。
        // 要把go也加进来匹配，可以改写为java|php|go。

        // 使用括号
        // 现在我们想要匹配字符串learn java、learn php和learn go怎么办？
        // 一个最简单的规则是learn\sjava|learn\sphp|learn\sgo，
        // 但是这个规则太复杂了，可以把公共部分提出来，然后用(...)把子规则括起来表示成learn\\s(java|php|go)。
        String regex6 = "learn\\s(java|php|go)";
        System.out.println("learn java".matches(regex6)); // true
        System.out.println("learn Java".matches(regex6)); // false
        System.out.println("learn php".matches(regex6)); // true
        System.out.println("learn Go".matches(regex6)); // false
        //
        // 上面的规则仍然不能匹配learn Java、learn Go这样的字符串。
        // 试修改正则，使之能匹配大写字母开头的learn Java、learn Php、learn Go。
        String regex7 = "learn\\s(java|php|go|Java|Php|Go)";
        System.out.println("learn java".matches(regex7)); // true
        System.out.println("learn Java".matches(regex7)); // true
        System.out.println("learn php".matches(regex7)); // true
        System.out.println("learn Go".matches(regex7)); // true
    }
}
