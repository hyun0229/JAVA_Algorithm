package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
            node[] nodes = new node[time+1];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = new node();
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
                            udpatetime(entry.getKey(),entry.getValue(),nodes[entry.getKey()],power);
                        }
                    }
                }
            }
            int ans = 0;
            int a= 0;
            int b =0;
            for (node node : nodes) {
                System.out.print(node.a+" ");
                a+= node.a;
            }
            System.out.println();
            for (node node : nodes) {
                System.out.print(node.b+" ");
                b+= node.b;
            }
            System.out.println();
            System.out.println(a);
            System.out.println(b);
            System.out.println(a+b);
		}
	
    }
    private static void udpatetime(Integer time, Integer flag, node node,int power) {
        if (!node.flag) {
            if (flag == 0) {
                if (node.a < power) {
                    node.a = power;
                    return;
                }
            }
            if (flag == 1) {
                if (node.b < power) {
                    node.b = power;
                    return;
                }
            }
            if (flag == 2) {
                if (node.a + node.b < power) {
                    node.a = power/2;
                    node.b = power/2;
                    node.flag = true;
                    return;
                }
                if (node.a < node.b) {
                    if (node.b<power) {
                        node.b = power;
                    }
                    else if(node.a<power){
                        node.a = power;
                    }
                    return;
                }
                if (node.a > node.b) {
                    if (node.a<power) {
                        node.a = power;
                    }
                    else if(node.b<power){
                        node.b = power;
                    }
                    return;
                }
            }
        }
        else{
            if (flag == 0) {
                if (node.a < power) {
                    node.a = power;
                    node.b *=2;
                    node.flag = false;
                    return;
                }
            }
            if (flag == 1) {
                if (node.b < power) {
                    node.b = power;
                    node.a *=2;
                    node.flag = false;
                    return;
                }
            }
            if (flag == 2) {
                if (node.a + node.b < power) {
                    node.a = power/2;
                    node.b = power/2;
                    return;
                }
            }
        }
    }
    static class node{
        int a,b;
        boolean flag;
        node(){
            this.a = 0;
            this.b = 0;
            this.flag = true;
        }
    }
}
