package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class N2251 {

	static int Acapa, Bcapa, Ccapa;
	static boolean[][][] visited = new boolean[201][201][201];
	static Queue<Node> q = new LinkedList<>();
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sArr = br.readLine().split(" ");
		Acapa = Integer.parseInt(sArr[0]);
		Bcapa = Integer.parseInt(sArr[1]);
		Ccapa = Integer.parseInt(sArr[2]);
		// 0 : A / 1 : B / 2 : C
		bfs();
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++)
			System.out.print(list.get(i) + " ");
	}

	public static void bfs() {
		int depth=0;
		q.add(new Node(0, 0, Ccapa));
		visited[0][0][Ccapa] = true;
		while (!q.isEmpty()) {
//			System.out.println("======depth : " + depth + "========");
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Node front = q.poll();
				int A = front.A, B = front.B, C = front.C;
				if (A == 0 && !list.contains(C))
					list.add(C);
				visited[A][B][C] = true;
//				System.out.println("(" + A + "," + B + "," + C + ")");
				if (A != 0) { // A가 비어있지 않을 때
					// B에다가 붓기
					int abA, abB, abC;
					if (A + B > Bcapa) { // 용량이 넘침
						abA = A - (Bcapa - B);
						abB = Bcapa;
						abC = C;
					} else { // 용량이 넘치지 않음
						abA = 0;
						abB = A + B;
						abC = C;
					}
					if (!visited[abA][abB][abC])
						q.add(new Node(abA, abB, abC));

					// C에다가 붓기
					int acA, acB, acC;
					if (A + C > Ccapa) {
						acA = A - (Ccapa - C);
						acB = B;
						acC = Ccapa;
					} else {
						acA = 0;
						acB = B;
						acC = A + C;
					}
					if (!visited[acA][acB][acC])
						q.add(new Node(acA, acB, acC));
				}
				if (B != 0) {
					// A에다가 붓기
					int baA, baB, baC;
					if (B + A > Acapa) { // 용량이 넘침
						baA = Acapa;
						baB = B - (Acapa - A);
						baC = C;
					} else { // 용량이 넘치지 않음
						baA = A + B;
						baB = 0;
						baC = C;
					}
					if (!visited[baA][baB][baC])
						q.add(new Node(baA, baB, baC));

					// C에다가 붓기
					int bcA, bcB, bcC;
					if (B + C > Ccapa) {
						bcA = A;
						bcB = B - (Ccapa - C);
						bcC = Ccapa;
					} else {
						bcA = A;
						bcB = 0;
						bcC = B + C;
					}
					if (!visited[bcA][bcB][bcC])
						q.add(new Node(bcA, bcB, bcC));

				}
				if (C != 0) { // C가 비어있지 않을 때
					// A에다가 붓기
					int caA, caB, caC;
					if (C + A > Acapa) { // 용량이 넘침
						caA = Acapa;
						caB = B;
						caC = C - (Acapa - A);
					} else { // 용량이 넘치지 않음
						caA = A + C;
						caB = B;
						caC = 0;
					}
					if (!visited[caA][caB][caC])
						q.add(new Node(caA, caB, caC));

					// B에다가 붓기
					int cbA, cbB, cbC;
					if (C + B > Bcapa) {
						cbA = A;
						cbB = Bcapa;
						cbC = C - (Bcapa - B);
					} else {
						cbA = A;
						cbB = B + C;
						cbC = 0;
					}
					if (!visited[cbA][cbB][cbC])
						q.add(new Node(cbA, cbB, cbC));
				}
			}
			depth++;
		}
	}

	public static class Node {
		int A, B, C;

		Node(int A, int B, int C) {
			this.A = A;
			this.B = B;
			this.C = C;
		}
	}

}
