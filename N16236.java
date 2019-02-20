package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class N16236 {

	//미친 문제 ;;;;
	static int N, x0, y0, sharkSize, cnt, limit;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static int[][] table;
	static boolean[][][] visited;
	static ArrayList<Node> nextNode = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		table = new int[N][N];
		visited = new boolean[N][N][1000];
		sharkSize = 2; cnt = 0; limit = 0;
		for(int i=0; i<N; i++) {
			String[] sArr = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				table[i][j] = Integer.parseInt(sArr[j]);
				if(table[i][j] == 9) {
					x0=i; y0=j;
				}
			}
		}
		nextNode(x0, y0);	
		System.out.println(cnt);
	}
	
	public static void nextNode(int xx, int yy) { // 재귀 탐색 부분 
		table[xx][yy] = 0; // 방문한 곳은 0으로 변환 
		ArrayList<Node> list = getNearestDistElems(xx,yy); //가까운 거리의 좌표들 
		if(list.size()==0)
			return;
		int resX=0, resY=0, max=0;
		for(int i=0; i<list.size(); i++) {
			int x = list.get(i).x;
			int y = list.get(i).y;
			int score = (N-x)*100000 + (N-y)*100;
			//score 계산 부분 : 어느 먹이를 선택할 것인가?
			//x와 y의 값이 작을수록 score를 크게 한다.
			//x의 비중이 더 크다 -> 문제에서 우선순위를 위쪽을 더 많이 줬기 때문  
			
			if(score>max) { // score가 가장 큰 것을 최종 목표로 잡는다.
				resX=x; resY=y; max=score;
			}
		}
		//limit : 상어 뱃속 먹이 갯수
		//뱃속 먹이 갯수가 상어 크기만큼 차면 상어 크기 증가. limit은 초기화 
		limit++;
		if(limit==sharkSize) {
			sharkSize++;
			limit = 0;
		}
		nextNode(resX, resY);
	}
	
	//가까운 거리에 있는 원소들 중에서 상어크기보다 작고 0이 아닌 좌표 list를 return
	public static ArrayList<Node> getNearestDistElems(int x, int y) {
		int depth = 0;
		Queue<Node> q = new LinkedList<>();
		ArrayList<Node> list = new ArrayList<>();
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;
		q.add(new Node(x, y));
		boolean found = false; // 가까운 거리의 원소를 찾았으면 true로 변환 
		while(!q.isEmpty()) {
			if(found) { // 먹잇감을 최소 거리에서 찾음 
				cnt+=depth; // 이동 횟수 추가 
				while(!q.isEmpty()) { 
					// ex)짧은 거리가 6임 -> 거리 6에 있는 상어크기보다 작은 먹이가 있는 곳을 리스트에 추가  
					Node front = q.poll();
					int xx = front.x; 
					int yy = front.y;
					if(table[xx][yy] > 0 && table[xx][yy] <sharkSize)
						list.add(new Node(xx,yy));
				}
			}
			int qSize = q.size();
			for(int aa=0; aa<qSize; aa++) {
				Node front = q.poll();
				for(int i=0; i<4; i++) {
					int cx = front.x + dx[i];
					int cy = front.y + dy[i];
					if(cx<0 || cx>=N || cy<0 || cy>=N)
						continue;
					if(visited[cx][cy]) continue;
					visited[cx][cy] = true;
					if(table[cx][cy] < sharkSize && table[cx][cy] != 0) { // 먹잇감 발견!!!
						q.add(new Node(cx, cy));
						found = true;
					}
					else if(table[cx][cy] == sharkSize || table[cx][cy] == 0) { // 길 
						q.add(new Node(cx, cy));
					}
				}
			}
			depth++;
		}
		return list;
	}
	
	public static class Node{
		int x, y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
}
