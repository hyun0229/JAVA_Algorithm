import java.util.*;

public class Solution {
    public int solution(String begin, String target, String[] words) {
        List<String> list = new ArrayList<>(Arrays.asList(words));
        list.add(begin);

        // 중간 패턴 맵: "*ot" → ["hot","dot", ...]
        Map<String, List<String>> patternMap = new HashMap<>();
        int L = begin.length();
        for (String w : list) {
            for (int i = 0; i < L; i++) {
                String pat = w.substring(0, i) + '*' + w.substring(i + 1);
                patternMap.computeIfAbsent(pat, k -> new ArrayList<>()).add(w);
            }
        }

        // BFS
        Queue<String> q = new ArrayDeque<>();
        Map<String, Integer> dist = new HashMap<>();
        q.add(begin); dist.put(begin, 0);

        while (!q.isEmpty()) {
            String cur = q.poll();
            int d = dist.get(cur);
            // 한 글자씩 바꿔볼 패턴 생성
            for (int i = 0; i < L; i++) {
                String pat = cur.substring(0, i) + '*' + cur.substring(i + 1);
                List<String> neighbors = patternMap.getOrDefault(pat, Collections.emptyList());
                for (String nxt : neighbors) {
                    if (!dist.containsKey(nxt)) {
                        dist.put(nxt, d + 1);
                        if (nxt.equals(target)) return d + 1;
                        q.add(nxt);
                    }
                }
            }
        }

        return 0;
    }
}
