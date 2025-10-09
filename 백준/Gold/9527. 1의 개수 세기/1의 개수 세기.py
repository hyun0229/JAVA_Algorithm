import sys
input = sys.stdin.readline

d = [0] * 60
d[0] = 1
for i in range(1, 60):
    d[i] = d[i - 1] * 2 + (1 << i)

def count_ones_up_to(x):
    if x < 0:
        return 0
    res = x & 1
    for i in range(59, 0, -1):
        if x & (1 << i):
            res += d[i - 1] + (x - (1 << i) + 1)
            x -= (1 << i)
    return res

A, B = map(int, input().split())
print(count_ones_up_to(B) - count_ones_up_to(A - 1))
