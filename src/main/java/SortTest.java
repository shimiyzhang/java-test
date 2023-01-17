import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class SortTest {
    public static void main(String[] args) {
        Double[] list_double = new Double[10];
        list_double[0] = 1.2;
        list_double[1] = 2.2;
        list_double[2] = 8.2;
        list_double[3] = 4.2;
        list_double[4] = 7.2;
        list_double[5] = 6.2;
        list_double[6] = 5.2;
        list_double[7] = 4.2;
        list_double[8] = 9.2;
        list_double[9] = 10.2;

        Character[] list_char = new Character[5];
        list_char[0] = 'A';
        list_char[1] = 'C';
        list_char[2] = 'E';
        list_char[3] = 'B';
        list_char[4] = 'D';
        for (int i = 0; i < list_char.length; i++) {
            list_char[i] = new Character(list_char[i]);
        }

        java.util.Arrays.sort(list_char);
        System.out.println("升序:");
        for (Character emp : list_char) {
            System.out.print(emp + "——");
        }

        Arrays.sort(list_char, Collections.reverseOrder());
        System.out.println("降序:");
        for (Character emp : list_char) {
            System.out.print(emp + "——");
        }
    }
}
