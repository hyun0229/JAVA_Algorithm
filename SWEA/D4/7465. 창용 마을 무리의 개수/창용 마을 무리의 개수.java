import java.io.*;
import java.util.*;

public class Solution {
    static int[] arr;
    public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            make(N);
            for (int i = 0; i <M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken())-1;
                int y = Integer.parseInt(st.nextToken())-1;
                union(x,y);
            }
            Set<Integer> set = new HashSet<>();
            for (int i : arr) {
                set.add(find(i));
            }
            StringBuffer sb = new StringBuffer();
            sb.append('#').append(test_case).append(' ').append(set.size());
            System.out.println(sb.toString());
		}
	}
    private static void union(int x, int y) {
        int i = find(x);
        int j = find(y);
        if (i==j)return;
        arr[j] = i;
    }
    private static int find(int f) {
        if (arr[f] == f)return f;
        return arr[f] = find(arr[f]);
    }
    private static void make(int n) {
        arr= new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
    }

}
