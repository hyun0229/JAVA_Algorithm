class Solution
{
    int N,M;
    public int solution(int [][]board)
    {
        int max = 0;
        N = board.length;
        M = board[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]==0)continue;
                int num = 1;
                if(!(i==0||j==0))num = Math.min(Math.min(board[i-1][j], board[i][j-1]),board[i-1][j-1])+1;
                max = Math.max(max, num);
                board[i][j] = num;
            }
        }
        return max*max;
    }
}