package ObjectOrientedFoundation;

/**
 * 抽象类
 */
public class AbstractClass {
    public static void main(String[] args) {
        PersonWithAbstract p = new StudentWithAbstract();
        p.run();

        // 通过抽象类PersonWithAbstract类型去引用具体的子类的实例
        PersonWithAbstract s = new StudentWithAbstract();
        PersonWithAbstract t = new TeacherWithAbstract();
        // 不关心Person变量的具体子类型:
        s.run();
        t.run();

        // 用抽象类给一个有工资收入和稿费收入的小伙伴算税
        IncomeWithAbstract[] incomes = new IncomeWithAbstract[]{
                new SalaryIncomeWithAbstract(8000),
                new royaltiesIncomeWithAbstract(15000)
        };
        System.out.println(totalTax(incomes));
    }

    public static double totalTax(IncomeWithAbstract... incomes) {
        double total = 0;
        for (IncomeWithAbstract income : incomes) {
            total += income.getTax();
        }
        return total;
    }
}

// 由于多态的存在，每个子类都可以覆写父类的方法
abstract class PersonWithAbstract {
    // public void run() {
    // }

    // 如果父类Person的run()方法没有实际意义，能否去掉方法的执行语句？
    // public void run(); // Compile Error!
    // 不行，会导致编译错误，因为定义方法的时候，必须实现方法的语句。

    // 能不能去掉父类的run()方法？
    // 还是不行，因为去掉父类的run()方法，就失去了多态的特性。例如，runTwice()就无法编译
    // public void runTwice(Person p) {
    //     p.run(); // Person没有run()方法，会导致编译错误
    //     p.run();
    // }

    // 把父类的方法声明为抽象方法(必须把类本身也声明为abstract)
    public abstract void run();

    public void hello() {
        System.out.println("person.hello");
    }
}

class StudentWithAbstract extends PersonWithAbstract {
    // Person类定义了抽象方法run()，那么，在实现子类Student的时候，就必须覆写run()方法
    @Override
    public void run() {
        super.hello();
        System.out.println("Student.run");
    }
}

class TeacherWithAbstract extends PersonWithAbstract {
    @Override
    public void run() {
        System.out.println("Teacher.run");
    }
}

// 用抽象类给一个有工资收入和稿费收入的小伙伴算税
abstract class IncomeWithAbstract {
    protected double income;

    public IncomeWithAbstract(double income) {
        this.income = income;
    }

    abstract double getTax();
}

// 对于工资收入，可以减去一个基数，那么我们可以从Income派生出SalaryIncome，并覆写getTax()：
class SalaryIncomeWithAbstract extends IncomeWithAbstract {
    public SalaryIncomeWithAbstract(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        if (income <= 5000) {
            return 0;
        }
        return (income - 5000) * 0.2;
    }
}

// 稿费收入，在基本税额上免除100,税额至少为0
class royaltiesIncomeWithAbstract extends IncomeWithAbstract {

    public royaltiesIncomeWithAbstract(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        // 基本税额
        double tax = income * 0.1;
        if (tax >= 100) {
            return tax - 100;
        }
        return tax;
    }
}