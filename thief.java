package dp;

public class thief {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {1,2,3,1};
		System.out.println(solution(A));
	}

	public static int solution(int[] money) {
		int answer = 0;
		int len = money.length;
		int[] dp = new int[len];

		dp[0] = money[0];
		dp[1] = Math.max(money[0], money[1]);
		for (int i = 2; i < len - 1; i++)
			dp[i] = Math.max(dp[i - 2] + money[i], dp[i - 1]);
		answer = dp[len - 2];

		dp[0] = 0;
		dp[1] = money[1];
		for (int i = 2; i < len; i++)
			dp[i] = Math.max(dp[i - 2] + money[i], dp[i - 1]);
		answer = Math.max(answer, dp[len - 1]);
		return answer;
	}

}
