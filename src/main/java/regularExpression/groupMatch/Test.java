package regularExpression.groupMatch;

import java.util.regex.*;

// 练习
// 利用分组匹配，从字符串"23:01:59"提取时、分、秒。
public class Test {
    public static void main(String[] args) {
        String input = "23:01:59";
        extractTime(input);
        extractTime("hello world");
    }

    public static void extractTime(String input) {
        String regex = "^(\\d{2}):(\\d{2}):(\\d{2})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String hour = matcher.group(1);
            String minute = matcher.group(2);
            String second = matcher.group(3);
            System.out.println(hour);
            System.out.println(minute);
            System.out.println(second);
        } else {
            System.out.println("匹配失败!");
        }
    }
}
