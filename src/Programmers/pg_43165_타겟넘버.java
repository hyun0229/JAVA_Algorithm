package Programmers;

public class pg_43165_타겟넘버 {
    public static void main(String[] args) {
        Solution2 s = new Solution2();
        int ans = s.solution(new int[]{4, 1, 2, 1}, 4);
        System.out.println(ans);
    }

}
class Solution2 {
    int ans = 0;
    public int solution(int[] numbers, int target) {
        recursive(0,numbers,target,0);
        int answer = ans;
        return answer;
    }
    private void recursive(int lv, int[] numbers, int target,int sum) {
        if(lv == numbers.length){
            if (target == sum) {
                ans++;
            }    
            return;
        }
        recursive(lv+1, numbers, target, sum+numbers[lv]);
        recursive(lv+1, numbers, target, sum-numbers[lv]);
    }
}