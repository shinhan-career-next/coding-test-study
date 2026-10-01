/*
 * [문제 정보]
 * - 문제명: 신규 아이디 추천
 * - 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/72410
 * - 작성자:
 *
 * [1. 문제 이해]
 * - 입력값과 출력값: "...!@BaT#*..y.abcdefghijklm" -> "bat.y.abcdefghi"
 * - 핵심 조건 및 제한사항:
 * new_id는 길이 1 이상 1,000 이하인 문자열입니다.
 * new_id는 알파벳 대문자, 알파벳 소문자, 숫자, 특수문자로 구성되어 있습니다.
 * new_id에 나타날 수 있는 특수문자는 -_.~!@#$%^&*()=+[{]}:?,<>/ 로 한정됩니다.
 *
 * - 반드시 만족해야 하는 규칙:
 *
 *
 *
 * [2. 접근 방법]
 * - 고려한 방법:
 * - 선택한 알고리즘/자료구조:
 * - 이 방법을 선택한 이유:
 *
 * [3. 구현 설계]
 * - 처리 순서:
 *   1)
 *   2)
 *   3)
 * - 핵심 불변식 또는 정답을 보장하는 근거:
 *
 * [4. 복잡도]
 * - 시간 복잡도:
 * - 공간 복잡도:
 *
 * [5. 예외 케이스]
 * - 빈 값/최솟값/최댓값:
 * - 중복 또는 경계 조건:
 *
 * [6. 회고]
 * - 막혔던 부분
 * 1. 소문자 치환 : String 변수명 = 문자열.toLowerCase();
 * 2. 문자열 내 특정 문자 삭제 : String 변수명 = 문자열.replace("_", "");
 * 3. 문자열 내 특정 문자 삭제 : String 변수명 = 문자열.replaceAll("[^a-z0-9._-]", "");
 * 4. 정규식 : [abc] -> a,b,c 중 하나 | [a-z0-9] : a~z, 0~9 | [^...] 대괄호 안을 제외한 문자 |
 * - 개선할 점 또는 다른 풀이:
 */

class Solution {
    public String solution(String new_id) {
        String answer = "";


        // 아이디 길이 : 3~15자
        // 소문자, 숫자, -, _, . 만 사용 가능
        // 단 .은 처음과 끝에 사용 불가능

        // 1단계 : 모든 문자를 소문자로 치환
        String lower = new_id.toLowerCase();


        // 2단계 : "소문자, 숫자, -, _, ." 빼고 다 삭제
        String newString = lower.replaceAll("[^a-z0-9-_\\.]", "");



        // 3단계 : 마침표 2연속 이상 1개로 축소
        newString = newString.replaceAll("\\.+", ".");


        // 4단계 : 마침표가 맨앞 or 맨뒤에 있다면 삭제
        if(!newString.isEmpty() && newString.charAt(0) == '.'){
            newString = newString.substring(1);
        }

        if(!newString.isEmpty() && newString.charAt(newString.length() - 1) == '.'){
            newString = newString.substring(0, newString.length() - 1);
        }


        // 5단계 : 빈 문자열이라면 -> a 추가
        if(newString.isEmpty()){
            newString = "a";
        }


        // 6단계 : 길이가 16자 이상이라면 처음부터 15자까지 잘라 & 맨 마지막이 마침표라면 삭제
        if(newString.length() > 15){
            newString = newString.substring(0,15);
        }

        if(!newString.isEmpty() && newString.charAt(newString.length() - 1) == '.'){
            newString = newString.substring(0, newString.length() - 1);
        }



        // 7단계 : 길이가 2자리 이하면 마지막 문자를 길이 3이 될 때까지 반복
        while(newString.length() < 3){
            newString = newString + newString.charAt(newString.length()-1);
        }

        answer = newString;

        return answer;
    }
}