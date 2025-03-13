package baekjoon;

import java.util.ArrayDeque;
import java.util.Deque;

import java.util.Scanner;

public class boj_1511_숫자만들기 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[10];
        int cnt = 0;
        for (int i = 0; i < 10; i++) {
            arr[i] = sc.nextInt();
            cnt+= arr[i];
        }
        for (int i = 0; i < 10; i++) {
            
        }


    }
    private static int max_num(int[] arr, int before) {
        for (int i = 9; i >= 0; i--) {
            if(arr[i]!=0 && i!=before){
                return i;
            }
        }
        return -1;
    }
}
