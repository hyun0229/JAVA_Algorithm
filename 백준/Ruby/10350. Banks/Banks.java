
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int arr[] = new int[N*2];
        st = new StringTokenizer(br.readLine());
        int S = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            arr[i+N] = num;
            S+=num;
        }
        for (int i = 1; i < N*2; i++) {
            arr[i]+=arr[i-1];
        }

        long result = 0;
        
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                
                if (arr[j + i] - arr[j] < 0) {
                    result += Math.ceil((double) Math.abs(arr[j + i] - arr[j]) / S);
                }
            }
        }
        System.out.println(result);
    }
}
