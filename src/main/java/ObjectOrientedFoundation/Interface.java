package ObjectOrientedFoundation;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

/**
 * 接口: interface
 * 所谓interface，就是比抽象类还要抽象的纯抽象接口
 */
public class Interface {
    public static void main(String[] args) {
        // 继承关系
        // 合理设计interface和abstract class的继承关系，可以充分复用代码。
        // 公共逻辑适合放在abstract class中，具体逻辑放到各个子类，而接口层次代表抽象程度。
        List list = new ArrayList(); // 用List接口引用具体子类的实例
        Collection collection = list; // 向上转型为Collection接口
        Iterable iterable = collection; // 向上转型为Iterable接口

        // 用接口给一个有工资收入和稿费收入的小伙伴算税。
        IncomeWithInterface[] incomes = new IncomeWithInterface[]{
                new SalaryIncomeWithInterface(8000),
                new royaltiesIncomeWithInterface(15000)
        };

        double total = 0;
        for (IncomeWithInterface income : incomes) {
            total += income.getTax();
        }
        System.out.println(total); // 300 + 1400
    }
}

// 如果一个抽象类没有字段，所有方法全部都是抽象方法：
abstract class PersonLikeInterface {
    public abstract void run();

    public abstract String getName();
}

// 就可以把该抽象类改写为接口：interface
interface PersonWithInterface {
    // 因为接口定义的所有方法默认都是public abstract的，所以这两个修饰符不需要写出来（写不写效果都一样）。
    void run();

    // 等价于public abstract void run();
    String getName();
    // 等价于public abstract String  getName();
}

// 当一个具体的class去实现一个interface时，需要使用implements关键字。
class StudentWithInterface implements PersonWithInterface {
    private String name;

    public StudentWithInterface(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(this.name + " run");
    }

    @Override
    public String getName() {
        return name;
    }
}

interface HelloWithInterface {
    void say();
}

// 接口继承(相当于扩展了接口的方法)
interface PersonWithHello extends HelloWithInterface {
    void run();

    String getName();
}

// 在Java中，一个类只能继承自另一个类，不能从多个类继承。但是，一个类可以实现多个interface
class StudentWithInterfaces implements PersonWithInterface, HelloWithInterface {
    private String name;

    public StudentWithInterfaces(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(this.name + " run");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void say() {
        System.out.println(this.name + "say");
    }
}

// 用接口给一个有工资收入和稿费收入的小伙伴算税。
interface IncomeWithInterface {
    double getTax();
}

class SalaryIncomeWithInterface implements IncomeWithInterface {
    private double income;

    public SalaryIncomeWithInterface(double income) {
        this.income = income;
    }

    @Override
    public double getTax() {
        if (income > 5000) {
            return (income - 5000) * 0.1;
        }
        return 0;
    }
}

class royaltiesIncomeWithInterface implements IncomeWithInterface {
    private double income;

    public royaltiesIncomeWithInterface(double income) {
        this.income = income;
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