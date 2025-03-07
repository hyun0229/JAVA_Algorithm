package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1074_Z {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int ans = recursive(N,r,c);
        System.out.println(ans);

    }
    private static int recursive(int lv,int x, int y) {
        int num = 0;
        int r =x;
        int c = y;
        int N = (int) (Math.pow(2, lv)/2);
        if (r<N&&c<N){
            num = 0;
        }
        else if (r<N&&c>=N) {
            num = N*N;
            c-=N;
        }
        else if (r>=N&&c<N) {
            num = N*N*2;
            r-=N;
        }
        else{
            num = N*N*3;
            r-=N;
            c-=N;
        }
        if(lv==1)return num;
        
        return num+recursive(lv-1,r,c);
    }
}
