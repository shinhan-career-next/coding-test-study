import java.util.*;

class Solution {
    Set<Integer> set = new HashSet<>();
    boolean[] visited;
    String nums;

    public int solution(String numbers) {
        nums = numbers;
        visited = new boolean[numbers.length()];
        dfs("");

        return set.size();
    }

    public void dfs(String current) {
        if (!current.isEmpty()) {
            int num = Integer.parseInt(current);
            if(isPrime(num)){
                set.add(num);
            }
        }

        for (int i = 0; i < nums.length(); i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            dfs(current + nums.charAt(i));
            visited[i] = false;
        }
    }

    public boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) return false;
        }

        return true;
    }
}
