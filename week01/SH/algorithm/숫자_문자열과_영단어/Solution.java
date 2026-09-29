/*
 *
 * [2. 접근 방법]
 * - 고려한 방법:
 * - 선택한 알고리즘/자료구조: 조건문을 활용한 하드코딩
 * - 이 방법을 선택한 이유: 성능 상 하자가 없고 실전에서도 다른 방법이 생각 안나면 고민할 시간에 그냥 작성했을 거 같아요.
 *
 * [6. 회고]
 * - 막혔던 부분:
 * - 개선할 점 또는 다른 풀이: replaceAll 사용하는 풀이
 */
class Solution {
    public int solution(String s) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < s.length(); i++){
            switch(s.charAt(i)){
                case 'z':
                    i += 3;
                    sb.append('0');
                    break;
                case 'o':
                    i += 2;
                    sb.append('1');
                    break;
                case 't':
                    if(s.charAt(i + 1) == 'w'){
                        i += 2;
                        sb.append('2');
                    }else{
                        i += 4;
                        sb.append('3');
                    }
                    break;
                case 'f':
                    if(s.charAt(i + 1) == 'o'){
                        i += 3;
                        sb.append('4');
                    }else{
                        i += 3;
                        sb.append('5');
                    }
                    break;
                case 's':
                    if(s.charAt(i + 1) == 'i'){
                        i += 2;
                        sb.append('6');
                    }else{
                        i += 4;
                        sb.append('7');
                    }
                    break;
                case 'e':
                    i += 4;
                    sb.append('8');
                    break;
                case 'n':
                    i += 3;
                    sb.append('9');
                    break;
                default:
                    sb.append(s.charAt(i));
            }
        }

        int answer = Integer.parseInt(sb.toString());
        return answer;
    }
}
