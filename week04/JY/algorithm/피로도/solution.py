# [문제 정보]
# - 문제명:피로도
# - 문제 링크:https://school.programmers.co.kr/learn/courses/30/lessons/87946
# - 작성자:이지연
#
# [1. 문제 이해]
# - 입력값과 출력값:입력:k=80, dungeons=[[80,20],[50,40],[30,10]], 출력:3
# - 핵심 조건 및 제한사항:k 1~5000, 던전 개수 1~8개, 던전은 [최소 필요 피로도, 소모 피로도]이고 최소 필요가 항상 소모보다 크거나 같음
# - 반드시 만족해야 하는 규칙:현재 피로도가 최소 필요 피로도 이상이어야 던전 탐험 가능하고, 탐험 후 소모 피로도만큼 줄어듦. 최대 몇 개 탐험할 수 있는지 반환
#
# [2. 접근 방법]
# - 고려한 방법: 순서대로 탐험하기, 모든 순서 다 해보기
# - 선택한 알고리즘/자료구조: 완전탐색(순열 permutations)
# - 이 방법을 선택한 이유: 처음엔 순서대로 돌면 되는 줄 알았는데 도는 순서에 따라 탐험 개수가 달라짐. 어떤 순서가 최선인지 규칙으로 찾기 어렵고 던전이 최대 8개라 다 돌아봐도 괜찮을 거라고 생각함
#
# [3. 구현 설계]
# - 처리 순서:
#   1) from itertools import permutations로 불러옴
#   2) permutations(dungeons)로 던전을 도는 모든 순서를 꺼내기
#   3) order마다 result=0, now_k=k로 리셋. k를 직접 빼면 다음 order에서 피로도가 돌아오지 않아 복사본 now_k를 사용했다.
#   4) for need,cost in order로 던전을 하나씩 꺼내서 need<=now_k면 result+1, now_k-=cost
#   5) order 하나 끝나면 max 함수로 answer에 바로 최고 기록 갱신 answer=max(answer,result) 
# - 핵심 불변식 또는 정답을 보장하는 근거:모든 순서를 다 해보고 그중 최댓값을 저장하니까 가장 많이 탐험하는 경우를 업데이트할 수 있다.
#
# [4. 복잡도]
# - 시간 복잡도:O(N! x N) - 순서 N!가지 x 던전 N개
# - 공간 복잡도:O(N) - order 1개당 던전 N개
#
# [5. 예외 케이스]
# - 빈 값/최솟값/최댓값: 던전 1개고 피로도 부족하면 answer=0 그대로 반환
# - 중복 또는 경계 조건:.
#
# [6. 회고]
# - 막혔던 부분: permutations를 처음 사용해보았다. permutations(리스트)는 모든 순서를 튜플로 하나씩 줌. 순서 중요하면 permutations, 고르기만 하면 combinations 사용 가능
#   k를 order마다 리셋 안 하고 직접 빼서 다음 순서가 줄어든 피로도로 시작했음. 매번 now_k=k로 복사해 사용해야 함
#   answer=max(answer,result)가 제일 헷갈림. =는 오른쪽 먼저 계산하고 왼쪽에 넣어서 result+=1처럼 자기 자신을 써도 됨. answer에 최댓값 업데이트해서 저장하기
# - 개선할 점 또는 다른 풀이: DFS(재귀)로 풀면 더 이상 들어갈 던전이 없을 때 그 경로를 멈춰서 탐색을 줄일 수 있음. (재귀가 맞는 거 같은데 이렇게 풀었다. 나중에 다시 봐야 할듯...)


# 문제에서 요구하는 solution 함수를 작성합니다.

from itertools import permutations

def solution(k, dungeons):
    
    answer=0
    
    result=0
    for order in permutations(dungeons): #permutations
        #print(order)
        result=0
        now_k=k
        for need,cost in order:
            #print(need,cost)
            if need<=now_k:
                result+=1
                now_k-=cost
        answer=max(answer,result) #max
    return answer