package codil;

import java.util.HashMap;
import java.util.Map;

public class codiltest_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Map<Integer, Integer> map = new HashMap<>();
		int[] A = {3,4,3,0,2,2,3,0,0};
		System.out.println(solution(A));
		
	}

	public static int solution(int[] ranks) {
		int len = ranks.length;
		int cnt = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<ranks.length; i++) {
			if(map.get(ranks[i])==null)
				map.put(ranks[i], 1);
			else
				map.put(ranks[i], map.get(ranks[i])+1);
		}
		for(int key : map.keySet()) {
			if(map.get(key+1) != null) 
				cnt+=map.get(key);
		}
		return cnt;
    }
}
