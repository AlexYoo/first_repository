package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class N16234 {

	static int N,L,R,cnt;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static int[][] table;
	static boolean[][] visited;
	static ArrayList<Node> list = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sArr = br.readLine().split(" ");
		N = Integer.parseInt(sArr[0]);
		L = Integer.parseInt(sArr[1]);
		R = Integer.parseInt(sArr[2]);
		cnt = 0;
		table = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			sArr = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				table[i][j] = Integer.parseInt(sArr[j]);
			}
		}
		while(true) {
			if(isPaused()) break; // 인구이동이 더이상 일어나지 않을 때 
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					list.clear(); // list 안에는 연합의 정보들을 갖고 있음 
					if(!visited[i][j]) 
						dfs(i,j);
					int pl = getTotalPopulation(); // 연합 하나의 전체 인구수 
					for(int aa=0; aa<list.size(); aa++) {
						int x = list.get(aa).x;
						int y = list.get(aa).y;
						table[x][y] = pl / list.size(); // 연합 인구 분배 
					}
				}
			}
			cnt++;
		}
		
		System.out.println(cnt);
		
	}
	
	public static int getTotalPopulation() {
		int sum = 0;
		for(int i=0; i<list.size(); i++) {
			sum+=list.get(i).pl;
		}
		return sum;
	}
	
	
	public static void dfs(int x, int y) {
		if(visited[x][y]) return;
		visited[x][y] = true;
		list.add(new Node(x,y,table[x][y]));
		for(int i=0; i<4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if(cx < 0 || cx >= N || cy < 0 || cy >= N)
				continue;
			int plGap = Math.abs(table[x][y] - table[cx][cy]); 
			if(plGap >= L && plGap <= R) // 인구 갭이 L과 R 사이면 연합 추가 
				dfs(cx,cy);
		}
	}
	
	public static boolean isPaused() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(int aa=0; aa<4; aa++) {
					int cx = i + dx[aa];
					int cy = j + dy[aa];
					if(cx < 0 || cx >= N || cy < 0 || cy >= N)
						continue;
					int plGap = Math.abs(table[i][j]-table[cx][cy]);
					if(plGap >= L && plGap <= R) return false; //인구 이동이 일어남 
				}
			}
		}
		return true;
	}

	public static class Node{
		int x,y;
		int pl;
		Node(int x, int y, int pl){
			this.x = x;
			this.y = y;
			this.pl = pl;
		}
	}
	
}
