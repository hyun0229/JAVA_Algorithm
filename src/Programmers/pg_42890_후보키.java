package Programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class pg_42890_후보키 {
    public static void main(String[] args) {
        Solution_42890 s = new Solution_42890();
        String[][] strings = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},
        {"500","muzi","music","3"}, {"600","apeach","music","2"}
    };
        System.out.println(s.solution(strings));
    }
}
class Solution_42890 {
    boolean[] vis;
    int ans;
    boolean chk_cow[];


    public int solution(String[][] relation) {
        int size = relation[0].length;
        vis = new boolean[size];
        chk_cow = new boolean[size];
        for (int i = 1; i <= size; i++) {
            recursive(0,i,0,relation,new ArrayList<Integer>());
            for (int j = 0; j < relation[0].length; j++) {
                vis[j] = chk_cow[j];
            }
        }

        return ans;

    }
    private void recursive(int lv, int size,int index, String[][] relation,ArrayList<Integer> arr) {
        if (lv == size) {
            if (check(arr,relation)) {
                for (Integer integer : arr) {
                    chk_cow[integer] = true;
                }
                ans++;
            }
        }
        for (int i = index; i < relation[0].length; i++) {
            if(vis[i])continue;
            arr.add(i);
            recursive(lv+1, size, i+1, relation,arr);
            arr.remove(arr.size()-1);
        }

    }
    private boolean check(ArrayList<Integer> arr, String[][] relation) {
        boolean flag = true;
        int row = relation.length;
        int size = arr.size();
        Set<String> chkSet = new HashSet<>();
        StringBuilder sb;
        for (int i = 0; i < row; i++) {
            sb = new StringBuilder();
            for (int j = 0; j < size; j++) {
                sb.append(relation[i][arr.get(j)]);
            }
            if (chkSet.contains(sb.toString())) {
                flag = false;
                break;
            }
            chkSet.add(sb.toString());
        }
        return flag;
    }
}