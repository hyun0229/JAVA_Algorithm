import sys
input = sys.stdin.readline

n,m = map(int, input().split())

a_ls = list(map(int,input().split()))
n_ls = [0]
tmp = 0
n_max = 0
for i in range(1,n+1):
    tmp += a_ls[i-1]
    n_ls.append(tmp)
    n_max = max(n_max,a_ls[i-1])

i = n_max
j = n_ls[-1]
while i<=j:
    find = int((i+j)/2)
    s_idnx = 0
    cnt = 0
    for k in range(1,n+1):
        if find < n_ls[k]-n_ls[s_idnx]:
            cnt +=1
            s_idnx = k-1
        elif find == n_ls[k]-n_ls[s_idnx]:
            cnt+=1
            s_idnx = k
    if s_idnx != n:
        cnt +=1
    if cnt <= m:
        j = find -1
    else:
        i = find +1
        
print(i)



