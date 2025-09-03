import java.io.*;
import java.util.*;

public class Main {
    static int[] led = {
            0b1111110, // 0
            0b0110000, // 1
            0b1101101, // 2
            0b1111001, // 3
            0b0110011, // 4
            0b1011011, // 5
            0b1011111, // 6
            0b1110000, // 7
            0b1111111, // 8
            0b1111011  // 9
    };

    static int[][] diff = new int[10][10];

    static void buildDiff() {
        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                diff[a][b] = Integer.bitCount(led[a] ^ led[b]);
            }
        }
    }

    static int[] toDigits(int num, int len) {
        int[] digits = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            digits[i] = num % 10;
            num /= 10;
        }
        return digits;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); 
        int K = Integer.parseInt(st.nextToken()); 
        int P = Integer.parseInt(st.nextToken()); 
        int X = Integer.parseInt(st.nextToken()); 

        buildDiff();

        int[] now = toDigits(X, K);
        int ans = 0;

        for (int i = 1; i <= N; i++) {
            if (i == X) continue; 
            int[] cand = toDigits(i, K);

            int change = 0;
            for (int j = 0; j < K; j++) {
                change += diff[now[j]][cand[j]];
                if (change > P) break;
            }
            if (change >= 1 && change <= P) ans++;
        }

        System.out.println(ans);
    }
}
