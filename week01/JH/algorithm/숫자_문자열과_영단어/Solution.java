class Solution {
    public int solution(String s) {
        int answer = 0;
        int digit = 1;

        for (int i = s.length() - 1; i >= 0; i--) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                answer += (s.charAt(i) - '0') * digit;
                digit *= 10;
            } else {
                int j = 0;
                while ('a' <= s.charAt(i - j) && s.charAt(i - j) <= 'z') {
                    String word = s.substring(i - j, i + 1);
                    if (stringToInt(word) > -1) {
                        answer += stringToInt(word) * digit;
                        digit *= 10;
                        i = i - j;
                        j = -1;
                        break;
                    }
                    if (i - j == 0) break;
                    j++;
                }
            }
        }

        return answer;
    }

    public int stringToInt(String word) {
        int number = 0;
        switch (word) {
            case "zero" -> number = 0;
            case "one" -> number = 1;
            case "two" -> number = 2;
            case "three" -> number = 3;
            case "four" -> number = 4;
            case "five" -> number = 5;
            case "six" -> number = 6;
            case "seven" -> number = 7;
            case "eight" -> number = 8;
            case "nine" -> number = 9;
            default -> number = -1;
        }
        return number;
    }
}
