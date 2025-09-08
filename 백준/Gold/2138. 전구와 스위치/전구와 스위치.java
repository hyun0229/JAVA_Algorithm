import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static char[] origin, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        origin = br.readLine().toCharArray();
        target = br.readLine().toCharArray();

        int res1 = solve(origin.clone(), false);

        int res2 = solve(origin.clone(), true);

        int ans = -1;
        if (res1 >= 0 && res2 >= 0) ans = Math.min(res1, res2);
        else if (res1 >= 0) ans = res1;
        else if (res2 >= 0) ans = res2;

        System.out.println(ans);
    }

    public static int solve(char[] arr, boolean pressFirst) {
        int cnt = 0;

        if (pressFirst) { // 첫 번째 스위치 누름
            press(arr, 0);
            cnt++;
        }

        for (int i = 1; i < N; i++) {
            if (arr[i - 1] != target[i - 1]) {
                press(arr, i);
                cnt++;
            }
        }

        for (int i = 0; i < N; i++) {
            if (arr[i] != target[i]) return -1;
        }
        return cnt;
    }

    public static void press(char[] arr, int idx) {
        for (int j = idx - 1; j <= idx + 1; j++) {
            if (j >= 0 && j < N) {
                arr[j] = (arr[j] == '0') ? '1' : '0';
            }
        }
    }
}
