class Solution {
    public String solution(String number, int k) {
        int slen = number.length();
        int anslen = slen - k;
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<slen;i++){
            char tmp = number.charAt(i);
            //k만큼 숫자를 빼야함 + 맨 앞지리 숫자가 제일 크면 큰수임
            //그래서 맨 앞자리 숫자를 큰수를 유지하기위해 숫자가 들어면 비교해서 맨 뒷자리 부터 빼버림
            //단 k가 0이 될 경우 멈춰!
            while(k!=0&&sb.length()!=0&&tmp-sb.charAt(sb.length() - 1)>0){
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            //앞에 나보다 작은 숫자는 다 제거했으니 내가 들어감
            sb.append(tmp);
        }
        //k가 0이 아닌데 끝났네?? 그럼 맨 뒤에서부터 number의 길이 - k가 답의 길이일테니 뒤에서부터 제가함
        while(anslen<sb.length()){
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}