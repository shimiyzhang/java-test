// 计算一元二次方程ax^2+bx+c的两个解
public class QuadraticEquationWithOneUnknown {
    int a, b, c;

    public QuadraticEquationWithOneUnknown(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void printResult() {
        double sqrt = Math.sqrt(b * b - 4 * a * c);
        double r1 = (-b + sqrt) / 2 * a;
        double r2 = (-b - sqrt) / 2 * a;
        System.out.println(r1);
        System.out.println(r2);
    }

    public static void main(String[] args) {
        QuadraticEquationWithOneUnknown result = new QuadraticEquationWithOneUnknown(1, 2, 1);
        result.printResult();
    }
}
