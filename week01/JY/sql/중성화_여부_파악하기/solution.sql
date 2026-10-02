-- [문제 정보]
-- - 문제명:중성화 여부 파악하기
-- - 문제 링크:https://school.programmers.co.kr/learn/courses/30/lessons/59409
-- - 작성자:이지연
--
-- [1. 문제 이해]
-- - 사용 테이블 및 핵심 컬럼:
-- - 출력해야 하는 컬럼/형식 (컬럼명, 별칭, 소수점, 날짜 형식):
-- - 정렬 조건:문제참고
-- - 반드시 만족해야 하는 조건:문제참고
--
-- [2. 접근 방법]
-- - 필요한 절: SELECT / WHERE / GROUP BY / HAVING / JOIN / 서브쿼리 / ORDER BY
-- - 행 조건(WHERE) vs 그룹 조건(HAVING) 구분:
-- - JOIN이 필요하다면 종류와 연결 키 (INNER / LEFT / ...):
-- - 사용할 함수 (COUNT, SUM, YEAR, DATE_FORMAT, ROUND, IFNULL ...):
--
-- [3. 쿼리 설계]
-- - 실행 순서대로 정리: FROM → WHERE → GROUP BY → HAVING → SELECT → ORDER BY
--   1) FROM/JOIN:ANIMAL_INS
--   2) WHERE:
--   3) GROUP BY / HAVING:
--   4) SELECT:ANIMAL_ID,NAME,CASE~ THEN~ END AS 로 값 변환한 SEX_UPON_INTAKE
--   5) ORDER BY / LIMIT:ANIMAL_ID ASC;
--
-- [4. 예외 케이스]
-- - NULL 값 처리 (IS NULL, IFNULL, COUNT(*) vs COUNT(컬럼)): NULLABLE FALSE라 하지 않음
-- - 날짜/범위 경계:.
-- - 중복 처리 (DISTINCT 필요 여부):.
-- - JOIN 시 누락/중복 행 발생 여부:.
--
-- [5. 회고]
-- - 막혔던 부분:
-- - 1) A를 B로 값을 바꿀 때 어떤 구문을 사용해야 하는지? =>CASE THEN
-- - 2) SELECT 절에서 열 이름 바꿔야 하나 고민했는데 CASE THEN END AS 열이름 해서 그냥 써줘도 된다 못풀뻔~
-- - 헷갈린 문법 정리: CASE~THEN~END AS~
-- - 다른 풀이 (서브쿼리 ↔ JOIN, LIKE ↔ YEAR() 등):


-- 정답 쿼리

SELECT ANIMAL_ID,
        NAME,
        CASE 
            WHEN SEX_UPON_INTAKE LIKE 'Spayed%' THEN 'O'
            WHEN SEX_UPON_INTAKE LIKE 'Neutered%' THEN 'O'
            WHEN SEX_UPON_INTAKE LIKE 'Intact%' THEN 'X'
        END AS '중성화'
FROM ANIMAL_INS
ORDER BY ANIMAL_ID ASC;