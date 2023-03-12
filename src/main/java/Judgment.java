import java.util.Random;
import java.util.Scanner;

// 判断
public class Judgment {
    public static void main(String[] args) {
        // if判断
        Scanner scanner = new Scanner(System.in);
        // int n = scanner.nextInt();
        // if (n >= 90) {
        //     System.out.println("优秀");
        // } else if (n >= 60) {
        //     System.out.println("及格了");
        // } else {
        //     System.out.println("挂科了");
        // }
        // System.out.println("END");
        //
        // // 判断引用类型相等
        // String s1 = "hello";
        // String s2 = "HELLO".toLowerCase();
        // System.out.println(s1);
        // System.out.println(s2);
        // // ==表示“引用是否相等”，或者说，是否指向同一个对象。
        // if (s1 == s2) {
        //     System.out.println("s1 == s2");
        // } else {
        //     System.out.println("s1 != s2");
        // }
        // // 要判断引用类型的变量内容是否相等，必须使用equals()方法：
        // if (s1 != null && s1.equals(s2)) {
        //     System.out.println("s1 equals s2");
        // } else {
        //     System.out.println("s1 not equals s2");
        // }
        //
        // // 请用if ... else编写一个程序，用于计算体质指数BMI，并打印结果。
        // // BMI = 体重(kg)除以身高(m)的平方
        // // BMI结果：
        // // 过轻：低于18.5
        // // 正常：18.5-25
        // // 过重：25-28
        // // 肥胖：28-32
        // // 非常肥胖：高于32
        // System.out.println("请输入你的身高(m): ");
        // double height = scanner.nextDouble();
        // System.out.println("请输入你的体重(kg): ");
        // double weight = scanner.nextDouble();
        // double BMI = weight / (height * height);
        // System.out.println(BMI);
        // if (BMI < 18.5) {
        //     System.out.println("过轻");
        // } else if (BMI >= 18.5 && BMI < 25) {
        //     System.out.println("正常");
        // } else if (BMI >= 25 && BMI < 28) {
        //     System.out.println("过重");
        // } else if (BMI >= 28 && BMI < 32) {
        //     System.out.println("肥胖");
        // } else {
        //     System.out.println("非常肥胖");
        // }
        //
        // // switch多重选择
        // int option = 1;
        // switch (option) {
        //     case 1:
        //         System.out.println("Selected 1");
        //         break;
        //     case 2:
        //         System.out.println("Selected 2");
        //         break;
        //     case 3:
        //         System.out.println("Selected 3");
        //         break;
        //     default:
        //         System.out.println("Not selected");
        //         break;
        // }
        // // switch表达式(从Java 12开始)
        // String fruit = "apple";
        // switch (fruit) {
        //     case "apple" -> System.out.println("Selected apple");
        //     case "pear" -> System.out.println("Selected pear");
        //     case "mango" -> {
        //         System.out.println("Selected mango");
        //         System.out.println("Good choice!");
        //     }
        //     default -> System.out.println("No fruit selected");
        // }

        // 使用switch实现一个简单的石头、剪子、布游戏。
        System.out.println("please choice:");
        System.out.println(" 1: Rock");
        System.out.println(" 2: Scissors");
        System.out.println(" 3: Paper");
        System.out.println(" other: END");
        System.out.println("you have three times");

        for (int i = 0; i < 3; i++) {
            int choice = scanner.nextInt();
            Random random = new Random();
            int rd = random.nextInt(3) + 1;
            System.out.println("you: " + (choice == 1 ? "Rock" : choice == 2 ? "Scissors" : "Paper"));
            System.out.println("him: " + (rd == 1 ? "Rock" : rd == 2 ? "Scissors" : "Paper"));
            switch (choice) {
                case 1:
                    System.out.println(rd == 1 ? "draw" : rd == 2 ? "win" : "lose");
                    break;
                case 2:
                    System.out.println(rd == 2 ? "draw" : rd == 3 ? "win" : "lose");
                    break;
                case 3:
                    System.out.println(rd == 3 ? "draw" : rd == 1 ? "win" : "lose");
                    break;
                default:
                    System.out.println("END");
                    break;
            }
        }
    }
}
