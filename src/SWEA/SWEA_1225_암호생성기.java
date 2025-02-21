package SWEA;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_1225_암호생성기
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int test = scanner.nextInt(); 
            Queue<Integer> queue = new ArrayDeque<>();

            for (int i = 0; i < 8; i++) {
                queue.add(scanner.nextInt());
            }

            int dec = 1; // 감소값 초기화
            while (true) {
                int tmp = queue.poll() - dec; 
                dec++; 
                if (dec > 5) {
                    dec = 1; 
                }

                if (tmp < 1) {
                    queue.add(0);
                    break;
                } else {
                    queue.add(tmp);
                }
            }

            System.out.print("#" + test_case);
            for (int num : queue) {
                System.out.print(" " + num);
            }
            System.out.println();
        }

        scanner.close();
    }
}