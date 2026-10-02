-- [문제 정보]
-- - 문제명:조건에 맞는 회원수 구하기
-- - 문제 링크:https://school.programmers.co.kr/learn/courses/30/lessons/131535
-- - 작성자:
--
-- [1. 문제 이해]
-- - 사용 테이블 및 핵심 컬럼:
-- - 출력해야 하는 컬럼/형식 (컬럼명, 별칭, 소수점, 날짜 형식):
-- - 정렬 조건:문제참고
-- - 반드시 만족해야 하는 조건:USER_INFO 테이블에서 2021년에 가입한 회원 중 나이가 20세 이상 29세 이하인 회원이 몇 명인지 출력하는 SQL문을 작성해주세요.
--
-- [2. 접근 방법]
-- - 필요한 절: SELECT / WHERE / GROUP BY / HAVING / JOIN / 서브쿼리 / ORDER BY
-- - 행 조건(WHERE) vs 그룹 조건(HAVING) 구분:
-- - JOIN이 필요하다면 종류와 연결 키 (INNER / LEFT / ...):
-- - 사용할 함수 (COUNT, SUM, YEAR, DATE_FORMAT, ROUND, IFNULL ...):
--
-- [3. 쿼리 설계]
-- - 실행 순서대로 정리: FROM → WHERE → GROUP BY → HAVING → SELECT → ORDER BY
--   1) FROM/JOIN:USER_INFO
--   2) WHERE:WHERE JOINED LIKE '2021%' AND AGE BETWEEN 20 AND 29
--   3) GROUP BY / HAVING:
--   4) SELECT:COUNT(*)
--   5) ORDER BY / LIMIT:
--
-- [4. 예외 케이스]
-- - NULL 값 처리 (IS NULL, IFNULL, COUNT(*) vs COUNT(컬럼)):.
-- - 날짜/범위 경계 (이상·이하·미만, BETWEEN 양끝 포함 여부):.
-- - 중복 처리 (DISTINCT 필요 여부):.
-- - JOIN 시 누락/중복 행 발생 여부:.
--
-- [5. 회고]
-- - 막혔던 부분:LIKE 기억 못할 뻔함 좀 외우자
-- - 헷갈린 문법 정리: LEKE, BEETWEEN a AND b
-- - 다른 풀이 (서브쿼리 ↔ JOIN, LIKE ↔ YEAR() 등):


-- 정답 쿼리

SELECT COUNT(*)
FROM USER_INFO
WHERE JOINED LIKE '2021%' AND AGE BETWEEN 20 AND 29