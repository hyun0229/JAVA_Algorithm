import java.util.*;
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int N = timeToSeconds(play_time);
        int size = timeToSeconds(adv_time);
        int M = logs.length;
        int[] arr = new int[N+1];
        for(String s : logs){
            String[] tmp = s.split("-");
            int start = timeToSeconds(tmp[0]);
            int end = timeToSeconds(tmp[1]);
            arr[start]++;
            arr[end]--;
        }
        long sum = 0;
        long max = 0;
        int ans = 0;
        int now = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0 ; i<=N;i++){
            now+=arr[i];
            q.add(now);
            sum+=now;
            if(q.size()>size){
                sum-=q.poll();
            }
            if(max<sum&&q.size()==size){
                max = sum;
                ans = i-size+1;
            }
        }
        
        return secondsToTime(ans);
    }
        public int timeToSeconds(String time) {
        String[] parts = time.split(":"); // "02:03:55" -> ["02", "03", "55"]
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        return hours * 3600 + minutes * 60 + seconds;
    }
        public String secondsToTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}