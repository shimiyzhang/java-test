package regularExpression.searchAndReplace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 搜索和替换
// 小结
// 使用正则表达式可以：
//
// 分割字符串：String.split()
// 搜索子串：Matcher.find()
// 替换字符串：String.replaceAll()
public class Main {
    public static void main(String[] args) {
        // 分割字符串
        // 使用正则表达式分割字符串可以实现更加灵活的功能。
        // String.split()方法传入的正是正则表达式。
        // 我们来看下面的代码：
        "a b c".split("\\s"); // { "a", "b", "c" }
        "a b  c".split("\\s"); // { "a", "b", "", "c" }
        "a, b ;; c".split("[,;\\s]+"); // { "a", "b", "c" }
        // 如果我们想让用户输入一组标签，然后把标签提取出来，因为用户的输入往往是不规范的，
        // 这时，使用合适的正则表达式，就可以消除多个空格、混合,和;这些不规范的输入，直接提取出规范的字符串。

        // 搜索字符串
        // 使用正则表达式还可以搜索字符串，我们来看例子：
        String s = "the quick brown fox jumps over the lazy dog.";
        Pattern p = Pattern.compile("\\wo\\w");
        Matcher m = p.matcher(s);
        while (m.find()) {
            String sub = s.substring(m.start(), m.end());
            System.out.println(sub);
        }
        // 我们获取到Matcher对象后，不需要调用matches()方法（因为匹配整个串肯定返回false），
        // 而是反复调用find()方法，在整个串中搜索能匹配上\\wo\\w规则的子串，并打印出来。
        // 这种方式比String.indexOf()要灵活得多，因为我们搜索的规则是3个字符：中间必须是o，前后两个必须是字符[A-Za-z0-9_]。

        // 替换字符串
        // 使用正则表达式替换字符串可以直接调用String.replaceAll()，它的第一个参数是正则表达式，第二个参数是待替换的字符串。
        // 我们还是来看例子：
        String s1 = "The     quick\t\t brown   fox  jumps   over the  lazy dog.";
        String r1 = s1.replaceAll("\\s+", " ");
        System.out.println(r1); // "The quick brown fox jumps over the lazy dog."
        // 上面的代码把不规范的连续空格分隔的句子变成了规范的句子。可见，灵活使用正则表达式可以大大降低代码量。

        // 反向引用
        // 如果我们要把搜索到的指定字符串按规则替换，比如前后各加一个<b>xxxx</b>，
        // 这个时候，使用replaceAll()的时候，我们传入的第二个参数可以使用$1、$2来反向引用匹配到的子串。例如：
        String s2 = "the quick brown fox jumps over the lazy dog.";
        String r2 = s2.replaceAll("\\s([a-z]{4})\\s", " <b>$1</b> ");
        System.out.println(r2);
        // 上述代码的运行结果是：
        //
        // the quick brown fox jumps <b>over</b> the <b>lazy</b> dog.
        // 它实际上把任何4字符单词的前后用<b>xxxx</b>括起来。
        // 实现替换的关键就在于" <b>$1</b> "，它用匹配的分组子串([a-z]{4})替换了$1。
    }
}
