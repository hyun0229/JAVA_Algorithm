package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class pg_43164_여행경로 {
    public static void main(String[] args) {
        Solution_43164 s = new Solution_43164();
        String[][] flights = {
            {"ICN", "JFK"},
            {"HND", "IAD"},
            {"JFK", "HND"}
        };
        System.out.println(Arrays.toString(s.solution(flights)));
    }
}
class Solution_43164 {
    boolean flag = false;
    ArrayList<String> ans;
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        HashMap<String,Integer> chkMap = new HashMap<>();
        ArrayList<ArrayList<Node>> arr = new ArrayList<>();
        for (String[] string : tickets) {
            if (chkMap.containsKey(string[0])) {
                int num = chkMap.get(string[0]);
                arr.get(num).add(new Node(string[1],false));
            }
            else{
                chkMap.put(string[0], arr.size());
                arr.add(new ArrayList<>());
                arr.get(arr.size()-1).add(new Node(string[1],false));
            }
        }
        for (ArrayList<Node> arrayList : arr) {
            Collections.sort(arrayList);
        }
        String airport = "ICN";
        ans = new ArrayList<>();
        ans.add(airport);
        dfs(airport,chkMap,arr,tickets.length+1);
        answer = new String[ans.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }

    public class Node implements Comparable<Node> {
        boolean vis;
        String goal;
        public Node(String goal, boolean vis){
            this.goal = goal;
            this.vis = vis;
        }
        @Override
        public int compareTo(Node o) {
            return this.goal.compareTo(o.goal);
        }
    }
    private void dfs(String airport, HashMap<String,Integer> chkMap, ArrayList<ArrayList<Node>> arr,int size) {
        if (ans.size() == size) {
            flag = true;
            return;
        }
        if (!chkMap.containsKey(airport))return;
        int idx = chkMap.get(airport);
        for (Node n : arr.get(idx)) {
            if (n.vis)continue;
            n.vis = true;
            ans.add(n.goal);
            dfs(n.goal, chkMap, arr,size);
            if (flag)return;
            n.vis = false;
            ans.remove(ans.size()-1);
        }
    }
}