/*
1. 커스텀 정렬 : Arrays.sort(list, (a,b) -> {});
    1-1. return -1 일때 a가 b보다 앞으로 옴.
2. 대문자로 변경 : a.toUpperCase();
3. 문자 비교 함수 : a.compareTo(b);
4. index찾기 : Arrays.asList(files).indexOf(a);
*/

import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        for(int i = 0; i < files.length; i++){
            answer[i] = files[i];
        }
        
        Arrays.sort(answer, (a,b) -> {
            //HEAD부터 비교
            int aIndex = 0; 
            int bIndex = 0;
            while(true){
                if(a.charAt(aIndex) >= '0' && a.charAt(aIndex) <= '9'){
                    break;
                }
                aIndex++;
            }
            
            while(true){
                if(b.charAt(bIndex) >= '0' && b.charAt(bIndex) <= '9'){
                    break;
                }
                bIndex++;
            }
            
            String aHead = a.substring(0, aIndex).toUpperCase();
            String bHead = b.substring(0, bIndex).toUpperCase();
            
            if(!aHead.equals(bHead)){
                return aHead.compareTo(bHead);
            }
            
            // NUMBER 비교
            int aStartIndex = aIndex;
            int bStartIndex = bIndex;
            
            while(aIndex < a.length()){
                if(a.charAt(aIndex) < '0' || a.charAt(aIndex) > '9'){
                    break;
                }
                aIndex++;
            }
            
            while(bIndex < b.length()){
                if(b.charAt(bIndex) < '0' || b.charAt(bIndex) > '9'){
                    break;
                }
                bIndex++;
            }
            int aN = Integer.parseInt(a.substring(aStartIndex, aIndex));
            int bN = Integer.parseInt(b.substring(bStartIndex, bIndex));
            
            if(aN != bN){
                return aN < bN ? -1 : 1;
            }
            
            //HEAD, NUMBER 둘 다 같으면
            int aI = Arrays.asList(files).indexOf(a);
            int bI = Arrays.asList(files).indexOf(b);
            
            return aI < bI ? -1 : 1;
        });
        
        
        return answer;
    }
}


/*
개인적인 풀이과정

1. 커스텀 정렬 (Comparator) 써야겠다 생각함.
2. 입력형식 확인함 files는 1000 이하, 파일명은 100 글자 이하니까
    정렬하는 데 N log(N), N=1000 걸릴테니
    Comparator 안에서는 N, N=100 까지 써도 되겠다 생각함   
3. 숫자를 찾아야 하니까 앞에서부터 '0' 이상 '9' 이하를 찾음 (index를 저장하면서)
4. 숫자 전까지(HEAD) 비교, 다르면 return aHead.compareTo(bHead);
5. 숫자(NUMBER) 비교, 다르면 return aN < bN ? -1 : 1;
6. HEAD, NUMBER 둘 다 같으면 당연히 원래 위치대로 나올 줄 알았는데? 막 섞여 나옴.
7. files에서 문자열 찾아서 위치(index)를 기준으로 정렬 기준을 만들어줘야겠다
8. files 배열 하나로 하니까 정렬 과정에서 원래 index값이 훼손돼서 answer[] 라는 새로운 배열 만듦.

*/
