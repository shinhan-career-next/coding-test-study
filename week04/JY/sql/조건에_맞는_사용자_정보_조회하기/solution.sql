-- [문제 정보]
-- - 문제명:카테고리 별 도서 판매량 집계하기
-- - 문제 링크:https://school.programmers.co.kr/learn/courses/30/lessons/144855
-- - 작성자:이지연
--
-- [1. 문제 이해]
-- - 사용 테이블 및 핵심 컬럼:BOOK(BOOK_ID, CATEGORY), BOOK_SALES(BOOK_ID, SALES_DATE, SALES)
-- - 출력해야 하는 컬럼/형식 (컬럼명, 별칭, 소수점, 날짜 형식):CATEGORY, TOTAL_SALES
-- - 정렬 조건:카테고리명 오름차순
-- - 반드시 만족해야 하는 조건:2022년 1월 판매량만 카테고리별로 합산
--
-- [2. 접근 방법]
-- - 필요한 절: SELECT / JOIN / WHERE / GROUP BY / ORDER BY
-- - 행 조건(WHERE) vs 그룹 조건(HAVING) 구분: 1월 판매 기록만 남기는 행 조건이라 WHERE 사용
-- - JOIN이 필요하다면 종류와 연결 키 (INNER / LEFT / ...):INNER JOIN, BOOK_ID로 연결. 카테고리- BOOK,판매량- BOOK_SALES에 있어서 Join필요
-- - 사용할 함수 (COUNT, SUM, YEAR, DATE_FORMAT, ROUND, IFNULL ...):SUM
--
-- [3. 쿼리 설계]
-- - 실행 순서대로 정리: FROM → WHERE → GROUP BY → HAVING → SELECT → ORDER BY
--   1) FROM/JOIN:BOOK B JOIN BOOK_SALES S ON B.BOOK_ID = S.BOOK_ID 로 판매 기록마다 그 책 카테고리 붙임
--   2) WHERE:S.SALES_DATE >= '2022-01-01' AND S.SALES_DATE < '2022-02-01'
--   3) GROUP BY / HAVING:GROUP BY B.CATEGORY
--   4) SELECT:B.CATEGORY, SUM(S.SALES) AS TOTAL_SALES
--   5) ORDER BY / LIMIT:B.CATEGORY
--
-- [4. 예외 케이스]
-- - NULL 값 처리 (IS NULL, IFNULL, COUNT(*) vs COUNT(컬럼)): 전부 NULLABLE FALSE라 처리 안함
-- - 날짜/범위 경계 (이상·이하·미만, BETWEEN 양끝 포함 여부): < '2022-02-01'로 2월부터 제외
-- - 중복 처리 (DISTINCT 필요 여부):.
-- - JOIN 시 누락/중복 행 발생 여부: 
--
-- [5. 회고]
-- - 막혔던 부분:
-- - 1) 카테고리랑 판매량이 다른 테이블에 있어서 BOOK_ID로  JOIN해야 한다.
-- - 2) 판매량은 행 개수가 아니라 SALES 값을 더해야 해서 COUNT가 아니라 SUM을 사용해야 한다.
-- - 헷갈린 문법 정리: BOOK_ID처럼 두 테이블에 다 있는 컬럼은 앞에 테이블 별칭을 붙여야 한다. (B.BOOK_ID, S.BOOK_ID)
-- - 다른 풀이 (서브쿼리 ↔ JOIN, LIKE ↔ YEAR() 등):.


-- 정답 쿼리

SELECT B.CATEGORY, SUM(S.SALES) AS TOTAL_SALES
FROM BOOK B
JOIN BOOK_SALES S ON B.BOOK_ID = S.BOOK_ID
WHERE S.SALES_DATE >= '2022-01-01' AND S.SALES_DATE < '2022-02-01'
GROUP BY B.CATEGORY
ORDER BY B.CATEGORY;