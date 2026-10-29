import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int len = citations.length;
        int[] arr = new int[len];
        Arrays.sort(citations);

        for (int i = 0; i < len; i++) {
            arr[i] = citations[len - i - 1];
        }

        // System.out.println(Arrays.toString(arr));

        // for (int i = 0; i < len; i++) {
        //     if (i + 1 == arr[i]) {
        //         return i + 1;
        //     } else if (i > arr[i]) {
        //         return i;
        //     }
        //     return
        // }

        return answer;
    }
}
