import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        
        int count = 0;
        for(int i = 0; i < record.length; i++){
            String[] strList = record[i].split(" ");
            if(strList[0].equals("Enter")){
                map.put(strList[1], strList[2]);
                count++;
                continue;
            }
            if(strList[0].equals("Change")){
                map.put(strList[1], strList[2]);
            }
            if(strList[0].equals("Leave")){
                count++;
            }
        }
        
        
        String[] answer = new String[count];
        int index = 0;
        for(int i = 0; i < record.length; i++){
            String[] strList = record[i].split(" ");
            if(strList[0].equals("Enter")){
                answer[index++] = map.get(strList[1]) + "님이 들어왔습니다.";
                continue;
            }
            if(strList[0].equals("Leave")){
                answer[index++] = map.get(strList[1]) + "님이 나갔습니다.";
            }
        }
        
        return answer;
    }
}
