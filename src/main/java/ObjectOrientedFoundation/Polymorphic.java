package ObjectOrientedFoundation;

// 多态: 针对某个类型的方法调用，其真正执行的方法取决于运行时期实际类型的方法，而非变量的声明类型。
// 多态的特性就是，运行期才能动态决定调用的子类方法。
// 对某个类型调用某个方法，执行的实际方法可能是某个子类的覆写方法。
public class Polymorphic {
    public static void main(String[] args) {
        // 实际类型为Student，引用类型为Person的变量p
        PersonWithPolymorphic p = new StudentWithPolymorphic(1999);
        p.run(); // "Student.run"
        p.name = "Xiao Ming";
        StudentWithPolymorphic s = new StudentWithPolymorphic(2000);
        s.name = "Xiao Ming";
        // s.school = "Bei Da"; // 对final字段重新赋值会报错
        System.out.println(p.equals(s));
        System.out.println(p.hashCode());

        // 给一个有普通收入、工资收入和享受国务院特殊津贴的小伙伴算税:
        Income[] incomes = new Income[]{
                new Income(3000),
                new SalaryIncome(7500),
                new StateCouncilSpecialAllowance(15000)
        };
        System.out.println(totalTax(incomes)); // 300 + 500 + 0 = 800

        // 给一个有工资收入和稿费收入的小伙伴算税:
        Income[] incomesWithRoyalties = new Income[]{
                new Income(4000),
                new royaltiesIncome(15000)
        };
        System.out.println(totalTax(incomesWithRoyalties)); // 400 + 1400 = 1800
    }

    // 对于一个人的所有收入进行报税:
    // 利用多态，totalTax()方法只需要和Income打交道，
    // 它完全不需要知道Salary和StateCouncilSpecialAllowance的存在，就可以正确计算出总的税。
    public static double totalTax(Income... incomes) {
        double total = 0;
        for (Income income : incomes) {
            total += income.getTax();
        }
        return total;
    }
}

class PersonWithPolymorphic {
    protected String name;

    public void run() {
        System.out.println("Person.run");
    }

    // 用final修饰的方法不能被Override
    public final String hello() {
        return "Hello, " + name;
    }

    // 覆写Object方法
    // 显示更有意义的字符串:
    @Override
    public String toString() {
        return "Person:name=" + name;
    }

    // 比较是否相等:
    @Override
    public boolean equals(Object o) {
        // 当且仅当o为Person类型:
        if (o instanceof PersonWithPolymorphic) {
            PersonWithPolymorphic p = (PersonWithPolymorphic) o;
            // 并且name字段相同时，返回true:
            return this.name.equals(p.name);
        }
        return false;
    }

    // 计算hash:
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}

// 用final修饰的类不能被继承
final class StudentWithPolymorphic extends PersonWithPolymorphic {
    // 用final修饰的字段在初始化后不能被修改
    public final String school = "Qing Hua";
    public final int birth;

    // 在构造方法中初始化final字段
    public StudentWithPolymorphic(int birth) {
        this.birth = birth;
    }

    // 在继承关系中，子类如果定义了一个与父类方法签名完全相同的方法，被称为覆写（Override）。
    @Override
    public void run() {
        // 调用super
        // 在子类的覆写方法中，如果要调用父类的被覆写的方法，可以通过super来调用。
        super.run();
        System.out.println("Student.run");
    }

    // 不是Override，因为参数不同:
    public void run(String s) {
        System.out.println("Student.run");
    }

    // 不是Override，因为返回值不同，编译器报错:
    // public int run() {
    //     return 0;
    // }

    // compile error: 不允许覆写
    // @Override
    // public String hello() {
    //     return "Hello, " + name;
    // }
}

// compile error: 不允许继承自Person
// class HighSchoolStudent extends StudentWithPolymorphic {
// }

// 假设我们定义一种收入，需要给它报税，那么先定义一个Income类：
class Income {
    protected double income;

    public Income(double income) {
        this.income = income;
    }

    public double getTax() {
        return income * 0.1; // 税率10%
    }
}

// 对于工资收入，可以减去一个基数，那么我们可以从Income派生出SalaryIncome，并覆写getTax()：
class SalaryIncome extends Income {
    public SalaryIncome(double income) {
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

// 如果你享受国务院特殊津贴，那么按照规定，可以全部免税：
class StateCouncilSpecialAllowance extends Income {
    public StateCouncilSpecialAllowance(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        return 0;
    }
}

// 稿费收入，在基本税额上免除100,税额至少为0
class royaltiesIncome extends Income {

    public royaltiesIncome(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        double tax = super.getTax();
        if (tax >= 100) {
            return tax - 100;
        }
        return tax;
    }
}