package SWEA;

import java.util.Arrays;
import java.util.Scanner;

public class SWEA_14510_나무높이 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T= sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int[] arr = new int[N];
            int day1 = 0;
            int day2 = 0;
            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr);
            int bigtree= arr[N-1];
            for (int i = 0; i < arr.length; i++) {
                int num = bigtree - arr[i];
                int a = (int)(num/3);
                int b = num%3;
                day1 +=a;
                day2 +=a;
                if (b==1) {
                    day1+=1;
                }
                if (b==2) {
                    day2+=1;
                }
            }
            int ans =0;

            if (day1>day2) {
                int num = day1-day2;
                ans = (day1 - (num/2))*2-1;
            }
            else{
                int num = day2-day1;
                ans = (day2 - (num/2))*2;
            }
            System.out.println(String.format("#%d %d",test_case,ans));
            
        }
    }
}
