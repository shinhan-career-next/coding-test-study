import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Integer[] arr = new Integer[citations.length];
        for(int i = 0; i < arr.length; i++){
            arr[i] = citations[i];
        }
        
        Arrays.sort(arr, Collections.reverseOrder());
        
        int start = 0;
        int end = arr[0];
        int mid = 0;
        
        while(start <= end){
            mid = (start + end) / 2;
            int count = 0;
            for(int i = 0; i < arr.length; i++){
                if(arr[i] >= mid){
                    if(++count >= mid){
                        answer = mid;
                        start = mid + 1;
                        break;
                    }
                }
            }
            if(count < mid){
                end = mid - 1;
            }
        }
        
        
        return answer;
    }
}
