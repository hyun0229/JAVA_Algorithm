import sys

input = sys.stdin.readline

s = input().strip()
bomb = input().strip()
m = len(bomb)

stack = []

for ch in s:
    stack.append(ch)

    if len(stack) >= m and ''.join(stack[-m:]) == bomb:
        for _ in range(m):
            stack.pop()

if stack:
    print(''.join(stack))
else:
    print("FRULA")
