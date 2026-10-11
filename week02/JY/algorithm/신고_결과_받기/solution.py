# [문제 정보]
# - 문제명:신고 결과 받기
# - 문제 링크:https://school.programmers.co.kr/learn/courses/30/lessons/92334
# - 작성자: 이지연
#
# [1. 문제 이해]
# - 입력값과 출력값: 입력: id_list:["muzi", "frodo", "apeach", "neo"], report:["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"], k:2
# - 핵심 조건 및 제한사항:id_list 길이 2~1000, report 길이 1~200000, k는 1~200. report는 "신고한id 신고당한id" 형태로 공백 하나로 구분된다
# - 반드시 만족해야 하는 규칙:같은 사람을 여러 번 신고해도 1회로 처리한다. k번 이상 신고당하면 정지되고, 정지된 사람을 신고한 유저에게 메일이 간다. id_list 순서대로 메일 받은 횟수를 반환한다
#
# [2. 접근 방법]
# - 고려한 방법: 딕셔너리의 값을 배열, 정수 등으로 사용해 필요한 값을 저장해 사용한다. for 반복문으로 딕셔너라,배열을 읽는다.
# - 선택한 알고리즘/자료구조: 딕셔너리, set 
# - 이 방법을 선택한 이유: A:A를 신고한 사람 으로 키-값 딕셔너리를 가지고 있어야, A를 신고한 사람에게 메일을 보낼 수 있다. 
#                       또한 B:B가 받은 메일 수 를 받을 딕셔너리가 따로 필요
#                       1명이 같은 사람을 n번 신고해도 기록은 1번만 남아야 하기 때문에 중복제거 set이 필수이다 
#
# [3. 구현 설계]
# - 처리 순서:
#   1) id,report,k 3개 값이 주어진다. 두 가지 딕셔너리(키-값) 구조가 필요하다.
#   2) A:A를 신고한 사람[] 을 넣을 id_dict, B:B가 받은 메일 수(int)를 넣을 mail을 루프로 초기화한다.
#   3)신고한 사람 a[0], 신고당한 사람 a[1] 이므로 id_dict에 a[1]이 있다면 a[1]키에 a[0]값을 넣는다. 이때 report를 set자료구조로 중복불가하게 만들어 검사해야 한다.
#   4)id_dict.values() 의 길이가 >=k이면 정지메일을 발송하게 되므로 mail의 해당 키일 때 값에 +=1 한다. 값은 정지 메일을 받은 횟수가 된다.
#   5) 값을 answer에 넣어 배열로 만들어 return한다.
# - 핵심 불변식 또는 정답을 보장하는 근거:for i in set(report): 로 하나의 이름에 대해서 한 번만 값을 넣어주어야 
#
# [4. 복잡도]
# - 시간 복잡도:O(N+M) - N은 report 길이, M은 id_list 길이. set 변환과 report 순회가 O(N), 메일 카운트도 전체 신고 수만큼만 돈다
# - 공간 복잡도:O(N+M) - set(report), id_dict 리스트에 신고 기록 최대 N개, mail은 M개
#
# [5. 예외 케이스]
# - 빈 값/최솟값/최댓값:정지된 사람이 아무도 없으면 mail 값이 전부 0으로 남아서 [0,0,...]이 반환된다. k=1이면 한 번이라도 신고당한 사람은 전부 정지된다
# - 중복 또는 경계 조건:"ryan con"처럼 같은 신고가 여러 번 있으면 set으로 1개만 남긴다. 신고 횟수가 정확히 k일 때도 정지되므로 >= 로 비교한다
#
# [6. 회고]
# - 막혔던 부분: 중복처리를 어떤 단게에서 해야 할지 고민했는데, 원본인 report에서 A가 B를 신고했을 때 항상 같은 문구로 나오므로 report에서 처리하는 것이 가장 합리적이다.
# - 개선할 점 또는 다른 풀이: [참고]id_dict 값을 리스트 대신 set으로 두고 add하면 report를 set으로 안 바꿔도 중복이 자동으로 걸러진다. 마지막 answer는 mail.values() 대신 id_list 순서로 mail[i]를 꺼내면 순서가 더 확실하다
#                           이중 for문이 쓰여서 시간복잡도가 걱정되었는데, 어차피 유저가 많아도 for문 루프수가 신고 개수를 넘을 수 없어서 O(N+M) 시간복잡도임을 배울 수 있었다.
#
# 문제에서 요구하는 solution 함수를 작성합니다.

def solution(id_list, report, k):
    
    
    answer = []
    id_dict={}

    #풀다 보니 mail딕셔너리도 필요하다
    mail={}

    #딕셔너리에 값 할당-id_list 빈 배열 할당, mail 정수 0 할당
    for i in id_list:
        id_dict[i]=[]
        mail[i]=0

    #print("id_dict:",id_dict)
    #print("mail",mail)


    #신고한 사람 a[0], 신고당한 사람 a[1]
    for i in set(report):
        a=i.split()
        if a[1] in id_dict:
            id_dict[a[1]].append(a[0])


    
    for j in id_dict.values():
        #print("j:",j)
        if len(j)>=k:
            #print("정지 메일 발송")
            for p in j:
                if p in id_dict:
                    mail[p]+=1


    for i in mail.values():
        answer.append(i)
    
    return answer