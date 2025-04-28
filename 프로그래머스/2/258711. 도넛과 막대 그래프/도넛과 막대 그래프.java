import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Map<Integer,Node> map = new HashMap<>();
        for(int[] is : edges){
            int out = is[0];
            int in = is[1];
            if(!map.containsKey(out)){
                map.put(out,new Node());
            }
            map.get(out).out+=1;
            if(!map.containsKey(in)){
                map.put(in,new Node());
            }
            map.get(in).in+=1;
            
        }
        for (Map.Entry<Integer, Node> entry : map.entrySet()){
            int index = entry.getKey();
            Node node = entry.getValue();
            int in = node.in;
            int out = node.out;
            if(out>=2&&in==0)answer[0] = index;
            if(out>=2&&in>=2)answer[3]++;
            if(out==0&&in>=1)answer[2]++;
        }
        answer[1] = map.get(answer[0]).out - answer[3] - answer[2];
        return answer;
    }
    public class Node{
        int in;
        int out;
        public Node(){
            in = 0;
            out = 0;
            
        }
    }
}