package solo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW4013 {

	static int N, M, K;
	static int[][] magnets;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		String[] sArr;
		for (int aa = 0; aa < tc; aa++) {
			K = Integer.parseInt(br.readLine());
			magnets = new int[4][8];
			// 자석 정보 집어넣기
			for (int i = 0; i < 4; i++) {
				sArr = br.readLine().split(" ");
				for (int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(sArr[j]);
				}
			}

			// 명령어 집어 넣기
			for (int i = 0; i < K; i++) {
				sArr = br.readLine().split(" ");
				int mag = Integer.parseInt(sArr[0]);
				int dir = Integer.parseInt(sArr[1]);
				//각 command마다 visited는 초기화 해주어야 한다. 
				visited = new boolean[4];
				dfs(new Node(mag - 1, dir, false));
			}
			System.out.println("#" + (aa + 1) + " " + getScore());
		}

	}

	public static void dfs(Node node) { // 현재 자석 위치 and 방향
		int mag = node.mag; // 자석 위치 
		int dir = node.dir; // 방향 
		boolean terminated = node.terminated; // 멈추는지 안멈추는지 
		
		//copy가 반드시 필요하다!!! 왜냐하면 동시에 톱니바퀴들이 움직여야 하기 때문 
		final int[][] copy = new int[4][8]; 
		for(int bb=0; bb<4; bb++) {
			for(int cc=0; cc<8; cc++) {
				copy[bb] = magnets[bb].clone();
			}
		}
		if (terminated) return; // 이전의 계산에서 멈추라는 명령을 받았으면
		if (mag < 0 || mag > 3) return; // 범위 체크
		if(visited[mag]) return; // 방문 했으면 탐색 종료 
		visited[mag] = true; // 방문 체크 
		if(dir==1) updateCW(mag); // 시계 방향으로 회전하여 톱니바퀴 상태 업데이트 
		else if(dir==-1) updateCCW(mag); // 반시계 방향으로 
		
		if(mag-1>=0) { // 왼쪽으로 탐색 
			if(copy[mag-1][2] != copy[mag][6]) { //극이 다르면 
				dfs(new Node(mag-1, dir*(-1), false));
			}else if(copy[mag-1][2] == copy[mag][6]) { //극이 같으면 
				dfs(new Node(mag-1, dir, true));
			}
		}
		
		if(mag+1<=3) { // 오른쪽으로 탐색 
			if(copy[mag+1][6] != copy[mag][2]) { // 극이 다르면 
				dfs(new Node(mag+1, dir*(-1), false));
			}else if(copy[mag+1][6] == copy[mag][2]) { // 극이 같으면 
				dfs(new Node(mag+1, dir, true));
			}
		}
		
	}

	public static int getScore() { // 점수 계산 
		return magnets[0][0] * 1 + magnets[1][0] * 2 + magnets[2][0] * 4 + magnets[3][0] * 8;
	}

	public static void updateCW(int mag) { // 시계방향 회전 (ClockWise) 
		int[] copy = magnets[mag].clone();
		for (int i = 0; i < 8; i++) {
			if (i == 0)
				magnets[mag][i] = copy[7];
			else
				magnets[mag][i] = copy[i - 1];
		}

	}

	public static void updateCCW(int mag) { // 반시계 방향 회전 (CounterClockWise)
		int[] copy = magnets[mag].clone();
		for (int i = 0; i < 8; i++) {
			if (i == 7)
				magnets[mag][i] = copy[0];
			else
				magnets[mag][i] = copy[i + 1];
		}
	}

	public static class Node {
		int mag, dir;
		boolean terminated;
		Node(int mag, int dir, boolean terminated) {
			this.mag = mag;
			this.dir = dir;
			this.terminated = terminated;
		}
	}

}
