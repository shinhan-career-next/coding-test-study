# [문제 정보]
# - 문제명:오픈채팅방
# - 문제 링크:https://school.programmers.co.kr/learn/courses/30/lessons/42888
# - 작성자:이지연
#
# [1. 문제 이해]
# - 입력값과 출력값:["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"], 출력:["Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."]
# - 핵심 조건 및 제한사항:record 길이 1~100000, 유저는 uid로 구분하고 닉네임은 중복 가능
# - 반드시 만족해야 하는 규칙: 이름이 변경된 것도 전체 메세지에 반영되어야 한다.Change는 메시지를 남기지 않는다.
#
#
# [2. 접근 방법]
# - 고려한 방법: 딕셔너리 사용, 배열 사용해서 변환하기
# - 선택한 알고리즘/자료구조: 딕셔너리, 배열, 튜플 , for문
# - 이 방법을 선택한 이유: 아이디 - 닉네임을 쌍으로 저장해아 하기 때문
#
# [3. 구현 설계]
# - 처리 순서:
#   1) 기존 record for문으로 하나씩 받아 분해한다.(split 사용)
#   2) 'Enter','Change' 로 메세지가 시작할 때만 아이디와 닉네임이 함께 전달되므로 이때만 딕셔너리에 저장하면 된다. 
#   3) Enter, Leave 일 때만 메세지가 출력되므로 조건에 맞게 배열(1) result에 append한다. 이때 닉네임이 아닌 아이디를 append해야 마지막에 한번에 처리할 수 있다.
#   4) result는 튜플(a,b) 형태로 저장되어야 나중에 아이디 a부분만 닉네임으로 변경할 수 있다!
#   5) answer 배열에 아이디-> 닉네임으로 딕셔너리를 이용해 변경하여 메세지를 appned한 후 return한다.
# - 핵심 불변식 또는 정답을 보장하는 근거:
#
# [4. 복잡도]
# - 시간 복잡도:O(N) - record 두 번 순회, dict 조회는 O(1)
# - 공간 복잡도:O(N) - my_dict, result, answer 모두 최대 N개
#
#
# [5. 예외 케이스]
# - 빈 값/최솟값/최댓값:
# - 중복 또는 경계 조건: 'Enter','Change' 메세지일 때만 아이디-닉네임 쌍을 만든다.
#
# [6. 회고]
# - 막혔던 부분: 처음에 아이디가 아닌 닉네임으로 바로 넣고, 어떻게 마지막에 바꿔야하는지를 고민했다.
#               닉네임을 처음에 사용하면 문자열이 고정되어 바꿀 수 없다. 전체 메세지를 아이디+메세지 로 변환 후 전체를 반복문으로 아이디-> 닉네임 변환 해줘야한다.
#               아이디-> 닉네임 변환을 위해 자료구조 튜플을 배열에 넣는 것이 효과적이었다.
# - 개선할 점 또는 다른 풀이: 여러 자료구조를 전반적으로 학습할 수 있는 문제였다. result를 중간 구조로 썼는데 더 짧게 코드를 쓰는 방법이 있을지 궁금하긴 하다


# 문제에서 요구하는 solution 함수를 작성합니다.

def solution(record):
    #결과 배열
    answer=[]
    result=[]
    my_dict={} #아이디,닉네임 저장

    for i in record:
        textarray=i.split()

        if textarray[0] in ('Enter','Change'):
            my_dict[textarray[1]]=textarray[2]

        if textarray[0]=="Enter":
            result.append((textarray[1],"님이 들어왔습니다."))
        
        elif textarray[0]=="Leave":
            result.append((textarray[1],"님이 나갔습니다."))
        

    for j in result:
        answer.append(my_dict[j[0]]+j[1])
    
    return answer
