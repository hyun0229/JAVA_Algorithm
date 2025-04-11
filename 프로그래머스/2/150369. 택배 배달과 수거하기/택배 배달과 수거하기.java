import java.util.ArrayDeque;
import java.util.Queue;


class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        Queue<Integer> devq = new ArrayDeque<>();
        Queue<Integer> pickq = new ArrayDeque<>();
        chkq(devq,cap,n,deliveries);
        chkq(pickq,cap,n,pickups);
        //System.out.println(devq.size());
        // for (Integer integer : devq) {
        //     System.out.print(integer+" ");
        // }
        // System.out.println();
        // for (Integer integer : pickq) {
        //     System.out.print(integer+" ");
        // }
        while (!(devq.isEmpty()&&pickq.isEmpty())) {
            if (devq.isEmpty()) {
                answer += (pickq.poll()+1)*2;
            }
            else if (pickq.isEmpty()) {
                answer += (devq.poll()+1)*2;
            }
            else{
                int num = Math.max(pickq.poll()+1, devq.poll()+1);
                answer+= num*2;
            }
        }



        return answer;
    }
    private void chkq(Queue<Integer> q, int cap, int N, int[] arr) {
        int p1 = N-1;
        int p2 = N-1;

        while (p1!=0||p2!=0) {
            if ((p1==p2||arr[p1]==0)&&p1!=0){
                p1--;
                continue;
            }
            if (p1 == 0&&arr[p1]==0) {
                if (arr[p2]>0) {
                    q.add(p2);
                    arr[p2] -=cap;
                }
                else{
                    break;
                }
            }
            if (arr[p2]==0)p2 = p1;
            else{
                if (arr[p2]>=cap) {
                    q.add(p2);
                    arr[p2]-=cap;
                }
                else if(arr[p1] + arr[p2]>=cap){
                    q.add(p2);
                    arr[p1] -= (cap-arr[p2]);
                    arr[p2] = 0;
                }
                else{
                    arr[p2] += arr[p1];
                    arr[p1] = 0;
                }
            }
        }
        if (arr[0]>0) {
            while (arr[0]>0) {
                q.add(0);
                arr[0]-=cap;
            }
        }
    }
}