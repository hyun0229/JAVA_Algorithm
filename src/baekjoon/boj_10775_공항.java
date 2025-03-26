package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class boj_10775_공항 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(bf.readLine());
        int P = Integer.parseInt(bf.readLine());
        arr = new int[G+1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        int cnt = 0;
        for (int i = 0; i < P; i++) {
            int num = Integer.parseInt(bf.readLine());
            int find = find(num);
            if (find==0) {
                break;
            }
            cnt++;
            union(find-1, num);
        }
        System.out.println(cnt);
    }
    private static boolean union(int i, int j) {
        int x = find(i);
        int y = find(j);
        if (x!=y){
            arr[y] = x;
            return true;
        }
        return false;
    }
    private static int find(int i) {
        if (i == arr[i])return i;
        return arr[i] = find(arr[i]);
    }
}
