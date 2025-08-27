import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int H,W;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        int[] arr = new int[W];

        int maxIndex = 0;
        int maxValue = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (maxValue<tmp){
                maxIndex = i;
                maxValue = tmp;
            }
            arr[i] = tmp;
        }
        int ans = 0;
        int now = 0;
        for (int i = 0; i < maxIndex; i++) {
            if (now < arr[i]){
                now = arr[i];
            }else{
                ans += now -arr[i];
            }
        }
        now = 0;
        for (int i = W-1; i > maxIndex; i--) {
            if (now < arr[i]){
                now = arr[i];
            }else{
                ans += now -arr[i];
            }
        }
        System.out.println(ans);

    }
}
