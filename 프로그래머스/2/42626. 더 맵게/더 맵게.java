import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : scoville) {
            pq.add(i);
        }
        while (pq.size()!=1) {
            if (pq.peek()>=K) {
                break;
            }
            int a = pq.poll();
            int b = pq.poll();
            pq.add(a+(b*2));
            answer++;
        }
        if (pq.size() ==1 && pq.peek()<K) {
            return -1;
        }
        return answer;
    }
}