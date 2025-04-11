
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int getGCD(int num1, int num2) {
        if (num1 % num2 == 0) {
            return num2;
        }
        return getGCD(num2, num1 % num2);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int num[] = new int[N];
        for (int i = 0; i < num.length; i++) {
            st = new StringTokenizer(bf.readLine());
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);
        int arr[] = new int[N-1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = num[i+1]-num[i];
        }
        Arrays.sort(arr);
        int gcd = arr[0];
        for (int i = 1; i < arr.length; i++) {
            gcd = getGCD(gcd, arr[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= gcd; i++) {
            if (gcd%i==0) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString().trim());
    }
}
