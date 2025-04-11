import java.util.*;

class Solution {
    public Integer[] solution(int[] progresses, int[] speeds) {
        List<Integer> ans = new ArrayList<>();
        int n = progresses.length;

        // 각 작업의 완료일까지 걸리는 날짜 계산
        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
            int remain = 100 - progresses[i];
            days[i] = (remain + speeds[i] - 1) / speeds[i];  // 올림 처리
        }

        int count = 1;
        int prevDay = days[0];

        for (int i = 1; i < n; i++) {
            if (days[i] <= prevDay) {
                count++; // 이전 기능과 함께 배포
            } else {
                ans.add(count); // 이전 그룹 마감
                count = 1;      // 새로운 배포 시작
                prevDay = days[i];
            }
        }

        ans.add(count); // 마지막 그룹 추가

        return ans.toArray(new Integer[0]);
    }
}
