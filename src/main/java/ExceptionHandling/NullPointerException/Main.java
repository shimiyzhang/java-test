package ExceptionHandling.NullPointerException;

// NullPointerException即空指针异常，俗称NPE。
// 如果一个对象为null，调用其方法或访问其字段就会产生NullPointerException，这个异常通常是由JVM抛出的
// 小结
//        NullPointerException是Java代码常见的逻辑错误，应当早暴露，早修复；
//
//        可以启用Java 14的增强异常信息来查看NullPointerException的详细错误信息。
public class Main {
    public static void main(String[] args) {
//        String s = null;
//        System.out.println(s.toLowerCase());

//        处理NullPointerException
//        NullPointerException是一种代码逻辑错误，遵循原则是早暴露，早修复，严禁使用catch来隐藏这种编码错误：

//        错误示例: 捕获NullPointerException
//        try {
//            transferMoney(from, to, amount);
//        } catch (NullPointerException e) {
//        }

//        成员变量在定义时初始化：
//        public class Person {
//            private String name = "";
//        }
//        使用空字符串""而不是默认的null可避免很多NullPointerException，
//        编写业务逻辑时，用空字符串""表示未填写比null安全得多。
//
//        返回空字符串""、空数组而不是null：
//        public String[] readLinesFromFile(String file) {
//            if (getFileSize(file) == 0) {
//                // 返回空数组而不是null:
//                return new String[0];
//            }
//              ...
//        }
//        这样可以使得调用方无需检查结果是否为null。

//        如果调用方一定要根据null判断，比如返回null表示文件不存在，那么考虑返回Optional<T>：
//        public Optional<String> readFromFile(String file) {
//            if (!fileExist(file)) {
//                return Optional.empty();
//            }
//              ...
//        }
//        这样调用方必须通过Optional.isPresent()判断是否有结果。

//        定位NullPointerException
        Person p = new Person();
        System.out.println(p.address.city.toLowerCase());
    }
}

class Person {
    String[] name = new String[2];
    Address address =  new Address();
}

class Address {
    String city;
    String street;
    String zipcode;
}