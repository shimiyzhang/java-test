package regularExpression.matchRule;

// 匹配规则
// 小结
// 单个字符的匹配规则如下：
//
// 正则表达式	    规则	                    可以匹配
// A	        指定字符	                A
// \u548c	    指定Unicode字符	        和
// .	        任意字符	                a，b，&，0
// \d	        数字0~9	                0~9
// \w	        大小写字母，数字和下划线	    a~z，A~Z，0~9，_
// \s	        空格、Tab键	            空格，Tab
// \D	        非数字	                a，A，&，_，……
// \W	        非\w	                &，@，中，……
// \S	        非\s	                a，A，&，_，……
// 多个字符的匹配规则如下：
//
// 正则表达式	    规则	                    可以匹配
// A*	        任意个数字符	            空，A，AA，AAA，……
// A+	        至少1个字符	            A，AA，AAA，……
// A?	        0个或1个字符	            空，A
// A{3}	        指定个数字符	            AAA
// A{2,3}	    指定范围个数字符	        AA，AAA
// A{2,}	    至少n个字符	            AA，AAA，AAAA，……
// A{0,3}	    最多n个字符	            空，A，AA，AAA
public class Main {
    public static void main(String[] args) {
        // 正则表达式的匹配规则是从左到右按规则匹配。我们首先来看如何使用正则表达式来做精确匹配。

        // 对于正则表达式abc来说，它只能精确地匹配字符串"abc"，不能匹配"ab"，"Abc"，"abcd"等其他任何字符串。
        //
        // 如果正则表达式有特殊字符，那就需要用\转义。
        // 例如，正则表达式a\&c，其中\&是用来匹配特殊字符&的，它能精确匹配字符串"a&c"，但不能匹配"ac"、"a-c"、"a&&c"等。

        // 要注意正则表达式在Java代码中也是一个字符串，
        // 所以，对于正则表达式a\&c来说，对应的Java字符串是"a\\&c"，
        // 因为\也是Java字符串的转义字符，两个\\实际上表示的是一个\：
        String re1 = "abc";
        System.out.println("abc".matches(re1));
        System.out.println("Abc".matches(re1));
        System.out.println("abcd".matches(re1));

        String re2 = "a&c"; // 对应的正则是a\&c
        System.out.println("a&c".matches(re2));
        System.out.println("a-c".matches(re2));
        System.out.println("a&&c".matches(re2));
        // 如果想匹配非ASCII字符，例如中文，那就用\ u####的十六进制表示，例如：a\u548cc匹配字符串"a和c"，中文字符和的Unicode编码是548c。

        // 匹配任意字符
        // 精确匹配实际上用处不大，因为我们直接用String.equals()就可以做到。
        // 大多数情况下，我们想要的匹配规则更多的是模糊匹配。我们可以用.匹配一个任意字符。
        //
        // 例如，正则表达式a.c中间的.可以匹配一个任意字符，例如，下面的字符串都可以被匹配：
        //
        // "abc"，因为.可以匹配字符b；
        // "a&c"，因为.可以匹配字符&；
        // "acc"，因为.可以匹配字符c。
        // 但它不能匹配"ac"、"a&&c"，因为.匹配一个字符且仅限一个字符。
        System.out.println("abc".matches("a.c")); // true
        System.out.println("a&c".matches("a.c")); // true
        System.out.println("ac".matches("a.c")); // false


        // 匹配数字
        // 用.可以匹配任意字符，这个口子开得有点大。如果我们只想匹配0~9这样的数字，可以用\d匹配。例如，正则表达式00\d可以匹配：
        //
        // "007"，因为\d可以匹配字符7；
        // "008"，因为\d可以匹配字符8。
        // 它不能匹配"00A"，"0077"，因为\d仅限单个数字字符。
        System.out.println("007".matches("00\\d")); // true
        System.out.println("008".matches("00\\d")); // true
        System.out.println("00A".matches("00\\d")); // false

        // 匹配常用字符
        // 用\w可以匹配一个字母、数字或下划线，w的意思是word。例如，java\w可以匹配：
        //
        // "javac"，因为\w可以匹配英文字符c；
        // "java9"，因为\w可以匹配数字字符9；。
        // "java_"，因为\w可以匹配下划线_。
        // 它不能匹配"java#"，"java "，因为\w不能匹配#、空格等字符。
        System.out.println("javac".matches("java\\w")); // true
        System.out.println("java9".matches("java\\w")); // true
        System.out.println("java_".matches("java\\w")); // true
        System.out.println("java#".matches("java\\w")); // false

        // 匹配空格字符
        // 用\s可以匹配一个空格字符，注意空格字符不但包括空格，还包括tab字符（在Java中用\t表示）。例如，a\sc可以匹配：
        //
        // "a c"，因为\s可以匹配空格字符；
        // "a c"，因为\s可以匹配tab字符\t。
        // 它不能匹配"ac"，"abc"等。
        System.out.println("a c".matches("a\\sc")); // true
        System.out.println("a\tc".matches("a\\sc")); // true

        // 匹配非数字
        // 用\d可以匹配一个数字，而\D则匹配一个非数字。例如，00\D可以匹配：
        //
        // "00A"，因为\D可以匹配非数字字符A；
        // "00#"，因为\D可以匹配非数字字符#。
        // 00\d可以匹配的字符串"007"，"008"等，00\D是不能匹配的。
        //
        // 类似的，\W可以匹配\w不能匹配的字符，\S可以匹配\s不能匹配的字符，这几个正好是反着来的。
        System.out.println("java9".matches("java\\d")); // true
        System.out.println("java10".matches("java\\d")); // false
        System.out.println("javac".matches("java\\d")); // false

        System.out.println("javax".matches("java\\D")); // true
        System.out.println("java#".matches("java\\D")); // true
        System.out.println("java5".matches("java\\D")); // false

        // 重复匹配
        // 我们用\d可以匹配一个数字，例如，A\d可以匹配"A0"，"A1"，如果要匹配多个数字，比如"A380"，怎么办？
        //
        // 修饰符*可以匹配任意个字符，包括0个字符。我们用A\d*可以匹配：
        //
        // A：因为\d*可以匹配0个数字；
        // A0：因为\d*可以匹配1个数字0；
        // A380：因为\d*可以匹配多个数字380。
        System.out.println("A".matches("A\\d*")); // true
        System.out.println("A0".matches("A\\d*")); // true
        System.out.println("A380".matches("A\\d*")); // true

        // 修饰符+可以匹配至少一个字符。我们用A\d+可以匹配：
        //
        // A0：因为\d+可以匹配1个数字0；
        // A380：因为\d+可以匹配多个数字380。
        // 但它无法匹配"A"，因为修饰符+要求至少一个字符。
        System.out.println("A0".matches("A\\d+")); // true
        System.out.println("A380".matches("A\\d+")); // true
        System.out.println("A".matches("A\\d+")); // false

        // 修饰符?可以匹配0个或一个字符。我们用A\d?可以匹配：
        //
        // A：因为\d?可以匹配0个数字；
        // A0：因为\d?可以匹配1个数字0。
        // 但它无法匹配"A33"，因为修饰符?超过1个字符就不能匹配了。
        System.out.println("A".matches("A\\d?")); // true
        System.out.println("A0".matches("A\\d?")); // true
        System.out.println("A33".matches("A\\d?")); // false

        // 如果我们想精确指定n个字符怎么办？用修饰符{n}就可以。A\d{3}可以精确匹配：
        //
        // A380：因为\d{3}可以匹配3个数字380。
        System.out.println("A380".matches("A\\d{3}")); // true
        System.out.println("A3800".matches("A\\d{3}")); // false
        // 如果我们想指定匹配n~m个字符怎么办？用修饰符{n,m}就可以。A\d{3,5}可以精确匹配：
        //
        // A380：因为\d{3,5}可以匹配3个数字380；
        // A3800：因为\d{3,5}可以匹配4个数字3800；
        // A38000：因为\d{3,5}可以匹配5个数字38000。
        System.out.println("A380".matches("A\\d{3,5}")); // true
        System.out.println("A3800".matches("A\\d{3,5}")); // true
        System.out.println("A38000".matches("A\\d{3,5}")); // true
        // 如果没有上限，那么修饰符{n,}就可以匹配至少n个字符。
        System.out.println("A380000".matches("A\\d{3,}")); // true
    }
}
