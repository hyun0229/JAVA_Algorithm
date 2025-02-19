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

    ArrayList<Integer> keyList;
    int ans;
    


    public int solution(String[][] relation) {
        int size = relation[0].length;
        keyList = new ArrayList<>();
        ans = 0;
        for (int i = 1; i <= size; i++) {
            recursive(0,i,0,relation,new ArrayList<Integer>());
        }
        return ans;

    }
    private void recursive(int lv, int size,int index, String[][] relation,ArrayList<Integer> arr) {
        if (lv == size) {
            int key = 0;
            for (int idx : arr) {
                key|=(1<<idx);
            }
            for (int keys : keyList) {
                if ((key&keys)==keys) {
                    return;
                }
            }
            if (check(arr,relation)) {
                keyList.add(key);
                ans++;
            }
            return;
        }
        for (int i = index; i < relation[0].length; i++) {
            arr.add(i);
            recursive(lv+1, size, i+1, relation,arr);
            arr.remove(arr.size()-1);
        }

    }
    private boolean check(ArrayList<Integer> arr, String[][] relation) {
        int row = relation.length;
        int size = arr.size();
        Set<String> chkSet = new HashSet<>();
        StringBuilder sb;
        for (int i = 0; i < row; i++) {
            sb = new StringBuilder();
            for (int j = 0; j < size; j++) {
                sb.append(relation[i][arr.get(j)]);
            }
            chkSet.add(sb.toString());
        }
        return chkSet.size() == row;
    }
}