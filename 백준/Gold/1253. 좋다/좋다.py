import sys
input = sys.stdin.readline

n = int(input())
ls = list(map(int,input().split()))
ls.sort()
cnt = 0

for k in range(n):
    i,j=0,n-1
    find = ls[k]
    while i<j:
        if ls[i] + ls[j] < find:
            i+=1
        elif ls[i] + ls[j] > find:
            j-=1
        else :
            if i != k and j != k:
                cnt+=1
                break
            elif i == k :
                i+=1
            else :
                j-=1

print(cnt)