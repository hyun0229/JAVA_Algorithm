
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {
    ArrayList<String> ansls;
    HashMap<String,Integer> chk;
    int max = 0;
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        for (int i = 0; i < orders.length; i++) {
            char[] sort = orders[i].toCharArray();
            Arrays.sort(sort);
            orders[i] = new String(sort);
        }
        PriorityQueue<String> pq = new PriorityQueue<>();
        for (int i = 0; i < course.length; i++) {
            ansls = new ArrayList<>();
            int c_size = course[i];
            chk = new HashMap<>();
            max = 0;
            for (String order : orders) {
                if(order.length()<c_size) continue;
                recursive(0,c_size,new ArrayList<Character>(),order,0);
            }
            if(max==1) continue;
            for (String string : ansls) {
                pq.add(string);
            }
        }
        answer = new String[pq.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = pq.poll();
        }
        return answer;
    }
    private void recursive(int lv, int c_size, ArrayList<Character> arr,String order, int index) {
        if (lv == c_size) {
            StringBuilder sb = new StringBuilder();
            for (Character character : arr) {
                sb.append(character);
            }
            String num = sb.toString();
            chk.merge(num, 1, Integer::sum);

            if (max<chk.get(num)) {
                max = chk.get(num);
                ansls.clear();
                ansls.add(num);
            }
            else if (max == chk.get(num)) {
                ansls.add(num);
            }
            return;
        }
        for (int i = index; i < order.length(); i++) {
            arr.add(order.charAt(i));
            recursive(lv+1, c_size, arr, order, i+1);
            arr.remove(arr.size()-1);
        }
    }

}