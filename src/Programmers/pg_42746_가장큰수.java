package Programmers;

import java.util.PriorityQueue;

public class pg_42746_가장큰수 {
    class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<node> pq = new PriorityQueue<>();
        for(int i : numbers){
            pq.add(new node(i));
        }
        if(pq.peek().num.charAt(0)=='0') return "0";
        while(!pq.isEmpty()){
            node n = pq.poll();
            sb.append(n.num);
        }
        
        return sb.toString();
    }
    static class node implements Comparable<node>{
        public String num;
        int size;
        public node(int num){
            this.num = Integer.toString(num);
            this.size = this.num.length();
        }
        @Override
        public int compareTo(node o){
            int n1 = Integer.parseInt(this.num+o.num);
            int n2 = Integer.parseInt(o.num+this.num);
            return n2-n1;
        }
    }
}
}
