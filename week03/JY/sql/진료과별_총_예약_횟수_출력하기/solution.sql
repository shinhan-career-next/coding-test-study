-- [문제 정보]
-- - 문제명:이름에 el이 들어가는 동물 찾기
-- - 문제 링크:https://school.programmers.co.kr/learn/courses/30/lessons/59047
-- - 작성자:이지연
--
-- [1. 문제 이해]
-- - 사용 테이블 및 핵심 컬럼:ANIMAL_INS / ANIMAL_ID, ANIMAL_TYPE, NAME
-- - 출력해야 하는 컬럼/형식 (컬럼명, 별칭, 소수점, 날짜 형식):ANIMAL_ID, NAME
-- - 정렬 조건:이름 순, 이름 같으면 아이디 순
-- - 반드시 만족해야 하는 조건:이름에 el이 들어가는 개만 조회. 대소문자 구분 안함
--
-- [2. 접근 방법]
-- - 필요한 절: SELECT / WHERE / ORDER BY
-- - 행 조건(WHERE) vs 그룹 조건(HAVING) 구분: 그룹으로 묶을 필요 없어서 WHERE만 씀
-- - JOIN이 필요하다면 종류와 연결 키 (INNER / LEFT / ...):필요없음
-- - 사용할 함수 (COUNT, SUM, YEAR, DATE_FORMAT, ROUND, IFNULL ...):LIKE, LOWER
--
-- [3. 쿼리 설계]
-- - 실행 순서대로 정리: FROM → WHERE → GROUP BY → HAVING → SELECT → ORDER BY
--   1) FROM/JOIN:ANIMAL_INS
--   2) WHERE:ANIMAL_TYPE = 'Dog' AND LOWER(NAME) LIKE '%el%'
--   3) GROUP BY / HAVING:
--   4) SELECT:ANIMAL_ID, NAME
--   5) ORDER BY / LIMIT:NAME, ANIMAL_ID (둘 다 오름차순)
--
-- [4. 예외 케이스]
-- - NULL 값 처리 (IS NULL, IFNULL, COUNT(*) vs COUNT(컬럼)): NAME이 NULLABLE TRUE인데 NULL은 LIKE에서 알아서 빠져서 따로 처리X
-- - 날짜/범위 경계:.
-- - 중복 처리 (DISTINCT 필요 여부):.
-- - JOIN 시 누락/중복 행 발생 여부:.
--
-- [5. 회고]
-- - 막혔던 부분:
-- - 1) 이름에 el 들어가는 동물만 찾으면 Ella(고양이)도 나옴. 개만 찾아야 하니 ANIMAL_TYPE = 'Dog' 조건을 같이 걸어야함
-- - 2) 대소문자 구분을 하지 않으므로 Elijah도 나와야 함. LOWER(NAME)로 소문자로 바꾼 다음 '%el%' 찾음
-- - 헷갈린 문법 정리: 포함은 LIKE '%값%', 정렬 기준 여러개는 ORDER BY 컬럼1, 컬럼2
-- - 다른 풀이 (서브쿼리 ↔ JOIN, LIKE ↔ YEAR() 등): MySQL은 대소문자 구분을 하지 않아 LOWER 없이 NAME LIKE '%el%'만 써도 통과함.  LOWER 사용을 더 추천


-- 정답 쿼리

SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE ANIMAL_TYPE = 'Dog'
  AND LOWER(NAME) LIKE '%el%'
ORDER BY NAME, ANIMAL_ID;