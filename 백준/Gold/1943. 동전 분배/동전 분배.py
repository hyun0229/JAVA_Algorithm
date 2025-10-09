import sys
input = sys.stdin.readline

def can_divide(coins):
    total = 0
    for v, c in coins:
        total += v * c
    if total % 2 == 1:
        return False
    target = total // 2
    dp = [False] * (target + 1)
    dp[0] = True
    for value, count in coins:
        k = 1
        while count > 0:
            use = min(k, count)
            cost = value * use
            for x in range(target, cost - 1, -1):
                if dp[x - cost]:
                    dp[x] = True
            count -= use
            k <<= 1
    return dp[target]

T = 3
for _ in range(T):
    N = int(input().strip())
    coins = []
    for __ in range(N):
        v, c = map(int, input().split())
        coins.append((v, c))
    print(1 if can_divide(coins) else 0)