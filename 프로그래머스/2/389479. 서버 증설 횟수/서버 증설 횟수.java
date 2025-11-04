import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int n = players.length; 
        int[] shutDownSchedule = new int[n + k]; 
        int runningServers = 0; 
        int totalAdded = 0;     
        
        for (int t = 0; t < n; t++) {
            runningServers -= shutDownSchedule[t];
            
            int requiredServers = players[t] / m;
            
            if (runningServers < requiredServers) {
                int add = requiredServers - runningServers;
                totalAdded += add;
                runningServers += add;
                if (t + k < shutDownSchedule.length) {
                    shutDownSchedule[t + k] += add;
                }
            }
        }
        
        return totalAdded;
    }
}
