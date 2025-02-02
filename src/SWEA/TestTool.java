package SWEA;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TestTool {
    public static void test_map(Object arr) {
        // 배열인지 확인
        if (!arr.getClass().isArray()) {
            throw new IllegalArgumentException("입력값이 배열이 아닙니다.");
        }

        int rows = Array.getLength(arr);  // 배열의 행 개수

        for (int i = 0; i < rows; i++) {
            Object row = Array.get(arr, i);  // 한 행(row) 가져오기
            // char[][] 같은 기본형 배열인지 확인
            if (row instanceof char[]) {
                System.out.println(Arrays.toString((char[]) row));
            } else if (row instanceof int[]) {
                System.out.println(Arrays.toString((int[]) row));
            } else if (row instanceof double[]) {
                System.out.println(Arrays.toString((double[]) row));
            } else if (row instanceof Object[]) {
                System.out.println(Arrays.deepToString((Object[]) row));
            } else {
                System.out.println(row);
            }
        }
    }

}
