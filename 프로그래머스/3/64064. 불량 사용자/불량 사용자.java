import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    int ans;
    boolean vis[];
    HashSet<Integer> chkHashSet;

    public int solution(String[] user_id, String[] banned_id) {
        int size = banned_id.length;
        vis = new boolean[user_id.length];
        ans = 0;
        chkHashSet = new HashSet<>();
        recursive(0,size,user_id,banned_id,new ArrayList<Integer>(),0);
        return ans;
    }
    private void recursive(int lv,int size, String[] user_id, String[] banned_id,ArrayList<Integer> arr,int sum) {
        if (lv == size) {
            if (chkHashSet.contains(sum))return;
            for (int i = 0; i < size; i++) {
                if (!check(user_id[arr.get(i)],banned_id[i]))return;
            }
            ans++;
            // for (Integer integer : arr) {
            //     System.out.print(user_id[integer]+", ");
            // }
            // System.out.println();
            chkHashSet.add(sum);
            return;
        }
        for (int i = 0; i < user_id.length; i++) {
            if (!vis[i]) {
                vis[i] = true;
                arr.add(i);
                int num = 1<<i;
                recursive(lv+1, size, user_id,banned_id,arr,sum+num);
                arr.remove(arr.size()-1);
                vis[i] = false;
            }
        }
    }
    private boolean check(String id, String banid) {
        if (id.length()!=banid.length()) {
            return false;
        }
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) != banid.charAt(i) &&banid.charAt(i) !='*')return false;
        }
        //System.out.println(id+ " "+banid);
        return true;
    }
}