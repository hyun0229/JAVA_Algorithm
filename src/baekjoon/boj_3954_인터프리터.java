package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_3954_인터프리터 {
    static final long limt_roop =  50000000;
    static final long MAX_VALUE = (long) Math.pow(2, 8);
    static int sm, sc, si;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            sm = Integer.parseInt(st.nextToken());
            sc = Integer.parseInt(st.nextToken());
            si = Integer.parseInt(st.nextToken()); 
            long[] memory = new long[sm];
            char[] ip = new String(br.readLine()).toCharArray();
            char[] chars = new String(br.readLine()).toCharArray();
            check(memory,ip,chars); 
        }
    }
                
    private static void check(long[] memory, char[] ip, char[] chars) {
        long time = 0;
        int i = 0;
        int index = 0;
        int char_index = 0;
        Stack<long[]> stackL = new Stack<>();
        //Stack<long[]> stackR = new Stack<>();
        while (i!=sc) {
            switch (ip[i]) {
                case '-':
                    memory[index]--;
                    if(memory[index]== -1)memory[index] = MAX_VALUE;
                    i++;
                    break;
                case '+':
                memory[index]++;
                    if(memory[index]== MAX_VALUE+1)memory[index] = 0;
                    i++;
                    break;
                case '<':
                    index--;
                    if(index == -1)index = sm-1;
                    i++;
                    break;
                case '>':
                    index++;
                    if(index == sm)index = 0;
                    i++;
                    break;
                case ',':
                    if(char_index == si)memory[index] +=255;
                    else memory[index] += chars[char_index++];
                    if(memory[index]> MAX_VALUE)memory[index] = memory[index]%MAX_VALUE-1;
                    i++;
                    break;
                case '[':
                    if (memory[index] == 0){
                        while (true){
                            if (ip[i++] == ']')break;
                        }
                    }
                    else{
                        if (stackL.isEmpty() || stackL.peek()[0] != i) {
                            stackL.add(new long[]{i,-1});
                            i++;
                        }
                    }
                    break;
                case ']':
                    if (memory[index] == 0) {
                        i++;
                        stackL.pop();
                    }
                    else if(stackL.peek()[1] !=-1 && limt_roop<= time - stackL.peek()[1]){
                        System.out.println(String.format("Loops %d %d", stackL.peek()[0], i));
                        return;
                    }
                    else{
                        i = (int) stackL.peek()[0]+1;
                        if(stackL.peek()[1]==-1){
                            stackL.pop();
                            stackL.add(new long[]{i-1,time});
                        }
                    }
                    break;
                
                default:
                    i++;
                    break;
            }
            time++;
        }
        System.out.println("Terminates");
    }
}