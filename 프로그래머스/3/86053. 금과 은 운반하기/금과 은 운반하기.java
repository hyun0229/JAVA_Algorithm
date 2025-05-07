class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        int N = g.length;
        long left = 1;
        long right =  4*1000000000000000L;

        while(left<=right){
            long mid = (left + right) / 2;
            long totalGold = 0;
            long totalSilver = 0;
            long total = 0;
            for (int i = 0; i < N; i++) {
                long time = t[i];
                long weight = w[i];
                long trips = mid / (time * 2);
                if (mid % (time * 2) >= time) {
                    trips++; 
                }

                long maxMove = trips * weight;
                long movedGold = Math.min(g[i], maxMove);
                long movedSilver = Math.min(s[i], maxMove);
                long movedTotal = Math.min(g[i] + s[i], maxMove);

                totalGold += movedGold;
                totalSilver += movedSilver;
                total += movedTotal;
            }
            if (totalGold >= a && totalSilver >= b && total >= a + b) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
}