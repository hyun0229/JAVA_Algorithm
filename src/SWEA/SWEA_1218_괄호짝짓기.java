package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_1218_괄호짝짓기 
{
	public static void main(String args[]) throws Exception
	{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = Integer.parseInt(br.readLine());
            String s = br.readLine();
            Stack<Character> stack = new Stack<>();
            int ans = 1;
            //'()', '[]', '{}', '<>' 
            for (int i = 0; i < N; i++) {
                char now = s.charAt(i);
                if (now == ')' ){
                    if (!stack.isEmpty()&&stack.peek() == now-1)stack.pop();
                    else ans = 0;
                }
                else if (now == ']' || now == '}' || now == '>') {
                    if (!stack.isEmpty()&&stack.peek() == now-2)stack.pop();
                    else ans = 0;
                }
                else stack.add(now);
                if (ans == 0)break;
            }
            if (!stack.isEmpty()) ans =0; 
            System.out.println(String.format("#%d %d", test_case,ans));
		}
	}
}