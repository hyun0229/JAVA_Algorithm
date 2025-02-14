package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_3954_인터프리터 {
    static int sm, sc, si;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            sm = Integer.parseInt(st.nextToken());
            sc = Integer.parseInt(st.nextToken());
            si = Integer.parseInt(st.nextToken()); 
            int[] memory = new int[sm];
            char[] ip = new String(br.readLine()).toCharArray();
            char[] chars = new char[si];
            check(memory,ip,chars);
        }
    }
                
    private static void check(int[] memory, char[] ip, char[] chars) {
        
    }
}