import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Solution {
    public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++){
            int N = Integer.parseInt(br.readLine());
            int dp[] = new int[N];
            int len = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());
                int idx = Arrays.binarySearch(dp, 0, len, x);
                if (idx<0) {
                    idx = -idx-1;
                }
                if (len == idx) {
                    dp[idx] = x;
                    len++;
                }
                else{
                    dp[idx] = x;
                }
            }
            StringBuilder sb =new StringBuilder();
            sb.append('#').append(test_case).append(' ').append(len);
            System.out.println(sb.toString());
		}
	}
}
