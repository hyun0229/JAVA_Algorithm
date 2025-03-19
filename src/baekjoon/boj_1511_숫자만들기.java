package baekjoon;

import java.util.Scanner;

public class boj_1511_숫자만들기 {

    static void solve(int[] arr) {
        int lastNum = -1;
        StringBuilder sb = new StringBuilder();

        while (true) {
            int total = 0;
            for (int i = 0; i < 10; i++) {
                total += arr[i];
            }
            if (total == 0) {
                break;
            }

            int maxIdx = -1;
            int maxCnt = -1;
            int biggestNum = -1;

            for (int i = 0; i < 10; i++) {

                if (arr[i] >= maxCnt) {
                    maxCnt = arr[i];
                    maxIdx = i;
                }
                if (arr[i] > 0 && i != lastNum) {
                    biggestNum = i;
                }
            }
            double ratio = (double) maxCnt / total;
            int selectedNum;
            if (ratio > 0.5) {
                selectedNum = maxIdx;
            } else {
                selectedNum = biggestNum;
            }

            if (selectedNum == -1) {
                break;
            }
            sb.append(selectedNum);
            arr[selectedNum]--;
            lastNum = selectedNum;
        }

        System.out.println(sb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[10];
        int total = 0;

        for (int i = 0; i < 10; i++) {
            arr[i] = sc.nextInt();
            total += arr[i];
        }

        int maxCnt = 0;
        for (int i = 0; i < 10; i++) {
            if (arr[i] > maxCnt) {
                maxCnt = arr[i];
            }
        }
        boolean isAllCard = false;
        if ((total + 1) / 2 >= maxCnt) {
            isAllCard = true;
        }

        if (total % 2 == 1 && (total + 1) / 2 == arr[0]) {
            isAllCard = false;
        }

        if (!isAllCard) {
            if (maxCnt == arr[0]) {
                int sumOthers = 0;
                for (int i = 1; i < 10; i++) {
                    sumOthers += arr[i];
                }
                arr[0] = Math.max(1, sumOthers);
            } else {
                int idxOfMax = 0;
                for (int i = 1; i < 10; i++) {
                    if (arr[i] > arr[idxOfMax]) {
                        idxOfMax = i;
                    }
                }
                arr[idxOfMax] = total - arr[idxOfMax] + 1;
            }
        }

        solve(arr);
    }
}
