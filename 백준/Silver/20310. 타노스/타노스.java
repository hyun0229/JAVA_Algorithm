import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int countZero = 0;
        int countOne = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)-'0' == 0) countZero++;
            else countOne++;
        }
        countZero = countZero!=0?countZero/2 : 0;
        countOne = countOne!=0?countOne/2 : 0;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)-'0' == 0){
                if (countZero == 0)continue;
                sb.append('0');
                countZero--;
            }
            else{
                if (countOne != 0){
                    countOne--;
                    continue;
                }
                sb.append('1');
            }
        }
        System.out.println(sb.toString());
    }
}
