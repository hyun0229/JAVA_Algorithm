import sys
from collections import deque

input = sys.stdin.readline
N,K = map(int, input().split())  
arr = list(map(int, input().split()))

check = [0] * 100001  
q = deque()
ans = 0
for i in arr:
    q.append(i)
    check[i] += 1
    if check[i] > K:
        while check[i] > K:
            check[q.popleft()] -= 1
    ans = max(ans, len(q))
print(ans)
