class Solution {
    public int solution(int[] info, int[][] edges) {
        int N = info.lenght;
        List<Integer>[] edgeList = new ArrayList[N];
        for(int i = 0; i<edges.lenght; i++){
            edgeList[i] = new ArrayList<>();
        }
        
        for(int i = 0; i<edges.lenght; i++){
            int x = edges[0];
            int y = edges[1];
            edgeList[x].add(y);
            edgeList[y].add(x);
        }
        boolean vis[][] = new int[N][1<<N];
        Queue<Node> q = new ArrayDeque<>();
        vis[0][0] = true;
        q.add(new Node(0,0,1,1,0));
        while(!q.isEmpty()){
            Node node = q.poll;
            int now = node.now;
            int before = node.now;
            int sheep = node.sheep;
            int wolf = node.wolf;
            int key = node.key;
            for(int i : edgeList[now]]){
                int nextkey = |= (1 << 0);
                if((vis[i][j])==key)continue;

            }
            
        }
        
        
        
        return answer;
    }
    public class Node{
        int now, before, key, sheep, wolf;
        public node(int now, int before, int key, int sheep, int wolf){
            this.now = now;
            this.before = before;
            this.key = key;
            this.sheep = sheep;
            this.wolf = wolf;
        }
    }
}