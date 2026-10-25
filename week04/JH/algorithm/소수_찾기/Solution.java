import java.util.HashSet;
import java.util.Set;

class Solution {

    private final Set<Integer> candidates = new HashSet<>();

    public int solution(String numbers) {
        candidates.clear();

        boolean[] visited = new boolean[numbers.length()];
        makeNumbers(numbers, visited, new StringBuilder());

        int answer = 0;

        for (int number : candidates) {
            if (isPrime(number)) {
                answer++;
            }
        }

        return answer;
    }

    private void makeNumbers(
        String numbers,
        boolean[] visited,
        StringBuilder current
    ) {
        for (int i = 0; i < numbers.length(); i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            current.append(numbers.charAt(i));

            // 현재까지 조합한 숫자 저장
            candidates.add(Integer.parseInt(current.toString()));

            // 다음 숫자를 이어 붙이는 순열 탐색
            makeNumbers(numbers, visited, current);

            // 선택 이전 상태로 복구
            current.deleteCharAt(current.length() - 1);
            visited[i] = false;
        }
    }

    private boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        for (int divisor = 2; divisor * divisor <= number; divisor++) {
            if (number % divisor == 0) {
                return false;
            }
        }

        return true;
    }
}
