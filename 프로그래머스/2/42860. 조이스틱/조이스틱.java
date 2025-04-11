import java.util.ArrayDeque;
class Solution {
    int answer,len;
    ArrayDeque<Integer> dq;
    int sLength;
    public int solution(String name) {
        answer = 0;
        len = Integer.MAX_VALUE;
        sLength = name.length();
        dq = new ArrayDeque<>();
        for (int i = 0; i < sLength; i++) {
            char tmp = name.charAt(i);
            if (i!=0&&tmp!='A') {
                dq.add(i);
            }
            answer += Math.min(91-tmp, tmp-'A');
        }
        recursive(0,0);
        return answer+len;
    }
    private void recursive(int now,int rLength) {
        if (dq.isEmpty()) {
            len = Math.min(rLength, len);
            return;
        }
        int num = 0;
        int tmp = 0;
        num = dq.pollFirst();
        if (num<now)tmp = sLength - now +num;
        else tmp = num - now;
        recursive(num, rLength+tmp);
        dq.addFirst(num);
        num = dq.pollLast();
        if (num<now)tmp = now-num;
        else tmp = now+(sLength-num);
        recursive(num, rLength+tmp);
        dq.addLast(num);
    }
}
