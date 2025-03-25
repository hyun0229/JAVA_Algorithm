package Programmers;

public class pg_43105_정수삼각형 {
    class Solution {
        public int solution(int[][] triangle) {
            int answer = 0;
            int hight =  triangle.length;
            for (int i = hight-2; i >= 0; i--) {
                for (int j = 0; j < triangle[i].length; j++) {
                    triangle[i][j]+=Math.max(triangle[i+1][j], triangle[i+1][j+1]);
                }
            }
            return triangle[0][0];
        }
    }
}
