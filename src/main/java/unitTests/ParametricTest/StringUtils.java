package unitTests.ParametricTest;

public class StringUtils {
    // 假设我们自己编写了一个StringUtils.capitalize()方法，它会把字符串的第一个字母变为大写，后续字母变为小写：
    public static String capitalize(String s) {
        if (s.length() == 0) {
            return s;
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }
}
