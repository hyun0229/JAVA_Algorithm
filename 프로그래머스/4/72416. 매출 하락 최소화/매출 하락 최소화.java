import java.util.*;
class Solution {
    public int solution(int[] sales, int[][] links) {
        int V = sales.length;
        List<Integer>[] graph = new ArrayList[V];
        List<Integer>[] rgraph = new ArrayList[V];
        
        for(int i = 0; i<V;i++){
            graph[i] = new ArrayList<>();
            rgraph[i] = new ArrayList<>();
        }
        int indegree[] = new int[V]; 
        for(int i = 0; i<V-1;i++){
            int x = links[i][0]-1;
            int y = links[i][1]-1;
            graph[y].add(x);
            rgraph[x].add(y);
            indegree[x]++;
        }
        int select[] = new int[V];
        int unselect[] = new int[V];
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0;i<V;i++){
            if(indegree[i]==0)q.add(i);
        }
        //System.out.println(Arrays.toString(indegree));
        while(!q.isEmpty()){
            int now = q.poll();
            boolean flag = false;
            unselect[now] = select[now];
            int minValue = Integer.MAX_VALUE;
            for(int i : rgraph[now]){
                if(select[i]<=unselect[i])flag = true;
                minValue = Math.min(minValue, unselect[now]-unselect[i]+select[i]);
            }
            select[now]+=sales[now];
            if(!flag&&minValue!=Integer.MAX_VALUE){
                unselect[now] = minValue;
            }
            minValue = Math.min(unselect[now], select[now]);
            for(int i : graph[now]){
                select[i] += minValue;
                if(--indegree[i] ==0){
                    q.add(i);
                }
            }
        }
        //System.out.println(Arrays.toString(select));
        //System.out.println(Arrays.toString(unselect));
        return Math.min(select[0],unselect[0]);
    }
}