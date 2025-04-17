import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int left = 1;               
        int right = distance;       
        int ans = 0;                

        while (left <= right) {
            int mid = (left + right) / 2;
            int removeCount = 0;
            int prev = 0;          
            for (int rock : rocks) {
                if (rock - prev < mid) {
                    removeCount++;
                } else {
                    prev = rock;
                }
            }
            if (distance - prev < mid) {
                removeCount++;
            }
            if (removeCount > n) {
                right = mid - 1;
            } else {
                ans = mid;
                left = mid + 1;
            }
        }

        return ans;
    }
}
