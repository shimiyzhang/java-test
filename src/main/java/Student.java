import java.util.Scanner;

public class Student {
    int age;

    public Student(int age) {
        this.age = age;
    }

    public boolean isSchoolBoy() {
        return age >= 6 && age <= 12;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            int age = scan.nextInt();
            if (age == 0) {
                System.out.println("EXIT");
                break;
            }
            Student student = new Student(age);
            System.out.println(student.isSchoolBoy() ? "是小学生" : "不是小学生");
        }
    }
}
