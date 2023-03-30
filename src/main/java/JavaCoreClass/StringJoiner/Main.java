package JavaCoreClass.StringJoiner;

import java.util.StringJoiner;

/**
 * StringJoiner
 * 在构造字符串时，可以自动添加前缀、后缀及分隔符
 */
public class Main {
    public static void main(String[] args) {
        String[] names = {"Bob", "Alice", "Grace"};

        // StringBuilder
        var sb = new StringBuilder();
        sb.append("Hello ");
        for (String name : names) {
            sb.append(name).append(", ");
        }
        // 注意去掉最后的", ":
        sb.delete(sb.length() - 2, sb.length());
        sb.append("!");
        System.out.println(sb.toString());

        // StringJoiner
        StringJoiner sj = new StringJoiner(", ", "Hello ", "!");
        for (String name : names) {
            sj.add(name);
        }
        System.out.println(sj.toString());

        // String.join()
        System.out.println(String.join(", ", names));

        // 练习
        // 请使用StringJoiner构造一个SELECT语句：
        String[] fields = {"name", "position", "salary"};
        String table = "employee";
        String select = buildSelectSql(table, fields);
        System.out.println(select);
        System.out.println("SELECT name, position, salary FROM employee".equals(select) ? "测试成功" : "测试失败");
    }

    static String buildSelectSql(String table, String[] fields) {
        StringJoiner sj = new StringJoiner(", ", "SELECT ", " FROM " + table);
        for (String field : fields) {
            sj.add(field);
        }
        return sj.toString();
    }
}
