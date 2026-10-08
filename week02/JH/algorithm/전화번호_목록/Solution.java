import java.util.*;

/*
 * [문제 정보]
 * - 문제명: 전화번호 목록
 * - 작성자: JH
 *
 * [1. 문제 이해]
 * - 한 전화번호가 다른 전화번호의 접두어인지 확인
 * - 접두어 관계가 하나라도 존재하면 false 반환
 *
 * [2. 접근 방법]
 * - 전화번호 목록을 사전순으로 정렬
 * - 접두어 관계가 있다면 정렬 후 서로 인접하게 되는 성질 활용
 * - 인접한 두 번호만 startsWith()로 비교
 *
 * [3. 구현 설계]
 * 1) Arrays.sort()로 phone_book 정렬
 * 2) 현재 번호와 다음 번호를 순차 비교
 * 3) 다음 번호가 현재 번호로 시작하면 false 반환
 * 4) 모든 비교 통과 시 true 반환
 *
 * [4. 복잡도]
 * - 시간 복잡도: O(N log N) — 전화번호 정렬 비용
 * - 공간 복잡도: O(N) — 객체 배열 정렬 과정의 임시 공간 기준
 *
 * [5. 예외 케이스]
 * - 전화번호가 한 개인 경우 비교 없이 true 반환
 * - 길이가 다른 번호 사이의 접두어 관계 확인
 *
 * [6. 회고 및 메서드 사용법]
 * - Arrays.sort(array): 배열을 오름차순으로 정렬
 * - text.startsWith(prefix): text가 prefix로 시작하는지 확인
 */
class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }

        return true;
    }
}
