import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        long sum1 = 0;
        long sum2 = 0;
        for(int i : queue1){
            sum1+=i;
            q1.add(i);
        }
        for(int i : queue2){
            sum2+=i;
            q2.add(i);
        }
        int max = queue1.length + queue2.length;
        max*=2;
        if(sum1 == sum2)return 0;
        for(int i = 0; i<max ; i++){
            if(sum1 == sum2)return i;
            if(sum1>sum2){
                int tmp = q1.poll();
                sum1-=tmp;
                sum2+=tmp;
                q2.add(tmp);
            }
            else{
                int tmp = q2.poll();
                sum2-=tmp;
                sum1+=tmp;
                q1.add(tmp);
            }
            
        }
        
        return answer;
    }
}