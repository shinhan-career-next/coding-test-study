-- [문제 정보]
-- - 문제명:경기도에 위치한 식품창고 목록 출력하기
-- - 문제 링크:https://school.programmers.co.kr/learn/courses/30/lessons/131114
-- - 작성자:이지연
--
-- [1. 문제 이해]
-- - 사용 테이블 및 핵심 컬럼:FOOD_WAREHOUSE / WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS, FREEZER_YN
-- - 출력해야 하는 컬럼/형식 (컬럼명, 별칭, 소수점, 날짜 형식):WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS, FREEZER_YN
-- - 정렬 조건:창고 ID 오름차순
-- - 반드시 만족해야 하는 조건:경기도에 있는 창고만 조회. 냉동시설 여부가 NULL이면 N으로 출력
--
-- [2. 접근 방법]
-- - 필요한 절: SELECT / WHERE / ORDER BY
-- - 행 조건(WHERE) vs 그룹 조건(HAVING) 구분: 그룹으로 묶을 필요 없어서 WHERE만 사용
-- - JOIN이 필요하다면 종류와 연결 키 (INNER / LEFT / ...):필요없음
-- - 사용할 함수 (COUNT, SUM, YEAR, DATE_FORMAT, ROUND, IFNULL ...):IFNULL, LIKE
--
-- [3. 쿼리 설계]
-- - 실행 순서대로 정리: FROM → WHERE → GROUP BY → HAVING → SELECT → ORDER BY
--   1) FROM/JOIN:FOOD_WAREHOUSE
--   2) WHERE:ADDRESS LIKE '경기도%'
--   3) GROUP BY / HAVING:
--   4) SELECT:WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS, IFNULL(FREEZER_YN,'N') AS FREEZER_YN
--   5) ORDER BY / LIMIT:WAREHOUSE_ID ASC
--
-- [4. 예외 케이스]
-- - NULL 값 처리 (IS NULL, IFNULL, COUNT(*) vs COUNT(컬럼)): FREEZER_YN이 NULL이면 IFNULL로 'N' 넣음
-- - 날짜/범위 경계:.
-- - 중복 처리 (DISTINCT 필요 여부):.
-- - JOIN 시 누락/중복 행 발생 여부:.
--
-- [5. 회고]
-- - 막혔던 부분:
-- - 1) NULL 바꾸는 함수가 DB마다 이름이 다름. NVL은 Oracle 전용이라 MySQL에선 안됨. MySQL은 IFNULL, MSSQL은 ISNULL(a,b)인데 MySQL의 ISNULL은 인자 1개만 받고 NULL인지 0/1로 확인만 해서 에러남
-- - 2) AS를 꼭 써야 하나 헷갈림. 안 쓰면 컬럼 이름이 IFNULL(FREEZER_YN,'N')으로 나옴. 함수 쓴 컬럼은 AS로 원래 이름을 맞춰줘야 함(IFNULL(FREEZER_YN,'N') AS FREEZER_YN)
-- - 헷갈린 문법 정리: IFNULL(확인할 컬럼, NULL일 때 넣을 값) / 'N'처럼 따옴표 안 쓰면 문자가 아니라 컬럼으로 인식함
-- - 다른 풀이 (서브쿼리 ↔ JOIN, LIKE ↔ YEAR() 등): COALESCE(FREEZER_YN,'N')도 똑같이 됨. COALESCE는 모든 DB에서 되는 표준이라 이걸 자주 써보자


-- 정답 쿼리

SELECT WAREHOUSE_ID,WAREHOUSE_NAME,ADDRESS,IFNULL(FREEZER_YN,'N') AS FREEZER_YN
FROM FOOD_WAREHOUSE
WHERE ADDRESS LIKE "경기도%"
ORDER BY WAREHOUSE_ID ASC