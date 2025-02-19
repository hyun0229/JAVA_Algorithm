package Programmers;

import java.util.Arrays;

public class pg_87946_피로도 {

    public static void main(String[] args) {
        Solution_87946 s = new Solution_87946();
        int ans = s.solution(80, new int[][]{{80, 20},{50,40},{30,10}});
        System.out.println(ans);
    }

}
class Solution_87946 {
    int ans = 0;
    boolean flag;
    public int solution(int k, int[][] dungeons) {
        flag = true;
        recursive(0,dungeons,k,new boolean[dungeons.length]);
        int answer = ans;
        return answer;
    }
    private void recursive(int lv, int[][] dungeons, int k,boolean[] vis) {
        if (lv == dungeons.length) {
            flag = false;
            ans = lv;
            return;
        }
        for (int i = 0; i < dungeons.length; i++) {
            if (!flag)return;
            if (!vis[i]) {
                vis[i] = true;
                int x = dungeons[i][0];
                int y = dungeons[i][1];
                if (x<=k)recursive(lv+1, dungeons, k-y, vis);;
                vis[i] = false;
            }
        }
        if (!flag)return;
        ans = Math.max(ans,lv);
    }
}