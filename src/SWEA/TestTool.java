package SWEA;

public class TestTool {
    
    public static void main(String[] args) {
        // 배열인지 확인
        int N = 8;
        long[] apt = new long[N+1];
        apt[0] = 1;
        apt[1] = 1;
        for (int i = 2; i < N+1; i++) {
            apt[i] = apt[i-1] + apt[i-2];
        }
        System.out.println("아파트 "+ apt[N]);
        N = 6;
        long stick[] = new long[N+1];
        stick[0] = 1;
        stick[1] = 2;
        for (int i = 2; i < N+1; i++) {
            stick[i] = 2*stick[i-1] + stick[i-2];
        }
        System.out.println("막대기 " + stick[N]);
    }
}