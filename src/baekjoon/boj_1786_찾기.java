package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj_1786_찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] text = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();
        int tLnegth = text.length;
        int pLnegth = pattern.length;
        int pi[] = new int[pLnegth];
        for (int i = 1, j = 0; i < pLnegth; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j-1];
            }
            if (pattern[i] == pattern[j]) {
                pi[i] = j+1;
                j++;
            }
        }
        ArrayList<Integer> ls = new ArrayList<>();
        int cnt = 0;
        for (int i = 0,j=0; i < tLnegth; i++) {
            while (j > 0 && text[i] != pattern[j]) {
                j = pi[j-1];
            }
            if (text[i] == pattern[j]) {
                if (j == pLnegth - 1) {
                    cnt++;
                    ls.add(i - j + 1);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append('\n');
        for (Integer integer : ls) {
            sb.append(integer).append(' ');
        }
        System.out.println(sb.toString());
    }
}
