/*
 * [6. 회고]
 * - 막혔던 부분:
 *  1. String[] strList = String.split(s) : s를 기준으로 String을 나눔
 *  2. Map.getOrDeafault(a, b) : map.get(a) 가 null이라면 b, null 이 아니라면 map.get(a)를 반환
 *  3. 삽입 : Map.put(a,b),  Set.add(a)
 *  4. Map.values() : Map에서 value들만 가져옴
 * - 개선할 점 또는 다른 풀이:
 */

import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        HashMap<String, HashSet<String>> reportMap = new HashMap<>();
        HashMap<String, Integer> countMap = new HashMap<>();
        
        for(int i = 0; i <  report.length; i++){
            String[] strList = report[i].split(" ");
            HashSet<String> set = reportMap.getOrDefault(strList[0], new HashSet<String>());
            set.add(strList[1]);
            reportMap.put(strList[0], set);
        }
        
        for(HashSet<String> set : reportMap.values()){
            for(String s : set){
                countMap.put(s, countMap.getOrDefault(s, 0) + 1);
            }
        }
        
        for(int i = 0; i < id_list.length; i++){
            if(reportMap.get(id_list[i]) != null){
                for(String s : reportMap.get(id_list[i])){
                    if(countMap.get(s) >= k){
                        answer[i]++;
                    }
                }
            }
        }
        
        return answer;
    }
}
