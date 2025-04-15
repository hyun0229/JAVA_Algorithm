import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine()); // 선분의 개수 입력
        int[][] arr = new int[N][2]; // 선분 배열 생성

        // 선분 정보 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 시작점
            arr[i][1] = Integer.parseInt(st.nextToken()); // 끝점
        }
		Arrays.sort(arr,(a,b) -> a[0] - b[0]);
		int p1 = arr[0][0], p2 = arr[0][1];
		int ans = 0;
		for(int i = 1; i<N;i++) {
			if(p2<arr[i][0]) {
				ans += p2-p1;
				p1 = arr[i][0];
				p2 = arr[i][1];
			}
			else {
				p2 = Math.max(p2,arr[i][1]);
			}
		}
		ans += p2-p1;
		System.out.println(ans);
	}

}
