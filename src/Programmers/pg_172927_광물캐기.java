package Programmers;

import java.util.PriorityQueue;

public class pg_172927_광물캐기 {
    public static void main(String[] args) {
        Solution_172927 s = new Solution_172927();
        int[] arr1 = {0, 1, 1};

        String[] arr2 = {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};

        System.out.println(s.solution(arr1, arr2));
    }
}


class Solution_172927 {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int N = minerals.length%5 == 0 ? minerals.length/5 : minerals.length/5 +1;
        int pick_num = (picks[0]+picks[1]+picks[2])*5;
        int num = 0;
        int arrs=0;
        int arri=0;
        int arrd=0;
        PriorityQueue<Mine> pq = new PriorityQueue<>();
        boolean flag =true;
        for (String string : minerals) {

            if (num%5 == 0 && num!=0) {
                pq.add(new Mine(arrd, arri, arrs));
                arrs=0;arri=0;arrd=0;
            }
            if (num == pick_num) {
                flag = false;
                break;
            }
            if (string.length() == 7){
                arrs+=25;
                arri+=5;
                arrd+=1;
            }
            else if(string.length() == 4){
                arrs+=5;
                arri+=1;
                arrd+=1;
            }
            else{
                arrs+=1;
                arri+=1;
                arrd+=1;
            }
            num++;
        }
        if (flag) {
            pq.add(new Mine(arrd, arri, arrs));
        }
        while (!pq.isEmpty()) {
            Mine m = pq.poll();
            System.out.println(m.d+" "+m.i +" "+m.s +" ");
            if (picks[0] != 0){
                picks[0]--;
                answer+=m.d;
            }
            else if (picks[1] != 0) {
                picks[1]--;
                answer+=m.i;
            }
            else{
                answer+=m.s;
            }
        }

        return answer;
    }
    public class Mine implements Comparable<Mine>{
        int d,i,s;
        Mine(int d, int i, int s){
            this.d = d;
            this.i = i;
            this.s = s;
        }
        @Override
        public int compareTo(Mine o) {
            return Integer.compare(o.s,this.s);
        }        
    }
}