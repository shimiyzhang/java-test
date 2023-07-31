package JavaCoreClass.EnumerateClass;

//    枚举类
public class Main {
    public static void main(String[] args) {
        int day = 1;
        if (day == Weekday.SAT || day == Weekday.SUN) {
            // TODO: work at home
        }

        String color = "r";
        if (Color.RED.equals(color)) {
            // TODO:
        }

//        使用常量表示一组枚举值时，编辑器无法检查每个值的合理性
//        定义的常量仍可与其他变量比较，但其用途并非是枚举星期值

//        enum定义枚举类
        WeekDate date = WeekDate.SUN;
        if (date == WeekDate.SAT || date == WeekDate.SUN) {
            System.out.println("Work at home!");
        } else {
            System.out.println("Work at office!");
        }

//        int a = 1;
//        if (a == WeekDate.SUN) { // Operator '==' cannot be applied to 'int', 'JavaCoreClass.EnumerateClass.WeekDate'
//        }

//        不同类型的枚举不能互相比较或者赋值，因为类型不符
//        WeekDate d = Colors.RED; // Compile error: incompatible types

//        enum的比较
//        使用enum定义的枚举类是一种引用类型
//        enum类型的每个常量在JVM中只有一个唯一实例，所以可以直接用==比较
        Colors c = Colors.RED;
        if (c == Colors.BLUE) {
        }
        if (c.equals(Colors.RED)) {
        }

//        enum类型
//        通过enum定义的枚举类和其他的class没有区别；
//        定义的enum类型总是继承自java.lang.Enum，且无法被继承；
//        只能定义出enum的实例，无法通过new操作符创建enum的实例；
//        定义的每个实例都是引用类型的唯一实例；
//        可以将enum类型用于switch语句。

//        enum是一个class，每个枚举的值都是class实例
//        实例方法：
//        name() 返回常量名
        System.out.println(WeekDate.SUN.name()); // SUN
//        ordinal() 返回定义的常量的顺序，从0开始计数
        System.out.println(WeekDate.FRI.ordinal()); // 5

        WeekDayValue dv = WeekDayValue.FRI;
        if (dv.dayValue == 6 || dv.dayValue == 0) {
            System.out.println("Work at home!");
        } else {
            System.out.println("Work at office!");
        }

//        默认情况下，对枚举常量调用toString()会返回和name()一样的字符串。
//        但是，toString()可以被覆写，而name()则不行。
        WeekChineseDay cd = WeekChineseDay.SUN;
        if (cd.dayValue == 6 || cd.dayValue == 0) {
            System.out.println("Today is " + cd + ". Work at home!");
        } else {
            System.out.println("Today is " + cd + ". Work at office!");
        }

//    注意：判断枚举常量的名字，要始终使用name()方法，绝不能调用toString()！

//        枚举类可以应用在switch语句中
        WeekDate weekday = WeekDate.SUN;
        switch (weekday) {
            case MON:
            case TUE:
            case WED:
            case THU:
            case FRI:
                System.out.println("Today is " + weekday + ". Work at office!");
                break;
            case SAT:
            case SUN:
                System.out.println("Today is " + weekday + ". Work at home!");
                break;
            default:
                throw new RuntimeException("cannot process " + weekday);
        }

//        小结
//        Java使用enum定义枚举类型，它被编译器编译为final class Xxx extends Enum { … }；
//
//        通过name()获取常量定义的字符串，注意不要使用toString()；
//
//        通过ordinal()返回常量定义的顺序（无实质意义）；
//
//        可以为enum编写构造方法、字段和方法
//
//        enum的构造方法要声明为private，字段强烈建议声明为final；
//
//        enum适合用在switch语句中。
    }
}

class Weekday {
    public static final int SUN = 0;
    public static final int MON = 1;
    public static final int TUE = 2;
    public static final int WED = 3;
    public static final int THU = 4;
    public static final int FRI = 5;
    public static final int SAT = 6;
}

class Color {
    public static final String RED = "r";
    public static final String GREEN = "g";
    public static final String BLUE = "b";
}

enum WeekDate {
    SUN, MON, TUE, WED, THU, FRI, SAT;
}

enum Colors {
    RED,GREEN,BLUE;
}

enum WeekDayValue {
    MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6), SUN(0);

    public final int dayValue;

    WeekDayValue(int dayValue) {
        this.dayValue = dayValue;
    }
}

enum WeekChineseDay {
    MON(1, "星期一"), TUE(2, "星期二"), WED(3, "星期三"), THU(4, "星期四"), FRI(5, "星期五"), SAT(6, "星期六"), SUN(0, "星期日");

    public final int dayValue;
    private final String chinese;

    WeekChineseDay(int dayValue, String chinese) {
        this.dayValue = dayValue;
        this.chinese = chinese;
    }

//    覆写toString的目的是在输出时更具有可读性
    @Override
    public String toString() {
        return this.chinese;
    }
}