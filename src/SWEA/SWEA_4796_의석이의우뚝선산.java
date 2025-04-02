package SWEA;

import java.util.Scanner;

public class SWEA_4796_의석이의우뚝선산 {
    public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            int up = 0;
            int down = 0;
            int tmp = -1;
            boolean flag = false;
            int ans = 0;
            for (int i = 0; i < N; i++) {
                if (tmp == -1 )tmp = sc.nextInt();
                else{
                    int now = sc.nextInt();
                    if (flag&& tmp<now) {
                        ans += down*up;
                        up = 1;
                        down = 0;
                        flag = false;
                    }
                    else if (tmp >now){
                        down++;
                        flag = true;
                    }
                    else if(!flag && tmp <now)up++;
                    tmp = now;
                }
            }
            ans+=down * up;
            System.out.println(String.format("#%d %d",test_case, ans));
		}
	}

}
