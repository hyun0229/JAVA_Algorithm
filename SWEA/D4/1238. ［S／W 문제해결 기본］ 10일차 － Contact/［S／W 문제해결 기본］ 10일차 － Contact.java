import java.io.StringReader;
import java.util.*;
public class Solution {
    static Set<Integer> set;
    static Map<Integer,List<Integer>> map;
    static Node ans;
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
    
        for (int test_case = 1; test_case <= T; test_case++) {
            set = new HashSet<>();
            map = new HashMap<>();
            int N = sc.nextInt();
            int start = sc.nextInt();
            for (int i = 0; i < N/2; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                if (!map.containsKey(x)) {
                    map.put(x,new ArrayList<>());
                }
                map.get(x).add(y);
            }
            bfs(start);
            StringBuilder sb = new StringBuilder();
            sb.append('#').append(test_case).append(' ').append(ans.num);
            System.out.println(sb.toString());
        }
    }
    private static void bfs(int start) {
        Queue<Node> q = new ArrayDeque<>();
        Node startNode = new Node(0, start);
        q.add(startNode);
        ans = startNode; 
        set.add(start);
        while (!q.isEmpty()) {
            Node node = q.poll();
            int now = node.num;
            int lv = node.lv;
            if (!map.containsKey(now))continue;
            for (int i : map.get(now)) {
                if (set.contains(i))continue;
                set.add(i);
                Node tmp =new Node(lv+1, i); 
                q.add(tmp);
                if (ans.compareTo(tmp)==1) {
                    ans = tmp;
                }
            }
        }
    }
    static class Node implements Comparable<Node>{
        int lv, num;
        public Node(int lv, int num){
            this.lv = lv;
            this.num = num;
        }
        @Override
        public int compareTo(Node o) {
            if (this.lv == o.lv) {
                return Integer.compare(o.num,this.num);
            }   
            return Integer.compare(o.lv, this.lv);
        }
    }
}
