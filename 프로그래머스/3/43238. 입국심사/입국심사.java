class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long right = (long)n*(long)times[times.length-1];
        long left = 0;
        while (left <= right) {
            long mid = (left+right)/2;
            long complete = 0;
            
            for (int i=0; i<times.length; i++) {
                complete += mid/times[i];
            }
            
            if (complete < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}