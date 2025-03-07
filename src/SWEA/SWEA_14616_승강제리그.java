package SWEA;

import java.util.Scanner;
import java.util.TreeSet;

public class SWEA_14616_승강제리그 {
    private static Scanner sc;
    private static UserSolution usersolution = new UserSolution();

    private final static int CMD_INIT = 100;
    private final static int CMD_MOVE = 200;
    private final static int CMD_TRADE = 300;

    private static boolean run() throws Exception {

        int query_num = sc.nextInt();
        int ans;
        boolean ok = false;

        for (int q = 0; q < query_num; q++) {
            int query = sc.nextInt();

            if (query == CMD_INIT) {
                int N = sc.nextInt();
                int L = sc.nextInt();
                int mAbility[] = new int[N];
                for (int i = 0; i < N; i++) {
                    mAbility[i] = sc.nextInt();
                }
                usersolution.init(N, L, mAbility);
                ok = true;
            } else if (query == CMD_MOVE) {
                int ret = usersolution.move();
                ans = sc.nextInt();
                if (ans != ret) {
                    ok = false;
                }
            } else if (query == CMD_TRADE) {
                int ret = usersolution.trade();
                ans = sc.nextInt();
                if (ans != ret) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        int T, MARK;

        System.setIn(new java.io.FileInputStream("src\\SWEA\\sample_input.txt"));
        sc = new Scanner(System.in);
        T = sc.nextInt();
        MARK = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int score = run() ? MARK : 0;
            System.out.println("#" + tc + " " + score);
        }
        sc.close();
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}

class UserSolution {
    Leauge[] leauges;
    int N, L;

    void init(int N, int L, int mAbility[]) {
        this.N = N;
        this.L = L;
        leauges = new Leauge[L];
        for (int i = 0; i < L; i++) {
            leauges[i] = new Leauge();
        }
        int num = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < N / L; j++) {
                leauges[i].add(new Player(num, mAbility[num]));
                // System.out.println(num);
                num++;
                // debug();
            }
        }
    }

    // void debug(){
    // for (Leauge leauge : leauges) {
    // if (!leauge.front.isEmpty()){
    // for (Player p : leauge.front) {
    // System.out.print(p.id+" ");
    // }
    // }
    // if (leauge.mid != null) {
    // System.out.print(leauge.mid.id+" ");
    // }
    // if(!leauge.back.isEmpty()){
    // for (Player p : leauge.back) {
    // System.out.print(p.id+" ");
    // }
    // }
    // }
    // System.out.println();
    // }
    int move() {
        int sum = 0;
        Player fistPlayers[] = new Player[L - 1];
        Player lastPlayers[] = new Player[L - 1];
        for (int i = 0; i < leauges.length - 1; i++) {
            fistPlayers[i] = leauges[i + 1].popfirst();
            lastPlayers[i] = leauges[i].poplast();
            sum += fistPlayers[i].id + lastPlayers[i].id;
        }
        for (int i = 0; i < leauges.length - 1; i++) {
            leauges[i].add(fistPlayers[i]);
            leauges[i + 1].add(lastPlayers[i]);
        }
        // System.out.println(sum);
        // debug();
        return sum;
    }

    int trade() {
        int sum = 0;
        Player fistPlayers[] = new Player[L - 1];
        Player midPlayers[] = new Player[L - 1];
        for (int i = 0; i < leauges.length - 1; i++) {
            midPlayers[i] = leauges[i].popMid();
            fistPlayers[i] = leauges[i + 1].popfirst();
            sum += fistPlayers[i].id + midPlayers[i].id;
        }
        for (int i = 0; i < leauges.length - 1; i++) {
            leauges[i].add(fistPlayers[i]);
            leauges[i + 1].add(midPlayers[i]);
        }
        // System.out.println(sum);
        // debug();
        return sum;
    }

    static class Leauge {
        TreeSet<Player> front, back;
        Player mid;

        Leauge() {
            front = new TreeSet<>();
            back = new TreeSet<>();
        }

        public void add(Player player) {
            if (front.isEmpty() && back.isEmpty() && mid == null)
                mid = player;
            else if (front.size() == back.size()) {
                if (mid.compareTo(player) == 1)
                    front.add(player);
                else
                    back.add(player);
            } else if (front.size() > back.size()) {
                if (mid.compareTo(player) == 1) {
                    front.add(player);
                    Player num = front.last();
                    back.add(mid);
                    mid = num;
                    front.remove(num);
                } else
                    back.add(player);
            } else {
                if (mid.compareTo(player) == 1)
                    front.add(player);
                else {
                    back.add(player);
                    Player num = back.first();
                    front.add(mid);
                    mid = num;
                    back.remove(num);
                }
            }

        }

        public Player popfirst() {
            return remove(0);
        }

        public Player popMid() {
            return remove(1);
        }

        public Player poplast() {
            return remove(2);
        }

        public Player remove(int flag) {
            if (flag == 0) {
                Player num = front.first();
                front.remove(num);
                return num;
            }
            if (flag == 1) {
                Player num = mid;
                mid = back.first();
                back.remove(mid);
                return num;
            } else {
                Player num = back.last();
                back.remove(num);
                return num;
            }
        }

    }

    static class Player implements Comparable<Player> {
        int id, ability;

        Player(int id, int ability) {
            this.id = id;
            this.ability = ability;
        }

        @Override
        public int compareTo(Player o) {
            if (this.ability == o.ability) {
                return Integer.compare(this.id, o.id);
            }
            return Integer.compare(o.ability, this.ability);
        }

    }
}
