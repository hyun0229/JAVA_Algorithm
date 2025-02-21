package Programmers;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class pg_176962_과제진행하기 {
    public static void main(String[] args) {
        String[][] arr = {
            {"science", "12:40", "50"},
            {"music", "12:20", "40"},
            {"history", "14:00", "30"},
            {"computer", "12:30", "100"}
        };
        Solution_176962 s = new Solution_176962();
        System.out.println("\n"+Arrays.toString(s.solution(arr)));
    }
}
class Solution_176962 {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (String[] plan : plans) {
            pq.add(new Node(plan[0], plan[1], plan[2]));
        }
        Stack<Node> st = new Stack<>();
        int i =0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (!pq.isEmpty()) {
                int time = pq.peek().t - node.t;
                if (time<node.w) {
                    node.w -= time;
                    st.add(node);
                }
                else{
                    answer[i++] = node.name;
                    time -= node.w;
                    while (!st.isEmpty()&&time>0) {
                        if (st.peek().w>time) {
                            st.peek().w -= time;
                            break;
                        }
                        else{
                            answer[i++] = st.peek().name;
                            time-=st.peek().w;
                            st.pop();
                        }
                    }
                }
            }
            else{
                answer[i++] = node.name;
            }
        }
        int size = st.size();
        for (int j = 0; j < size; j++) {
            answer[i++] = st.pop().name;
        }

        return answer;
    }
    static class Node implements Comparable<Node>{
        String name;
        int t,w;
        Node(String name, String time, String W){
            this.name = name;
            String num[] = time.split(":");
            this.t = Integer.parseInt(num[0])*60 + Integer.parseInt(num[1]);
            this.w = Integer.parseInt(W);
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.t,o.t);
        }
    }
}