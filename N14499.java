package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N14499 {

	static int N, M, x, y, K;
	static int[][] table;
	static int[] dice;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sArr = br.readLine().split(" ");
		N = Integer.parseInt(sArr[0]);
		M = Integer.parseInt(sArr[1]);
		x = Integer.parseInt(sArr[2]);
		y = Integer.parseInt(sArr[3]);
		K = Integer.parseInt(sArr[4]);
		dice = new int[7];

		table = new int[N][M];
		for (int i = 0; i < N; i++) {
			sArr = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				table[i][j] = Integer.parseInt(sArr[j]);
			}
		}

		sArr = br.readLine().split(" ");
		for (int i = 0; i < K; i++) {
			int command = Integer.parseInt(sArr[i]);
			update(command);
		}
	}

	public static void update(int command) {
		if (command == 1) {
			if (y + 1 < M) {
				++y;
				goRight();
				System.out.println(dice[1]);
			}
		}

		else if (command == 2) {
			if (y - 1 >= 0) {
				--y;
				goLeft();
				System.out.println(dice[1]);
			}
		}

		else if (command == 3) {
			if (x - 1 >= 0) {
				--x;
				goUp();
				System.out.println(dice[1]);
			}
		}

		else if (command == 4) {
			if (x + 1 < N) {
				++x;
				goDown();
				System.out.println(dice[1]);
			}
		}

	}


	public static void goRight() {
		int[] copy = dice.clone();
		dice[1] = copy[4];
		dice[3] = copy[1];
		dice[4] = copy[6];
		if (table[x][y] != 0) { // 바닥이 0이 아님
			dice[6] = table[x][y];
			table[x][y] = 0;
		} else { // 바닥이 0임
			dice[6] = copy[3];
			table[x][y] = copy[3];
		}
	}

	public static void goLeft() {
		int[] copy = dice.clone();
		dice[1] = copy[3];
		dice[3] = copy[6];
		dice[4] = copy[1];
		if (table[x][y] != 0) { // 바닥이 0이 아님
			dice[6] = table[x][y];
			table[x][y] = 0;
		} else { // 바닥이 0임
			dice[6] = copy[4];
			table[x][y] = copy[4];
		}
	}

	public static void goUp() {
		int[] copy = dice.clone();
		dice[1] = copy[5];
		dice[2] = copy[1];
		dice[5] = copy[6];
		if (table[x][y] != 0) { // 바닥이 0이 아님
			dice[6] = table[x][y];
			table[x][y] = 0;
		} else { // 바닥이 0임
			dice[6] = copy[2];
			table[x][y] = copy[2];
		}
	}

	public static void goDown() {
		int[] copy = dice.clone();
		dice[1] = copy[2];
		dice[2] = copy[6];
		dice[5] = copy[1];
		if (table[x][y] != 0) { // 바닥이 0이 아님
			dice[6] = table[x][y];
			table[x][y] = 0;
		} else { // 바닥이 0임
			dice[6] = copy[5];
			table[x][y] = copy[5];
		}
	}
}
