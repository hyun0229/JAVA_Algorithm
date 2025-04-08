package baekjoon;

import java.io.*;
import java.util.*;

public class bj_11722_가장긴감소하는부분수열_세그버전 { //세그먼트 트리 버전
    static int[] segTree;
    static int size;

    static int query(int node, int start, int end, int l, int r) {
        if(r < start || end < l) return 0;
        if(l <= start && end <= r) return segTree[node];
        int mid = (start + end) / 2;
        int left = query(node * 2, start, mid, l, r);
        int right = query(node * 2 + 1, mid + 1, end, l, r);
        return Math.max(left, right);
    }
    
    static void update(int node, int start, int end, int idx, int val) {
        if(idx < start || idx > end) return;
        if(start == end) {
            segTree[node] = Math.max(segTree[node], val);
        } else {
            int mid = (start + end) / 2;
            if(idx <= mid) update(node * 2, start, mid, idx, val);
            else update(node * 2 + 1, mid + 1, end, idx, val);
            segTree[node] = Math.max(segTree[node * 2], segTree[node * 2 + 1]);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Set<Integer> set = new TreeSet<>();
        for (int num : arr) {
            set.add(num);
        }
        List<Integer> list = new ArrayList<>(set);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), i + 1);
        }
        size = list.size();
        segTree = new int[size * 4];
        
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int idx = map.get(arr[i]);
            int best = 0;
            if (idx < size) { 
                best = query(1, 1, size, idx + 1, size);
            }
            int dp = best + 1;
            ans = Math.max(ans, dp);
            update(1, 1, size, idx, dp);
        }
        System.out.println(ans);
    }
}
