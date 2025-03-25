package Programmers;

import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class pg_42628_이중우선순위큐 {
    class Solution {
        public int[] solution(String[] operations) {
            int[] answer = new int[2];
            PriorityQueue<node> minpq = new PriorityQueue<>();
            PriorityQueue<node> maxpq = new PriorityQueue<>(Collections.reverseOrder());
            Set<String> set = new HashSet<>();
            for (int i = 0; i < operations.length; i++) {
                String[] parts = operations[i].split(" ");
                String order = parts[0];
                String num = parts[1];
                if (order.equals("I")) {
                    String key = i + "," + num;
                    node tmp = new node(Integer.parseInt(num), key);
                    minpq.add(tmp);
                    maxpq.add(tmp);
                } else {
                    if (num.equals("-1")) {
                        while (!minpq.isEmpty()) {
                            node tmp = minpq.poll();
                            if (!set.contains(tmp.key)) {
                                set.add(tmp.key);
                                break;
                            }
                        }
                    }
                    if (num.equals("1")) {
                        while (!maxpq.isEmpty()) {
                            node tmp = maxpq.poll();
                            if (!set.contains(tmp.key)) {
                                set.add(tmp.key);
                                break;
                            }
                        }
                    }
                }
            }
            while (!maxpq.isEmpty()) {
                node tmp = maxpq.poll();
                if (!set.contains(tmp.key)) {
                    answer[0] = tmp.num;
                    break;
                }
            }
            while (!minpq.isEmpty()) {
                node tmp = minpq.poll();
                if (!set.contains(tmp.key)) {
                    answer[1] = tmp.num;
                    break;
                }
            }
            return answer;
        }

        static class node implements Comparable<node> {
            int num;
            String key;

            public node(int num, String key) {
                this.num = num;
                this.key = key;
            }

            @Override
            public int compareTo(node o) {
                return Integer.compare(this.num, o.num);
            }
        }
    }

}
