import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int arr[] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c>='a')arr[c-'a']++;
            else arr[c-'A']++;
        }
        int max = 0;
        char alpha = '?';
        for (int i = 0; i < arr.length; i++) {
            if (max == arr[i]) {
                alpha = '?';
            }
            else if (max < arr[i]) {
                max = arr[i];
                alpha = (char)(i + 'A');
            }
        }
        System.out.println(alpha);
    }
}
