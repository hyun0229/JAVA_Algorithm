import java.util.*;
class Solution {
    int[][] answer;
    int pre,post;
    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        Node[] arr = new Node[n];
        for(int i = 0 ; i<n; i++){
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            arr[i] = new Node(i+1,x,y);
        }
        Arrays.sort(arr);
        Node start = arr[0];
        for (int i = 1; i < n; i++) {
            Node tmp = start;
            Node curr = arr[i];
            while (true) {
                if (curr.x < tmp.x) {
                    if (tmp.L == null) {
                        tmp.L = curr;
                        break;
                    }
                    tmp = tmp.L;
                } else {
                    if (tmp.R == null) {
                        tmp.R = curr;
                        break;
                    }
                    tmp = tmp.R;
                }
            }
        }
        answer = new int[2][n];
        pre = 0;
        post = 0;
        dfs(start);
        return answer;
    }
    public void dfs(Node now){
        answer[0][pre++] = now.num;
        if(now.L!=null)dfs(now.L);
        if(now.R!=null)dfs(now.R);
        answer[1][post++] = now.num;
        
    }
    public class Node implements Comparable<Node>{
        int num,x,y;
        Node R,L;
        public Node(int num,int x, int y){
            this.num = num;
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Node o) {
            return o.y - this.y;
        }
    }
}