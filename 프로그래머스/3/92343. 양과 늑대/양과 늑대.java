import java.util.*;

class Solution {
    int maxSheep = 0;
    List<Integer>[] tree;

    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }

        // 시작 노드 0부터 DFS
        Set<Integer> next = new HashSet<>();
        next.add(0);
        dfs(0, 0, 0, next, info);

        return maxSheep;
    }

    private void dfs(int node, int sheep, int wolf, Set<Integer> next, int[] info) {
        if (info[node] == 0) sheep++;
        else wolf++;

        if (wolf >= sheep) return;

        maxSheep = Math.max(maxSheep, sheep);

        Set<Integer> newNext = new HashSet<>(next);
        newNext.remove(node);
        newNext.addAll(tree[node]);

        for (int nextNode : newNext) {
            dfs(nextNode, sheep, wolf, newNext, info);
        }
    }
}
