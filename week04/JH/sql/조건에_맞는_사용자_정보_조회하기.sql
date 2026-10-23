# 아 벽 느낀다. 도저히 못하긋다.
SELECT u.USER_ID,
       u.NICKNAME,
       CONCAT_WS(
           ' ',
           u.CITY,
           u.STREET_ADDRESS1,
           u.STREET_ADDRESS2
       ) AS 전체주소,
       CONCAT(
           SUBSTRING(u.TLNO, 1, 3),
           '-',
           SUBSTRING(u.TLNO, 4, 4),
           '-',
           SUBSTRING(u.TLNO, 8, 4)
       ) AS 전화번호
FROM USED_GOODS_USER AS u
WHERE u.USER_ID IN (
    SELECT b.WRITER_ID
    FROM USED_GOODS_BOARD AS b
    GROUP BY b.WRITER_ID
    HAVING COUNT(*) >= 3
)
ORDER BY u.USER_ID DESC;
