import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 987654321;
    
    public static void main(String[] args) throws Exception {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int n = sc.nextInt(), m = sc.nextInt();
        ArrayList<Integer>[] g = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) g[i] = new ArrayList<>();
        int[] in = new int[n + 1], layer = new int[n + 1], fp = new int[n + 1], ln = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            g[u].add(v); 
            in[v]++;
        }
        int[] q = new int[n + 1];
        int front = 0, back = 0;
        for (int i = 1; i <= n; i++) {
            if (in[i] == 0) q[back++] = i;
        }
        while (front < back) {
            int cur = q[front++];
            for (int nxt : g[cur]) {
                layer[nxt] = Math.max(layer[cur] + 1, layer[nxt]);
                if (--in[nxt] == 0) q[back++] = nxt;
            }
        }
        for (int i = 1; i <= n; i++) {
            fp[i] = INF;
            if (!g[i].isEmpty())
                for (int nxt : g[i])
                    fp[i] = Math.min(fp[i], layer[nxt]);
        }
        int maxLayer = 0;
        for (int i = 1; i <= n; i++) {
            ln[layer[i]]++;
            if (layer[i] > maxLayer) maxLayer = layer[i];
        }
        int[] mx = new int[maxLayer + 1];
        Arrays.fill(mx, -1);
        for (int i = 1; i <= n; i++) {
            int L = layer[i];
            mx[L] = Math.max(mx[L], fp[i]);
        }
        int[] cum = new int[maxLayer + 1];
        cum[0] = -1;
        for (int L = 1; L <= maxLayer; L++)
            cum[L] = Math.max(cum[L - 1], mx[L - 1]);
        
        ArrayList<Integer> ans = new ArrayList<>();
        for (int p = 1; p <= n; p++) {
            int L = layer[p];
            boolean flag = ln[L] == 1 ? (cum[L] > L) : true;
            if (!flag) ans.add(p);
        }
        Collections.sort(ans);
        out.println(ans.size());
        for (int i = 0; i < ans.size(); i++)
            out.print(ans.get(i) + (i + 1 == ans.size() ? "" : " "));
        out.flush();
        out.close();
    }
    
    static class FastReader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while(c <= ' ') c = read();
            boolean neg = c == '-';
            if(neg) c = read();
            do {
                ret = ret * 10 + c - '0';
                c = read();
            } while(c > ' ');
            return neg ? -ret : ret;
        }
        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, 0, BUFFER_SIZE);
            if(bytesRead == -1) buffer[0] = -1;
            bufferPointer = 0;
        }
    }
}
