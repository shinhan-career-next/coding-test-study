/*
 * [2. 접근 방법]
 * - 고려한 방법:
 * - 선택한 알고리즘/자료구조: 완전탐색
 * - 이 방법을 선택한 이유: s길이가 1000밖에 안돼서 시간초과는 안날 거라 판단
 *
 *
 * [5. 예외 케이스]
 * - 빈 값/최솟값/최댓값:
 * - 중복 또는 경계 조건: s 길이가 1일 때 따로 예외 처리함.
 *
 * [6. 회고]
 * - 막혔던 부분:
 *  비교 메서드 equals()
 *  문자열 자르기 substring(start, end)
 * - 개선할 점 또는 다른 풀이:
 */
class Solution {
    // 문제에서 요구하는 solution 메서드를 작성합니다.
    class Solution {
        public int solution(String s) {
            int min = Integer.MAX_VALUE;

            if(s.length() == 1){
                return 1;
            }

            for(int i = 1; i <= s.length() / 2; i++){
                StringBuilder sb = new StringBuilder();
                int count = 1;
                int j = 0;
                for(; j <= s.length() - 2 * i; j += i){
                    if(s.substring(j, j + i).equals(s.substring(j + i, j + 2 * i))){
                        count++;
                    }else{
                        if(count != 1){
                            sb.append(Integer.toString(count));
                        }
                        sb.append(s.substring(j, j + i));
                        count = 1;
                    }
                }

                if(count != 1){
                    sb.append(Integer.toString(count));
                }
                sb.append(s.substring(j, j + i));

                for(int k = j + i; k < s.length(); k++){
                    sb.append(s.charAt(k));
                }

                if(min > sb.length()){
                    min = sb.length();
                }
            }

            return min;
        }
    }

}
