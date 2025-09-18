import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] cnt = new int[100001];
        int L = 0;
        long ans = 0;

        for (int R = 0; R < n; R++) {
            while (cnt[arr[R]] > 0) {
                cnt[arr[L]]--;
                L++;
            }
            cnt[arr[R]]++;
            ans += (R - L + 1);  
        }
        System.out.println(ans);
    }
}
