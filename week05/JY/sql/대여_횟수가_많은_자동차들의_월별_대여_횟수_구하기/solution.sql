-- [문제 정보]
-- - 문제명:조건에 맞는 사용자와 총 거래금액 조회하기
-- - 문제 링크:https://school.programmers.co.kr/learn/courses/30/lessons/164668
-- - 작성자:이지연
--
-- [1. 문제 이해]
-- - 사용 테이블 및 핵심 컬럼:USED_GOODS_BOARD(WRITER_ID, PRICE, STATUS), USED_GOODS_USER(USER_ID, NICKNAME)
-- - 출력해야 하는 컬럼/형식 (컬럼명, 별칭, 소수점, 날짜 형식):USER_ID, NICKNAME, TOTAL_SALES
-- - 정렬 조건:총거래금액 오름차순
-- - 반드시 만족해야 하는 조건:완료된 거래(STATUS='DONE')의 총금액이 70만 원 이상인 사람만 조회
--
-- [2. 접근 방법]
-- - 필요한 절: SELECT / JOIN / WHERE / GROUP BY / HAVING / ORDER BY
-- - 행 조건(WHERE) vs 그룹 조건(HAVING) 구분: 완료된 거래만 남기는 건 행 하나하나 거르는 거라 WHERE. 총금액 70만 원 이상은 사람별로 묶고 SUM한 결과로 거르는 거라 HAVING
-- - JOIN이 필요하다면 종류와 연결 키 (INNER / LEFT / ...):INNER JOIN, B.WRITER_ID = U.USER_ID. 출력할 컬럼이 양쪽 테이블에 있어서(NICKNAME은 USER, 금액은 BOARD) IN 서브쿼리가 아니라 JOIN 써야함
-- - 사용할 함수 (COUNT, SUM, YEAR, DATE_FORMAT, ROUND, IFNULL ...):SUM
--
-- [3. 쿼리 설계]
-- - 실행 순서대로 정리: FROM → WHERE → GROUP BY → HAVING → SELECT → ORDER BY
--   1) FROM/JOIN:USED_GOODS_BOARD B JOIN USED_GOODS_USER U ON B.WRITER_ID=U.USER_ID 로 두 테이블 먼저 붙임
--   2) WHERE:STATUS = 'DONE'
--   3) GROUP BY / HAVING:GROUP BY USER_ID, NICKNAME / HAVING TOTAL_SALES>=700000
--   4) SELECT:USER_ID, U.NICKNAME, SUM(PRICE) AS TOTAL_SALES
--   5) ORDER BY / LIMIT:TOTAL_SALES ASC
--
-- [4. 예외 케이스]
-- - NULL 값 처리 (IS NULL, IFNULL, COUNT(*) vs COUNT(컬럼)): PRICE, STATUS 모두 NULLABLE FALSE라 처리 안함
-- - 날짜/범위 경계 (이상·이하·미만, BETWEEN 양끝 포함 여부): 70만 원 딱 맞는 사람도 포함이라 >= 씀
-- - 중복 처리 (DISTINCT 필요 여부): GROUP BY로 사람별 한 줄씩만 나옴
-- - JOIN 시 누락/중복 행 발생 여부: 게시글 하나에 작성자 한 명이 붙어서 행이 늘어나지 않음
--
-- [5. 회고]
-- - 막혔던 부분:
-- - 1) 처음에 SELECT에 PRICE를 그냥 씀. 묶고 나면 한 사람 안에 PRICE가 여러 개라 뭘 보여줄지 모름. GROUP BY에 쓴 컬럼이나 SUM, COUNT 같은 집계함수로 감싼 것만 SELECT에 쓸 수 있음을 기억하기...
-- - 2) JOIN 쓰는 법 까먹음. 지난번 IN 서브쿼리는 출력 컬럼이 한 테이블에만 있어서였고, 이번엔 양쪽에 있어서 JOIN
-- - 3) WRITER_ID로 묶어야 하는지 USER_ID로 묶어야 하는지 헷갈림. JOIN이 제일 먼저 실행돼서 GROUP BY는 이미 합쳐진 테이블에서 함. 합친 테이블에선 WRITER_ID랑 USER_ID가 항상 같은 값이라 뭘로 묶어도 같음.(묶고 그룹바이 실행)TOTAL_SALES는 원래 테이블에 있는 컬럼이 아니라 SUM으로 새로 만든 값임
-- - 4) NICKNAME을 GROUP BY에 안 넣어도 통과했는데, MySQL이 USER_ID가 기본키라 실행된 거고 다른 db에서는 에러가 날 수 있어서 같이 넣어주었다.
-- - 헷갈린 문법 정리: 쓰는 순서 SELECT-FROM-JOIN-WHERE-GROUP BY-HAVING-ORDER BY / GROUP BY는 콤마로 여러 개 가능 / SELECT에서 집계함수 밖에 있는 컬럼은 전부 GROUP BY에 씀
-- - 다른 풀이 (서브쿼리 ↔ JOIN, LIKE ↔ YEAR() 등): HAVING에 별칭 대신 SUM(PRICE)>=700000 쓰면 MySQL 말고 다른 DB에서도 됨


-- 정답 쿼리

SELECT USER_ID,U.NICKNAME,SUM(PRICE) AS TOTAL_SALES
FROM USED_GOODS_BOARD B
JOIN USED_GOODS_USER U ON B.WRITER_ID=U.USER_ID
WHERE STATUS = 'DONE'
GROUP BY USER_ID,NICKNAME
HAVING TOTAL_SALES>=700000
ORDER BY TOTAL_SALES ASC