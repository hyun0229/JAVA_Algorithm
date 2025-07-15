import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static boolean checkNum(long num, String target) {
        String numbers = String.valueOf(num);
        int len = target.length();
        int now = 0;
        for (int i = 0; i < numbers.length(); i++) {
            if (numbers.charAt(i) == target.charAt(now)) now++;
            if (now == len) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        long num = 0L;
        StringBuilder chk = new StringBuilder();

        for (char c : s.toCharArray()) {
            chk.append(c);
            if (chk.length() > 1 && checkNum(num, chk.toString())) {
                continue;
            } else {
                chk = new StringBuilder();
                chk.append(c);
            }
            while (true) {
                num++;
                if (checkNum(num, chk.toString())) break;
            }
        }

        System.out.print(num);
    }
}
