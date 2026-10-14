import java.util.*;

/*
 * [문제 정보]
 * - 문제명: 파일명 정렬
 * - 작성자: JH
 *
 * [1. 문제 이해]
 * - 파일명을 HEAD, NUMBER, TAIL 세 부분으로 분리
 * - HEAD의 대소문자를 구분하지 않고 1차 정렬
 * - HEAD가 같은 경우 NUMBER의 숫자값으로 2차 정렬
 * - HEAD와 NUMBER가 모두 같은 경우 입력 순서 유지
 *
 * [2. 접근 방법]
 * - Character.isDigit(file.charAt(index))로 숫자 여부 확인
 * - 첫 숫자의 위치를 HEAD의 끝으로 사용
 * - 최대 5자리 연속 숫자의 끝을 NUMBER의 끝으로 사용
 * - 각 파일을 {HEAD, NUMBER, TAIL} 형태의 2차원 배열에 저장
 * - Comparator를 사용해 HEAD와 NUMBER 순서로 안정 정렬
 *
 * [3. 구현 설계]
 * 1) 각 파일에서 HEAD와 NUMBER의 경계 인덱스 탐색
 * 2) substring()으로 HEAD, NUMBER, TAIL 분리
 * 3) compareToIgnoreCase()로 HEAD 비교
 * 4) HEAD가 같은 경우 Integer.parseInt()로 NUMBER 비교
 * 5) 정렬된 세 부분을 StringBuilder로 결합
 *
 * [4. 복잡도]
 * - 시간 복잡도: O(N log N) — 파일 정보 정렬 비용
 * - 공간 복잡도: O(N) — 분리한 파일 정보와 결과 배열 저장
 *
 * [5. 예외 케이스]
 * - NUMBER가 0으로 시작하는 경우 원본 문자열 유지
 * - TAIL이 없는 경우 빈 문자열로 처리
 * - HEAD와 NUMBER가 같은 파일의 기존 순서 유지
 *
 * [6. 회고 및 메서드 사용법]
 * - 문자 숫자 판별: Character.isDigit(text.charAt(index))
 * - 배열 초기화: String[] values = {"A", "B", "C"}
 * - 배열 정렬: Arrays.sort(array, comparator)
 * - 문자열 비교: compareTo(), 대소문자 무시 비교: compareToIgnoreCase()
 * - 문자열의 숫자 변환: Integer.parseInt(text)
 * - 문자열 누적: StringBuilder.append(value)
 * - StringBuilder 초기화: setLength(0), 역순 변환: reverse().toString()
 * - 숫자 비교 시 뺄셈보다 Integer.compare(a, b) 사용 권장
 */
class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        String[][] array = new String[files.length][3];

        // {HEAD, NUMBER, TAIL} 형태로 분리
        for (int i = 0; i < files.length; i++) {
            String file = files[i];
            int index1 = 0;
            int index2;

            // HEAD 시작부터 끝부분까지 탐색
            while (!Character.isDigit(file.charAt(index1))) {
                index1++;
            }

            // NUMBER 시작부터 최대 5자리까지 탐색
            index2 = index1;
            int len = file.length();
            while (index2 < len
                    && Character.isDigit(file.charAt(index2))
                    && index2 - index1 < 5) {
                index2++;
            }

            // HEAD, NUMBER, TAIL 분류
            String head = file.substring(0, index1);
            String number = file.substring(index1, index2);
            String tail = file.substring(index2);
            String[] arr = {head, number, tail};
            array[i] = arr;
        }

        // HEAD 기준 정렬 후 NUMBER 기준 정렬
        Arrays.sort(array, (a, b) -> {
            if (a[0].equalsIgnoreCase(b[0])) {
                return Integer.compare(Integer.parseInt(a[1]), Integer.parseInt(b[1]));
            }
            return a[0].compareToIgnoreCase(b[0]);
        });

        // HEAD + NUMBER + TAIL 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i][0]);
            sb.append(array[i][1]);
            sb.append(array[i][2]);
            answer[i] = sb.toString();
            sb.setLength(0);
        }

        return answer;
    }
}
