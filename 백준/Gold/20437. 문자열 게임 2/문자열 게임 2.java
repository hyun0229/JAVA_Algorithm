import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

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
            int dist = 0;
            for(char c : s.toCharArray()){
                dist++;
                int arpha = c-'a';
                q[arpha].add(dist);
                if ( q[arpha].size() == K){
                    int tmp = dist - q[arpha].poll();
                    Max = Math.max(Max,tmp);
                    Min = Math.min(Min,tmp);
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
