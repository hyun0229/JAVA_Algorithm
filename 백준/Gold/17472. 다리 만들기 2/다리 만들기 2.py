import sys
from collections import deque
import heapq

input = sys.stdin.readline
INF = 10**9

N, M = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(N)]

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

# 1) 섬 라벨링 (BFS)
island_id = [[0]*M for _ in range(N)]
K = 0

def label_bfs(sx, sy, idx):
    q = deque([(sx, sy)])
    island_id[sx][sy] = idx
    while q:
        x, y = q.popleft()
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if 0 <= nx < N and 0 <= ny < M:
                if grid[nx][ny] == 1 and island_id[nx][ny] == 0:
                    island_id[nx][ny] = idx
                    q.append((nx, ny))

for i in range(N):
    for j in range(M):
        if grid[i][j] == 1 and island_id[i][j] == 0:
            K += 1
            label_bfs(i, j, K)

if K <= 1:
    print(0)
    sys.exit(0)

# 2) 다리 후보 간선(최소 길이) 찾기
best = [[INF]*(K+1) for _ in range(K+1)]

for x in range(N):
    for y in range(M):
        a = island_id[x][y]
        if a == 0:
            continue
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            length = 0
            while 0 <= nx < N and 0 <= ny < M:
                b = island_id[nx][ny]
                if b == a:
                    break
                if b == 0:
                    length += 1
                    nx += dx[d]
                    ny += dy[d]
                    continue
                # 다른 섬 b 도착
                if length >= 2:
                    if length < best[a][b]:
                        best[a][b] = best[b][a] = length
                break

# 인접 리스트로 변환
adj = [[] for _ in range(K+1)]
for a in range(1, K+1):
    for b in range(a+1, K+1):
        if best[a][b] != INF:
            w = best[a][b]
            adj[a].append((w, b))
            adj[b].append((w, a))

# 3) Prim MST
visited = [False]*(K+1)
pq = []
visited_count = 0
total = 0

# 아무 섬(1)에서 시작
visited[1] = True
visited_count = 1
for w, nxt in adj[1]:
    heapq.heappush(pq, (w, nxt))

while pq and visited_count < K:
    w, v = heapq.heappop(pq)
    if visited[v]:
        continue
    visited[v] = True
    visited_count += 1
    total += w
    for nw, nv in adj[v]:
        if not visited[nv]:
            heapq.heappush(pq, (nw, nv))

print(total if visited_count == K else -1)