package Study2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class N15683 {

	static int N, M, min = Integer.MAX_VALUE;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	
	// brute force 문제이므로 dfs로 해결 & copy를 만들어 줄 필요가 있음 
	// 방향에 대한 모든 경우의 수를 넣어줌 ... 
	static int[][][] dir = { {}, // 0 
			{ { 0 }, { 1 }, { 2 }, { 3 } }, // 1
			{ { 0, 1 }, { 2, 3 } }, // 2
			{ { 1, 2 }, { 0, 2 }, { 0, 3 }, { 1, 3 } }, // 3
			{ { 0, 1, 2 }, { 1, 2, 3 }, { 0, 1, 3 }, { 0, 2, 3 } }, // 4
			{ { 0, 1, 2, 3 } } // 5
	};
	static int[][] table;
	static ArrayList<Node> cctvList = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sArr = br.readLine().split(" ");
		int cur = 0;
		N = Integer.parseInt(sArr[0]);
		M = Integer.parseInt(sArr[1]);
		table = new int[N][M];
		for (int i = 0; i < N; i++) {
			sArr = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				cur = table[i][j] = Integer.parseInt(sArr[j]);
				if (cur >= 1 && cur <= 5) { // cctv
					cctvList.add(new Node(i, j, cur));
				}
			}
		}
		
		dfs(0,table);

		System.out.println(min);
	}

	public static void dfs(int depth, int[][] table) {
		if (depth == cctvList.size()) { // cctv 개수만큼 탐색을 돌았을 때 
//			for(int aa=0; aa<N; aa++) {
//				for(int j=0; j<M; j++) {
//					System.out.print(table[aa][j]+" ");				
//				}
//				System.out.println();
//			}
//			System.out.println();
			int sz = getSafeZone(table);
			if (sz < min)
				min = sz; // 사각지대 최소값 구하기
			return;
		}

		Node node = cctvList.get(depth);
		// 1 3 4 => 4, 2 => 2, 5 => 1
		int i_range = getRange_i(node.cctvNum);
		// 1 => 1, 2 3 => 2, 4 => 3, 5 => 4
		int j_range = getRange_j(node.cctvNum);
		
		for (int i = 0; i < i_range; i++) {
			// i 인덱스가 넘어갈 때 마다 cctv가 작동하도록 하게 함 
			// 모든 경우의 수를 알아봐야 하므로 copy	생성 
			int[][] copy = new int[N][M];
			for (int aa = 0; aa < N; aa++) {
				copy[aa] = table[aa].clone();
			}
			//위 방법 말고도 이중 for문을 가지고도 만들 수 있다. 
			
			for (int j = 0; j < j_range; j++) {
				int cx = node.x;
				int cy = node.y;
				int cd = node.cctvNum;
				while (true) { // 설정된 방향으로 계속 cctv를 작동시킴 
					cx += dx[dir[cd][i][j]];
					cy += dy[dir[cd][i][j]];
					if(cx < 0 || cx >= N || cy < 0 || cy >= M) // 범위체크 
						break;
					if(copy[cx][cy] == 6) // 벽 체크 
						break;
					if(copy[cx][cy] >=1 && copy[cx][cy] <= 5) // cctv 체크 
						continue;
					copy[cx][cy] = 9; // 방문했던 곳은 9로 표시 
				}
			}
			dfs(depth+1, copy);
		}
	}

	public static int getRange_i(int cctvNum) {
		int a = 0;
		if (cctvNum == 1 || cctvNum == 3 || cctvNum == 4)
			a = 4;
		else if (cctvNum == 2)
			a = 2;
		else if (cctvNum == 5)
			a = 1;
		return a;
	}

	public static int getRange_j(int cctvNum) {
		int a = 0;
		if (cctvNum == 1)
			a = 1;
		else if (cctvNum == 2 || cctvNum == 3)
			a = 2;
		else if (cctvNum == 4)
			a = 3;
		else if (cctvNum == 5)
			a = 4;
		return a;
	}

	public static int getSafeZone(int[][] table) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (table[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}

	public static class Node {
		int x, y, cctvNum;

		Node(int x, int y, int cctvNum) {
			this.x = x;
			this.y = y;
			this.cctvNum = cctvNum;
		}
	}
}
