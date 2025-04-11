import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int arr[] = new int[N];
        for(int i = 0 ; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int bottom = 1;
        int top = arr[N - 1] - arr[0]+1;
        int mid = 0;
        int answer = 0;
        while (bottom <= top) {
            mid = (bottom + top) / 2;
            int ck = check(arr, mid);
            if (ck >= C) {
                answer = mid;
                bottom = mid + 1;
            } else {
                top = mid - 1;
            }
        }
        System.out.println(answer);
    }
    private static int check(int[] arr, int mid) {
        int cnt = 1;
        int now = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - now >= mid) {
                cnt++;
                now = arr[i];
            }
        }
        return cnt;
    }
}