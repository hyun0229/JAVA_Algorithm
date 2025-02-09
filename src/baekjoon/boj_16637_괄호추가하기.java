package baekjoon;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class boj_16637_괄호추가하기 {
    static int ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = (int)(sc.nextInt()/2);
        sc.nextLine();             // 남은 개행 문자 소비
        String s = sc.nextLine();
        Stack<Character> sts = new Stack<>();
        sts.push(s.charAt(0));
        ans = Integer.MIN_VALUE;
        recursive(0,N,s,sts,true);
        System.out.println(ans);
    }
    private static void recursive(int i, int n, String s, Stack<Character> stack,boolean flag) {
        if (i == n) {
            Stack<Integer> num = new Stack<>();
            for (Character c : stack) {
                int x,y;
                switch (c) {
                    case '+':
                    y = num.pop();
                    x = num.pop();
                    num.push(x+y);
                        break;
                    case '-':
                    y = num.pop();
                    x = num.pop();
                    num.push(x-y);
                        break;    
                    case '*':
                    y = num.pop();
                    x = num.pop();
                    num.push(x*y);
                        break;
                    default:
                        num.push(c-'0');
                        break;
                }
            }
            ans = Math.max(num.peek(), ans);
            return;
            
        }
        int x = i*2+1;
        i++;

        if (flag) {
            stack.push(s.charAt(x+1));
            stack.push(s.charAt(x));
            recursive(i, n, s, stack, false);
            stack.pop();
            stack.pop();
        }
        else{

            stack.push(s.charAt(x+1));
            stack.push(s.charAt(x));
            recursive(i, n, s, stack, false);
            stack.pop();
            stack.pop();
            char p = stack.pop();
            stack.push(s.charAt(x+1));
            stack.push(s.charAt(x));
            stack.push(p);
            recursive(i, n, s, stack, true);
            stack.pop();
            stack.pop();
            stack.pop();
            stack.push(p);
        }
        

    }

}
