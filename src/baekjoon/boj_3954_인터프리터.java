package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_3954_인터프리터 {
    static final int limt_roop =  50000000;
    static final short MAX_VALUE = 255;
    static int sm, sc, si;
    static int[][] matching;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            sm = Integer.parseInt(st.nextToken());
            sc = Integer.parseInt(st.nextToken());
            si = Integer.parseInt(st.nextToken()); 
            short[] memory = new short[sm];
            char[] ip = new String(br.readLine()).toCharArray();
            char[] chars = new String(br.readLine()).toCharArray();
            matching = new int[sc][2];
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < sc; i++) {
                if (ip[i] == '[') {
                    stack.push(i);
                } else if (ip[i] == ']') {
                    int openIndex = stack.pop(); 
                    matching[openIndex][0] = i; 
                    matching[i][0] = openIndex;     
                }
            }
            stack.clear();
            check(memory,ip,chars); 
        }
    }
                
    private static void check(short[] memory, char[] ip, char[] chars) {
        int time = 0;
        int i = 0;
        int index = 0;
        int char_index = 0;
        while (i<sc) {
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
                    if(char_index == si)memory[index] =255;
                    else memory[index] = (short)chars[char_index++];
                    if(memory[index]> MAX_VALUE)memory[index] = (short)((short)memory[index]%(MAX_VALUE+1));
                    i++;
                    break;
                case '[':
                    if (memory[index] == 0){
                        i = matching[i][0]+1;
                    }
                    else{
                        i++;
                    }
                    break;

                case ']':
                    if (memory[index] == 0) {
                        matching[i][1]= 0;
                        i++;
                    }
                    else if(matching[i][1]!=0 && limt_roop<= time - matching[i][1]){
                        System.out.println(String.format("Loops %d %d", matching[i][0], i));
                        return;
                    }
                    else{
                        if (matching[i][1] ==0) {
                            matching[i][1] = time;
                        }
                        i = matching[i][0]+1;
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