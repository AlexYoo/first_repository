package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N1012 {

	static int N, M, cnt = 0;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] table;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int aa = 0; aa < tc; aa++) {
			String[] sArr = br.readLine().split(" ");
			N = Integer.parseInt(sArr[0]);
			M = Integer.parseInt(sArr[1]);
			int K = Integer.parseInt(sArr[2]);
			int x, y;
			table = new int[N][M];
			for (int i = 0; i < K; i++) {
				sArr = br.readLine().split(" ");
				x = Integer.parseInt(sArr[0]);
				y = Integer.parseInt(sArr[1]);
				table[x][y] = 1;
			}

			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (table[i][j] == 1 && table[i][j] != 9) {
						// System.out.println("i : " + i + " j : " + j);
						dfs(i, j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}

	public static void dfs(int x, int y) {
		if (table[x][y] == 9)
			return;
		table[x][y] = 9;
		for (int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if (cx < 0 || cx >= N || cy < 0 || cy >= M)
				continue;
			if (table[cx][cy] == 1)
				dfs(cx, cy);
		}
	}

}
