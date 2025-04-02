package Programmers;
import java.util.*;


public class pg_42586_기능개발 {
    class Solution {
    public Integer[] solution(int[] progresses, int[] speeds) {
        Queue<Node> q = new ArrayDeque<>();
        for(int i = 0; i<progresses.length ; i++){
            q.add(new Node(progresses[i],speeds[i]));
        }
        List<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()){
            Node node = q.poll();
            int cnt = 1;
            int day = node.time/node.speed;
            if(node.time%node.speed!=0)day+=1;
            int q_size = q.size();
            for(int i = 0 ; i<q_size ; i++){
                Node tmp = q.poll();
                tmp.time-= tmp.speed*day;
                if(tmp.time>0){
                    q.add(tmp);
                    continue;
                }
                cnt++;
            }
            ans.add(cnt);
        }
        
        return ans.toArray(new Integer[0]);
    }
    static class Node{
        int time,speed;
        public Node(int progress,int speed){
            this.time = 100- progress;
            this.speed = speed;
        }
    }
    
}

}
