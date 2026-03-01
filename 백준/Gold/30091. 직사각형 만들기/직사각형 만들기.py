N = int(input())
ls = list(map(int, input().split()))
hmap = {}
for i in range(N):
    for j in range(i + 1, N):
        l = ls[i]+ ls[j]
        if l not in hmap:
            hmap[l] = {
                "number" : set(),
                "sum": 0
            }
            hmap[l]["number"].add(i)
            hmap[l]["number"].add(j)
            hmap[l]["sum"]+=ls[i]*ls[j]
        else:
            if i in hmap[l]["number"] or j in hmap[l]["number"]:
                continue
            else:
                hmap[l]["number"].add(i)
                hmap[l]["number"].add(j)
                hmap[l]["sum"]+=ls[i]*ls[j]
ans = 0
for k in hmap:
    if ans < hmap[k]["sum"]:
        ans = hmap[k]["sum"]

print(ans)