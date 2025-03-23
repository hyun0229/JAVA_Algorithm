package Programmers;

import java.util.ArrayList;
import java.util.List;

public class pg_17676_추석트래픽 {

    class Solution {
        public int solution(String[] lines) {
            int answer = 0;
            List<TimePair> logs = new ArrayList<>();
            for (String log : lines) {
                logs.add(timeParser(log));
            }
            for (TimePair timePair : logs) {
                long s = timePair.end;
                long e = s + 999;
                int cnt = 0;
                for (TimePair chk : logs) {
                    if (chk.start <= e && chk.end >= s) {
                        cnt++;
                    }
                }
                answer = Math.max(answer, cnt);
            }
            return answer;
        }
        public static TimePair timeParser(String log) {
            String[] parts = log.split(" ");
            String timeStr = parts[1];
            String dStr = parts[2]; 

            String[] hms = timeStr.split(":");
            int hour = Integer.parseInt(hms[0]);
            int minute = Integer.parseInt(hms[1]);

            String[] sec = hms[2].split("\\.");
            int second = Integer.parseInt(sec[0]);
            int millis = Integer.parseInt(sec[1]);

            long end = hour * 3600000L + minute * 60000L + second * 1000L + millis;

            double t = Double.parseDouble(dStr.replace("s", ""));
            long num = (long)(t * 1000);

            long start = end - num + 1;

            return new TimePair(start, end);
        }
        static class TimePair {
            long start,end;
            TimePair(long start, long end){
                this.start = start;
                this.end = end;
            }
        }
    }
}
