# [문제 정보]
# - 문제명:문자열 압축
# - 문제 링크:https://school.programmers.co.kr/learn/courses/30/lessons/60057
# - 작성자:이지연
#
# [1. 문제 이해]
# - 입력값과 출력값:[예시] 입력:"abcabcdede",출력:8
# - 핵심 조건 및 제한사항:s 길이 1~1000, 알파벳 소문자만
# - 반드시 만족해야 하는 규칙:1개 이상 단위로 잘라 압축한 문자열 중 가장 짧은 것의 길이 반환하는 solution함수 작성
#
# [2. 접근 방법]
# - 고려한 방법: 완전탐색,문자열 슬라이싱
# - 선택한 알고리즘/자료구조: 완전탐색
# - 이 방법을 선택한 이유: 어떤 단위가 제일 짧을지 미리 알 수 없어서 전부 잘라봐야함. 
#
# [3. 구현 설계]
# - 처리 순서: 자르는 단위 i를 1부터 len(s)//2까지 바꿔가며 압축 후 제일 짧은 길이 저장
#   1) answer를 len(s)로 초기화
#   2) 첫 덩어리 s[0:i]를 prev에 저장, count는 1
#   3) j를 i씩 늘리면서 s[j:j+i]로 자르고 prev랑 비교
#   4) 같으면 count+1, 다르면 숫자+prev를 result에 붙이고 리셋. count 1이면 숫자 생략
#   5) 반복문 끝나고 마지막 덩어리 따로 붙여줘야함
#   6) min으로 제일 짧은 길이 저장
# - 핵심 불변식 또는 정답을 보장하는 근거: 절반보다 길게 자르면 반복이 생길 수 없어서 절반까지만 보면 모든 경우 확인함
#
# [4. 복잡도]
# - 시간 복잡도:O(N²) - 단위 N/2개 x 문자열 전체 순회
# - 공간 복잡도:O(N) - result가 최대 N만큼 메모리 사용
#
# [5. 예외 케이스]
# - 빈 값/최솟값/최댓값: 길이 1이면 반복문 안 돌고 answer=1 그대로 반환
# - 중복 또는 경계 조건: 반복 10번 이상이면 숫자 두 자리라 str(count)로 처리함
#
# [6. 회고]
# - 막혔던 부분: 가장 짧은 압축이 자르는 단위가 짧은 건 줄 알았는데 결과 문자열 길이였음. 풀이가 안 떠올라서 해답 참고해 작성했다
# - 개선할 점 또는 다른 풀이: 다 해보는 것 말고 더 좋은 방법이 있을 것 같았는데 다 해보는 게 맞는 것 같다. 다시 공부해야할 듯

def solution(s):
    answer = len(s)

    for i in range(1, len(s) // 2 + 1):      #i-자르는 단위
        result = ""                           #압축 결과 문자열
        prev = s[0:i]
        count = 1

        for j in range(i, len(s), i):         #j-덩어리 시작 위치
            now = s[j:j + i]                  #현재 덩어리
            if now == prev:
                count += 1
            else:
                result += (str(count) if count > 1 else "") + prev
                prev = now
                count = 1

        result += (str(count) if count > 1 else "") + prev

        answer = min(answer, len(result))

    return answer