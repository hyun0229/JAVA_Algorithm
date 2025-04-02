package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


public class SWEA_2383_점심시간 {

    static int ans = 0,N;
    static ArrayList<int[]> peoples;
    static Node stairs[];
    
    static class Node {
        int x,y,k;
        public Node(int x, int y, int k) {
            this.x= x; this.y = y; this.k = k;
        }
    }

    public static void main(String args[]) throws Exception
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T;
        T = Integer.parseInt(st.nextToken());
        
        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            ans = Integer.MAX_VALUE;
            N  = Integer.parseInt(st.nextToken());
            peoples = new ArrayList<>();
            stairs = new Node[2];
            int num = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    if(x==0) continue;
                    if (x == 1)peoples.add(new int[]{i,j});
                    else stairs[num++] = new Node(i, j, x);
                }
            }
            ArrayList<Node>[] arr = new ArrayList[2];
            arr[0] = new ArrayList<>();
            arr[1] = new ArrayList<>();
            recursive(0,peoples.size(),arr);
            System.out.println(String.format("#%d %d", test_case,ans));
        }
    }

    private static void recursive(int lv, int size,ArrayList<Node>[] arr) {
        if(lv == size) {
            int num =getTime(arr);
//            if (num == 8) {
//                for (ArrayList<Node> arrayList : arr) {
//                    for (Node a : arrayList) {
//                        System.out.print(a.x+ " "+a.y+" "+a.k +"/");
//                    }
//                    System.out.println("\n_________________");
//                }
//            }
            ans = Math.min(ans, num);
            return;
        }
        int[] people = peoples.get(lv);
        for (int i = 0; i < 2; i++) {
            Node node = new Node(people[0], people[1], 0); 
            node.k = Math.abs(stairs[i].x-node.x) + Math.abs(stairs[i].y-node.y)+1;
            arr[i].add(node);
            recursive(lv+1, size, arr);
            arr[i].remove(arr[i].size()-1);
        }
        
    }

    private static int getTime(ArrayList<Node>[] arr) {
        int k = 0;
        int cnt = peoples.size();
        Queue<Node>[] q= new ArrayDeque[2];
        for (int i = 0; i < 2; i++) {
            q[i] = new ArrayDeque<>();
            for (int j = 0; j < arr[i].size(); j++) {
                Node n = new Node(arr[i].get(j).x, arr[i].get(j).y, arr[i].get(j).k);
                q[i].add(n);
            }
            
        }
        Queue<Node>[] staq= new ArrayDeque[2];
        staq[0] = new ArrayDeque<>();
        staq[1] = new ArrayDeque<>();
        
        while (cnt!=0) {
            k++;
            for (int i = 0; i < 2; i++) {
                
                int sizeq = staq[i].size();
                for (int j = 0; j < sizeq; j++) {
                    Node n = staq[i].poll();
                    n.k -=1;
                    if (n.k <= 0) {
                        cnt--;
                    }
                    else staq[i].add(n);
                }
                int size = q[i].size();
                for (int j = 0; j < size; j++) {
                    Node n = q[i].poll();
                    n.k -=1;
                    if (n.k <= 0 && staq[i].size()<3 ) {
                        n.k = stairs[i].k;
                        staq[i].add(n);
                    }
                    else q[i].add(n);
                }
            }
        }
        
        return k;
    }
}
