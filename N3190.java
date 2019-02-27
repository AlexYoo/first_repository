package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class N3190 {
	
	static int N, K, L, curDirec, headX, headY, cnt;
	static final int[] dx = {0,1,0,-1};
	static final int[] dy = {1,0,-1,0};
	static int[][] table;
	static ArrayList<Node> command = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		table = new int[N][N];
		curDirec = 0; headX = 0; headY = 0; cnt = 0;
		for(int i=0; i<K; i++) {
			String[] sArr = br.readLine().split(" ");
			int x = Integer.parseInt(sArr[0])-1;
			int y = Integer.parseInt(sArr[1])-1;
			table[x][y] = 1; // 사과를 놓음 
		}
		L = Integer.parseInt(br.readLine());
		int[] secArr = new int[L];
		String[] direcArr = new String[L];
		for(int i=0; i<L; i++) {
			String[] sArr = br.readLine().split(" ");
			secArr[i] = Integer.parseInt(sArr[0]);
			direcArr[i] = sArr[1];
		}
		
		for(int i=0; i<L; i++) {
			if(i!=0)
				command.add(new Node(secArr[i]-secArr[i-1], direcArr[i]));
			else
				command.add(new Node(secArr[i], direcArr[i]));
		}
		
		execute();
		System.out.println(cnt);
	}
	
	public static int execute() {
		ArrayList<Node> snakeList = new ArrayList<>();
		snakeList.add(new Node(0,0));
		for(int aa=0; aa<command.size(); aa++) {
			int sec = command.get(aa).sec;
			String direc = command.get(aa).direc;
			for(int i=0; i<sec; i++) {
				cnt++; // 초 증가 
				headX += dx[curDirec];
				headY += dy[curDirec];
				if(headX < 0 || headX>=N || headY<0 || headY>=N) // 범위를 벗어남 
					return cnt;

				for(int bb=0; bb<snakeList.size(); bb++) //자기 몸에 부딪힘 
					if(headX==snakeList.get(bb).x && headY==snakeList.get(bb).y)
						return cnt;
				
				if(table[headX][headY] == 1) {
					snakeList.add(new Node(headX,headY));
					table[headX][headY] = 0;
				} else if(table[headX][headY]==0){
					snakeList.add(new Node(headX, headY));
					snakeList.remove(0);
				}
			}
			if(direc.equals("L")) { // 좌회전 
				curDirec = (curDirec+3)%4;
			}else if(direc.equals("D")) { // 우회전 
				curDirec = (curDirec+5)%4;
			}

			if(aa==command.size()-1) { // 마지막 명령일때만 계속 진행 
				while(true) {
					cnt++; // 초 증가 
					headX+=dx[curDirec];
					headY+=dy[curDirec];
					
					if(headX < 0 || headX>=N || headY<0 || headY>=N) // 범위를 벗어남 
						return cnt;
					for(int bb=0; bb<snakeList.size(); bb++) //자기 몸에 부딪힘 
						if(headX==snakeList.get(bb).x && headY==snakeList.get(bb).y)
							return cnt;
					if(table[headX][headY] == 1) {
						snakeList.add(new Node(headX,headY));
						table[headX][headY] = 0;
					} else if(table[headX][headY]==0){
						snakeList.add(new Node(headX, headY));
						snakeList.remove(0);
					}
					
				}
			}
		} // end of for
		
		return cnt;
	}
	
	
	public static class Node{
		int x, y;
		int sec;
		String direc;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
		Node(int sec, String direc){
			this.sec = sec;
			this.direc = direc;
		}
	}

}
