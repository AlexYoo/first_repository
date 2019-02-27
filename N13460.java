package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class N13460 {

	static int N, M, rx0, ry0, bx0, by0;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static char[][] table;
	static boolean[][][][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sArr = br.readLine().split(" ");
		N = Integer.parseInt(sArr[0]);
		M = Integer.parseInt(sArr[1]);
		table = new char[N][M];
		visited = new boolean[N][M][N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				table[i][j] = s.charAt(j);
				if (table[i][j] == 'R') {
					rx0 = i;
					ry0 = j;
				} else if (table[i][j] == 'B') {
					bx0 = i;
					by0 = j;
				}
			}
		}

		System.out.println(execute());

	}

	public static int execute() {
		boolean[][][][] visited = new boolean[N][M][N][M];
		int depth =1;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(rx0,ry0,bx0,by0));
		visited[rx0][ry0][bx0][by0] = true;
		while(!q.isEmpty()) {
			if(depth>10) break;
			int qSize = q.size();
			for(int aa=0; aa<qSize; aa++) {
				Node front = q.poll();
				for(int i=0; i<4; i++) {
					boolean rPaused = false, bPaused = false;
					boolean rGoal = false, bGoal = false;
					boolean rFirst = false, bFirst = false;
					int crx = front.rx, cry = front.ry, cbx = front.bx, cby = front.by;
//					System.out.println(crx+","+cry+" "+cbx+","+cby);
					if(i==0) {
						if(cry<=cby) rFirst = true;
						else bFirst = true;
					}else if(i==1) {
						if(cry<=cby) bFirst = true;
						else rFirst = true;
					}else if(i==2) {
						if(crx<=cbx) rFirst = true;
						else bFirst = true;
					}else if(i==3) {
						if(crx<=cbx) bFirst = true;
						else rFirst = true;
					}
					
					if(rFirst) {
						loopRed:
							while(true) {
//								System.out.println(crx+","+cry+" "+cbx+","+cby);
								if(rPaused) break loopRed;
								
								if(table[crx+dx[i]][cry+dy[i]]=='#') {
									rPaused = true;
								}else if(table[crx+dx[i]][cry+dy[i]]=='O') {
									rPaused = true;
									rGoal = true;
									crx = -10000; cry = -10000;
								}else {
									crx+=dx[i]; cry+=dy[i];
								}
							}
					
						loopBlue:
							while(true) {
//								System.out.println(crx+","+cry+" "+cbx+","+cby);
								if(bPaused) break loopBlue;
								
								if(table[cbx+dx[i]][cby+dy[i]]=='#') {
									bPaused = true;
								}else if(table[cbx+dx[i]][cby+dy[i]]=='O') {
									bPaused = true;
									bGoal = true;
								}else if(cbx+dx[i]==crx && cby+dy[i]==cry) {
									bPaused = true;
								}else{
									cbx+=dx[i]; cby+=dy[i];
								} 
							}
								
						if(bPaused && rPaused) {
							if(rGoal && !bGoal) {
								return depth;
							}else if(bGoal) {
								// do nothing
							}else {
								if(!visited[crx][cry][cbx][cby]) {
									visited[crx][cry][cbx][cby] = true;
									q.add(new Node(crx,cry,cbx,cby));
								}
							}
						}
					
					}else if(bFirst){
						loopBlue:
							while(true) {
//								System.out.println(crx+","+cry+" "+cbx+","+cby);
								if(bPaused) break loopBlue;
								
								if(table[cbx+dx[i]][cby+dy[i]]=='#') {
									bPaused = true;
								}else if(table[cbx+dx[i]][cby+dy[i]]=='O') {
									bPaused = true;
									bGoal = true;
								}else{
									cbx+=dx[i]; cby+=dy[i];
								}
							}
						
						loopRed:
							while(true) {
//								System.out.println(crx+","+cry+" "+cbx+","+cby);
								if(rPaused) break loopRed;
								if(table[crx+dx[i]][cry+dy[i]]=='#') {
									rPaused = true;
								}else if(table[crx+dx[i]][cry+dy[i]]=='O') {
									rPaused = true;
									rGoal = true;
								}else if(crx+dx[i]==cbx && cry+dy[i]==cby) {
									rPaused = true;
								}else {
									crx+=dx[i]; cry+=dy[i];
								}
							}
					
						
								
						if(bPaused && rPaused) {
							if(rGoal && !bGoal) {
								return depth;
							}else if(bGoal) {
								// do nothing
							}else {
								if(!visited[crx][cry][cbx][cby]) {
									visited[crx][cry][cbx][cby] = true;
									q.add(new Node(crx,cry,cbx,cby));
								}
							}
						}
					}
				}
			}
			depth++;
		}
		return -1;
	}

	public static class Node {
		int rx, ry, bx, by;

		Node(int rx, int ry, int bx, int by) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
		}
	}
}
