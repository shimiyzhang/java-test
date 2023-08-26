package regularExpression.searchAndReplace;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 练习
// 模板引擎是指，定义一个字符串作为模板：
//
// Hello, ${name}! You are learning ${lang}!
// 其中，以${key}表示的是变量，也就是将要被替换的内容
//
// 当传入一个Map<String, String>给模板后，需要把对应的key替换为Map的value。
//
// 例如，传入Map为：
//
// {
//     "name": "Bob",
//     "lang": "Java"
// }
// 然后，${name}被替换为Map对应的值"Bob”，${lang}被替换为Map对应的值"Java"，最终输出的结果为：
//
// Hello, Bob! You are learning Java!
// 请编写一个简单的模板引擎，利用正则表达式实现这个功能。
// 提示：参考Matcher.appendReplacement()方法。
public class Test {
    public static void main(String[] args) {
        String template = "Hello, ${name}! You are learning ${lang}!";
        Map<String, String> data = Map.of("name", "Bob", "lang", "Java");
        String render = new Template(template).render(data);
        System.out.println(render);
    }
}

class Template {
    final String template;
    final Pattern pattern = Pattern.compile("\\$\\{(\\w+)}");

    public Template(String template) {
        this.template = template;
    }

    public String render(Map<String, String> data) {
        Matcher matcher = pattern.matcher(template);

        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            // 获取匹配到的字符串
            String key = matcher.group(1);
            // 从Map中获取对应的值
            String value = data.getOrDefault(key, "");
            // 将匹配到的字符串替换为指定的字符串，并追加到StringBuilder中
            matcher.appendReplacement(result, value);
        }
        // 将匹配后的剩余部分追加到StringBuilder中
        matcher.appendTail(result);
        return result.toString();
    }
}
