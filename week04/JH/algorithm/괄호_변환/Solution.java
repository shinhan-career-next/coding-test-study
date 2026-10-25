class Solution {

    public String solution(String p) {
        return convert(p);
    }

    private String convert(String w) {
        // 빈 문자열 처리
        if (w.isEmpty()) {
            return "";
        }

        // 가장 작은 균형잡힌 문자열 u와 나머지 v로 분리
        int balance = 0;
        int splitIndex = 0;

        for (int i = 0; i < w.length(); i++) {
            balance += w.charAt(i) == '(' ? 1 : -1;

            if (balance == 0) {
                splitIndex = i + 1;
                break;
            }
        }

        String u = w.substring(0, splitIndex);
        String v = w.substring(splitIndex);

        // u가 올바른 문자열인 경우
        if (isCorrect(u)) {
            return u + convert(v);
        }

        // u가 올바르지 않은 문자열인 경우
        StringBuilder result = new StringBuilder();

        result.append('(');
        result.append(convert(v));
        result.append(')');

        // u의 앞뒤 문자 제거 후 괄호 방향 변환
        for (int i = 1; i < u.length() - 1; i++) {
            result.append(u.charAt(i) == '(' ? ')' : '(');
        }

        return result.toString();
    }

    private boolean isCorrect(String value) {
        int balance = 0;

        for (int i = 0; i < value.length(); i++) {
            balance += value.charAt(i) == '(' ? 1 : -1;

            // 닫는 괄호가 먼저 나온 경우
            if (balance < 0) {
                return false;
            }
        }

        return balance == 0;
    }
}
