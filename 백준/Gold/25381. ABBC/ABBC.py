import sys
from collections import deque

def main():
    input = sys.stdin.readline
    S = input().rstrip()

    # 1단계: BC 제거
    b_positions = []        # 'B'의 인덱스 저장
    bc_used = 0             # BC 연산 횟수
    bc_ptr = 0              # 사용된 B 포인터
    for i, ch in enumerate(S):
        if ch == 'B':
            b_positions.append(i)
        elif ch == 'C':
            if bc_ptr < len(b_positions):
                bc_used += 1
                bc_ptr += 1

    # 남은 B 인덱스 리스트
    rem_b = b_positions[bc_ptr:]

    # 2단계: AB 제거
    a_positions = [i for i, ch in enumerate(S) if ch == 'A']
    ab_used = 0
    ai, bi = 0, 0
    while ai < len(a_positions) and bi < len(rem_b):
        if a_positions[ai] < rem_b[bi]:
            ab_used += 1
            ai += 1
            bi += 1
        else:
            bi += 1

    # 결과 출력
    print(bc_used + ab_used)

if __name__ == "__main__":
    main()
