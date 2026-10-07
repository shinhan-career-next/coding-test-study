def solution(s):
    answer = len(s)
    
    for unit in range(1, len(s) // 2 + 1):
        chunks = []
        
        for i in range(0, len(s), unit):
            chunks.append(s[i:i + unit])
            
        count = 1
        result = ''
        
        for i in range(1, len(chunks)):
            if chunks[i - 1] == chunks[i]:
                count += 1
            else:
                if count >= 2:
                    result += str(count)
                    result += chunks[i - 1]
                    count = 1
                else:
                    result += chunks[i - 1]
        
        if count >= 2:
            result += str(count)
            result += chunks[-1]
        else:
            result += chunks[-1]
        
        if len(result) < answer:
            answer = len(result)
        
    return answer