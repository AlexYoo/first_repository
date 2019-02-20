package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class N5373 {

	static String[] command;
	static char[][] cube = new char[6][9]; 
	static Map<String, String> map = new HashMap<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sArr = br.readLine().split(" ");
		int tc = Integer.parseInt(sArr[0]);
		for (int aa = 0; aa < tc; aa++) {
			initMapAndCube(); // 초기화 
			int n = Integer.parseInt(br.readLine());
			command = new String[n];
			command = br.readLine().split(" ");
			
			for(int i=0; i<command.length; i++)
				execute(command[i]);
			
			for(int i=0; i<9; i++) {
				System.out.print(cube[0][i]);
				if((i+1)%3==0) System.out.println();
			}
		}
	}
	
	public static void execute(String command) {
		String wholeText = map.get(command);
		String[] sArr = wholeText.split("/");
		char[][] cubeCopy = new char[6][9];
		for(int i=0; i<6; i++) 
			cubeCopy[i] = cube[i].clone();
		
		for(int i=1; i<5; i++) {
			int cur_side = sArr[i].charAt(0) - '0'; // side
			int cur_p1 = sArr[i].charAt(1) - '0'; // piece 1
			int cur_p2 = sArr[i].charAt(2) - '0'; // piece 2
			int cur_p3 = sArr[i].charAt(3) - '0'; // piece 3
			
			int pre_side = sArr[i-1].charAt(0) - '0'; // side
			int pre_p1 = sArr[i-1].charAt(1) - '0'; // piece 1
			int pre_p2 = sArr[i-1].charAt(2) - '0'; // piece 2
			int pre_p3 = sArr[i-1].charAt(3) - '0'; // piece 3
			
			cube[cur_side][cur_p1] = cubeCopy[pre_side][pre_p1];
			cube[cur_side][cur_p2] = cubeCopy[pre_side][pre_p2];
			cube[cur_side][cur_p3] = cubeCopy[pre_side][pre_p3];
		}
		
		// 자체 회전도 고려해주어야 한다.
		int side = Integer.parseInt(sArr[5]); // side 정보 
		if(command.charAt(1)=='-') { // 반시계 방향 
			cube[side][6] = cubeCopy[side][0]; cube[side][3] = cubeCopy[side][1];
			cube[side][0] = cubeCopy[side][2]; cube[side][1] = cubeCopy[side][5];
			cube[side][2] = cubeCopy[side][8]; cube[side][5] = cubeCopy[side][7];
			cube[side][8] = cubeCopy[side][6]; cube[side][7] = cubeCopy[side][3];
		}else if(command.charAt(1)=='+') { // 시계방향 
			cube[side][2] = cubeCopy[side][0]; cube[side][5] = cubeCopy[side][1];
			cube[side][8] = cubeCopy[side][2]; cube[side][7] = cubeCopy[side][5];
			cube[side][6] = cubeCopy[side][8]; cube[side][3] = cubeCopy[side][7];
			cube[side][0] = cubeCopy[side][6]; cube[side][1] = cubeCopy[side][3];
		}
	}

	public static void initMapAndCube() { //abcd => a:cube side, bcd : side index
		// 전역 map에다가 모든 경우의 수를 담아 놓는다. -> 수정하기 편함!! 
		map.put("U-", "5012/3012/4012/2012/5012/0"); map.put("U+", "5012/2012/4012/3012/5012/0");
		map.put("D-", "5678/2678/4678/3678/5678/1"); map.put("D+", "5678/3678/4678/2678/5678/1");
		map.put("F-", "0678/4852/1210/5036/0678/2"); map.put("F+", "0678/5036/1210/4852/0678/2");
		map.put("B-", "0012/5258/1876/4630/0012/3"); map.put("B+", "0012/4630/1876/5258/0012/3");
		map.put("L-", "0036/3852/1036/2036/0036/4"); map.put("L+", "0036/2036/1036/3852/0036/4");
		map.put("R-", "0258/2258/1258/3630/0258/5"); map.put("R+", "0258/3630/1258/2258/0258/5");
		
		for(int i=0; i<6; i++) { // 큐브 초기화 
			for(int j=0; j<9; j++) {
				char c = ' ';
				if(i==0) c = 'w'; else if(i==1) c = 'y';
				else if(i==2) c = 'r'; else if(i==3) c = 'o';
				else if(i==4) c = 'g'; else if(i==5) c = 'b';
				cube[i][j] = c;
			}
		}
	}
}
