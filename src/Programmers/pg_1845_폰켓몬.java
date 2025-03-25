package Programmers;

import java.util.*;


public class pg_1845_폰켓몬 {

}
class Solution_1845 {
    public int solution(int[] nums) {
        Set<Integer> chk= new HashSet<>();
        for (int i : nums) {
            chk.add(i);
            if (chk.size()==nums.length/2) {
                break;
            }
        }
        return chk.size();
    }
    
}