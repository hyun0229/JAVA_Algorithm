import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int N = people.length;
        int left = 0;
        int right = N-1;
        int cnt = 0;
        while(left < right){
            int num = people[left] + people[right];
            if(num<=limit){
                left++;
                cnt++;
            }
            right--;
        }
        int ans = (N-cnt * 2) + cnt;
        return ans;
    }
}