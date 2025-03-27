package Programmers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class pg_42576_완주하지못한선수 {
    class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String,Integer> map = new HashMap<>();
        for(String s : participant){
            if(!map.containsKey(s)){
                map.put(s,0);
            }
            map.put(s,map.get(s)+1);
        }
        for(String s : completion){
            map.put(s,map.get(s)-1);
        }
        for(Entry<String, Integer> entry : map.entrySet()){
            if (entry.getValue() == 1) {
                answer = entry.getKey();
                break;
            }
        }
        return answer;
    }
    
}

}
