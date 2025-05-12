import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String,Genre> map = new HashMap<>();
        for(int i = 0 ; i < genres.length ; i++){
            String s = genres[i];
            int v = plays[i];
            if(map.containsKey(s)){
                map.get(s).set(i,v);
            }else{
                Genre genre = new Genre(i,v);
                map.put(s,genre);
            }
        }
        Genre[] arr = new Genre[map.size()];
        int tmp = 0;
        for (Genre g : map.values()) {
            arr[tmp++] = g;
        }
        Arrays.sort(arr);
        ArrayList<Integer> answer = new ArrayList<>();
        for(Genre g : arr){
            answer.add(g.first_idx);
            if(g.second_value == -1)continue;
            answer.add(g.second_idx);
        }
        int[] ans = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            ans[i] = answer.get(i);
        }
        return ans;
    }
    public class Genre implements Comparable<Genre>{
        int sum;
        int first_idx;
        int second_idx;
        int first_value;
        int second_value;
        public Genre(int idx,int value){
            this.sum = value;
            this.first_idx = idx;
            this.first_value = value;
            this.second_value = -1;
        }
        public void set(int idx, int value){
            sum += value;
            if(first_value < value || (first_value == value && first_idx > idx)){
                second_idx = first_idx;
                second_value = first_value;
                first_idx = idx;
                first_value = value;
            }else if(second_value < value ||(second_value == value && second_idx > idx)){
                second_idx = idx;
                second_value = value;
            }
        }
        @Override
        public int compareTo(Genre o) {
            return  o.sum - this.sum;
        }
    }
}