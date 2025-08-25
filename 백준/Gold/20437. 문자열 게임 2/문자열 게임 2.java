import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            ArrayDeque<Integer>[] q = new ArrayDeque[26];
            for (int j = 0; j < 26; j++) {
                q[j] = new ArrayDeque();
            }
            int Max = Integer.MIN_VALUE;
            int Min = Integer.MAX_VALUE;
            int K = Integer.parseInt(br.readLine());
            int arr[] = new int[26];
            int dist = 0;
            for(char c : s.toCharArray()){
                dist++;
                q[c-'a'].add(dist);
                if (++arr[c-'a'] == K){
                    int tmp = dist - q[c-'a'].poll();
                    Max = Math.max(Max,tmp);
                    Min = Math.min(Min,tmp);
                    arr[c-'a']--;
                }
            }
            if (Max == Integer.MIN_VALUE){
                sb.append(-1).append('\n');
                continue;
            }
            Max++;
            Min++;
            sb.append(Min).append(' ').append(Max).append('\n');
        }
        System.out.println(sb);
    }

}
