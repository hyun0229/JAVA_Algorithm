import sys
from collections import Counter, defaultdict


input = sys.stdin.readline
N = int(input().strip())
L = list(map(int, input().split()))

cnt = Counter(L)              # 길이별 개수
vals = sorted(cnt.keys())     # 서로 다른 길이들
sum_area = defaultdict(int)   # S(=a+b) -> 넓이합

K = len(vals)
for i in range(K):
    a = vals[i]
    ca = cnt[a]

    # a == b 인 경우: 같은 길이끼리 묶기
    pairs_same = ca // 2
    if pairs_same:
        S = a + a
        sum_area[S] += pairs_same * a * a

    # a < b 인 경우
    for j in range(i + 1, K):
        b = vals[j]
        pairs = ca if ca < cnt[b] else cnt[b]   # min(cnt[a], cnt[b])
        if pairs:
            S = a + b
            sum_area[S] += pairs * a * b

print(max(sum_area.values(), default=0))

