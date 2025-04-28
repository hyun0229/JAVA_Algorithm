import java.util.*;
class Solution {
    boolean[] map;
    int now;
    int last;
    TreeSet<Integer> set;
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        map = new boolean[n];
        set = new TreeSet<>();
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i = 0; i<n;i++){
            map[i] = true;
            set.add(i);
        }
        now = k;
        last = 0;
        for(String s : cmd){
            String tmp[] = s.split(" ");
            if(tmp[0].equals("D")){
                int cnt = Integer.parseInt(tmp[1]);
                downMove(cnt);
            }
            if(tmp[0].equals("U")){
                int cnt = Integer.parseInt(tmp[1]);
                upMove(cnt);    
            }
            if(tmp[0].equals("C")){
                map[now]= false;
                set.remove(now);
                dq.add(now);
                Integer next = set.higher(now);
                if(next == null)next = set.lower(now);
                now = next;
            }
            if(tmp[0].equals("Z")){
                int recover = dq.pollLast();
                map[recover] = true;
                set.add(recover);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(boolean chk : map){
            if(chk)sb.append('O');
            else sb.append('X');
        }
        return sb.toString();
    }
    void upMove(int cnt) {
        int tmp = now;
        for (int i = 0; i < cnt; i++) {
            Integer next = set.lower(tmp);
            tmp = next;
        }
        now = tmp;
    }

    void downMove(int cnt) {
        int tmp = now;
        for (int i = 0; i < cnt; i++) {
            Integer next = set.higher(tmp);
            tmp = next;
        }
        now = tmp;
    }
}