# [Gold I] 다리 만들기 2 - 17472 

[문제 링크](https://www.acmicpc.net/problem/17472) 

### 성능 요약

메모리: 14248 KB, 시간: 108 ms

### 분류

너비 우선 탐색, 브루트포스 알고리즘, 깊이 우선 탐색, 그래프 이론, 그래프 탐색, 구현, 최소 스패닝 트리

### 제출 일자

2025년 2월 10일 12:44:25

### 문제 설명

<p>섬으로 이루어진 나라가 있고, 모든 섬을 다리로 연결하려고 한다. 이 나라의 지도는 N×M 크기의 이차원 격자로 나타낼 수 있고, 격자의 각 칸은 땅이거나 바다이다.</p>

<p>섬은 연결된 땅이 상하좌우로 붙어있는 덩어리를 말하고, 아래 그림은 네 개의 섬으로 이루어진 나라이다. 색칠되어있는 칸은 땅이다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/38cb578e-b289-4b72-841e-422a1458d617/-/preview/" style="width: 200px; height: 178px;"></p>

<p>다리는 바다에만 건설할 수 있고, 다리의 길이는 다리가 격자에서 차지하는 칸의 수이다. 다리를 연결해서 모든 섬을 연결하려고 한다. 섬 A에서 다리를 통해 섬 B로 갈 수 있을 때, 섬 A와 B를 연결되었다고 한다. 다리의 양 끝은 섬과 인접한 바다 위에 있어야 하고, 한 다리의 방향이 중간에 바뀌면 안된다. 또, 다리의 길이는 2 이상이어야 한다.</p>

<p>다리의 방향이 중간에 바뀌면 안되기 때문에, 다리의 방향은 가로 또는 세로가 될 수 밖에 없다. 방향이 가로인 다리는 다리의 양 끝이 가로 방향으로 섬과 인접해야 하고, 방향이 세로인 다리는 다리의 양 끝이 세로 방향으로 섬과 인접해야 한다.</p>

<p>섬 A와 B를 연결하는 다리가 중간에 섬 C와 인접한 바다를 지나가는 경우에 섬 C는 A, B와 연결되어있는 것이 아니다. </p>

<p>아래 그림은 섬을 모두 연결하는 올바른 2가지 방법이고, 다리는 회색으로 색칠되어 있다. 섬은 정수, 다리는 알파벳 대문자로 구분했다.</p>

<table class="table table-bordered" style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 50%; text-align: center;"><img alt="" src="https://upload.acmicpc.net/41f71ecc-97b4-4351-b741-4b8336576246/-/preview/" style="width: 220px; height: 195px;"></td>
			<td style="width: 50%; text-align: center;"><img alt="" src="https://upload.acmicpc.net/3b158fdf-74ba-47d7-a224-9e5b753b8453/-/preview/" style="width: 220px; height: 195px;"></td>
		</tr>
		<tr>
			<td style="width: 50%; text-align: center;">
			<p>다리의 총 길이: 13</p>

			<p>D는 2와 4를 연결하는 다리이고, 3과는 연결되어 있지 않다.</p>
			</td>
			<td style="width: 50%; text-align: center;">
			<p>다리의 총 길이: 9 (최소)</p>
			</td>
		</tr>
	</tbody>
</table>

<p>다음은 올바르지 않은 3가지 방법이다</p>

<table class="table table-bordered" style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 33%; text-align: center;"><img alt="" src="https://upload.acmicpc.net/c7c663a1-4ebb-4c89-9a6a-4157513c1a30/-/preview/" style="width: 220px; height: 194px;"></td>
			<td style="width: 34%; text-align: center;"><img alt="" src="https://upload.acmicpc.net/390361f9-0647-4ff8-9709-7c1de26c0929/-/preview/" style="width: 220px; height: 195px;"></td>
			<td style="width: 33%; text-align: center;"><img alt="" src="https://upload.acmicpc.net/2a1d4415-0a0d-4508-8a14-1956fdf650ec/-/preview/" style="width: 220px; height: 196px;"></td>
		</tr>
		<tr>
			<td style="width: 33%; text-align: center;">C의 방향이 중간에 바뀌었다</td>
			<td style="width: 34%; text-align: center;">D의 길이가 1이다.</td>
			<td style="width: 34%; text-align: center;">가로 다리인 A가 1과 가로로 연결되어 있지 않다.</td>
		</tr>
	</tbody>
</table>

<p>다리가 교차하는 경우가 있을 수도 있다. 교차하는 다리의 길이를 계산할 때는 각 칸이 각 다리의 길이에 모두 포함되어야 한다. 아래는 다리가 교차하는 경우와 기타 다른 경우에 대한 2가지 예시이다.</p>

<table class="table table-bordered" style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 50%; text-align: center;"><img alt="" src="https://upload.acmicpc.net/b6f340e2-8248-4385-9a6a-546e7a2648e4/-/preview/" style="width: 220px; height: 195px;"></td>
			<td style="width: 50%; text-align: center;"><img alt="" src="https://upload.acmicpc.net/dd98ec33-6796-455d-a612-8db31a9806f0/-/preview/" style="width: 220px; height: 195px;"></td>
		</tr>
		<tr>
			<td style="width: 50%; text-align: center;vertical-align: middle;">
			<p>A의 길이는 4이고, B의 길이도 4이다.</p>

			<p>총 다리의 총 길이: 4 + 4 + 2 = 10</p>
			</td>
			<td style="width: 50%; text-align: center;">
			<p>다리 A: 2와 3을 연결 (길이 2)</p>

			<p>다리 B: 3과 4를 연결 (길이 3)</p>

			<p>다리 C: 2와 5를 연결 (길이 5)</p>

			<p>다리 D: 1과 2를 연결 (길이 2)</p>

			<p>총 길이: 12</p>
			</td>
		</tr>
	</tbody>
</table>

<p>나라의 정보가 주어졌을 때, 모든 섬을 연결하는 다리 길이의 최솟값을 구해보자.</p>

### 입력 

 <p>첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. 둘째 줄부터 N개의 줄에 지도의 정보가 주어진다. 각 줄은 M개의 수로 이루어져 있으며, 수는 0 또는 1이다. 0은 바다, 1은 땅을 의미한다.</p>

### 출력 

 <p>모든 섬을 연결하는 다리 길이의 최솟값을 출력한다. 모든 섬을 연결하는 것이 불가능하면 -1을 출력한다.</p>

