package regularExpression.groupMatch;

import java.util.regex.*;

// 分组匹配
// 小结
// 正则表达式用(...)分组可以通过Matcher对象快速提取子串：
//
// group(0)表示匹配的整个字符串；
// group(1)表示第1个子串，group(2)表示第2个子串，以此类推。
public class Main {
    public static void main(String[] args) {
        // 我们前面讲到的(...)可以用来把一个子规则括起来，这样写learn\s(java|php|go)就可以更方便地匹配长字符串了。
        // 实际上(...)还有一个重要作用，就是分组匹配。
        //
        // 我们来看一下如何用正则匹配区号-电话号码这个规则。利用前面讲到的匹配规则，写出来很容易：
        // \\d{3,4}-\\d{6,8}

        // 虽然这个正则匹配规则很简单，但是往往匹配成功后，下一步是提取区号和电话号码，分别存入数据库。
        // 于是问题来了：如何提取匹配的子串？
        //
        // 当然可以用String提供的indexOf()和substring()这些方法，
        // 但它们从正则匹配的字符串中提取子串没有通用性，下一次要提取learn\s(java|php)还得改代码。
        //
        // 正确的方法是用(...)先把要提取的规则分组，把上述正则表达式变为(\d{3,4})\-(\d{6,8})。

        // 现在问题又来了：匹配后，如何按括号提取子串？
        // 现在我们没办法用String.matches()这样简单的判断方法了，
        // 必须引入java.util.regex包，用Pattern对象匹配，匹配后获得一个Matcher对象，
        // 如果匹配成功，就可以直接从Matcher.group(index)返回子串：
        Pattern p = Pattern.compile("(\\d{3,4})-(\\d{6,8})");
        Matcher m = p.matcher("010-12345678");
        if (m.matches()) {
            String g1 = m.group(1);
            String g2 = m.group(2);
            System.out.println(g1); // 010
            System.out.println(g2); // 12345678
        } else {
            System.out.println("匹配失败!");
        }
        // 运行上述代码，会得到两个匹配上的子串010和12345678。
        // 要特别注意，Matcher.group(index)方法的参数用1表示第一个子串，2表示第二个子串。
        // 如果我们传入0会得到什么呢？答案是010-12345678，即整个正则匹配到的字符串。

        // Pattern
        // 我们在前面的代码中用到的正则表达式代码是String.matches()方法，
        // 而我们在分组提取的代码中用的是java.util.regex包里面的Pattern类和Matcher类。
        // 实际上这两种代码本质上是一样的，因为String.matches()方法内部调用的就是Pattern和Matcher类的方法。
        //
        // 但是反复使用String.matches()对同一个正则表达式进行多次匹配效率较低，因为每次都会创建出一样的Pattern对象。
        // 完全可以先创建出一个Pattern对象，然后反复使用，就可以实现编译一次，多次匹配：
        Pattern pattern = Pattern.compile("(\\d{3,4})-(\\d{7,8})");
        pattern.matcher("010-12345678").matches(); // true
        pattern.matcher("021-123456").matches(); // false
        pattern.matcher("022#1234567").matches(); // false
        // 获得Matcher对象:
        Matcher matcher = pattern.matcher("010-12345678");
        if (matcher.matches()) {
            String whole = matcher.group(0); // "010-12345678", 0表示匹配的整个字符串
            String area = matcher.group(1); // "010", 1表示匹配的第1个子串
            String tel = matcher.group(2); // "12345678", 2表示匹配的第2个子串
            System.out.println(whole);
            System.out.println(area);
            System.out.println(tel);
        }
        // 使用Matcher时，必须首先调用matches()判断是否匹配成功，匹配成功后，才能调用group()提取子串。
        //
        // 利用提取子串的功能，我们轻松获得了区号和号码两部分。
    }
}
