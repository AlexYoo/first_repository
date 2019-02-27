package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//0이 아닌 부분은 큐에 모두 넣어서 계산하면 편함

public class N12100_2 {

	static int N, max;
	static int[][] table = new int[21][21];
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		max=0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				table[i][j] = Integer.parseInt(st.nextToken());	
				max = Math.max(max, table[i][j]);
			}
		}
		dfs(0);
		System.out.println(max);
	}

	private static void dfs(int cnt) {
		if(cnt==5) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(table[i][j]>max) max = table[i][j];
				}
			}
			return;
		}

		int copy[][] = new int[21][21];
		copy(copy, table);

		for(int i=0;i<4;i++) {
			merge(i);
			dfs(cnt+1);
			copy(table, copy);
		}
	}

	static void copy(int[][] a, int[][] b) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				a[i][j] = b[i][j];
			}
		}
	}

	static Queue<Integer> q;
	private static void merge(int dir) {	
		q = new LinkedList<>();
		
		switch (dir) {
		case 0:
			//상
			for(int j=0;j<N;j++) {
				for(int i=0;i<N;i++) {		//큐에 담기
					if(table[i][j]!=0) {
						q.add(table[i][j]);
						table[i][j]=0;
					}
				}
				int n=0;
				int pop;
				while(!q.isEmpty()) {
					pop = q.poll();
					if(table[n][j]==0){
						table[n][j] = pop;
					}else if(table[n][j] == pop) {
						table[n][j] *= 2;
						n++;
					}else {
						table[++n][j] = pop;
					}
				}
			}
			break;
		case 1:
			//하
			for(int j=0;j<N;j++) {
				for(int i=N-1;i>=0;i--) {
					if(table[i][j]!=0) {
						q.add(table[i][j]);
						table[i][j]=0;
					}
				}
				int n=N-1;
				int pop;
				while(!q.isEmpty()) {
					pop = q.poll();
					if(table[n][j]==0){
						table[n][j] = pop;
					}else if(table[n][j] == pop) {
						table[n][j] *= 2;
						n--;
					}else {
						table[--n][j] = pop;
					}
				}
			}
			break;
		case 2:
			//좌
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(table[i][j]!=0) {
						q.add(table[i][j]);
						table[i][j]=0;
					}
				}
				int n=0;
				int pop;
				while(!q.isEmpty()) {
					pop = q.poll();
					if(table[i][n]==0){
						table[i][n] = pop;
					}else if(table[i][n] == pop) {
						table[i][n] *= 2;
						n++;
					}else {
						table[i][++n] = pop;
					}
				}
			}
			break;
		case 3:
			//우
			for(int i=0;i<N;i++) {
				for(int j=N-1;j>=0;j--) {
					if(table[i][j]!=0) {
						q.add(table[i][j]);
						table[i][j]=0;
					}
				}
				int n=N-1;
				int pop;
				while(!q.isEmpty()) {
					pop = q.poll();
					if(table[i][n]==0){
						table[i][n] = pop;
					}else if(table[i][n] == pop) {
						table[i][n] *= 2;
						n--;
					}else {
						table[i][--n] = pop;
					}
				}
			}
			break;

		}
	}
}