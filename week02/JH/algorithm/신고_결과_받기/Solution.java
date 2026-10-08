import java.util.*;

/*
 * [문제 정보]
 * - 문제명: 신고 결과 받기
 * - 작성자: JH
 *
 * [1. 문제 이해]
 * - 동일 사용자의 중복 신고는 1회로 처리
 * - k번 이상 신고된 사용자를 정지하고 신고자에게 처리 결과 메일 발송
 * - id_list 순서에 맞춘 사용자별 메일 수 반환
 *
 * [2. 접근 방법]
 * - 신고당한 사용자를 key, 신고자 집합을 value로 갖는 HashMap 사용
 * - 신고자 집합에 HashSet을 사용해 중복 신고 제거
 * - 정지 기준을 충족한 사용자의 신고자별 메일 수 집계
 *
 * [3. 구현 설계]
 * 1) 신고 기록을 분리해 신고당한 사용자별 신고자 Set 구성
 * 2) Set 크기가 k 이상인 경우 신고자별 메일 수 증가
 * 3) id_list 순서대로 메일 수를 answer에 저장
 *
 * [4. 복잡도]
 * - 시간 복잡도: O(R + U) — 신고 기록 R개와 사용자 U명 순회
 * - 공간 복잡도: O(R + U) — 중복 제거된 신고 관계와 메일 수 저장
 *
 * [5. 예외 케이스]
 * - 동일 신고자가 같은 사용자를 여러 번 신고한 경우 1회만 반영
 * - 신고 또는 메일 기록이 없는 사용자는 0 반환
 *
 * [6. 회고 및 Map/Set 사용법]
 * - Set.add(value): 요소 추가 및 중복 발생 시 false 반환
 * - Map.put(key, value): 신규 값 저장 또는 기존 값 덮어쓰기
 * - Map.get(key): key에 대응하는 value 조회
 * - Map.entrySet(): key와 value를 함께 순회할 때 사용
 */
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        HashMap<String, Set<String>> map = new HashMap<>();

        for (int i = 0; i < report.length; i++) {
            String[] arr = report[i].split(" ");

            String reporter = arr[0];
            String reported = arr[1];

            if (map.containsKey(reported)) {
                Set<String> set = map.get(reported);

                if (!set.contains(reporter)) {
                    set.add(reporter);
                }
            } else {
                Set<String> set = new HashSet<>();
                set.add(reporter);

                map.put(reported, set);
            }
        }

        HashMap<String, Integer> hm = new HashMap<>();

        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            Set<String> set = entry.getValue();

            if (set.size() >= k) {
                for (String name : set) {
                    if (hm.containsKey(name)) {
                        hm.put(name, hm.get(name) + 1);
                    } else {
                        hm.put(name, 1);
                    }
                }
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];

            if (hm.containsKey(id)) {
                answer[i] = hm.get(id);
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }
}
