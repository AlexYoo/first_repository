package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class N16235 {

	static int N, M, K;
	static int[] dx = { -1, -1, -1, 1, 1, 1, 0, 0 };
	static int[] dy = { -1, 0, 1, -1, 0, 1, -1, 1 };
	static int[][] S2D2;
	static Node[][] table;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sArr = br.readLine().split(" ");
		N = Integer.parseInt(sArr[0]);
		M = Integer.parseInt(sArr[1]);
		K = Integer.parseInt(sArr[2]);
		S2D2 = new int[N][N];
		table = new Node[N][N];
		for (int i = 0; i < N; i++) {
			sArr = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				S2D2[i][j] = Integer.parseInt(sArr[j]);
				ArrayList<Integer> a = new ArrayList<>();
				ArrayList<Integer> d = new ArrayList<>();
				table[i][j] = new Node(i, j, 5, a, d);
			}
		}

		for (int i = 0; i < M; i++) {
			sArr = br.readLine().split(" ");
			//인덱스 맞추기 위해 -1 
			int x = Integer.parseInt(sArr[0]) - 1;
			int y = Integer.parseInt(sArr[1]) - 1;
			int age = Integer.parseInt(sArr[2]);
			table[x][y].treesAlive.add(age);
		}

		//사계절 진행 
		for (int i = 0; i < K; i++) {
			spring(); summer(); fall(); winter();
		}

		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += table[i][j].treesAlive.size();
			}
		}
		System.out.println(sum);
	}

	//봄 : 나무가 자라고 죽음 
	public static void spring() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//해당 구역의 살아있는 나무 정렬 -> 어린 나무부터 먹게하기 위함.
				Collections.sort(table[i][j].treesAlive);
				ArrayList<Integer> list = new ArrayList<>();
				list.addAll(table[i][j].treesAlive);
				for(int aa=0; aa<list.size(); aa++) {
					// 먹을 양분이 있으면 양분 소모 시키고 나무 나이 1 증가 
					if(table[i][j].nutri - list.get(aa) >= 0) {
						table[i][j].nutri-=list.get(aa);
						table[i][j].treesAlive.set(aa, list.get(aa)+1);
					
					}else { // 먹을 양분이 없으면 살아있는 나무에서 제거, 죽은 나무에 추가 
						table[i][j].treesDead.add(list.get(aa));
						table[i][j].treesAlive.remove(list.get(aa));
					}
				}
			}
		}
	}

	//여름 : 죽은 나무가 양분이 됨 
	public static void summer() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ArrayList<Integer> list = table[i][j].treesDead;
				for (int aa = 0; aa < list.size(); aa++) {
					table[i][j].nutri += list.get(aa) / 2;
				}
				table[i][j].treesDead.clear();
			}
		}
	}

	//가을 : 5배수 나이인 나무 번식 
	public static void fall() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ArrayList<Integer> list = table[i][j].treesAlive;
				for (int aa = 0; aa < list.size(); aa++) {
					if (list.get(aa) % 5 == 0) {
						for (int bb = 0; bb < 8; bb++) {
							int cx = i + dx[bb];
							int cy = j + dy[bb];
							if (cx < 0 || cx >= N || cy < 0 || cy >= N)
								continue;
							table[cx][cy].treesAlive.add(1); // 나무 번식 시키기
						}
					}
				}
			}
		}
	}

	//겨울 : 양분 뿌리기
	public static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				table[i][j].nutri += S2D2[i][j];
			}
		}
	}

	public static class Node {
		int x, y, nutri;
		ArrayList<Integer> treesAlive, treesDead;

		Node(int x, int y, int nutri, 
				ArrayList<Integer> treesAlive, 
				ArrayList<Integer> treesDead) {
			this.x = x;
			this.y = y;
			this.nutri = nutri;
			this.treesAlive = treesAlive;
			this.treesDead = treesDead;
		}
	}

}
