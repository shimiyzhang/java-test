package exceptionHandling.CustomExceptions;

// 自定义异常
// 小结
//        抛出异常时，尽量复用JDK已定义的异常类型；
//
//        自定义异常体系时，推荐从RuntimeException派生“根异常”，再派生出业务异常；
//
//        自定义异常时，应该提供多种构造方法。
public class Main {
    public static void main(String[] args) {
//        Java标准库定义的常用异常包括：
//          Exception
//          │
//          ├─ RuntimeException
//          │  │
//          │  ├─ NullPointerException
//          │  │
//          │  ├─ IndexOutOfBoundsException
//          │  │
//          │  ├─ SecurityException
//          │  │
//          │  └─ IllegalArgumentException
//          │     │
//          │     └─ NumberFormatException
//          │
//          ├─ IOException
//          │  │
//          │  ├─ UnsupportedCharsetException
//          │  │
//          │  ├─ FileNotFoundException
//          │  │
//          │  └─ SocketException
//          │
//          ├─ ParseException
//          │
//          ├─ GeneralSecurityException
//          │
//          ├─ SQLException
//          │
//          └─ TimeoutException

        String message = login("admin", "admin");
        System.out.println(message);
    }

    //    当我们在代码中需要抛出异常时，尽量使用JDK已定义的异常类型。
//    例如，参数检查不合法，应该抛出IllegalArgumentException：
    static void check(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("age can't be negative");
        }
    }

//    自定义新的异常类型，保持一个合理的异常继承体系非常重要
//    一个常见的做法是自定义一个BaseException作为“根异常”，然后，派生出各种业务类型的异常。
    static String login(String username, String password) {
        if (username == null || password == null) {
            throw new LoginFailedException();
        } else if (!username.equals("admin") || !password.equals("admin")) {
            throw new UserNotFoundException();
        } else {
            return "login success";
        }
    }
}
