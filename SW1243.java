package Study2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW1243 {

	static int N;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] table, dp;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int aa = 0; aa < tc; aa++) {
			N = Integer.parseInt(br.readLine());
			table = new int[N][N];
			dp = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					table[i][j] = s.charAt(j) - '0';
					dp[i][j] = Integer.MAX_VALUE;
				}
			}
//			dp[0][0] = 0;
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					int min = Integer.MAX_VALUE;
//					if (!visited[i][j] && dp[i][j] < Integer.MAX_VALUE) {
//						min = dp[i][j];
//						visited[i][j] = true;
//						a(i, j);
//					}
//				}
//			}
			dp();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(dp[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("#" + (aa + 1) + " " + dp[N - 1][N - 1]);
		}
	}

	public static void dp() {
		dp[0][0] = 0;
		
		// 최악의 경우에 
		for (int k = 0; k < N * N; k++) {
			
			int min = Integer.MAX_VALUE, x = 0, y = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && dp[i][j] < min) {
						min = dp[i][j];
						x = i;
						y = j;
					}
				}
			}
			visited[x][y] = true;
			for (int i = 0; i < 4; i++) {
				int cx = x + dx[i];
				int cy = y + dy[i];
				if (cx < 0 || cx >= N || cy < 0 || cy >= N)
					continue;
				dp[cx][cy] = Math.min(dp[cx][cy], dp[x][y] + table[x][y]);
			}
		}
	}

}
