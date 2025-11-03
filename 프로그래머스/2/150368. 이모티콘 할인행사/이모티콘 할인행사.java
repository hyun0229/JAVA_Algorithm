import java.util.*;

class Solution {
    static int[] discounts = {10, 20, 30, 40};
    static int maxSubscriber = 0;
    static int maxRevenue = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        int[] selected = new int[emoticons.length];
        dfs(0, users, emoticons, selected);
        return new int[]{maxSubscriber, maxRevenue};
    }

    private void dfs(int depth, int[][] users, int[] emoticons, int[] selected) {
        if (depth == emoticons.length) {
            calculate(users, emoticons, selected);
            return;
        }

        for (int d : discounts) {
            selected[depth] = d;
            dfs(depth + 1, users, emoticons, selected);
        }
    }

    private void calculate(int[][] users, int[] emoticons, int[] selected) {
        int subscriber = 0;
        int revenue = 0;

        for (int[] user : users) {
            int discountThreshold = user[0];
            int priceThreshold = user[1];
            int total = 0;

            for (int i = 0; i < emoticons.length; i++) {
                if (selected[i] >= discountThreshold) {
                    total += emoticons[i] * (100 - selected[i]) / 100;
                }
            }

            if (total >= priceThreshold) {
                subscriber++;
            } else {
                revenue += total;
            }
        }

        if (subscriber > maxSubscriber) {
            maxSubscriber = subscriber;
            maxRevenue = revenue;
        } else if (subscriber == maxSubscriber && revenue > maxRevenue) {
            maxRevenue = revenue;
        }
    }
}
