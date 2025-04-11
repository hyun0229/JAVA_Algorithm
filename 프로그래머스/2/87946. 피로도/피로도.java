class Solution {
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