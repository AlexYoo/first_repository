package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N13458 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine()); // 시험장의 개수 
		long cnt = N; // 정감독의 수를 미리 카운팅 
		
		String[] sArr = br.readLine().split(" ");
		String[] sArr2 = br.readLine().split(" ");
		
		long B = Long.parseLong(sArr2[0]);
		long C = Long.parseLong(sArr2[1]);
		
		for(int i=0; i<N; i++) {
			long a = Long.parseLong(sArr[i])-B; //부감독(들)이 봐야하는 수강생 수 
			if(a>0) {  
				if(a%C==0) cnt += a/C;
				else cnt+=a/C+1;
			}
		}
		System.out.println(cnt+"");
		
	}

}
