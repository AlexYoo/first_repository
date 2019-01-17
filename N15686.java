package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class N15686 {

	static int N, M, min = Integer.MAX_VALUE;
	static int[][] table;
	static boolean[][] visited;
	static ArrayList<Point> house, chickenHouse;
	static ArrayList<Point> chCopy;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sArr = br.readLine().split(" ");
		N = Integer.parseInt(sArr[0]);
		M = Integer.parseInt(sArr[1]); // M <= chicken house <= 13
		table = new int[N][N];
		visited = new boolean[M+1][M+1];
		house = new ArrayList<>();
		chickenHouse = new ArrayList<>();
		chCopy = new ArrayList<>();
		min = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			sArr= br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				table[i][j] = Integer.parseInt(sArr[j]);
				if(table[i][j] == 2) {
					chickenHouse.add(new Point(i,j));
				}else if(table[i][j] == 1) {
					house.add(new Point(i,j));
				}
			}
		}
		dfs(0,0,chickenHouse.size());
		
		System.out.println(min);
		
	}
	
	public static void dfs(int x, int y, int depth) {
		if(depth == M) {
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(table[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			chCopy.clear();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(table[i][j]==2) {
						chCopy.add(new Point(i,j));
					}
				}
			}
			if(getTotalSum(table, chCopy) < min)
				min = getTotalSum(table, chCopy);
			
			return;
		}
		
		for(int i=x; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(table[i][j]==2) {
					table[i][j] = 0;
					dfs(i,j,depth-1);
					table[i][j] = 2;
				}
			}
		}
	}
	
	public static int getTotalSum(int[][] t, ArrayList<Point> chs) {
		int sum=0;
		for(int i=0; i<house.size(); i++) {
			sum+=getNearestChickenDistance(house.get(i), chs);
		}
		return sum;
	}
	
	public static int getNearestChickenDistance(Point h, ArrayList<Point> ch) {
		int min = Integer.MAX_VALUE;
		for(int i=0; i<ch.size(); i++) {
			int cd = getChickenDistance(h, ch.get(i));
			if(cd < min) min = cd; 
		}
//		System.out.println("("+h.x + "," + h.y + ") " + min);
		return min;
	}
	
	public static int getChickenDistance(Point p1, Point p2) {
		return Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y);
	}
	
	public static class Point{
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
