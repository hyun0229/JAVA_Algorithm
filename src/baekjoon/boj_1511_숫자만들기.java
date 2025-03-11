package baekjoon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class boj_1511_숫자만들기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[10];
        for (int i = 0; i < args.length; i++) {
            arr[i] = sc.nextInt();
        }
        Deque<Integer> dq = new ArrayDeque<>();
        int point = 9;
        for (int i = 9; i >= 0; i--) {
            if (arr[point]!=0) {
                dq.add(point);
                arr[point]--;
                break;
            }
        }
        while (true) {
            if (dq.peekLast() == point) {
                
            }
        }
    }
}
