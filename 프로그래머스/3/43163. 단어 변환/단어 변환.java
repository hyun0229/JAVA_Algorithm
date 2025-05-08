import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        HashMap<String,Word> map = new HashMap<>();
        map.put(begin, new Word());
        for(String s : words){
            Word tmp = new Word();
            for (Map.Entry<String, Word> entry : map.entrySet()) {
                int cnt = 0;
                if(!check(entry.getKey(), s)) continue;
                entry.getValue().arr.add(s);
                tmp.arr.add(entry.getKey());
            }
            map.put(s,tmp);
        }
        Queue<String> q = new ArrayDeque<>();
        q.add(begin);
        map.get(begin).vis = 0;
        while(!q.isEmpty()){
            String now = q.poll();
            int num = map.get(now).vis;
            ArrayList<String> arr = map.get(now).arr;
            for(String s : arr){
                Word tmp = map.get(s);
                if(tmp.vis != -1)continue;
                tmp.vis = num+1;
                q.add(s);
                if(s.equals(target)){
                    return num +1;
                }
            }
        }
        return 0;
    }
    public class Word{
        int vis;
        ArrayList<String> arr;
        public Word(){
            vis = -1;
            arr = new ArrayList<>();
        }
    }
    public boolean check(String o1, String o2){
        int cnt = 0;
        for(int i = 0; i < o1.length() ; i++){
            if(o1.charAt(i) != o2.charAt(i)) cnt++;
            if(cnt==2) return false;
        }
        return true;
    }
}

