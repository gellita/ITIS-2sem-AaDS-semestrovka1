import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader f = new FileReader("text.txt");
        Scanner scan = new Scanner(f);
        int i = 1;
        while (scan.hasNextLine()) {
            String a = scan.nextLine();
            int begin = (int) (Math.random() * (a.length() / 2 - 2)) + 2;
            int end = (int) (Math.random() * (a.length() - a.length() / 2)) + a.length() / 2;
            String pattern = a.substring(begin, end);
            long time = System.nanoTime();
            int[] res = boyerMoore(a, pattern);
            long result_time = (System.nanoTime() - time);
            System.out.printf(i + ") Выполнено за %s ns" + ". Результат - " + res[0] + ". Кол-во итераций - " + res[1] + "\n", result_time);
            i++;
        }
        f.close();
    }
    public static int[] boyerMoore(String text, String pattern) {
        int count_iterations = 0;
        int n = text.length();
        int m = pattern.length();
        if (m == 0) {
            return new int[]{0, 0};
        }
        int[] badMatchTable = new int[256];
        Arrays.fill(badMatchTable, m);
        for (int i = 0; i < m - 1; i++) {
            badMatchTable[pattern.charAt(i)] = m - i - 1;
            count_iterations++;
        }
        int i = m - 1;
        int j = i;
        int k = i;
        while (j >= 0 && i < n) {
            j = m - 1;
            k = i;
            while (j >= 0 && text.charAt(k) == pattern.charAt(j)) {
                k--;
                j--;
                count_iterations++;
            }
            i += badMatchTable[text.charAt(i)];

        }
        if (k >= n - m) {
            return new int[]{-1, count_iterations};
        } else {
            return new int[]{k + 1, count_iterations};
        }
    }
}