package Programmers;

import java.util.HashSet;
import java.util.Set;

public class pg_42895_N으로표현 {


    class Solution {
        Set<Integer>[] ls;
        public int solution(int N, int number) {
            int answer = 0;
            ls = new Set[9];
            for (int i = 0; i < ls.length; i++) {
                ls[i] = new HashSet<>();
            }
            if(N== number) return 1;
            ls[1].add(N);
            answer = dp(N, number, 1);
            return answer;
        }
        public int dp(int n, int number, int idx) {
            if (idx == 8) {
                return -1;
            }
            for (int i = 1; i <= idx; i++) {
                
            }
            return idx;

        }
        

    }
    
}
