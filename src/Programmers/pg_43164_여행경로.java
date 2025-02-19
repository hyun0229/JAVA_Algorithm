package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

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
    ArrayList<String> ans;
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        HashMap<String,Integer> chkMap = new HashMap<>();
        ArrayList<PriorityQueue<String>> arr = new ArrayList<>();
        for (String[] string : tickets) {
            if (chkMap.containsKey(string[0])) {
                int num = chkMap.get(string[0]);
                arr.get(num).add(string[1]);
            }
            else{
                chkMap.put(string[0], arr.size());
                arr.add(new PriorityQueue<>());
                arr.get(arr.size()-1).add(string[1]);
            }
        }
        String airport = "ICN";
        ans = new ArrayList<>();
        ans.add(airport);
        while (true) {
            if (!chkMap.containsKey(airport))break;
            int idx = chkMap.get(airport);
            if (arr.get(idx).isEmpty())break;
            airport = arr.get(idx).poll();
            ans.add(airport);
        }
        answer = new String[ans.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }

}