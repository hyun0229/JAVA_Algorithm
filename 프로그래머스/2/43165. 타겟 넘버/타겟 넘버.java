import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<String, Integer> memo = new HashMap<>();

    public int solution(int[] numbers, int target) {
        return dfs(0, 0, numbers, target);
    }

    private int dfs(int lv, int sum, int[] numbers, int target) {
        String key = lv + "," + sum;
        if (memo.containsKey(key)) return memo.get(key);
        
        if (lv == numbers.length) return sum == target ? 1 : 0;
        
        int result = dfs(lv + 1, sum + numbers[lv], numbers, target) +
                     dfs(lv + 1, sum - numbers[lv], numbers, target);
        memo.put(key, result);
        return result;
    }
}
