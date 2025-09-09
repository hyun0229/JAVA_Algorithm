import sys
input = sys.stdin.readline

N = int(input())
heights = []

for _ in range(N):
    x, y = map(int, input().split())
    heights.append(y)

stack = [0]
answer = 0

for y in heights:
    while stack and y < stack[-1]:
        stack.pop()
        answer += 1
    if not stack or y != stack[-1]:
        stack.append(y)

while stack:
    h = stack.pop()
    if h != 0:
        answer += 1

print(answer)
