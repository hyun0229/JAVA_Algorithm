import java.util.*;

class Solution {
   

    public int solution(int n, int[][] computers) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] vis = new boolean[n];
        int cnt=0;
        
        for(int k=0; k<n; k++){
            if(!vis[k]){
                cnt++;
                vis[k]=true;
                q.add(k);
                while(!q.isEmpty()){
                    int cur = q.poll(); 
                    for(int i=0; i<n; i++){ 
                        if(!vis[i] && computers[cur][i]==1){ 
                            vis[i]=true;
                            q.add(i); 
                        }
                    }
                }
            }
        }
        return cnt;
    }
}