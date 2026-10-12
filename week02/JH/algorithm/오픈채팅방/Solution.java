import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
 * [문제 정보]
 * - 문제명: 오픈채팅방
 * - 작성자: JH
 *
 * [1. 문제 이해]
 * - 사용자 ID별 최종 닉네임을 기준으로 입장/퇴장 메시지 출력 필요
 * - 닉네임 변경 기록 자체는 메시지에서 제외
 *
 * [2. 접근 방법]
 * - 첫 번째 순회에서 Enter와 Change 기록의 최종 닉네임 저장
 * - 두 번째 순회에서 Enter와 Leave 기록을 최종 닉네임으로 메시지화
 * - 사용자 ID와 닉네임의 대응 관계 저장을 위한 HashMap 사용
 * - 가변적인 출력 메시지 저장을 위한 ArrayList 사용 후 배열 변환
 *
 * [3. 구현 설계]
 * 1) record를 순회하며 Enter/Change의 사용자 ID와 닉네임을 map에 저장
 * 2) record를 다시 순회하며 Enter/Leave 메시지를 answer에 추가
 * 3) answer를 String 배열로 변환 후 반환
 *
 * [4. 복잡도]
 * - 시간 복잡도: O(N) — record 두 번 순회
 * - 공간 복잡도: O(N) — 사용자별 닉네임과 출력 메시지 저장
 *
 * [5. 예외 케이스]
 * - 동일 사용자가 닉네임을 여러 번 변경한 경우 마지막 닉네임 사용
 * - Change 기록은 결과 메시지에서 제외
 *
 * [6. 회고 및 컬렉션 사용법]
 * - ArrayList와 HashMap의 메서드가 익숙하지 않아 구현 과정에서 시간 소요
 * - ArrayList: add(value)로 추가, get(index)로 조회, size()로 크기 확인
 * - HashMap: put(key, value)로 저장/수정, get(key)로 값 조회
 * - List<String>을 배열로 변환할 때 toArray(new String[0]) 사용
 */
class Solution {
    public String[] solution(String[] record) {
        List<String> answer = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < record.length; i++) {
            String[] arr = record[i].split(" ");
            if (arr[0].equals("Enter")) {
                map.put(arr[1], arr[2]);
            } else if (arr[0].equals("Change")) {
                map.put(arr[1], arr[2]);
            }
        }

        for (int i = 0; i < record.length; i++) {
            String[] arr = record[i].split(" ");
            if (arr[0].equals("Enter")) {
                answer.add(map.get(arr[1]) + "님이 들어왔습니다.");
            } else if (arr[0].equals("Leave")) {
                answer.add(map.get(arr[1]) + "님이 나갔습니다.");
            }
        }

        return answer.toArray(new String[0]);
    }
}
