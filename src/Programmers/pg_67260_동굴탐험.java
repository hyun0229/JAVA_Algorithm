package Programmers;

import java.util.ArrayList;

public class pg_67260_동굴탐험{

}

class Solution_67260 {
    ArrayList<Integer>[] map;
    int[][] orders;
    
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        map = new ArrayList[n];
        orders = new int[n][2];
        for (int i = 0; i < n; i++) {
            map[n] = new ArrayList<>();
        }
        for (int[] is : path) {
            map[is[0]].add(is[1]);
            map[is[1]].add(is[0]);
        }
        for (int[] is : order) {
            int[][] = 
        }

        

        return answer;
    }


}