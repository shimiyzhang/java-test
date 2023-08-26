package regularExpression.matchRule;

import java.util.List;

// 练习
// 请编写一个正则表达式匹配国内的电话号码规则：3~4位区号加7~8位电话，中间用-连接，例如：010-12345678。
public class Test {
    public static void main(String[] args) {
        String regex = "\\d{3,4}-\\d{7,8}";

        for (String s : List.of("010-12345678", "020-9999999", "0755-7654321")) {
            if (!s.matches(regex)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        for (String s : List.of("010 12345678", "A20-9999999", "0755-7654.321")) {
            if (s.matches(regex)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        System.out.println("测试成功!");
    }
}
