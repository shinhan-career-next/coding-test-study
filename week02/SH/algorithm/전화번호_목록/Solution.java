// public boolean startsWith(String prefix)
// : 지정한 문자열(prefix)로 시작하면 true, 아니면 false를 반환합니다.
// public boolean startsWith(String prefix, int offset)
// : 지정한 위치(offset, 인덱스)부터 시작하여 해당 문자열이 있는지 확인합니다.

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        
        for(int i = 1; i < phone_book.length; i++){
            if (phone_book[i].startsWith(phone_book[i - 1])) {
                return false;
            }
        }
        
        return true;
    }
}
