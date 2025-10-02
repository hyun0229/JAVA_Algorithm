import sys
input = sys.stdin.readline

N, a, b = map(int, input().split())

if a + b - 1 > N:
    print(-1)
    exit()

arr = []
for i in range(1, a):
    arr.append(i)
arr.append(max(a, b))
for i in range(b - 1, 0, -1):
    arr.append(i)

remain = N - len(arr)

if a == 1:
    for _ in range(remain):
        arr.insert(1, 1)
else:
    for _ in range(remain):
        arr.insert(0, 1)

print(" ".join(map(str, arr)))
