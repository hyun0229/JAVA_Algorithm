import sys

def dfs(n, expr, cur):
    if cur > n:
        if eval(expr.replace(" ", "")) == 0:
            print(expr)
        return
    
    dfs(n, expr + ' ' + str(cur), cur + 1) 
    dfs(n, expr + '+' + str(cur), cur + 1) 
    dfs(n, expr + '-' + str(cur), cur + 1)


t = int(sys.stdin.readline())
for _ in range(t):
    n = int(sys.stdin.readline())
    dfs(n, "1", 2)   
    print()          

