package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_24678_돌무더기게임1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = 0;
            if (Integer.parseInt(st.nextToken())%2!=1)cnt++;
            if (Integer.parseInt(st.nextToken())%2!=1)cnt++;
            if (Integer.parseInt(st.nextToken())%2!=1)cnt++;
            System.out.println(cnt>1?'R':'B');
        }
    }
}
