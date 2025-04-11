from collections import deque
import sys

input = sys.stdin.readline
n,m = map(int, input().split())
ls = list(map(int,input().split()))
chk_dq = deque()
chk_dq.append((ls[0],0))
print(ls[0],end=' ')
for i in range(1,n):
    while chk_dq and chk_dq[-1][0] > ls[i]:
        chk_dq.pop()
    chk_dq.append((ls[i],i))
    if chk_dq[0][1] <= i-m and i-m >=0:
        chk_dq.popleft()
    
    print(chk_dq[0][0],end=' ')
