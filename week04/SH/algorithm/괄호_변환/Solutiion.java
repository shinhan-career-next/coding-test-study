class Solution {
    public String solution(String p) {
        return funcW(p);
    }
    
    public String funcW(String s){
        if(s.length() == 0) return s;
        
        int left = 0;
        int right = 0;
        boolean isCorrect = true;
        
        int i;
        for(i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                left++;
            }else if(s.charAt(i) == ')'){
                right++;
            }
            
            if(right > left){
                isCorrect = false;
            }else if(left == right){
                i++;
                break;
            }
        }
        return isCorrect ?
            s.substring(0, i) + funcW(s.substring(i, s.length())) :
            '(' + funcW(s.substring(i, s.length())) + ')' + reverse(s.substring(1, i - 1));
    }
    
    public String reverse(String s){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            sb.append(s.charAt(i) == '(' ? ')' : '(');
        }
        return sb.toString();
    }
}
