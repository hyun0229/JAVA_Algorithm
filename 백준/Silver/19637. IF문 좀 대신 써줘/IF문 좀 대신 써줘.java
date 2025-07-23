import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        TreeMap<Integer,String> titleMap = new TreeMap<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String title = st.nextToken();
            int power = Integer.parseInt(st.nextToken());
            if (!titleMap.containsKey(power)){
                titleMap.put(power,title);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < M; i++) {
            int power = Integer.parseInt(br.readLine());
            sb.append(titleMap.ceilingEntry(power).getValue()).append('\n');
        }
        System.out.println(sb.toString());
    }
}
