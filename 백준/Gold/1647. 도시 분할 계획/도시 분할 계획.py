import sys

input = sys.stdin.readline

def find(x, parent):
    while parent[x] != x:
        parent[x] = parent[parent[x]]  # path compression (halving)
        x = parent[x]
    return x

def union(a, b, parent, rank):
    ra = find(a, parent)
    rb = find(b, parent)
    if ra == rb:
        return False
    if rank[ra] < rank[rb]:
        parent[ra] = rb
    elif rank[ra] > rank[rb]:
        parent[rb] = ra
    else:
        parent[rb] = ra
        rank[ra] += 1
    return True

def main():
    N, M = map(int, input().split())
    edges = []
    for _ in range(M):
        a, b, c = map(int, input().split())
        edges.append((c, a, b))
    edges.sort()

    parent = list(range(N + 1))
    rank = [0] * (N + 1)

    total = 0
    max_edge = 0
    cnt = 0

    for c, a, b in edges:
        if union(a, b, parent, rank):
            total += c
            if c > max_edge:
                max_edge = c
            cnt += 1
            if cnt == N - 1:  # MST complete
                break

    # Split into 2 villages by removing the largest edge in MST
    print(total - max_edge)

if __name__ == "__main__":
    main()