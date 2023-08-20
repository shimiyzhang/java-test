package javaCoreClass.stringBuilder;

/**
 * StringBuilder
 * 为了能高效拼接字符串，Java标准库提供了StringBuilder，它是一个可变对象
 * StringBuffer，是Java早期的一个StringBuilder的线程安全版本.
 * 它通过同步来保证多个线程操作StringBuffer也是安全的，但是同步会带来执行速度的下降。
 */
public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder(1024);
        for (int i = 0; i < 1000; i++) {
            sb.append(',');
            sb.append(i);
        }
        String s = sb.toString();

        // 链式操作
        StringBuilder sb1 = new StringBuilder(1024);
        sb1.append("Mr ")
                .append("Bob")
                .append("!")
                .insert(0, "Hello, ");
        System.out.println(sb1);

        // 设计支持链式操作的类
        Adder adder = new Adder();
        adder.add(1)
                .add(2)
                .add(3)
                .inc()
                .add(5);
        System.out.println(adder.value());

        // 练习
        // 请使用StringBuilder构造一个INSERT语句：
        String[] fields = {"name", "position", "salary"};
        String table = "employee";
        String insert = buildInsertSql(table, fields);
        System.out.println(insert);
        String sss = "INSERT INTO employee (name, position, salary) VALUES (?, ?, ?)";
        System.out.println(sss.equals(insert) ? "测试成功" : "测试失败");
    }

    static String buildInsertSql(String table, String[] fields) {
        StringBuilder sb = new StringBuilder("INSERT INTO ").append(table).append(" (");
        for (int i = 0; i < fields.length; i++) {
            sb.append(fields[i]);
            if (i < fields.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(") VALUES (?, ?, ?)");
        return sb.toString();
    }
}

class Adder {
    private int sum = 0;

    public Adder add(int n) {
        sum += n;
        return this;
    }

    public Adder inc() {
        sum++;
        return this;
    }

    public int value() {
        return sum;
    }
}
