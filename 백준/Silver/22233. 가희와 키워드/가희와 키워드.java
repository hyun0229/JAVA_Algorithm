import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> keyWorlds = new HashSet<>();

        for (int i = 0; i <N ; i++) {
            String s = br.readLine();
            keyWorlds.add(s);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            String[] worlds = s.split(",");
            for (String world : worlds) {
                keyWorlds.remove(world);
            }
            sb.append(keyWorlds.size()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
