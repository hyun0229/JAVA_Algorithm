import sys

def is_win(b,p):
    return any(all(b[i]==p for i in line) for line in
               [(0,1,2),(3,4,5),(6,7,8),
                (0,3,6),(1,4,7),(2,5,8),
                (0,4,8),(2,4,6)])

for s in map(str.strip, sys.stdin):
    if s=="end": break
    x,o=s.count('X'),s.count('O')
    xw,ow=is_win(s,'X'),is_win(s,'O')
    print("valid" if (xw and not ow and x==o+1) or
                     (ow and not xw and x==o) or
                     (not xw and not ow and x==5 and o==4)
          else "invalid")
