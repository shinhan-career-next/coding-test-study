def solution(record):
    answer = []
    uid = {}
    
    for i in range(len(record)):
        record[i] = record[i].split()
        
        if record[i][0] != 'Leave':
            uid[record[i][1]] = record[i][2]
                
    for i in range(len(record)):
        if record[i][0] == 'Enter':
            answer.append(f'{uid[record[i][1]]}님이 들어왔습니다.')
        elif record[i][0] == 'Leave':
            answer.append(f'{uid[record[i][1]]}님이 나갔습니다.')
            
    return answer