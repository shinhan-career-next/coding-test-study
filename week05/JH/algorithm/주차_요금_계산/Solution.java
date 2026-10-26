// AI의 도움을 일부 받음.
// IN, OUT에 따라 어떻게 해야 할지 몰랐는데 IN일 때는 Map에 넣고 OUT일 때 시간을 처리하는 방식이 가장 좋았음.

import java.util.*;

class Solution {

    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inTimes = new HashMap<>();
        Map<String, Integer> totalTimes = new HashMap<>();

        // 입·출차 기록 처리
        for (String record : records) {
            String[] parts = record.split(" ");

            int time = toMinutes(parts[0]);
            String carNumber = parts[1];
            String status = parts[2];

            if (status.equals("IN")) {
                inTimes.put(carNumber, time);
            } else {
                int inTime = inTimes.remove(carNumber);
                int parkingTime = time - inTime;

                totalTimes.put(
                    carNumber,
                    totalTimes.getOrDefault(carNumber, 0) + parkingTime
                );
            }
        }

        // 출차 기록이 없는 차량 처리
        int endOfDay = toMinutes("23:59");

        for (Map.Entry<String, Integer> entry : inTimes.entrySet()) {
            String carNumber = entry.getKey();
            int inTime = entry.getValue();
            int parkingTime = endOfDay - inTime;

            totalTimes.put(
                carNumber,
                totalTimes.getOrDefault(carNumber, 0) + parkingTime
            );
        }

        // 차량 번호 오름차순 정렬
        List<String> carNumbers =
            new ArrayList<>(totalTimes.keySet());

        Collections.sort(carNumbers);

        // 차량별 요금 계산
        int[] answer = new int[carNumbers.size()];

        for (int i = 0; i < carNumbers.size(); i++) {
            String carNumber = carNumbers.get(i);
            int totalTime = totalTimes.get(carNumber);

            answer[i] = calculateFee(fees, totalTime);
        }

        return answer;
    }

    private int toMinutes(String time) {
        String[] parts = time.split(":");

        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        return hour * 60 + minute;
    }

    private int calculateFee(int[] fees, int totalTime) {
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        if (totalTime <= baseTime) {
            return baseFee;
        }

        int extraTime = totalTime - baseTime;
        int units = (extraTime + unitTime - 1) / unitTime;

        return baseFee + units * unitFee;
    }
}
