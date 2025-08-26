import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int l, h;
        public Node(int l, int h){
            this.l = l;
            this.h = h;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] ans = new int[N];
        ArrayDeque<Node> check = new ArrayDeque<>();
        Arrays.fill(ans,0);
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        check.addFirst(new Node(N-1,arr[N-1]));
        for (int i = N-2; i >=0 ; i--) {
            while(!check.isEmpty()&&arr[i]>=check.peek().h){
                Node node =check.poll();
                ans[node.l] = i+1;
            }
            check.addFirst(new Node(i,arr[i]));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(ans[i]).append(' ');
        }

        System.out.println(sb.toString());
    }
}
