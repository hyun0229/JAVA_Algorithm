# [Diamond IV] Critical Projects - 12823 

[문제 링크](https://www.acmicpc.net/problem/12823) 

### 성능 요약

메모리: 43252 KB, 시간: 312 ms

### 분류

방향 비순환 그래프, 그래프 이론, 위상 정렬

### 제출 일자

2025년 4월 4일 15:21:24

### 문제 설명

<p>A large project is subdivided into N different subprojects. The manager of the project established precedence relations among the subprojects. This means that there are pairs of subprojects u and v such that the completion of the subproject u must be finished before the start of the subproject v. In this case we say that u directly precedes v. We say that u precedes v if u directly precedes v or there is a subproject z such that u precedes z and z precedes v. Any subproject u is considered critical if for each subproject v (other than u) either v precedes u or u precedes v. It is known that the whole project can be completed, e.i., there is no subproject u such that u precedes itself.</p>

<p>Write a program that computes all the critical subprojects.</p>

### 입력 

 <p>The first line of the input contains two integers, N and M. N (1 ≤ N ≤ 100000) is the number of the subprojects and M (0 ≤ M ≤ 1000000) is the number of the direct precedence pairs. Subprojects are identified by the numbers 1, . . . , N. Each of the next M lines contains two integers u and v, (1 ≤ u ≠ v ≤ N) a direct precedence pair, that is u directly precedes v.</p>

### 출력 

 <p>The first line of the output must contain the number of critical subprojects. The second line contains the identifiers of the critical subprojects in ascending order. The numbers must be separated by a single space. If there is no critical subproject then the first and only line contains the number 0.</p>

