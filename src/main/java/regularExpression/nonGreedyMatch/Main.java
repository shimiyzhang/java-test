package regularExpression.nonGreedyMatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 非贪婪匹配
// 小结
// 正则表达式匹配默认使用贪婪匹配，可以使用?表示对某一规则进行非贪婪匹配。
//
// 注意区分?的含义：\d??。
public class Main {
    public static void main(String[] args) {
        // 在介绍非贪婪匹配前，我们先看一个简单的问题：
        //
        // 给定一个字符串表示的数字，判断该数字末尾0的个数。例如：
        //
        // "123000"：3个0
        // "10100"：2个0
        // "1001"：0个0
        // 可以很容易地写出该正则表达式：(\d+)(0*)，Java代码如下：
        Pattern pattern = Pattern.compile("(\\d+)(0*)");
        Matcher matcher = pattern.matcher("1230000");
        if (matcher.matches()) {
            System.out.println("group1=" + matcher.group(1)); // "1230000"
            System.out.println("group2=" + matcher.group(2)); // ""
        }
        // 然而打印的第二个子串是空字符串""。
        //
        // 实际上，我们期望分组匹配结果是：
        //
        // input	    \d+	        0*
        // 123000	    "123"	    "000"
        // 10100	    "101"	    "00"
        // 1001	        "1001"	    ""
        // 但实际的分组匹配结果是这样的：
        //
        // input	    \d+	        0*
        // 123000	    "123000"	""
        // 10100	    "10100"	    ""
        // 1001	        "1001"	    ""
        //
        // 仔细观察上述实际匹配结果，实际上它是完全合理的，因为\d+确实可以匹配后面任意个0。
        // 这是因为正则表达式默认使用贪婪匹配：任何一个规则，它总是尽可能多地向后匹配，因此，\d+总是会把后面的0包含进来。

        // 要让\d+尽量少匹配，让0*尽量多匹配，我们就必须让\d+使用非贪婪匹配。
        // 在规则\d+后面加个?即可表示非贪婪匹配。我们改写正则表达式如下：
        Pattern pattern1 = Pattern.compile("(\\d+?)(0*)");
        Matcher matcher1 = pattern1.matcher("1230000");
        if (matcher1.matches()) {
            System.out.println("group1=" + matcher1.group(1)); // "123"
            System.out.println("group2=" + matcher1.group(2)); // "0000"
        }
        // 因此，给定一个匹配规则，加上?后就变成了非贪婪匹配。

        // 我们再来看这个正则表达式(\d??)(9*)，注意\d?表示匹配0个或1个数字，后面第二个?表示非贪婪匹配，
        // 因此，给定字符串"9999"，匹配到的两个子串分别是""和"9999"，
        // 因为对于\d?来说，可以匹配1个9，也可以匹配0个9，
        // 但是因为后面的?表示非贪婪匹配，它就会尽可能少的匹配，结果是匹配了0个9。
    }
}
