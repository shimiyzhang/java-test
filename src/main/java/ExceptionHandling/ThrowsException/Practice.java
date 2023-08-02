package ExceptionHandling.ThrowsException;

// 如果传入的参数为负，则抛出IllegalArgumentException。
public class Practice {
    public static void main(String[] args) {
        System.out.println(tax(2000, 0.1));
        System.out.println(tax(-200, 0.1));
        System.out.println(tax(2000, -0.1));
    }

    static double tax(int salary, double rate) {
        if (salary < 0 || rate < 0) {
            throw new IllegalArgumentException("传入的参数不能为负");
        }
        return salary * rate;
    }
}
