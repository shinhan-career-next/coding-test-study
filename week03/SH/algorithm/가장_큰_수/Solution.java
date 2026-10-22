/*
(a+b).compareTo(b+a)
생각 못함...
*/

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        int count = 0;
        
        String[] strings = new String[numbers.length];
        for(int i = 0; i < strings.length; i++){
            strings[i] = Integer.toString(numbers[i]);
            if(numbers[i] == 0) {
                count++;
            }
        }
        
        if(count == numbers.length) return "0";
        
        Arrays.sort(strings, (a, b) -> {
            return -(a+b).compareTo(b+a);
        });
        
        for(int i = 0; i < strings.length; i++){
            answer.append(strings[i]);
        }
        
        
        return answer.toString();
    }
}
