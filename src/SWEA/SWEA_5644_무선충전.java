package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class SWEA_5644_무선충전 {
        public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
        int[] dx ={0,0,1,0,-1};
        int[] dy = {0,-1,0,1,0};
		for(int test_case = 1; test_case <= T; test_case++)
		{
            Map<Integer,Integer>[][] map = new HashMap[10][10];
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    map[i][j] = new HashMap<>();
                }
            }
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            map[0][0].put(0, 0);
            map[10-1][10-1].put(0, 1);
            for (int i = 0; i < 2; i++) {
                int x = i==0?0:9;
                int y = i==0?0:9;
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= time; j++) {
                    int m =  Integer.parseInt(st.nextToken());
                    x+=dx[m];
                    y+=dy[m];
                    if (map[x][y].containsKey(j)) {
                        map[x][y].put(j,2);
                    }
                    else{
                        map[x][y].put(j, i);
                    }
                }
            }
            PriorityQueue<BC>[] atime = new PriorityQueue[time+1];
            PriorityQueue<BC>[] btime = new PriorityQueue[time+1];
            for (int i = 0; i < btime.length; i++) {
                atime[i] = new PriorityQueue<>();
                btime[i] = new PriorityQueue<>();
            }
            for (int k = 0; k < N; k++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken())-1;
                int y = Integer.parseInt(st.nextToken())-1;
                int d = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());
                for (int i = x-d; i <= x+d; i++) {
                    for (int j = y-d; j <= y+d; j++) {
                        if (i<0||j<0||i>=10||j>=10)continue;
                        int num = Math.abs(x-i)+Math.abs(y-j);
                        if (num>d||map[i][j].size()==0)continue;
                        for ( Map.Entry<Integer, Integer> entry: map[i][j].entrySet()) {
                            if (entry.getValue() == 0) {
                                atime[entry.getKey()].add(new BC(k,power));
                            }
                            else if (entry.getValue() == 1) {
                                btime[entry.getKey()].add(new BC(k,power));
                            }
                            else{
                                atime[entry.getKey()].add(new BC(k,power));
                                btime[entry.getKey()].add(new BC(k,power));
                            }
                        }
                    }
                }
            }
            int ans = 0;
            for (int i = 0; i <= time; i++) {
                int sum = 0;
                if (!atime[i].isEmpty() && !btime[i].isEmpty()) {
                    BC a1 = atime[i].poll();
                    BC b1 = btime[i].poll();
                    if (a1.number != b1.number) {
                        sum = a1.power + b1.power;
                    } else {
                        int option1 = a1.power;
                        int a2 = 0, b2 = 0;
                        if (!atime[i].isEmpty()) {
                            a2 = atime[i].peek().power;
                        }
                        if (!btime[i].isEmpty()) {
                            b2 = btime[i].peek().power;
                        }
                        int option2 = Math.max(a2 + b1.power, a1.power + b2);
                        sum = Math.max(option1, option2);
                    }
                    ans += sum;
                } else if (!atime[i].isEmpty()) {
                    ans += atime[i].poll().power;
                } else if (!btime[i].isEmpty()) {
                    ans += btime[i].poll().power;
                }
            }
            System.out.println(String.format("#%d %d",test_case,ans));
		}
	
    }

    static class BC implements Comparable<BC>{
        int number,power;

        BC(int number, int power){
            this.number = number;
            this.power = power;
        }

        @Override
        public int compareTo(BC o) {
            return Integer.compare(o.power, this.power);
        }
        
    }
}
