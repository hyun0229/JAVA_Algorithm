class Solution {
    public String solution(String number, int k) {
        int slen = number.length();
        int anslen = slen - k;
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<slen;i++){
            char tmp = number.charAt(i);
            while(k!=0&&sb.length()!=0&&tmp-sb.charAt(sb.length() - 1)>0){
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            sb.append(tmp);
        }
        while(anslen<sb.length()){
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}