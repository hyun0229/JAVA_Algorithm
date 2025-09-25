import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static int rows, cols;
    static char[][] grid;
    static int[][] fireReachTime;
    static int[][] jihunReachTime;
    
    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());
        
        grid = new char[rows][cols];
        fireReachTime = new int[rows][cols];
        jihunReachTime = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            Arrays.fill(fireReachTime[i], -1);
            Arrays.fill(jihunReachTime[i], -1);
        }
        
        Queue<int[]> fireQueue = new LinkedList<>();
        Queue<int[]> jihunQueue = new LinkedList<>();
        
        for (int r = 0; r < rows; r++) {
            String line = br.readLine();
            for (int c = 0; c < cols; c++) {
                grid[r][c] = line.charAt(c);
                if (grid[r][c] == 'F') {
                    fireQueue.add(new int[]{r, c});
                    fireReachTime[r][c] = 0;
                } else if (grid[r][c] == 'J') {
                    jihunQueue.add(new int[]{r, c});
                    jihunReachTime[r][c] = 0;
                }
            }
        }
        
        while (!fireQueue.isEmpty()) {
            int[] cur = fireQueue.poll();
            int curRow = cur[0], curCol = cur[1];
            
            for (int d = 0; d < 4; d++) {
                int nextRow = curRow + dx[d];
                int nextCol = curCol + dy[d];
                
                if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) continue;
                if (grid[nextRow][nextCol] == '#') continue;
                if (fireReachTime[nextRow][nextCol] != -1) continue;
                
                fireReachTime[nextRow][nextCol] = fireReachTime[curRow][curCol] + 1;
                fireQueue.add(new int[]{nextRow, nextCol});
            }
        }
        
        while (!jihunQueue.isEmpty()) {
            int[] cur = jihunQueue.poll();
            int curRow = cur[0], curCol = cur[1];
            
            for (int d = 0; d < 4; d++) {
                int nextRow = curRow + dx[d];
                int nextCol = curCol + dy[d];
                
                if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) {
                    System.out.println(jihunReachTime[curRow][curCol] + 1);
                    return;
                }
                
                if (grid[nextRow][nextCol] == '#') continue;
                if (jihunReachTime[nextRow][nextCol] != -1) continue;
                
                int arriveTime = jihunReachTime[curRow][curCol] + 1;
                
                if (fireReachTime[nextRow][nextCol] != -1 && fireReachTime[nextRow][nextCol] <= arriveTime) continue;
                
                jihunReachTime[nextRow][nextCol] = arriveTime;
                jihunQueue.add(new int[]{nextRow, nextCol});
            }
        }
        
        System.out.println("IMPOSSIBLE");
    }
}
